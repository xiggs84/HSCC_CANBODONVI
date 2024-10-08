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
import vn.vnpt.repository.DanhMucCanBoRepository;
import vn.vnpt.service.DanhMucCanBoQueryService;
import vn.vnpt.service.DanhMucCanBoService;
import vn.vnpt.service.criteria.DanhMucCanBoCriteria;
import vn.vnpt.service.dto.DanhMucCanBoDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DanhMucCanBo}.
 */
@RestController
@RequestMapping("/api/danh-muc-can-bos")
public class DanhMucCanBoResource {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucCanBoResource.class);

    private static final String ENTITY_NAME = "canBoDonViDanhMucCanBo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhMucCanBoService danhMucCanBoService;

    private final DanhMucCanBoRepository danhMucCanBoRepository;

    private final DanhMucCanBoQueryService danhMucCanBoQueryService;

    public DanhMucCanBoResource(
        DanhMucCanBoService danhMucCanBoService,
        DanhMucCanBoRepository danhMucCanBoRepository,
        DanhMucCanBoQueryService danhMucCanBoQueryService
    ) {
        this.danhMucCanBoService = danhMucCanBoService;
        this.danhMucCanBoRepository = danhMucCanBoRepository;
        this.danhMucCanBoQueryService = danhMucCanBoQueryService;
    }

    /**
     * {@code POST  /danh-muc-can-bos} : Create a new danhMucCanBo.
     *
     * @param danhMucCanBoDTO the danhMucCanBoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhMucCanBoDTO, or with status {@code 400 (Bad Request)} if the danhMucCanBo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DanhMucCanBoDTO> createDanhMucCanBo(@RequestBody DanhMucCanBoDTO danhMucCanBoDTO) throws URISyntaxException {
        LOG.debug("REST request to save DanhMucCanBo : {}", danhMucCanBoDTO);
        if (danhMucCanBoDTO.getIdCanBo() != null) {
            throw new BadRequestAlertException("A new danhMucCanBo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        danhMucCanBoDTO = danhMucCanBoService.save(danhMucCanBoDTO);
        return ResponseEntity.created(new URI("/api/danh-muc-can-bos/" + danhMucCanBoDTO.getIdCanBo()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, danhMucCanBoDTO.getIdCanBo().toString()))
            .body(danhMucCanBoDTO);
    }

    /**
     * {@code PUT  /danh-muc-can-bos/:idCanBo} : Updates an existing danhMucCanBo.
     *
     * @param idCanBo the id of the danhMucCanBoDTO to save.
     * @param danhMucCanBoDTO the danhMucCanBoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucCanBoDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucCanBoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhMucCanBoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idCanBo}")
    public ResponseEntity<DanhMucCanBoDTO> updateDanhMucCanBo(
        @PathVariable(value = "idCanBo", required = false) final Long idCanBo,
        @RequestBody DanhMucCanBoDTO danhMucCanBoDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DanhMucCanBo : {}, {}", idCanBo, danhMucCanBoDTO);
        if (danhMucCanBoDTO.getIdCanBo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCanBo, danhMucCanBoDTO.getIdCanBo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucCanBoRepository.existsById(idCanBo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        danhMucCanBoDTO = danhMucCanBoService.update(danhMucCanBoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucCanBoDTO.getIdCanBo().toString()))
            .body(danhMucCanBoDTO);
    }

    /**
     * {@code PATCH  /danh-muc-can-bos/:idCanBo} : Partial updates given fields of an existing danhMucCanBo, field will ignore if it is null
     *
     * @param idCanBo the id of the danhMucCanBoDTO to save.
     * @param danhMucCanBoDTO the danhMucCanBoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucCanBoDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucCanBoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the danhMucCanBoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhMucCanBoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idCanBo}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhMucCanBoDTO> partialUpdateDanhMucCanBo(
        @PathVariable(value = "idCanBo", required = false) final Long idCanBo,
        @RequestBody DanhMucCanBoDTO danhMucCanBoDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DanhMucCanBo partially : {}, {}", idCanBo, danhMucCanBoDTO);
        if (danhMucCanBoDTO.getIdCanBo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCanBo, danhMucCanBoDTO.getIdCanBo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucCanBoRepository.existsById(idCanBo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhMucCanBoDTO> result = danhMucCanBoService.partialUpdate(danhMucCanBoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucCanBoDTO.getIdCanBo().toString())
        );
    }

    /**
     * {@code GET  /danh-muc-can-bos} : get all the danhMucCanBos.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhMucCanBos in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DanhMucCanBoDTO>> getAllDanhMucCanBos(
        DanhMucCanBoCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get DanhMucCanBos by criteria: {}", criteria);

        Page<DanhMucCanBoDTO> page = danhMucCanBoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /danh-muc-can-bos/count} : count all the danhMucCanBos.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDanhMucCanBos(DanhMucCanBoCriteria criteria) {
        LOG.debug("REST request to count DanhMucCanBos by criteria: {}", criteria);
        return ResponseEntity.ok().body(danhMucCanBoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /danh-muc-can-bos/:id} : get the "id" danhMucCanBo.
     *
     * @param id the id of the danhMucCanBoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhMucCanBoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DanhMucCanBoDTO> getDanhMucCanBo(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DanhMucCanBo : {}", id);
        Optional<DanhMucCanBoDTO> danhMucCanBoDTO = danhMucCanBoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhMucCanBoDTO);
    }

    /**
     * {@code DELETE  /danh-muc-can-bos/:id} : delete the "id" danhMucCanBo.
     *
     * @param id the id of the danhMucCanBoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanhMucCanBo(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DanhMucCanBo : {}", id);
        danhMucCanBoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
