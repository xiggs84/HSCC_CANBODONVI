package vn.vnpt.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;
import vn.vnpt.repository.ThongTinCapNhatTaiSanRepository;
import vn.vnpt.service.ThongTinCapNhatTaiSanQueryService;
import vn.vnpt.service.ThongTinCapNhatTaiSanService;
import vn.vnpt.service.criteria.ThongTinCapNhatTaiSanCriteria;
import vn.vnpt.service.dto.ThongTinCapNhatTaiSanDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.ThongTinCapNhatTaiSan}.
 */
@RestController
@RequestMapping("/api/thong-tin-cap-nhat-tai-sans")
public class ThongTinCapNhatTaiSanResource {

    private static final Logger LOG = LoggerFactory.getLogger(ThongTinCapNhatTaiSanResource.class);

    private static final String ENTITY_NAME = "taiSanThongTinCapNhatTaiSan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ThongTinCapNhatTaiSanService thongTinCapNhatTaiSanService;

    private final ThongTinCapNhatTaiSanRepository thongTinCapNhatTaiSanRepository;

    private final ThongTinCapNhatTaiSanQueryService thongTinCapNhatTaiSanQueryService;

    public ThongTinCapNhatTaiSanResource(
        ThongTinCapNhatTaiSanService thongTinCapNhatTaiSanService,
        ThongTinCapNhatTaiSanRepository thongTinCapNhatTaiSanRepository,
        ThongTinCapNhatTaiSanQueryService thongTinCapNhatTaiSanQueryService
    ) {
        this.thongTinCapNhatTaiSanService = thongTinCapNhatTaiSanService;
        this.thongTinCapNhatTaiSanRepository = thongTinCapNhatTaiSanRepository;
        this.thongTinCapNhatTaiSanQueryService = thongTinCapNhatTaiSanQueryService;
    }

