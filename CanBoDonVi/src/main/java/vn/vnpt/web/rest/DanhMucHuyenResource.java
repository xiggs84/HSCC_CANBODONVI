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
import vn.vnpt.repository.DanhMucHuyenRepository;
import vn.vnpt.service.DanhMucHuyenService;
import vn.vnpt.service.dto.DanhMucHuyenDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DanhMucHuyen}.
 */
@RestController
@RequestMapping("/api/danh-muc-huyens")
public class DanhMucHuyenResource {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucHuyenResource.class);

    private static final String ENTITY_NAME = "canBoDonViDanhMucHuyen";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhMucHuyenService danhMucHuyenService;

    private final DanhMucHuyenRepository danhMucHuyenRepository;

    public DanhMucHuyenResource(DanhMucHuyenService danhMucHuyenService, DanhMucHuyenRepository danhMucHuyenRepository) {
        this.danhMucHuyenService = danhMucHuyenService;
        this.danhMucHuyenRepository = danhMucHuyenRepository;
    }

    /**
     * {@code POST  /danh-muc-huyens} : Create a new danhMucHuyen.
     *
     * @param danhMucHuyenDTO the danhMucHuyenDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhMucHuyenDTO, or with status {@code 400 (Bad Request)} if the danhMucHuyen has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DanhMucHuyenDTO> createDanhMucHuyen(@RequestBody DanhMucHuyenDTO danhMucHuyenDTO) throws URISyntaxException {
        LOG.debug("REST request to save DanhMucHuyen : {}", danhMucHuyenDTO);
        if (danhMucHuyenRepository.existsById(danhMucHuyenDTO.getMaHuyen())) {
            throw new BadRequestAlertException("danhMucHuyen already exists", ENTITY_NAME, "idexists");
        }
        danhMucHuyenDTO = danhMucHuyenService.save(danhMucHuyenDTO);
        return ResponseEntity.created(new URI("/api/danh-muc-huyens/" + danhMucHuyenDTO.getMaHuyen()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, danhMucHuyenDTO.getMaHuyen()))
            .body(danhMucHuyenDTO);
    }

    /**
     * {@code PUT  /danh-muc-huyens/:maHuyen} : Updates an existing danhMucHuyen.
     *
     * @param maHuyen the id of the danhMucHuyenDTO to save.
     * @param danhMucHuyenDTO the danhMucHuyenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucHuyenDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucHuyenDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhMucHuyenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{maHuyen}")
    public ResponseEntity<DanhMucHuyenDTO> updateDanhMucHuyen(
        @PathVariable(value = "maHuyen", required = false) final String maHuyen,
        @RequestBody DanhMucHuyenDTO danhMucHuyenDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DanhMucHuyen : {}, {}", maHuyen, danhMucHuyenDTO);
        if (danhMucHuyenDTO.getMaHuyen() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(maHuyen, danhMucHuyenDTO.getMaHuyen())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucHuyenRepository.existsById(maHuyen)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        danhMucHuyenDTO = danhMucHuyenService.update(danhMucHuyenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucHuyenDTO.getMaHuyen()))
            .body(danhMucHuyenDTO);
    }

    /**
     * {@code PATCH  /danh-muc-huyens/:maHuyen} : Partial updates given fields of an existing danhMucHuyen, field will ignore if it is null
     *
     * @param maHuyen the id of the danhMucHuyenDTO to save.
     * @param danhMucHuyenDTO the danhMucHuyenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucHuyenDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucHuyenDTO is not valid,
     * or with status {@code 404 (Not Found)} if the danhMucHuyenDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhMucHuyenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{maHuyen}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhMucHuyenDTO> partialUpdateDanhMucHuyen(
        @PathVariable(value = "maHuyen", required = false) final String maHuyen,
        @RequestBody DanhMucHuyenDTO danhMucHuyenDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DanhMucHuyen partially : {}, {}", maHuyen, danhMucHuyenDTO);
        if (danhMucHuyenDTO.getMaHuyen() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(maHuyen, danhMucHuyenDTO.getMaHuyen())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucHuyenRepository.existsById(maHuyen)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhMucHuyenDTO> result = danhMucHuyenService.partialUpdate(danhMucHuyenDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucHuyenDTO.getMaHuyen())
        );
    }

    /**
     * {@code GET  /danh-muc-huyens} : get all the danhMucHuyens.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhMucHuyens in body.
     */
    @GetMapping("")
    public List<DanhMucHuyenDTO> getAllDanhMucHuyens() {
        LOG.debug("REST request to get all DanhMucHuyens");
        return danhMucHuyenService.findAll();
    }

    /**
     * {@code GET  /danh-muc-huyens/:id} : get the "id" danhMucHuyen.
     *
     * @param id the id of the danhMucHuyenDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhMucHuyenDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DanhMucHuyenDTO> getDanhMucHuyen(@PathVariable("id") String id) {
        LOG.debug("REST request to get DanhMucHuyen : {}", id);
        Optional<DanhMucHuyenDTO> danhMucHuyenDTO = danhMucHuyenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhMucHuyenDTO);
    }

    /**
     * {@code DELETE  /danh-muc-huyens/:id} : delete the "id" danhMucHuyen.
     *
     * @param id the id of the danhMucHuyenDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanhMucHuyen(@PathVariable("id") String id) {
        LOG.debug("REST request to delete DanhMucHuyen : {}", id);
        danhMucHuyenService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    @GetMapping("/by-ma-tinh/{maTinh}")
    public List<DanhMucHuyenDTO> getQuanHuyenByTinh(@PathVariable String maTinh) {
        return danhMucHuyenService.findByMaTinh(maTinh);
    }
}
