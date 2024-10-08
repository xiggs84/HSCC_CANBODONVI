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
import vn.vnpt.repository.QuyenRepository;
import vn.vnpt.service.QuyenQueryService;
import vn.vnpt.service.QuyenService;
import vn.vnpt.service.criteria.QuyenCriteria;
import vn.vnpt.service.dto.QuyenDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.Quyen}.
 */
@RestController
@RequestMapping("/api/quyens")
public class QuyenResource {

    private static final Logger LOG = LoggerFactory.getLogger(QuyenResource.class);

    private static final String ENTITY_NAME = "canBoDonViQuyen";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuyenService quyenService;

    private final QuyenRepository quyenRepository;

    private final QuyenQueryService quyenQueryService;

    public QuyenResource(QuyenService quyenService, QuyenRepository quyenRepository, QuyenQueryService quyenQueryService) {
        this.quyenService = quyenService;
        this.quyenRepository = quyenRepository;
        this.quyenQueryService = quyenQueryService;
    }

    /**
     * {@code POST  /quyens} : Create a new quyen.
     *
     * @param quyenDTO the quyenDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new quyenDTO, or with status {@code 400 (Bad Request)} if the quyen has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<QuyenDTO> createQuyen(@RequestBody QuyenDTO quyenDTO) throws URISyntaxException {
        LOG.debug("REST request to save Quyen : {}", quyenDTO);
        if (quyenDTO.getIdQuyen() != null) {
            throw new BadRequestAlertException("A new quyen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        quyenDTO = quyenService.save(quyenDTO);
        return ResponseEntity.created(new URI("/api/quyens/" + quyenDTO.getIdQuyen()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, quyenDTO.getIdQuyen().toString()))
            .body(quyenDTO);
    }

    /**
     * {@code PUT  /quyens/:idQuyen} : Updates an existing quyen.
     *
     * @param idQuyen the id of the quyenDTO to save.
     * @param quyenDTO the quyenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated quyenDTO,
     * or with status {@code 400 (Bad Request)} if the quyenDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the quyenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idQuyen}")
    public ResponseEntity<QuyenDTO> updateQuyen(
        @PathVariable(value = "idQuyen", required = false) final Long idQuyen,
        @RequestBody QuyenDTO quyenDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Quyen : {}, {}", idQuyen, quyenDTO);
        if (quyenDTO.getIdQuyen() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idQuyen, quyenDTO.getIdQuyen())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!quyenRepository.existsById(idQuyen)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        quyenDTO = quyenService.update(quyenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, quyenDTO.getIdQuyen().toString()))
            .body(quyenDTO);
    }

    /**
     * {@code PATCH  /quyens/:idQuyen} : Partial updates given fields of an existing quyen, field will ignore if it is null
     *
     * @param idQuyen the id of the quyenDTO to save.
     * @param quyenDTO the quyenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated quyenDTO,
     * or with status {@code 400 (Bad Request)} if the quyenDTO is not valid,
     * or with status {@code 404 (Not Found)} if the quyenDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the quyenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idQuyen}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<QuyenDTO> partialUpdateQuyen(
        @PathVariable(value = "idQuyen", required = false) final Long idQuyen,
        @RequestBody QuyenDTO quyenDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Quyen partially : {}, {}", idQuyen, quyenDTO);
        if (quyenDTO.getIdQuyen() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idQuyen, quyenDTO.getIdQuyen())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!quyenRepository.existsById(idQuyen)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QuyenDTO> result = quyenService.partialUpdate(quyenDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, quyenDTO.getIdQuyen().toString())
        );
    }

    /**
     * {@code GET  /quyens} : get all the quyens.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of quyens in body.
     */
    @GetMapping("")
    public ResponseEntity<List<QuyenDTO>> getAllQuyens(
        QuyenCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get Quyens by criteria: {}", criteria);

        Page<QuyenDTO> page = quyenQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /quyens/count} : count all the quyens.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countQuyens(QuyenCriteria criteria) {
        LOG.debug("REST request to count Quyens by criteria: {}", criteria);
        return ResponseEntity.ok().body(quyenQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /quyens/:id} : get the "id" quyen.
     *
     * @param id the id of the quyenDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the quyenDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<QuyenDTO> getQuyen(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Quyen : {}", id);
        Optional<QuyenDTO> quyenDTO = quyenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(quyenDTO);
    }

    /**
     * {@code DELETE  /quyens/:id} : delete the "id" quyen.
     *
     * @param id the id of the quyenDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuyen(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Quyen : {}", id);
        quyenService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
