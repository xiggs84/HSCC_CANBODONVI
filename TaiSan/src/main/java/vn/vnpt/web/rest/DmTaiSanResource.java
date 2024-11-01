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
import vn.vnpt.repository.DmTaiSanRepository;
import vn.vnpt.service.DmTaiSanService;
import vn.vnpt.service.dto.DmTaiSanDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DmTaiSan}.
 */
@RestController
@RequestMapping("/api/dm-tai-sans")
public class DmTaiSanResource {

    private static final Logger LOG = LoggerFactory.getLogger(DmTaiSanResource.class);

    private static final String ENTITY_NAME = "taiSanDmTaiSan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DmTaiSanService dmTaiSanService;

    private final DmTaiSanRepository dmTaiSanRepository;

    public DmTaiSanResource(DmTaiSanService dmTaiSanService, DmTaiSanRepository dmTaiSanRepository) {
        this.dmTaiSanService = dmTaiSanService;
        this.dmTaiSanRepository = dmTaiSanRepository;
    }

    /**
     * {@code POST  /dm-tai-sans} : Create a new dmTaiSan.
     *
     * @param dmTaiSanDTO the dmTaiSanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dmTaiSanDTO, or with status {@code 400 (Bad Request)} if the dmTaiSan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DmTaiSanDTO> createDmTaiSan(@RequestBody DmTaiSanDTO dmTaiSanDTO) throws URISyntaxException {
        LOG.debug("REST request to save DmTaiSan : {}", dmTaiSanDTO);
        if (dmTaiSanDTO.getIdTaiSan() != null) {
            throw new BadRequestAlertException("A new dmTaiSan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        dmTaiSanDTO = dmTaiSanService.save(dmTaiSanDTO);
        return ResponseEntity.created(new URI("/api/dm-tai-sans/" + dmTaiSanDTO.getIdTaiSan()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, dmTaiSanDTO.getIdTaiSan().toString()))
            .body(dmTaiSanDTO);
    }

    /**
     * {@code PUT  /dm-tai-sans/:idTaiSan} : Updates an existing dmTaiSan.
     *
     * @param idTaiSan the id of the dmTaiSanDTO to save.
     * @param dmTaiSanDTO the dmTaiSanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmTaiSanDTO,
     * or with status {@code 400 (Bad Request)} if the dmTaiSanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dmTaiSanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idTaiSan}")
    public ResponseEntity<DmTaiSanDTO> updateDmTaiSan(
        @PathVariable(value = "idTaiSan", required = false) final Long idTaiSan,
        @RequestBody DmTaiSanDTO dmTaiSanDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DmTaiSan : {}, {}", idTaiSan, dmTaiSanDTO);
        if (dmTaiSanDTO.getIdTaiSan() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idTaiSan, dmTaiSanDTO.getIdTaiSan())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dmTaiSanRepository.existsById(idTaiSan)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        dmTaiSanDTO = dmTaiSanService.update(dmTaiSanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmTaiSanDTO.getIdTaiSan().toString()))
            .body(dmTaiSanDTO);
    }

    /**
     * {@code PATCH  /dm-tai-sans/:idTaiSan} : Partial updates given fields of an existing dmTaiSan, field will ignore if it is null
     *
     * @param idTaiSan the id of the dmTaiSanDTO to save.
     * @param dmTaiSanDTO the dmTaiSanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmTaiSanDTO,
     * or with status {@code 400 (Bad Request)} if the dmTaiSanDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dmTaiSanDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dmTaiSanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idTaiSan}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DmTaiSanDTO> partialUpdateDmTaiSan(
        @PathVariable(value = "idTaiSan", required = false) final Long idTaiSan,
        @RequestBody DmTaiSanDTO dmTaiSanDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DmTaiSan partially : {}, {}", idTaiSan, dmTaiSanDTO);
        if (dmTaiSanDTO.getIdTaiSan() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idTaiSan, dmTaiSanDTO.getIdTaiSan())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dmTaiSanRepository.existsById(idTaiSan)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DmTaiSanDTO> result = dmTaiSanService.partialUpdate(dmTaiSanDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmTaiSanDTO.getIdTaiSan().toString())
        );
    }

    /**
     * {@code GET  /dm-tai-sans} : get all the dmTaiSans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dmTaiSans in body.
     */
    @GetMapping("")
    public List<DmTaiSanDTO> getAllDmTaiSans() {
        LOG.debug("REST request to get all DmTaiSans");
        return dmTaiSanService.findAll();
    }

    /**
     * {@code GET  /dm-tai-sans/:id} : get the "id" dmTaiSan.
     *
     * @param id the id of the dmTaiSanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dmTaiSanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DmTaiSanDTO> getDmTaiSan(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DmTaiSan : {}", id);
        Optional<DmTaiSanDTO> dmTaiSanDTO = dmTaiSanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dmTaiSanDTO);
    }

    /**
     * {@code DELETE  /dm-tai-sans/:id} : delete the "id" dmTaiSan.
     *
     * @param id the id of the dmTaiSanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDmTaiSan(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DmTaiSan : {}", id);
        dmTaiSanService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
