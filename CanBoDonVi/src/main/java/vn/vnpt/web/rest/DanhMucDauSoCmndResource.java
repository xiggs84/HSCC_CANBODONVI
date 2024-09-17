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
import vn.vnpt.repository.DanhMucDauSoCmndRepository;
import vn.vnpt.service.DanhMucDauSoCmndService;
import vn.vnpt.service.dto.DanhMucDauSoCmndDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DanhMucDauSoCmnd}.
 */
@RestController
@RequestMapping("/api/danh-muc-dau-so-cmnds")
public class DanhMucDauSoCmndResource {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucDauSoCmndResource.class);

    private static final String ENTITY_NAME = "canBoDonViDanhMucDauSoCmnd";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhMucDauSoCmndService danhMucDauSoCmndService;

    private final DanhMucDauSoCmndRepository danhMucDauSoCmndRepository;

    public DanhMucDauSoCmndResource(
        DanhMucDauSoCmndService danhMucDauSoCmndService,
        DanhMucDauSoCmndRepository danhMucDauSoCmndRepository
    ) {
        this.danhMucDauSoCmndService = danhMucDauSoCmndService;
        this.danhMucDauSoCmndRepository = danhMucDauSoCmndRepository;
    }

    /**
     * {@code POST  /danh-muc-dau-so-cmnds} : Create a new danhMucDauSoCmnd.
     *
     * @param danhMucDauSoCmndDTO the danhMucDauSoCmndDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhMucDauSoCmndDTO, or with status {@code 400 (Bad Request)} if the danhMucDauSoCmnd has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DanhMucDauSoCmndDTO> createDanhMucDauSoCmnd(@RequestBody DanhMucDauSoCmndDTO danhMucDauSoCmndDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save DanhMucDauSoCmnd : {}", danhMucDauSoCmndDTO);
        if (danhMucDauSoCmndDTO.getIdDauSo() != null) {
            throw new BadRequestAlertException("A new danhMucDauSoCmnd cannot already have an ID", ENTITY_NAME, "idexists");
        }
        danhMucDauSoCmndDTO = danhMucDauSoCmndService.save(danhMucDauSoCmndDTO);
        return ResponseEntity.created(new URI("/api/danh-muc-dau-so-cmnds/" + danhMucDauSoCmndDTO.getIdDauSo()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, danhMucDauSoCmndDTO.getIdDauSo().toString()))
            .body(danhMucDauSoCmndDTO);
    }

    /**
     * {@code PUT  /danh-muc-dau-so-cmnds/:idDauSo} : Updates an existing danhMucDauSoCmnd.
     *
     * @param idDauSo the id of the danhMucDauSoCmndDTO to save.
     * @param danhMucDauSoCmndDTO the danhMucDauSoCmndDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucDauSoCmndDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucDauSoCmndDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhMucDauSoCmndDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idDauSo}")
    public ResponseEntity<DanhMucDauSoCmndDTO> updateDanhMucDauSoCmnd(
        @PathVariable(value = "idDauSo", required = false) final Long idDauSo,
        @RequestBody DanhMucDauSoCmndDTO danhMucDauSoCmndDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DanhMucDauSoCmnd : {}, {}", idDauSo, danhMucDauSoCmndDTO);
        if (danhMucDauSoCmndDTO.getIdDauSo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idDauSo, danhMucDauSoCmndDTO.getIdDauSo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucDauSoCmndRepository.existsById(idDauSo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        danhMucDauSoCmndDTO = danhMucDauSoCmndService.update(danhMucDauSoCmndDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucDauSoCmndDTO.getIdDauSo().toString()))
            .body(danhMucDauSoCmndDTO);
    }

    /**
     * {@code PATCH  /danh-muc-dau-so-cmnds/:idDauSo} : Partial updates given fields of an existing danhMucDauSoCmnd, field will ignore if it is null
     *
     * @param idDauSo the id of the danhMucDauSoCmndDTO to save.
     * @param danhMucDauSoCmndDTO the danhMucDauSoCmndDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucDauSoCmndDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucDauSoCmndDTO is not valid,
     * or with status {@code 404 (Not Found)} if the danhMucDauSoCmndDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhMucDauSoCmndDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idDauSo}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhMucDauSoCmndDTO> partialUpdateDanhMucDauSoCmnd(
        @PathVariable(value = "idDauSo", required = false) final Long idDauSo,
        @RequestBody DanhMucDauSoCmndDTO danhMucDauSoCmndDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DanhMucDauSoCmnd partially : {}, {}", idDauSo, danhMucDauSoCmndDTO);
        if (danhMucDauSoCmndDTO.getIdDauSo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idDauSo, danhMucDauSoCmndDTO.getIdDauSo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucDauSoCmndRepository.existsById(idDauSo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhMucDauSoCmndDTO> result = danhMucDauSoCmndService.partialUpdate(danhMucDauSoCmndDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucDauSoCmndDTO.getIdDauSo().toString())
        );
    }

    /**
     * {@code GET  /danh-muc-dau-so-cmnds} : get all the danhMucDauSoCmnds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhMucDauSoCmnds in body.
     */
    @GetMapping("")
    public List<DanhMucDauSoCmndDTO> getAllDanhMucDauSoCmnds() {
        LOG.debug("REST request to get all DanhMucDauSoCmnds");
        return danhMucDauSoCmndService.findAll();
    }

    /**
     * {@code GET  /danh-muc-dau-so-cmnds/:id} : get the "id" danhMucDauSoCmnd.
     *
     * @param id the id of the danhMucDauSoCmndDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhMucDauSoCmndDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DanhMucDauSoCmndDTO> getDanhMucDauSoCmnd(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DanhMucDauSoCmnd : {}", id);
        Optional<DanhMucDauSoCmndDTO> danhMucDauSoCmndDTO = danhMucDauSoCmndService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhMucDauSoCmndDTO);
    }

    /**
     * {@code DELETE  /danh-muc-dau-so-cmnds/:id} : delete the "id" danhMucDauSoCmnd.
     *
     * @param id the id of the danhMucDauSoCmndDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanhMucDauSoCmnd(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DanhMucDauSoCmnd : {}", id);
        danhMucDauSoCmndService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
