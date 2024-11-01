package vn.vnpt.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
import vn.vnpt.repository.CauHinhThongTinDuongSuRepository;
import vn.vnpt.service.CauHinhThongTinDuongSuQueryService;
import vn.vnpt.service.CauHinhThongTinDuongSuService;
import vn.vnpt.service.criteria.CauHinhThongTinDuongSuCriteria;
import vn.vnpt.service.dto.CauHinhThongTinDuongSuDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.CauHinhThongTinDuongSu}.
 */
@RestController
@RequestMapping("/api/cau-hinh-thong-tin-duong-sus")
public class CauHinhThongTinDuongSuResource {

    private static final Logger LOG = LoggerFactory.getLogger(CauHinhThongTinDuongSuResource.class);

    private static final String ENTITY_NAME = "duongSuCauHinhThongTinDuongSu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CauHinhThongTinDuongSuService cauHinhThongTinDuongSuService;

    private final CauHinhThongTinDuongSuRepository cauHinhThongTinDuongSuRepository;

    private final CauHinhThongTinDuongSuQueryService cauHinhThongTinDuongSuQueryService;

    public CauHinhThongTinDuongSuResource(
        CauHinhThongTinDuongSuService cauHinhThongTinDuongSuService,
        CauHinhThongTinDuongSuRepository cauHinhThongTinDuongSuRepository,
        CauHinhThongTinDuongSuQueryService cauHinhThongTinDuongSuQueryService
    ) {
        this.cauHinhThongTinDuongSuService = cauHinhThongTinDuongSuService;
        this.cauHinhThongTinDuongSuRepository = cauHinhThongTinDuongSuRepository;
        this.cauHinhThongTinDuongSuQueryService = cauHinhThongTinDuongSuQueryService;
    }

