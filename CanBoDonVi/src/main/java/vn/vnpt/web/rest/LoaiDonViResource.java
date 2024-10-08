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
import vn.vnpt.repository.LoaiDonViRepository;
import vn.vnpt.service.LoaiDonViQueryService;
import vn.vnpt.service.LoaiDonViService;
import vn.vnpt.service.criteria.LoaiDonViCriteria;
import vn.vnpt.service.dto.LoaiDonViDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.LoaiDonVi}.
 */
@RestController
@RequestMapping("/api/loai-don-vis")
public class LoaiDonViResource {

    private static final Logger LOG = LoggerFactory.getLogger(LoaiDonViResource.class);

    private static final String ENTITY_NAME = "canBoDonViLoaiDonVi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LoaiDonViService loaiDonViService;

    private final LoaiDonViRepository loaiDonViRepository;

    private final LoaiDonViQueryService loaiDonViQueryService;

    public LoaiDonViResource(
        LoaiDonViService loaiDonViService,
        LoaiDonViRepository loaiDonViRepository,
        LoaiDonViQueryService loaiDonViQueryService
    ) {
        this.loaiDonViService = loaiDonViService;
        this.loaiDonViRepository = loaiDonViRepository;
        this.loaiDonViQueryService = loaiDonViQueryService;
    }

    /**
     * {@code POST  /loai-don-vis} : Create a new loaiDonVi.
     *
     * @param loaiDonViDTO the loaiDonViDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new loaiDonViDTO, or with status {@code 400 (Bad Request)} if the loaiDonVi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<LoaiDonViDTO> createLoaiDonVi(@RequestBody LoaiDonViDTO loaiDonViDTO) throws URISyntaxException {
        LOG.debug("REST request to save LoaiDonVi : {}", loaiDonViDTO);
        if (loaiDonViRepository.existsById(loaiDonViDTO.getIdLoaiDv())) {
            throw new BadRequestAlertException("loaiDonVi already exists", ENTITY_NAME, "idexists");
        }
        loaiDonViDTO = loaiDonViService.save(loaiDonViDTO);
        return ResponseEntity.created(new URI("/api/loai-don-vis/" + loaiDonViDTO.getIdLoaiDv()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, loaiDonViDTO.getIdLoaiDv()))
            .body(loaiDonViDTO);
    }

    /**
     * {@code PUT  /loai-don-vis/:idLoaiDv} : Updates an existing loaiDonVi.
     *
     * @param idLoaiDv the id of the loaiDonViDTO to save.
     * @param loaiDonViDTO the loaiDonViDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loaiDonViDTO,
     * or with status {@code 400 (Bad Request)} if the loaiDonViDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the loaiDonViDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idLoaiDv}")
    public ResponseEntity<LoaiDonViDTO> updateLoaiDonVi(
        @PathVariable(value = "idLoaiDv", required = false) final String idLoaiDv,
        @RequestBody LoaiDonViDTO loaiDonViDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update LoaiDonVi : {}, {}", idLoaiDv, loaiDonViDTO);
        if (loaiDonViDTO.getIdLoaiDv() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idLoaiDv, loaiDonViDTO.getIdLoaiDv())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!loaiDonViRepository.existsById(idLoaiDv)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        loaiDonViDTO = loaiDonViService.update(loaiDonViDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, loaiDonViDTO.getIdLoaiDv()))
            .body(loaiDonViDTO);
    }

    /**
     * {@code PATCH  /loai-don-vis/:idLoaiDv} : Partial updates given fields of an existing loaiDonVi, field will ignore if it is null
     *
     * @param idLoaiDv the id of the loaiDonViDTO to save.
     * @param loaiDonViDTO the loaiDonViDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loaiDonViDTO,
     * or with status {@code 400 (Bad Request)} if the loaiDonViDTO is not valid,
     * or with status {@code 404 (Not Found)} if the loaiDonViDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the loaiDonViDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idLoaiDv}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LoaiDonViDTO> partialUpdateLoaiDonVi(
        @PathVariable(value = "idLoaiDv", required = false) final String idLoaiDv,
        @RequestBody LoaiDonViDTO loaiDonViDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update LoaiDonVi partially : {}, {}", idLoaiDv, loaiDonViDTO);
        if (loaiDonViDTO.getIdLoaiDv() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idLoaiDv, loaiDonViDTO.getIdLoaiDv())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!loaiDonViRepository.existsById(idLoaiDv)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LoaiDonViDTO> result = loaiDonViService.partialUpdate(loaiDonViDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, loaiDonViDTO.getIdLoaiDv())
        );
    }

    /**
     * {@code GET  /loai-don-vis} : get all the loaiDonVis.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of loaiDonVis in body.
     */
    @GetMapping("")
    public ResponseEntity<List<LoaiDonViDTO>> getAllLoaiDonVis(
        LoaiDonViCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get LoaiDonVis by criteria: {}", criteria);

        Page<LoaiDonViDTO> page = loaiDonViQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /loai-don-vis/count} : count all the loaiDonVis.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countLoaiDonVis(LoaiDonViCriteria criteria) {
        LOG.debug("REST request to count LoaiDonVis by criteria: {}", criteria);
        return ResponseEntity.ok().body(loaiDonViQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /loai-don-vis/:id} : get the "id" loaiDonVi.
     *
     * @param id the id of the loaiDonViDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the loaiDonViDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LoaiDonViDTO> getLoaiDonVi(@PathVariable("id") String id) {
        LOG.debug("REST request to get LoaiDonVi : {}", id);
        Optional<LoaiDonViDTO> loaiDonViDTO = loaiDonViService.findOne(id);
        return ResponseUtil.wrapOrNotFound(loaiDonViDTO);
    }

    /**
     * {@code DELETE  /loai-don-vis/:id} : delete the "id" loaiDonVi.
     *
     * @param id the id of the loaiDonViDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoaiDonVi(@PathVariable("id") String id) {
        LOG.debug("REST request to delete LoaiDonVi : {}", id);
        loaiDonViService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
