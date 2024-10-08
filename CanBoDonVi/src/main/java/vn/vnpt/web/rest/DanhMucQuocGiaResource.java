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
import vn.vnpt.repository.DanhMucQuocGiaRepository;
import vn.vnpt.service.DanhMucQuocGiaQueryService;
import vn.vnpt.service.DanhMucQuocGiaService;
import vn.vnpt.service.criteria.DanhMucQuocGiaCriteria;
import vn.vnpt.service.dto.DanhMucQuocGiaDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DanhMucQuocGia}.
 */
@RestController
@RequestMapping("/api/danh-muc-quoc-gias")
public class DanhMucQuocGiaResource {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucQuocGiaResource.class);

    private static final String ENTITY_NAME = "canBoDonViDanhMucQuocGia";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhMucQuocGiaService danhMucQuocGiaService;

    private final DanhMucQuocGiaRepository danhMucQuocGiaRepository;

    private final DanhMucQuocGiaQueryService danhMucQuocGiaQueryService;

    public DanhMucQuocGiaResource(
        DanhMucQuocGiaService danhMucQuocGiaService,
        DanhMucQuocGiaRepository danhMucQuocGiaRepository,
        DanhMucQuocGiaQueryService danhMucQuocGiaQueryService
    ) {
        this.danhMucQuocGiaService = danhMucQuocGiaService;
        this.danhMucQuocGiaRepository = danhMucQuocGiaRepository;
        this.danhMucQuocGiaQueryService = danhMucQuocGiaQueryService;
    }

    /**
     * {@code POST  /danh-muc-quoc-gias} : Create a new danhMucQuocGia.
     *
     * @param danhMucQuocGiaDTO the danhMucQuocGiaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhMucQuocGiaDTO, or with status {@code 400 (Bad Request)} if the danhMucQuocGia has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DanhMucQuocGiaDTO> createDanhMucQuocGia(@RequestBody DanhMucQuocGiaDTO danhMucQuocGiaDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save DanhMucQuocGia : {}", danhMucQuocGiaDTO);
        if (danhMucQuocGiaDTO.getIdQuocGia() != null) {
            throw new BadRequestAlertException("A new danhMucQuocGia cannot already have an ID", ENTITY_NAME, "idexists");
        }
        danhMucQuocGiaDTO = danhMucQuocGiaService.save(danhMucQuocGiaDTO);
        return ResponseEntity.created(new URI("/api/danh-muc-quoc-gias/" + danhMucQuocGiaDTO.getIdQuocGia()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, danhMucQuocGiaDTO.getIdQuocGia().toString()))
            .body(danhMucQuocGiaDTO);
    }

    /**
     * {@code PUT  /danh-muc-quoc-gias/:idQuocGia} : Updates an existing danhMucQuocGia.
     *
     * @param idQuocGia the id of the danhMucQuocGiaDTO to save.
     * @param danhMucQuocGiaDTO the danhMucQuocGiaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucQuocGiaDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucQuocGiaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhMucQuocGiaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idQuocGia}")
    public ResponseEntity<DanhMucQuocGiaDTO> updateDanhMucQuocGia(
        @PathVariable(value = "idQuocGia", required = false) final Long idQuocGia,
        @RequestBody DanhMucQuocGiaDTO danhMucQuocGiaDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DanhMucQuocGia : {}, {}", idQuocGia, danhMucQuocGiaDTO);
        if (danhMucQuocGiaDTO.getIdQuocGia() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idQuocGia, danhMucQuocGiaDTO.getIdQuocGia())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucQuocGiaRepository.existsById(idQuocGia)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        danhMucQuocGiaDTO = danhMucQuocGiaService.update(danhMucQuocGiaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucQuocGiaDTO.getIdQuocGia().toString()))
            .body(danhMucQuocGiaDTO);
    }

    /**
     * {@code PATCH  /danh-muc-quoc-gias/:idQuocGia} : Partial updates given fields of an existing danhMucQuocGia, field will ignore if it is null
     *
     * @param idQuocGia the id of the danhMucQuocGiaDTO to save.
     * @param danhMucQuocGiaDTO the danhMucQuocGiaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucQuocGiaDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucQuocGiaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the danhMucQuocGiaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhMucQuocGiaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idQuocGia}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhMucQuocGiaDTO> partialUpdateDanhMucQuocGia(
        @PathVariable(value = "idQuocGia", required = false) final Long idQuocGia,
        @RequestBody DanhMucQuocGiaDTO danhMucQuocGiaDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DanhMucQuocGia partially : {}, {}", idQuocGia, danhMucQuocGiaDTO);
        if (danhMucQuocGiaDTO.getIdQuocGia() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idQuocGia, danhMucQuocGiaDTO.getIdQuocGia())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucQuocGiaRepository.existsById(idQuocGia)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhMucQuocGiaDTO> result = danhMucQuocGiaService.partialUpdate(danhMucQuocGiaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucQuocGiaDTO.getIdQuocGia().toString())
        );
    }

    /**
     * {@code GET  /danh-muc-quoc-gias} : get all the danhMucQuocGias.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhMucQuocGias in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DanhMucQuocGiaDTO>> getAllDanhMucQuocGias(
        DanhMucQuocGiaCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get DanhMucQuocGias by criteria: {}", criteria);

        Page<DanhMucQuocGiaDTO> page = danhMucQuocGiaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /danh-muc-quoc-gias/count} : count all the danhMucQuocGias.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDanhMucQuocGias(DanhMucQuocGiaCriteria criteria) {
        LOG.debug("REST request to count DanhMucQuocGias by criteria: {}", criteria);
        return ResponseEntity.ok().body(danhMucQuocGiaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /danh-muc-quoc-gias/:id} : get the "id" danhMucQuocGia.
     *
     * @param id the id of the danhMucQuocGiaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhMucQuocGiaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DanhMucQuocGiaDTO> getDanhMucQuocGia(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DanhMucQuocGia : {}", id);
        Optional<DanhMucQuocGiaDTO> danhMucQuocGiaDTO = danhMucQuocGiaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhMucQuocGiaDTO);
    }

    /**
     * {@code DELETE  /danh-muc-quoc-gias/:id} : delete the "id" danhMucQuocGia.
     *
     * @param id the id of the danhMucQuocGiaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanhMucQuocGia(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DanhMucQuocGia : {}", id);
        danhMucQuocGiaService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
