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
import vn.vnpt.repository.DanhMucDonViRepository;
import vn.vnpt.service.DanhMucDonViService;
import vn.vnpt.service.dto.DanhMucDonViDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DanhMucDonVi}.
 */
@RestController
@RequestMapping("/api/danh-muc-don-vis")
public class DanhMucDonViResource {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucDonViResource.class);

    private static final String ENTITY_NAME = "canBoDonViDanhMucDonVi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhMucDonViService danhMucDonViService;

    private final DanhMucDonViRepository danhMucDonViRepository;

    public DanhMucDonViResource(DanhMucDonViService danhMucDonViService, DanhMucDonViRepository danhMucDonViRepository) {
        this.danhMucDonViService = danhMucDonViService;
        this.danhMucDonViRepository = danhMucDonViRepository;
    }

    /**
     * {@code POST  /danh-muc-don-vis} : Create a new danhMucDonVi.
     *
     * @param danhMucDonViDTO the danhMucDonViDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhMucDonViDTO, or with status {@code 400 (Bad Request)} if the danhMucDonVi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DanhMucDonViDTO> createDanhMucDonVi(@RequestBody DanhMucDonViDTO danhMucDonViDTO) throws URISyntaxException {
        LOG.debug("REST request to save DanhMucDonVi : {}", danhMucDonViDTO);
        if (danhMucDonViDTO.getIdDonVi() != null) {
            throw new BadRequestAlertException("A new danhMucDonVi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        danhMucDonViDTO = danhMucDonViService.save(danhMucDonViDTO);
        return ResponseEntity.created(new URI("/api/danh-muc-don-vis/" + danhMucDonViDTO.getIdDonVi()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, danhMucDonViDTO.getIdDonVi().toString()))
            .body(danhMucDonViDTO);
    }

    /**
     * {@code PUT  /danh-muc-don-vis/:idDonVi} : Updates an existing danhMucDonVi.
     *
     * @param idDonVi the id of the danhMucDonViDTO to save.
     * @param danhMucDonViDTO the danhMucDonViDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucDonViDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucDonViDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhMucDonViDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idDonVi}")
    public ResponseEntity<DanhMucDonViDTO> updateDanhMucDonVi(
        @PathVariable(value = "idDonVi", required = false) final Long idDonVi,
        @RequestBody DanhMucDonViDTO danhMucDonViDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DanhMucDonVi : {}, {}", idDonVi, danhMucDonViDTO);
        if (danhMucDonViDTO.getIdDonVi() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idDonVi, danhMucDonViDTO.getIdDonVi())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucDonViRepository.existsById(idDonVi)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        danhMucDonViDTO = danhMucDonViService.update(danhMucDonViDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucDonViDTO.getIdDonVi().toString()))
            .body(danhMucDonViDTO);
    }

    /**
     * {@code PATCH  /danh-muc-don-vis/:idDonVi} : Partial updates given fields of an existing danhMucDonVi, field will ignore if it is null
     *
     * @param idDonVi the id of the danhMucDonViDTO to save.
     * @param danhMucDonViDTO the danhMucDonViDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucDonViDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucDonViDTO is not valid,
     * or with status {@code 404 (Not Found)} if the danhMucDonViDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhMucDonViDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idDonVi}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhMucDonViDTO> partialUpdateDanhMucDonVi(
        @PathVariable(value = "idDonVi", required = false) final Long idDonVi,
        @RequestBody DanhMucDonViDTO danhMucDonViDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DanhMucDonVi partially : {}, {}", idDonVi, danhMucDonViDTO);
        if (danhMucDonViDTO.getIdDonVi() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idDonVi, danhMucDonViDTO.getIdDonVi())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucDonViRepository.existsById(idDonVi)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhMucDonViDTO> result = danhMucDonViService.partialUpdate(danhMucDonViDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucDonViDTO.getIdDonVi().toString())
        );
    }

    /**
     * {@code GET  /danh-muc-don-vis} : get all the danhMucDonVis.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhMucDonVis in body.
     */
    @GetMapping("")
    public List<DanhMucDonViDTO> getAllDanhMucDonVis() {
        LOG.debug("REST request to get all DanhMucDonVis");
        return danhMucDonViService.findAll();
    }

    /**
     * {@code GET  /danh-muc-don-vis/:id} : get the "id" danhMucDonVi.
     *
     * @param id the id of the danhMucDonViDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhMucDonViDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DanhMucDonViDTO> getDanhMucDonVi(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DanhMucDonVi : {}", id);
        Optional<DanhMucDonViDTO> danhMucDonViDTO = danhMucDonViService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhMucDonViDTO);
    }

    /**
     * {@code DELETE  /danh-muc-don-vis/:id} : delete the "id" danhMucDonVi.
     *
     * @param id the id of the danhMucDonViDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanhMucDonVi(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DanhMucDonVi : {}", id);
        danhMucDonViService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/by-ten")
    public List<DanhMucDonViDTO> getDanhMucDonViByTenDonVi(@RequestParam("tenDonVi") String tenDonVi) {
        LOG.debug("REST request to get DanhMucDonVis by tenDonVi : {}", tenDonVi);
        return danhMucDonViService.findByTenDonVi(tenDonVi);
    }
}
