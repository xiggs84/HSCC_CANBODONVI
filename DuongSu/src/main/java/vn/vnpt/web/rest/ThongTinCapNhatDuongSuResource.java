package vn.vnpt.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import vn.vnpt.repository.ThongTinCapNhatDuongSuRepository;
import vn.vnpt.service.ThongTinCapNhatDuongSuQueryService;
import vn.vnpt.service.ThongTinCapNhatDuongSuService;
import vn.vnpt.service.criteria.ThongTinCapNhatDuongSuCriteria;
import vn.vnpt.service.dto.ThongTinCapNhatDuongSuDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.ThongTinCapNhatDuongSu}.
 */
@RestController
@RequestMapping("/api/thong-tin-cap-nhat-duong-sus")
public class ThongTinCapNhatDuongSuResource {

    private static final Logger LOG = LoggerFactory.getLogger(ThongTinCapNhatDuongSuResource.class);

    private static final String ENTITY_NAME = "duongSuThongTinCapNhatDuongSu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ThongTinCapNhatDuongSuService thongTinCapNhatDuongSuService;

    private final ThongTinCapNhatDuongSuRepository thongTinCapNhatDuongSuRepository;

    private final ThongTinCapNhatDuongSuQueryService thongTinCapNhatDuongSuQueryService;

    public ThongTinCapNhatDuongSuResource(
        ThongTinCapNhatDuongSuService thongTinCapNhatDuongSuService,
        ThongTinCapNhatDuongSuRepository thongTinCapNhatDuongSuRepository,
        ThongTinCapNhatDuongSuQueryService thongTinCapNhatDuongSuQueryService
    ) {
        this.thongTinCapNhatDuongSuService = thongTinCapNhatDuongSuService;
        this.thongTinCapNhatDuongSuRepository = thongTinCapNhatDuongSuRepository;
        this.thongTinCapNhatDuongSuQueryService = thongTinCapNhatDuongSuQueryService;
    }

