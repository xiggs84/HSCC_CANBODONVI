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
import vn.vnpt.repository.DanhMucCapQuanLyRepository;
import vn.vnpt.service.DanhMucCapQuanLyService;
import vn.vnpt.service.dto.DanhMucCapQuanLyDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DanhMucCapQuanLy}.
 */
@RestController
@RequestMapping("/api/danh-muc-cap-quan-lies")
public class DanhMucCapQuanLyResource {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucCapQuanLyResource.class);

    private static final String ENTITY_NAME = "canBoDonViDanhMucCapQuanLy";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhMucCapQuanLyService danhMucCapQuanLyService;

    private final DanhMucCapQuanLyRepository danhMucCapQuanLyRepository;

    public DanhMucCapQuanLyResource(
        DanhMucCapQuanLyService danhMucCapQuanLyService,
        DanhMucCapQuanLyRepository danhMucCapQuanLyRepository
    ) {
        this.danhMucCapQuanLyService = danhMucCapQuanLyService;
        this.danhMucCapQuanLyRepository = danhMucCapQuanLyRepository;
    }

    /**
     * {@code POST  /danh-muc-cap-quan-lies} : Create a new danhMucCapQuanLy.
     *
     * @param danhMucCapQuanLyDTO the danhMucCapQuanLyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhMucCapQuanLyDTO, or with status {@code 400 (Bad Request)} if the danhMucCapQuanLy has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DanhMucCapQuanLyDTO> createDanhMucCapQuanLy(@RequestBody DanhMucCapQuanLyDTO danhMucCapQuanLyDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save DanhMucCapQuanLy : {}", danhMucCapQuanLyDTO);
        if (danhMucCapQuanLyDTO.getIdCapQl() != null) {
            throw new BadRequestAlertException("A new danhMucCapQuanLy cannot already have an ID", ENTITY_NAME, "idexists");
        }
        danhMucCapQuanLyDTO = danhMucCapQuanLyService.save(danhMucCapQuanLyDTO);
        return ResponseEntity.created(new URI("/api/danh-muc-cap-quan-lies/" + danhMucCapQuanLyDTO.getIdCapQl()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, danhMucCapQuanLyDTO.getIdCapQl().toString()))
            .body(danhMucCapQuanLyDTO);
    }

    /**
     * {@code PUT  /danh-muc-cap-quan-lies/:idCapQl} : Updates an existing danhMucCapQuanLy.
     *
     * @param idCapQl the id of the danhMucCapQuanLyDTO to save.
     * @param danhMucCapQuanLyDTO the danhMucCapQuanLyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucCapQuanLyDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucCapQuanLyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhMucCapQuanLyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idCapQl}")
    public ResponseEntity<DanhMucCapQuanLyDTO> updateDanhMucCapQuanLy(
        @PathVariable(value = "idCapQl", required = false) final Long idCapQl,
        @RequestBody DanhMucCapQuanLyDTO danhMucCapQuanLyDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DanhMucCapQuanLy : {}, {}", idCapQl, danhMucCapQuanLyDTO);
        if (danhMucCapQuanLyDTO.getIdCapQl() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCapQl, danhMucCapQuanLyDTO.getIdCapQl())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucCapQuanLyRepository.existsById(idCapQl)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        danhMucCapQuanLyDTO = danhMucCapQuanLyService.update(danhMucCapQuanLyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucCapQuanLyDTO.getIdCapQl().toString()))
            .body(danhMucCapQuanLyDTO);
    }

    /**
     * {@code PATCH  /danh-muc-cap-quan-lies/:idCapQl} : Partial updates given fields of an existing danhMucCapQuanLy, field will ignore if it is null
     *
     * @param idCapQl the id of the danhMucCapQuanLyDTO to save.
     * @param danhMucCapQuanLyDTO the danhMucCapQuanLyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucCapQuanLyDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucCapQuanLyDTO is not valid,
     * or with status {@code 404 (Not Found)} if the danhMucCapQuanLyDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhMucCapQuanLyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idCapQl}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhMucCapQuanLyDTO> partialUpdateDanhMucCapQuanLy(
        @PathVariable(value = "idCapQl", required = false) final Long idCapQl,
        @RequestBody DanhMucCapQuanLyDTO danhMucCapQuanLyDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DanhMucCapQuanLy partially : {}, {}", idCapQl, danhMucCapQuanLyDTO);
        if (danhMucCapQuanLyDTO.getIdCapQl() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCapQl, danhMucCapQuanLyDTO.getIdCapQl())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucCapQuanLyRepository.existsById(idCapQl)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhMucCapQuanLyDTO> result = danhMucCapQuanLyService.partialUpdate(danhMucCapQuanLyDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucCapQuanLyDTO.getIdCapQl().toString())
        );
    }

    /**
     * {@code GET  /danh-muc-cap-quan-lies} : get all the danhMucCapQuanLies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhMucCapQuanLies in body.
     */
    @GetMapping("")
    public List<DanhMucCapQuanLyDTO> getAllDanhMucCapQuanLies() {
        LOG.debug("REST request to get all DanhMucCapQuanLies");
        return danhMucCapQuanLyService.findAll();
    }

    /**
     * {@code GET  /danh-muc-cap-quan-lies/:id} : get the "id" danhMucCapQuanLy.
     *
     * @param id the id of the danhMucCapQuanLyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhMucCapQuanLyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DanhMucCapQuanLyDTO> getDanhMucCapQuanLy(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DanhMucCapQuanLy : {}", id);
        Optional<DanhMucCapQuanLyDTO> danhMucCapQuanLyDTO = danhMucCapQuanLyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhMucCapQuanLyDTO);
    }

    /**
     * {@code DELETE  /danh-muc-cap-quan-lies/:id} : delete the "id" danhMucCapQuanLy.
     *
     * @param id the id of the danhMucCapQuanLyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanhMucCapQuanLy(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DanhMucCapQuanLy : {}", id);
        danhMucCapQuanLyService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