    /**
     * {@code POST  /cau-hinh-thong-tin-duong-sus} : Create a new cauHinhThongTinDuongSu.
     *
     * @param cauHinhThongTinDuongSuDTO the cauHinhThongTinDuongSuDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cauHinhThongTinDuongSuDTO, or with status {@code 400 (Bad Request)} if the cauHinhThongTinDuongSu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CauHinhThongTinDuongSuDTO> createCauHinhThongTinDuongSu(
        @Valid @RequestBody CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save CauHinhThongTinDuongSu : {}", cauHinhThongTinDuongSuDTO);
        if (cauHinhThongTinDuongSuDTO.getIdCauHinh() != null) {
            throw new BadRequestAlertException("A new cauHinhThongTinDuongSu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        cauHinhThongTinDuongSuDTO = cauHinhThongTinDuongSuService.save(cauHinhThongTinDuongSuDTO);
        return ResponseEntity.created(new URI("/api/cau-hinh-thong-tin-duong-sus/" + cauHinhThongTinDuongSuDTO.getIdCauHinh()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    cauHinhThongTinDuongSuDTO.getIdCauHinh().toString()
                )
            )
            .body(cauHinhThongTinDuongSuDTO);
    }

    /**
     * {@code PUT  /cau-hinh-thong-tin-duong-sus/:idCauHinh} : Updates an existing cauHinhThongTinDuongSu.
     *
     * @param idCauHinh the id of the cauHinhThongTinDuongSuDTO to save.
     * @param cauHinhThongTinDuongSuDTO the cauHinhThongTinDuongSuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cauHinhThongTinDuongSuDTO,
     * or with status {@code 400 (Bad Request)} if the cauHinhThongTinDuongSuDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cauHinhThongTinDuongSuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idCauHinh}")
    public ResponseEntity<CauHinhThongTinDuongSuDTO> updateCauHinhThongTinDuongSu(
        @PathVariable(value = "idCauHinh", required = false) final Long idCauHinh,
        @Valid @RequestBody CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update CauHinhThongTinDuongSu : {}, {}", idCauHinh, cauHinhThongTinDuongSuDTO);
        if (cauHinhThongTinDuongSuDTO.getIdCauHinh() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCauHinh, cauHinhThongTinDuongSuDTO.getIdCauHinh())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cauHinhThongTinDuongSuRepository.existsById(idCauHinh)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        cauHinhThongTinDuongSuDTO = cauHinhThongTinDuongSuService.update(cauHinhThongTinDuongSuDTO);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cauHinhThongTinDuongSuDTO.getIdCauHinh().toString())
            )
            .body(cauHinhThongTinDuongSuDTO);
    }

    /**
     * {@code PATCH  /cau-hinh-thong-tin-duong-sus/:idCauHinh} : Partial updates given fields of an existing cauHinhThongTinDuongSu, field will ignore if it is null
     *
     * @param idCauHinh the id of the cauHinhThongTinDuongSuDTO to save.
     * @param cauHinhThongTinDuongSuDTO the cauHinhThongTinDuongSuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cauHinhThongTinDuongSuDTO,
     * or with status {@code 400 (Bad Request)} if the cauHinhThongTinDuongSuDTO is not valid,
     * or with status {@code 404 (Not Found)} if the cauHinhThongTinDuongSuDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the cauHinhThongTinDuongSuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idCauHinh}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CauHinhThongTinDuongSuDTO> partialUpdateCauHinhThongTinDuongSu(
        @PathVariable(value = "idCauHinh", required = false) final Long idCauHinh,
        @NotNull @RequestBody CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update CauHinhThongTinDuongSu partially : {}, {}", idCauHinh, cauHinhThongTinDuongSuDTO);
        if (cauHinhThongTinDuongSuDTO.getIdCauHinh() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCauHinh, cauHinhThongTinDuongSuDTO.getIdCauHinh())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cauHinhThongTinDuongSuRepository.existsById(idCauHinh)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CauHinhThongTinDuongSuDTO> result = cauHinhThongTinDuongSuService.partialUpdate(cauHinhThongTinDuongSuDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cauHinhThongTinDuongSuDTO.getIdCauHinh().toString())
        );
    }

    /**
     * {@code GET  /cau-hinh-thong-tin-duong-sus} : get all the cauHinhThongTinDuongSus.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cauHinhThongTinDuongSus in body.
     */
    @GetMapping("")
    public ResponseEntity<List<CauHinhThongTinDuongSuDTO>> getAllCauHinhThongTinDuongSus(
        CauHinhThongTinDuongSuCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get CauHinhThongTinDuongSus by criteria: {}", criteria);

        Page<CauHinhThongTinDuongSuDTO> page = cauHinhThongTinDuongSuQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cau-hinh-thong-tin-duong-sus/count} : count all the cauHinhThongTinDuongSus.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countCauHinhThongTinDuongSus(CauHinhThongTinDuongSuCriteria criteria) {
        LOG.debug("REST request to count CauHinhThongTinDuongSus by criteria: {}", criteria);
        return ResponseEntity.ok().body(cauHinhThongTinDuongSuQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cau-hinh-thong-tin-duong-sus/:id} : get the "id" cauHinhThongTinDuongSu.
     *
     * @param id the id of the cauHinhThongTinDuongSuDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cauHinhThongTinDuongSuDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CauHinhThongTinDuongSuDTO> getCauHinhThongTinDuongSu(@PathVariable("id") Long id) {
        LOG.debug("REST request to get CauHinhThongTinDuongSu : {}", id);
        Optional<CauHinhThongTinDuongSuDTO> cauHinhThongTinDuongSuDTO = cauHinhThongTinDuongSuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cauHinhThongTinDuongSuDTO);
    }

    /**
     * {@code DELETE  /cau-hinh-thong-tin-duong-sus/:id} : delete the "id" cauHinhThongTinDuongSu.
     *
     * @param id the id of the cauHinhThongTinDuongSuDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCauHinhThongTinDuongSu(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete CauHinhThongTinDuongSu : {}", id);
        cauHinhThongTinDuongSuService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
