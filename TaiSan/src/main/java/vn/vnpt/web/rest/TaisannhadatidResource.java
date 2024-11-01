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
import vn.vnpt.repository.TaisannhadatidRepository;
import vn.vnpt.service.TaisannhadatidService;
import vn.vnpt.service.dto.TaisannhadatidDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.Taisannhadatid}.
 */
@RestController
@RequestMapping("/api/taisannhadatids")
public class TaisannhadatidResource {

    private static final Logger LOG = LoggerFactory.getLogger(TaisannhadatidResource.class);

    private static final String ENTITY_NAME = "taiSanTaisannhadatid";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaisannhadatidService taisannhadatidService;

    private final TaisannhadatidRepository taisannhadatidRepository;

    public TaisannhadatidResource(TaisannhadatidService taisannhadatidService, TaisannhadatidRepository taisannhadatidRepository) {
        this.taisannhadatidService = taisannhadatidService;
        this.taisannhadatidRepository = taisannhadatidRepository;
    }

    /**
     * {@code POST  /taisannhadatids} : Create a new taisannhadatid.
     *
     * @param taisannhadatidDTO the taisannhadatidDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taisannhadatidDTO, or with status {@code 400 (Bad Request)} if the taisannhadatid has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TaisannhadatidDTO> createTaisannhadatid(@RequestBody TaisannhadatidDTO taisannhadatidDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save Taisannhadatid : {}", taisannhadatidDTO);
        if (taisannhadatidDTO.getIdTaiSan() != null) {
            throw new BadRequestAlertException("A new taisannhadatid cannot already have an ID", ENTITY_NAME, "idexists");
        }
        taisannhadatidDTO = taisannhadatidService.save(taisannhadatidDTO);
        return ResponseEntity.created(new URI("/api/taisannhadatids/" + taisannhadatidDTO.getIdTaiSan()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, taisannhadatidDTO.getIdTaiSan().toString()))
            .body(taisannhadatidDTO);
    }

    /**
     * {@code PUT  /taisannhadatids/:idTaiSan} : Updates an existing taisannhadatid.
     *
     * @param idTaiSan the id of the taisannhadatidDTO to save.
     * @param taisannhadatidDTO the taisannhadatidDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taisannhadatidDTO,
     * or with status {@code 400 (Bad Request)} if the taisannhadatidDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taisannhadatidDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idTaiSan}")
    public ResponseEntity<TaisannhadatidDTO> updateTaisannhadatid(
        @PathVariable(value = "idTaiSan", required = false) final Long idTaiSan,
        @RequestBody TaisannhadatidDTO taisannhadatidDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Taisannhadatid : {}, {}", idTaiSan, taisannhadatidDTO);
        if (taisannhadatidDTO.getIdTaiSan() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idTaiSan, taisannhadatidDTO.getIdTaiSan())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taisannhadatidRepository.existsById(idTaiSan)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        taisannhadatidDTO = taisannhadatidService.update(taisannhadatidDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taisannhadatidDTO.getIdTaiSan().toString()))
            .body(taisannhadatidDTO);
    }

    /**
     * {@code PATCH  /taisannhadatids/:idTaiSan} : Partial updates given fields of an existing taisannhadatid, field will ignore if it is null
     *
     * @param idTaiSan the id of the taisannhadatidDTO to save.
     * @param taisannhadatidDTO the taisannhadatidDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taisannhadatidDTO,
     * or with status {@code 400 (Bad Request)} if the taisannhadatidDTO is not valid,
     * or with status {@code 404 (Not Found)} if the taisannhadatidDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the taisannhadatidDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idTaiSan}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TaisannhadatidDTO> partialUpdateTaisannhadatid(
        @PathVariable(value = "idTaiSan", required = false) final Long idTaiSan,
        @RequestBody TaisannhadatidDTO taisannhadatidDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Taisannhadatid partially : {}, {}", idTaiSan, taisannhadatidDTO);
        if (taisannhadatidDTO.getIdTaiSan() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idTaiSan, taisannhadatidDTO.getIdTaiSan())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taisannhadatidRepository.existsById(idTaiSan)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TaisannhadatidDTO> result = taisannhadatidService.partialUpdate(taisannhadatidDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taisannhadatidDTO.getIdTaiSan().toString())
        );
    }

    /**
     * {@code GET  /taisannhadatids} : get all the taisannhadatids.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taisannhadatids in body.
     */
    @GetMapping("")
    public List<TaisannhadatidDTO> getAllTaisannhadatids() {
        LOG.debug("REST request to get all Taisannhadatids");
        return taisannhadatidService.findAll();
    }

    /**
     * {@code GET  /taisannhadatids/:id} : get the "id" taisannhadatid.
     *
     * @param id the id of the taisannhadatidDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taisannhadatidDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaisannhadatidDTO> getTaisannhadatid(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Taisannhadatid : {}", id);
        Optional<TaisannhadatidDTO> taisannhadatidDTO = taisannhadatidService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taisannhadatidDTO);
    }

    /**
     * {@code DELETE  /taisannhadatids/:id} : delete the "id" taisannhadatid.
     *
     * @param id the id of the taisannhadatidDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaisannhadatid(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Taisannhadatid : {}", id);
        taisannhadatidService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
