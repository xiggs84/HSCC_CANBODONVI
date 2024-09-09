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
import vn.vnpt.repository.QuanHeMasterRepository;
import vn.vnpt.service.QuanHeMasterService;
import vn.vnpt.service.dto.QuanHeMasterDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.QuanHeMaster}.
 */
@RestController
@RequestMapping("/api/quan-he-masters")
public class QuanHeMasterResource {

    private static final Logger LOG = LoggerFactory.getLogger(QuanHeMasterResource.class);

    private static final String ENTITY_NAME = "duongSuQuanHeMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuanHeMasterService quanHeMasterService;

    private final QuanHeMasterRepository quanHeMasterRepository;

    public QuanHeMasterResource(QuanHeMasterService quanHeMasterService, QuanHeMasterRepository quanHeMasterRepository) {
        this.quanHeMasterService = quanHeMasterService;
        this.quanHeMasterRepository = quanHeMasterRepository;
    }

    /**
     * {@code POST  /quan-he-masters} : Create a new quanHeMaster.
     *
     * @param quanHeMasterDTO the quanHeMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new quanHeMasterDTO, or with status {@code 400 (Bad Request)} if the quanHeMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<QuanHeMasterDTO> createQuanHeMaster(@RequestBody QuanHeMasterDTO quanHeMasterDTO) throws URISyntaxException {
        LOG.debug("REST request to save QuanHeMaster : {}", quanHeMasterDTO);
        if (quanHeMasterDTO.getId() != null) {
            throw new BadRequestAlertException("A new quanHeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        quanHeMasterDTO = quanHeMasterService.save(quanHeMasterDTO);
        return ResponseEntity.created(new URI("/api/quan-he-masters/" + quanHeMasterDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, quanHeMasterDTO.getId().toString()))
            .body(quanHeMasterDTO);
    }

    /**
     * {@code PUT  /quan-he-masters/:id} : Updates an existing quanHeMaster.
     *
     * @param id the id of the quanHeMasterDTO to save.
     * @param quanHeMasterDTO the quanHeMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated quanHeMasterDTO,
     * or with status {@code 400 (Bad Request)} if the quanHeMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the quanHeMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<QuanHeMasterDTO> updateQuanHeMaster(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody QuanHeMasterDTO quanHeMasterDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update QuanHeMaster : {}, {}", id, quanHeMasterDTO);
        if (quanHeMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, quanHeMasterDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!quanHeMasterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        quanHeMasterDTO = quanHeMasterService.update(quanHeMasterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, quanHeMasterDTO.getId().toString()))
            .body(quanHeMasterDTO);
    }

    /**
     * {@code PATCH  /quan-he-masters/:id} : Partial updates given fields of an existing quanHeMaster, field will ignore if it is null
     *
     * @param id the id of the quanHeMasterDTO to save.
     * @param quanHeMasterDTO the quanHeMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated quanHeMasterDTO,
     * or with status {@code 400 (Bad Request)} if the quanHeMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the quanHeMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the quanHeMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<QuanHeMasterDTO> partialUpdateQuanHeMaster(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody QuanHeMasterDTO quanHeMasterDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update QuanHeMaster partially : {}, {}", id, quanHeMasterDTO);
        if (quanHeMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, quanHeMasterDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!quanHeMasterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QuanHeMasterDTO> result = quanHeMasterService.partialUpdate(quanHeMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, quanHeMasterDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /quan-he-masters} : get all the quanHeMasters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of quanHeMasters in body.
     */
    @GetMapping("")
    public List<QuanHeMasterDTO> getAllQuanHeMasters() {
        LOG.debug("REST request to get all QuanHeMasters");
        return quanHeMasterService.findAll();
    }

    /**
     * {@code GET  /quan-he-masters/:id} : get the "id" quanHeMaster.
     *
     * @param id the id of the quanHeMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the quanHeMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<QuanHeMasterDTO> getQuanHeMaster(@PathVariable("id") Long id) {
        LOG.debug("REST request to get QuanHeMaster : {}", id);
        Optional<QuanHeMasterDTO> quanHeMasterDTO = quanHeMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(quanHeMasterDTO);
    }

    /**
     * {@code DELETE  /quan-he-masters/:id} : delete the "id" quanHeMaster.
     *
     * @param id the id of the quanHeMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuanHeMaster(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete QuanHeMaster : {}", id);
        quanHeMasterService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
