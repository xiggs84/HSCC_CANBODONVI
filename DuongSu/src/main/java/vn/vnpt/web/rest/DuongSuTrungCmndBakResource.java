package vn.vnpt.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
import vn.vnpt.repository.DuongSuTrungCmndBakRepository;
import vn.vnpt.service.DuongSuTrungCmndBakService;
import vn.vnpt.service.dto.DuongSuTrungCmndBakDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DuongSuTrungCmndBak}.
 */
@RestController
@RequestMapping("/api/duong-su-trung-cmnd-baks")
public class DuongSuTrungCmndBakResource {

    private static final Logger LOG = LoggerFactory.getLogger(DuongSuTrungCmndBakResource.class);

    private static final String ENTITY_NAME = "duongSuDuongSuTrungCmndBak";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DuongSuTrungCmndBakService duongSuTrungCmndBakService;

    private final DuongSuTrungCmndBakRepository duongSuTrungCmndBakRepository;

    public DuongSuTrungCmndBakResource(
        DuongSuTrungCmndBakService duongSuTrungCmndBakService,
        DuongSuTrungCmndBakRepository duongSuTrungCmndBakRepository
    ) {
        this.duongSuTrungCmndBakService = duongSuTrungCmndBakService;
        this.duongSuTrungCmndBakRepository = duongSuTrungCmndBakRepository;
    }

    /**
     * {@code POST  /duong-su-trung-cmnd-baks} : Create a new duongSuTrungCmndBak.
     *
     * @param duongSuTrungCmndBakDTO the duongSuTrungCmndBakDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new duongSuTrungCmndBakDTO, or with status {@code 400 (Bad Request)} if the duongSuTrungCmndBak has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DuongSuTrungCmndBakDTO> createDuongSuTrungCmndBak(
        @Valid @RequestBody DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save DuongSuTrungCmndBak : {}", duongSuTrungCmndBakDTO);
        if (duongSuTrungCmndBakDTO.getId() != null) {
            throw new BadRequestAlertException("A new duongSuTrungCmndBak cannot already have an ID", ENTITY_NAME, "idexists");
        }
        duongSuTrungCmndBakDTO = duongSuTrungCmndBakService.save(duongSuTrungCmndBakDTO);
        return ResponseEntity.created(new URI("/api/duong-su-trung-cmnd-baks/" + duongSuTrungCmndBakDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, duongSuTrungCmndBakDTO.getId().toString()))
            .body(duongSuTrungCmndBakDTO);
    }

    /**
     * {@code PUT  /duong-su-trung-cmnd-baks/:id} : Updates an existing duongSuTrungCmndBak.
     *
     * @param id the id of the duongSuTrungCmndBakDTO to save.
     * @param duongSuTrungCmndBakDTO the duongSuTrungCmndBakDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated duongSuTrungCmndBakDTO,
     * or with status {@code 400 (Bad Request)} if the duongSuTrungCmndBakDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the duongSuTrungCmndBakDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DuongSuTrungCmndBakDTO> updateDuongSuTrungCmndBak(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DuongSuTrungCmndBak : {}, {}", id, duongSuTrungCmndBakDTO);
        if (duongSuTrungCmndBakDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, duongSuTrungCmndBakDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!duongSuTrungCmndBakRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        duongSuTrungCmndBakDTO = duongSuTrungCmndBakService.update(duongSuTrungCmndBakDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, duongSuTrungCmndBakDTO.getId().toString()))
            .body(duongSuTrungCmndBakDTO);
    }

    /**
     * {@code PATCH  /duong-su-trung-cmnd-baks/:id} : Partial updates given fields of an existing duongSuTrungCmndBak, field will ignore if it is null
     *
     * @param id the id of the duongSuTrungCmndBakDTO to save.
     * @param duongSuTrungCmndBakDTO the duongSuTrungCmndBakDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated duongSuTrungCmndBakDTO,
     * or with status {@code 400 (Bad Request)} if the duongSuTrungCmndBakDTO is not valid,
     * or with status {@code 404 (Not Found)} if the duongSuTrungCmndBakDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the duongSuTrungCmndBakDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DuongSuTrungCmndBakDTO> partialUpdateDuongSuTrungCmndBak(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DuongSuTrungCmndBak partially : {}, {}", id, duongSuTrungCmndBakDTO);
        if (duongSuTrungCmndBakDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, duongSuTrungCmndBakDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!duongSuTrungCmndBakRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DuongSuTrungCmndBakDTO> result = duongSuTrungCmndBakService.partialUpdate(duongSuTrungCmndBakDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, duongSuTrungCmndBakDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /duong-su-trung-cmnd-baks} : get all the duongSuTrungCmndBaks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of duongSuTrungCmndBaks in body.
     */
    @GetMapping("")
    public List<DuongSuTrungCmndBakDTO> getAllDuongSuTrungCmndBaks() {
        LOG.debug("REST request to get all DuongSuTrungCmndBaks");
        return duongSuTrungCmndBakService.findAll();
    }

    /**
     * {@code GET  /duong-su-trung-cmnd-baks/:id} : get the "id" duongSuTrungCmndBak.
     *
     * @param id the id of the duongSuTrungCmndBakDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the duongSuTrungCmndBakDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DuongSuTrungCmndBakDTO> getDuongSuTrungCmndBak(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DuongSuTrungCmndBak : {}", id);
        Optional<DuongSuTrungCmndBakDTO> duongSuTrungCmndBakDTO = duongSuTrungCmndBakService.findOne(id);
        return ResponseUtil.wrapOrNotFound(duongSuTrungCmndBakDTO);
    }

    /**
     * {@code DELETE  /duong-su-trung-cmnd-baks/:id} : delete the "id" duongSuTrungCmndBak.
     *
     * @param id the id of the duongSuTrungCmndBakDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDuongSuTrungCmndBak(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DuongSuTrungCmndBak : {}", id);
        duongSuTrungCmndBakService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
