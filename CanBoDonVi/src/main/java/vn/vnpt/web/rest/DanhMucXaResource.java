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
import vn.vnpt.repository.DanhMucXaRepository;
import vn.vnpt.service.DanhMucXaQueryService;
import vn.vnpt.service.DanhMucXaService;
import vn.vnpt.service.criteria.DanhMucXaCriteria;
import vn.vnpt.service.dto.DanhMucXaDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DanhMucXa}.
 */
@RestController
@RequestMapping("/api/danh-muc-xas")
public class DanhMucXaResource {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucXaResource.class);

    private static final String ENTITY_NAME = "canBoDonViDanhMucXa";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhMucXaService danhMucXaService;

    private final DanhMucXaRepository danhMucXaRepository;

    private final DanhMucXaQueryService danhMucXaQueryService;

    public DanhMucXaResource(
        DanhMucXaService danhMucXaService,
        DanhMucXaRepository danhMucXaRepository,
        DanhMucXaQueryService danhMucXaQueryService
    ) {
        this.danhMucXaService = danhMucXaService;
        this.danhMucXaRepository = danhMucXaRepository;
        this.danhMucXaQueryService = danhMucXaQueryService;
    }

    /**
     * {@code POST  /danh-muc-xas} : Create a new danhMucXa.
     *
     * @param danhMucXaDTO the danhMucXaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhMucXaDTO, or with status {@code 400 (Bad Request)} if the danhMucXa has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DanhMucXaDTO> createDanhMucXa(@RequestBody DanhMucXaDTO danhMucXaDTO) throws URISyntaxException {
        LOG.debug("REST request to save DanhMucXa : {}", danhMucXaDTO);
        if (danhMucXaRepository.existsById(danhMucXaDTO.getMaXa())) {
            throw new BadRequestAlertException("danhMucXa already exists", ENTITY_NAME, "idexists");
        }
        danhMucXaDTO = danhMucXaService.save(danhMucXaDTO);
        return ResponseEntity.created(new URI("/api/danh-muc-xas/" + danhMucXaDTO.getMaXa()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, danhMucXaDTO.getMaXa()))
            .body(danhMucXaDTO);
    }

    /**
     * {@code PUT  /danh-muc-xas/:maXa} : Updates an existing danhMucXa.
     *
     * @param maXa the id of the danhMucXaDTO to save.
     * @param danhMucXaDTO the danhMucXaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucXaDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucXaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhMucXaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{maXa}")
    public ResponseEntity<DanhMucXaDTO> updateDanhMucXa(
        @PathVariable(value = "maXa", required = false) final String maXa,
        @RequestBody DanhMucXaDTO danhMucXaDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DanhMucXa : {}, {}", maXa, danhMucXaDTO);
        if (danhMucXaDTO.getMaXa() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(maXa, danhMucXaDTO.getMaXa())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucXaRepository.existsById(maXa)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        danhMucXaDTO = danhMucXaService.update(danhMucXaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucXaDTO.getMaXa()))
            .body(danhMucXaDTO);
    }

    /**
     * {@code PATCH  /danh-muc-xas/:maXa} : Partial updates given fields of an existing danhMucXa, field will ignore if it is null
     *
     * @param maXa the id of the danhMucXaDTO to save.
     * @param danhMucXaDTO the danhMucXaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucXaDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucXaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the danhMucXaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhMucXaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{maXa}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhMucXaDTO> partialUpdateDanhMucXa(
        @PathVariable(value = "maXa", required = false) final String maXa,
        @RequestBody DanhMucXaDTO danhMucXaDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DanhMucXa partially : {}, {}", maXa, danhMucXaDTO);
        if (danhMucXaDTO.getMaXa() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(maXa, danhMucXaDTO.getMaXa())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucXaRepository.existsById(maXa)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhMucXaDTO> result = danhMucXaService.partialUpdate(danhMucXaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucXaDTO.getMaXa())
        );
    }

    /**
     * {@code GET  /danh-muc-xas} : get all the danhMucXas.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhMucXas in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DanhMucXaDTO>> getAllDanhMucXas(
        DanhMucXaCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get DanhMucXas by criteria: {}", criteria);

        Page<DanhMucXaDTO> page = danhMucXaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /danh-muc-xas/count} : count all the danhMucXas.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDanhMucXas(DanhMucXaCriteria criteria) {
        LOG.debug("REST request to count DanhMucXas by criteria: {}", criteria);
        return ResponseEntity.ok().body(danhMucXaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /danh-muc-xas/:id} : get the "id" danhMucXa.
     *
     * @param id the id of the danhMucXaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhMucXaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DanhMucXaDTO> getDanhMucXa(@PathVariable("id") String id) {
        LOG.debug("REST request to get DanhMucXa : {}", id);
        Optional<DanhMucXaDTO> danhMucXaDTO = danhMucXaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhMucXaDTO);
    }

    /**
     * {@code DELETE  /danh-muc-xas/:id} : delete the "id" danhMucXa.
     *
     * @param id the id of the danhMucXaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanhMucXa(@PathVariable("id") String id) {
        LOG.debug("REST request to delete DanhMucXa : {}", id);
        danhMucXaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