    /**
     * {@code POST  /thong-tin-cap-nhat-tai-sans} : Create a new thongTinCapNhatTaiSan.
     *
     * @param thongTinCapNhatTaiSanDTO the thongTinCapNhatTaiSanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new thongTinCapNhatTaiSanDTO, or with status {@code 400 (Bad Request)} if the thongTinCapNhatTaiSan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ThongTinCapNhatTaiSanDTO> createThongTinCapNhatTaiSan(
        @RequestBody ThongTinCapNhatTaiSanDTO thongTinCapNhatTaiSanDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save ThongTinCapNhatTaiSan : {}", thongTinCapNhatTaiSanDTO);
        if (thongTinCapNhatTaiSanDTO.getIdCapNhat() != null) {
            throw new BadRequestAlertException("A new thongTinCapNhatTaiSan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        thongTinCapNhatTaiSanDTO = thongTinCapNhatTaiSanService.save(thongTinCapNhatTaiSanDTO);
        return ResponseEntity.created(new URI("/api/thong-tin-cap-nhat-tai-sans/" + thongTinCapNhatTaiSanDTO.getIdCapNhat()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, thongTinCapNhatTaiSanDTO.getIdCapNhat().toString())
            )
            .body(thongTinCapNhatTaiSanDTO);
    }

    /**
     * {@code PUT  /thong-tin-cap-nhat-tai-sans/:idCapNhat} : Updates an existing thongTinCapNhatTaiSan.
     *
     * @param idCapNhat the id of the thongTinCapNhatTaiSanDTO to save.
     * @param thongTinCapNhatTaiSanDTO the thongTinCapNhatTaiSanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated thongTinCapNhatTaiSanDTO,
     * or with status {@code 400 (Bad Request)} if the thongTinCapNhatTaiSanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the thongTinCapNhatTaiSanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idCapNhat}")
    public ResponseEntity<ThongTinCapNhatTaiSanDTO> updateThongTinCapNhatTaiSan(
        @PathVariable(value = "idCapNhat", required = false) final Long idCapNhat,
        @RequestBody ThongTinCapNhatTaiSanDTO thongTinCapNhatTaiSanDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update ThongTinCapNhatTaiSan : {}, {}", idCapNhat, thongTinCapNhatTaiSanDTO);
        if (thongTinCapNhatTaiSanDTO.getIdCapNhat() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCapNhat, thongTinCapNhatTaiSanDTO.getIdCapNhat())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!thongTinCapNhatTaiSanRepository.existsById(idCapNhat)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        thongTinCapNhatTaiSanDTO = thongTinCapNhatTaiSanService.update(thongTinCapNhatTaiSanDTO);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, thongTinCapNhatTaiSanDTO.getIdCapNhat().toString())
            )
            .body(thongTinCapNhatTaiSanDTO);
    }

    /**
     * {@code PATCH  /thong-tin-cap-nhat-tai-sans/:idCapNhat} : Partial updates given fields of an existing thongTinCapNhatTaiSan, field will ignore if it is null
     *
     * @param idCapNhat the id of the thongTinCapNhatTaiSanDTO to save.
     * @param thongTinCapNhatTaiSanDTO the thongTinCapNhatTaiSanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated thongTinCapNhatTaiSanDTO,
     * or with status {@code 400 (Bad Request)} if the thongTinCapNhatTaiSanDTO is not valid,
     * or with status {@code 404 (Not Found)} if the thongTinCapNhatTaiSanDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the thongTinCapNhatTaiSanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idCapNhat}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ThongTinCapNhatTaiSanDTO> partialUpdateThongTinCapNhatTaiSan(
        @PathVariable(value = "idCapNhat", required = false) final Long idCapNhat,
        @RequestBody ThongTinCapNhatTaiSanDTO thongTinCapNhatTaiSanDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update ThongTinCapNhatTaiSan partially : {}, {}", idCapNhat, thongTinCapNhatTaiSanDTO);
        if (thongTinCapNhatTaiSanDTO.getIdCapNhat() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCapNhat, thongTinCapNhatTaiSanDTO.getIdCapNhat())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!thongTinCapNhatTaiSanRepository.existsById(idCapNhat)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ThongTinCapNhatTaiSanDTO> result = thongTinCapNhatTaiSanService.partialUpdate(thongTinCapNhatTaiSanDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, thongTinCapNhatTaiSanDTO.getIdCapNhat().toString())
        );
    }

    /**
     * {@code GET  /thong-tin-cap-nhat-tai-sans} : get all the thongTinCapNhatTaiSans.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of thongTinCapNhatTaiSans in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ThongTinCapNhatTaiSanDTO>> getAllThongTinCapNhatTaiSans(ThongTinCapNhatTaiSanCriteria criteria) {
        LOG.debug("REST request to get ThongTinCapNhatTaiSans by criteria: {}", criteria);

        List<ThongTinCapNhatTaiSanDTO> entityList = thongTinCapNhatTaiSanQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /thong-tin-cap-nhat-tai-sans/count} : count all the thongTinCapNhatTaiSans.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countThongTinCapNhatTaiSans(ThongTinCapNhatTaiSanCriteria criteria) {
        LOG.debug("REST request to count ThongTinCapNhatTaiSans by criteria: {}", criteria);
        return ResponseEntity.ok().body(thongTinCapNhatTaiSanQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /thong-tin-cap-nhat-tai-sans/:id} : get the "id" thongTinCapNhatTaiSan.
     *
     * @param id the id of the thongTinCapNhatTaiSanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the thongTinCapNhatTaiSanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ThongTinCapNhatTaiSanDTO> getThongTinCapNhatTaiSan(@PathVariable("id") Long id) {
        LOG.debug("REST request to get ThongTinCapNhatTaiSan : {}", id);
        Optional<ThongTinCapNhatTaiSanDTO> thongTinCapNhatTaiSanDTO = thongTinCapNhatTaiSanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(thongTinCapNhatTaiSanDTO);
    }

    /**
     * {@code DELETE  /thong-tin-cap-nhat-tai-sans/:id} : delete the "id" thongTinCapNhatTaiSan.
     *
     * @param id the id of the thongTinCapNhatTaiSanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThongTinCapNhatTaiSan(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete ThongTinCapNhatTaiSan : {}", id);
        thongTinCapNhatTaiSanService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