    /**
     * {@code POST  /thong-tin-cap-nhat-duong-sus} : Create a new thongTinCapNhatDuongSu.
     *
     * @param thongTinCapNhatDuongSuDTO the thongTinCapNhatDuongSuDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new thongTinCapNhatDuongSuDTO, or with status {@code 400 (Bad Request)} if the thongTinCapNhatDuongSu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ThongTinCapNhatDuongSuDTO> createThongTinCapNhatDuongSu(
        @RequestBody ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save ThongTinCapNhatDuongSu : {}", thongTinCapNhatDuongSuDTO);
        if (thongTinCapNhatDuongSuDTO.getIdCapNhat() != null) {
            throw new BadRequestAlertException("A new thongTinCapNhatDuongSu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        thongTinCapNhatDuongSuDTO = thongTinCapNhatDuongSuService.save(thongTinCapNhatDuongSuDTO);
        return ResponseEntity.created(new URI("/api/thong-tin-cap-nhat-duong-sus/" + thongTinCapNhatDuongSuDTO.getIdCapNhat()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    thongTinCapNhatDuongSuDTO.getIdCapNhat().toString()
                )
            )
            .body(thongTinCapNhatDuongSuDTO);
    }

    /**
     * {@code PUT  /thong-tin-cap-nhat-duong-sus/:idCapNhat} : Updates an existing thongTinCapNhatDuongSu.
     *
     * @param idCapNhat the id of the thongTinCapNhatDuongSuDTO to save.
     * @param thongTinCapNhatDuongSuDTO the thongTinCapNhatDuongSuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated thongTinCapNhatDuongSuDTO,
     * or with status {@code 400 (Bad Request)} if the thongTinCapNhatDuongSuDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the thongTinCapNhatDuongSuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idCapNhat}")
    public ResponseEntity<ThongTinCapNhatDuongSuDTO> updateThongTinCapNhatDuongSu(
        @PathVariable(value = "idCapNhat", required = false) final Long idCapNhat,
        @RequestBody ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update ThongTinCapNhatDuongSu : {}, {}", idCapNhat, thongTinCapNhatDuongSuDTO);
        if (thongTinCapNhatDuongSuDTO.getIdCapNhat() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCapNhat, thongTinCapNhatDuongSuDTO.getIdCapNhat())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!thongTinCapNhatDuongSuRepository.existsById(idCapNhat)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        thongTinCapNhatDuongSuDTO = thongTinCapNhatDuongSuService.update(thongTinCapNhatDuongSuDTO);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, thongTinCapNhatDuongSuDTO.getIdCapNhat().toString())
            )
            .body(thongTinCapNhatDuongSuDTO);
    }

    /**
     * {@code PATCH  /thong-tin-cap-nhat-duong-sus/:idCapNhat} : Partial updates given fields of an existing thongTinCapNhatDuongSu, field will ignore if it is null
     *
     * @param idCapNhat the id of the thongTinCapNhatDuongSuDTO to save.
     * @param thongTinCapNhatDuongSuDTO the thongTinCapNhatDuongSuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated thongTinCapNhatDuongSuDTO,
     * or with status {@code 400 (Bad Request)} if the thongTinCapNhatDuongSuDTO is not valid,
     * or with status {@code 404 (Not Found)} if the thongTinCapNhatDuongSuDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the thongTinCapNhatDuongSuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idCapNhat}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ThongTinCapNhatDuongSuDTO> partialUpdateThongTinCapNhatDuongSu(
        @PathVariable(value = "idCapNhat", required = false) final Long idCapNhat,
        @RequestBody ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update ThongTinCapNhatDuongSu partially : {}, {}", idCapNhat, thongTinCapNhatDuongSuDTO);
        if (thongTinCapNhatDuongSuDTO.getIdCapNhat() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCapNhat, thongTinCapNhatDuongSuDTO.getIdCapNhat())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!thongTinCapNhatDuongSuRepository.existsById(idCapNhat)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ThongTinCapNhatDuongSuDTO> result = thongTinCapNhatDuongSuService.partialUpdate(thongTinCapNhatDuongSuDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, thongTinCapNhatDuongSuDTO.getIdCapNhat().toString())
        );
    }

    /**
     * {@code GET  /thong-tin-cap-nhat-duong-sus} : get all the thongTinCapNhatDuongSus.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of thongTinCapNhatDuongSus in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ThongTinCapNhatDuongSuDTO>> getAllThongTinCapNhatDuongSus(
        ThongTinCapNhatDuongSuCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get ThongTinCapNhatDuongSus by criteria: {}", criteria);

        Page<ThongTinCapNhatDuongSuDTO> page = thongTinCapNhatDuongSuQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /thong-tin-cap-nhat-duong-sus/count} : count all the thongTinCapNhatDuongSus.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countThongTinCapNhatDuongSus(ThongTinCapNhatDuongSuCriteria criteria) {
        LOG.debug("REST request to count ThongTinCapNhatDuongSus by criteria: {}", criteria);
        return ResponseEntity.ok().body(thongTinCapNhatDuongSuQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /thong-tin-cap-nhat-duong-sus/:id} : get the "id" thongTinCapNhatDuongSu.
     *
     * @param id the id of the thongTinCapNhatDuongSuDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the thongTinCapNhatDuongSuDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ThongTinCapNhatDuongSuDTO> getThongTinCapNhatDuongSu(@PathVariable("id") Long id) {
        LOG.debug("REST request to get ThongTinCapNhatDuongSu : {}", id);
        Optional<ThongTinCapNhatDuongSuDTO> thongTinCapNhatDuongSuDTO = thongTinCapNhatDuongSuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(thongTinCapNhatDuongSuDTO);
    }

    /**
     * {@code DELETE  /thong-tin-cap-nhat-duong-sus/:id} : delete the "id" thongTinCapNhatDuongSu.
     *
     * @param id the id of the thongTinCapNhatDuongSuDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThongTinCapNhatDuongSu(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete ThongTinCapNhatDuongSu : {}", id);
        thongTinCapNhatDuongSuService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
