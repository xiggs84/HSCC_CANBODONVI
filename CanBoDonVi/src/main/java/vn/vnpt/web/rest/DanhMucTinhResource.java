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
import vn.vnpt.repository.DanhMucTinhRepository;
import vn.vnpt.service.DanhMucTinhService;
import vn.vnpt.service.dto.DanhMucTinhDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DanhMucTinh}.
 */
@RestController
@RequestMapping("/api/danh-muc-tinhs")
public class DanhMucTinhResource {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucTinhResource.class);

    private static final String ENTITY_NAME = "canBoDonViDanhMucTinh";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhMucTinhService danhMucTinhService;

    private final DanhMucTinhRepository danhMucTinhRepository;

    public DanhMucTinhResource(DanhMucTinhService danhMucTinhService, DanhMucTinhRepository danhMucTinhRepository) {
        this.danhMucTinhService = danhMucTinhService;
        this.danhMucTinhRepository = danhMucTinhRepository;
    }

    /**
     * {@code POST  /danh-muc-tinhs} : Create a new danhMucTinh.
     *
     * @param danhMucTinhDTO the danhMucTinhDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhMucTinhDTO, or with status {@code 400 (Bad Request)} if the danhMucTinh has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DanhMucTinhDTO> createDanhMucTinh(@RequestBody DanhMucTinhDTO danhMucTinhDTO) throws URISyntaxException {
        LOG.debug("REST request to save DanhMucTinh : {}", danhMucTinhDTO);
        if (danhMucTinhRepository.existsById(danhMucTinhDTO.getMaTinh())) {
            throw new BadRequestAlertException("danhMucTinh already exists", ENTITY_NAME, "idexists");
        }
        danhMucTinhDTO = danhMucTinhService.save(danhMucTinhDTO);
        return ResponseEntity.created(new URI("/api/danh-muc-tinhs/" + danhMucTinhDTO.getMaTinh()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, danhMucTinhDTO.getMaTinh()))
            .body(danhMucTinhDTO);
    }

    /**
     * {@code PUT  /danh-muc-tinhs/:maTinh} : Updates an existing danhMucTinh.
     *
     * @param maTinh the id of the danhMucTinhDTO to save.
     * @param danhMucTinhDTO the danhMucTinhDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucTinhDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucTinhDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhMucTinhDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{maTinh}")
    public ResponseEntity<DanhMucTinhDTO> updateDanhMucTinh(
        @PathVariable(value = "maTinh", required = false) final String maTinh,
        @RequestBody DanhMucTinhDTO danhMucTinhDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DanhMucTinh : {}, {}", maTinh, danhMucTinhDTO);
        if (danhMucTinhDTO.getMaTinh() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(maTinh, danhMucTinhDTO.getMaTinh())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucTinhRepository.existsById(maTinh)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        danhMucTinhDTO = danhMucTinhService.update(danhMucTinhDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucTinhDTO.getMaTinh()))
            .body(danhMucTinhDTO);
    }

    /**
     * {@code PATCH  /danh-muc-tinhs/:maTinh} : Partial updates given fields of an existing danhMucTinh, field will ignore if it is null
     *
     * @param maTinh the id of the danhMucTinhDTO to save.
     * @param danhMucTinhDTO the danhMucTinhDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucTinhDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucTinhDTO is not valid,
     * or with status {@code 404 (Not Found)} if the danhMucTinhDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhMucTinhDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{maTinh}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhMucTinhDTO> partialUpdateDanhMucTinh(
        @PathVariable(value = "maTinh", required = false) final String maTinh,
        @RequestBody DanhMucTinhDTO danhMucTinhDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DanhMucTinh partially : {}, {}", maTinh, danhMucTinhDTO);
        if (danhMucTinhDTO.getMaTinh() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(maTinh, danhMucTinhDTO.getMaTinh())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucTinhRepository.existsById(maTinh)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhMucTinhDTO> result = danhMucTinhService.partialUpdate(danhMucTinhDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucTinhDTO.getMaTinh())
        );
    }

    /**
     * {@code GET  /danh-muc-tinhs} : get all the danhMucTinhs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhMucTinhs in body.
     */
    @GetMapping("")
    public List<DanhMucTinhDTO> getAllDanhMucTinhs() {
        LOG.debug("REST request to get all DanhMucTinhs");
        return danhMucTinhService.findAll();
    }

    /**
     * {@code GET  /danh-muc-tinhs/:id} : get the "id" danhMucTinh.
     *
     * @param id the id of the danhMucTinhDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhMucTinhDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DanhMucTinhDTO> getDanhMucTinh(@PathVariable("id") String id) {
        LOG.debug("REST request to get DanhMucTinh : {}", id);
        Optional<DanhMucTinhDTO> danhMucTinhDTO = danhMucTinhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhMucTinhDTO);
    }

    /**
     * {@code DELETE  /danh-muc-tinhs/:id} : delete the "id" danhMucTinh.
     *
     * @param id the id of the danhMucTinhDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanhMucTinh(@PathVariable("id") String id) {
        LOG.debug("REST request to delete DanhMucTinh : {}", id);
        danhMucTinhService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
