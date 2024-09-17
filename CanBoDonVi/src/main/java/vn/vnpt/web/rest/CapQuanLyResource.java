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
import vn.vnpt.repository.CapQuanLyRepository;
import vn.vnpt.service.CapQuanLyService;
import vn.vnpt.service.dto.CapQuanLyDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.CapQuanLy}.
 */
@RestController
@RequestMapping("/api/cap-quan-lies")
public class CapQuanLyResource {

    private static final Logger LOG = LoggerFactory.getLogger(CapQuanLyResource.class);

    private static final String ENTITY_NAME = "canBoDonViCapQuanLy";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CapQuanLyService capQuanLyService;

    private final CapQuanLyRepository capQuanLyRepository;

    public CapQuanLyResource(CapQuanLyService capQuanLyService, CapQuanLyRepository capQuanLyRepository) {
        this.capQuanLyService = capQuanLyService;
        this.capQuanLyRepository = capQuanLyRepository;
    }

    /**
     * {@code POST  /cap-quan-lies} : Create a new capQuanLy.
     *
     * @param capQuanLyDTO the capQuanLyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new capQuanLyDTO, or with status {@code 400 (Bad Request)} if the capQuanLy has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CapQuanLyDTO> createCapQuanLy(@RequestBody CapQuanLyDTO capQuanLyDTO) throws URISyntaxException {
        LOG.debug("REST request to save CapQuanLy : {}", capQuanLyDTO);
        if (capQuanLyRepository.existsById(capQuanLyDTO.getIdCapQl())) {
            throw new BadRequestAlertException("capQuanLy already exists", ENTITY_NAME, "idexists");
        }
        capQuanLyDTO = capQuanLyService.save(capQuanLyDTO);
        return ResponseEntity.created(new URI("/api/cap-quan-lies/" + capQuanLyDTO.getIdCapQl()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, capQuanLyDTO.getIdCapQl()))
            .body(capQuanLyDTO);
    }

    /**
     * {@code PUT  /cap-quan-lies/:idCapQl} : Updates an existing capQuanLy.
     *
     * @param idCapQl the id of the capQuanLyDTO to save.
     * @param capQuanLyDTO the capQuanLyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated capQuanLyDTO,
     * or with status {@code 400 (Bad Request)} if the capQuanLyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the capQuanLyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idCapQl}")
    public ResponseEntity<CapQuanLyDTO> updateCapQuanLy(
        @PathVariable(value = "idCapQl", required = false) final String idCapQl,
        @RequestBody CapQuanLyDTO capQuanLyDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update CapQuanLy : {}, {}", idCapQl, capQuanLyDTO);
        if (capQuanLyDTO.getIdCapQl() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCapQl, capQuanLyDTO.getIdCapQl())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!capQuanLyRepository.existsById(idCapQl)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        capQuanLyDTO = capQuanLyService.update(capQuanLyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, capQuanLyDTO.getIdCapQl()))
            .body(capQuanLyDTO);
    }

    /**
     * {@code PATCH  /cap-quan-lies/:idCapQl} : Partial updates given fields of an existing capQuanLy, field will ignore if it is null
     *
     * @param idCapQl the id of the capQuanLyDTO to save.
     * @param capQuanLyDTO the capQuanLyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated capQuanLyDTO,
     * or with status {@code 400 (Bad Request)} if the capQuanLyDTO is not valid,
     * or with status {@code 404 (Not Found)} if the capQuanLyDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the capQuanLyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idCapQl}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CapQuanLyDTO> partialUpdateCapQuanLy(
        @PathVariable(value = "idCapQl", required = false) final String idCapQl,
        @RequestBody CapQuanLyDTO capQuanLyDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update CapQuanLy partially : {}, {}", idCapQl, capQuanLyDTO);
        if (capQuanLyDTO.getIdCapQl() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCapQl, capQuanLyDTO.getIdCapQl())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!capQuanLyRepository.existsById(idCapQl)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CapQuanLyDTO> result = capQuanLyService.partialUpdate(capQuanLyDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, capQuanLyDTO.getIdCapQl())
        );
    }

    /**
     * {@code GET  /cap-quan-lies} : get all the capQuanLies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of capQuanLies in body.
     */
    @GetMapping("")
    public List<CapQuanLyDTO> getAllCapQuanLies() {
        LOG.debug("REST request to get all CapQuanLies");
        return capQuanLyService.findAll();
    }

    /**
     * {@code GET  /cap-quan-lies/:id} : get the "id" capQuanLy.
     *
     * @param id the id of the capQuanLyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the capQuanLyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CapQuanLyDTO> getCapQuanLy(@PathVariable("id") String id) {
        LOG.debug("REST request to get CapQuanLy : {}", id);
        Optional<CapQuanLyDTO> capQuanLyDTO = capQuanLyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(capQuanLyDTO);
    }

    /**
     * {@code DELETE  /cap-quan-lies/:id} : delete the "id" capQuanLy.
     *
     * @param id the id of the capQuanLyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCapQuanLy(@PathVariable("id") String id) {
        LOG.debug("REST request to delete CapQuanLy : {}", id);
        capQuanLyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
