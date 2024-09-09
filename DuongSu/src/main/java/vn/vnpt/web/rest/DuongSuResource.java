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
import vn.vnpt.repository.DuongSuRepository;
import vn.vnpt.service.DuongSuService;
import vn.vnpt.service.dto.DuongSuDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DuongSu}.
 */
@RestController
@RequestMapping("/api/duong-sus")
public class DuongSuResource {

    private static final Logger LOG = LoggerFactory.getLogger(DuongSuResource.class);

    private static final String ENTITY_NAME = "duongSuDuongSu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DuongSuService duongSuService;

    private final DuongSuRepository duongSuRepository;

    public DuongSuResource(DuongSuService duongSuService, DuongSuRepository duongSuRepository) {
        this.duongSuService = duongSuService;
        this.duongSuRepository = duongSuRepository;
    }

    /**
     * {@code POST  /duong-sus} : Create a new duongSu.
     *
     * @param duongSuDTO the duongSuDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new duongSuDTO, or with status {@code 400 (Bad Request)} if the duongSu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DuongSuDTO> createDuongSu(@Valid @RequestBody DuongSuDTO duongSuDTO) throws URISyntaxException {
        LOG.debug("REST request to save DuongSu : {}", duongSuDTO);
        if (duongSuDTO.getIdDuongSu() != null) {
            throw new BadRequestAlertException("A new duongSu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        duongSuDTO = duongSuService.save(duongSuDTO);
        return ResponseEntity.created(new URI("/api/duong-sus/" + duongSuDTO.getIdDuongSu()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, duongSuDTO.getIdDuongSu().toString()))
            .body(duongSuDTO);
    }

    /**
     * {@code PUT  /duong-sus/:idDuongSu} : Updates an existing duongSu.
     *
     * @param idDuongSu the id of the duongSuDTO to save.
     * @param duongSuDTO the duongSuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated duongSuDTO,
     * or with status {@code 400 (Bad Request)} if the duongSuDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the duongSuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idDuongSu}")
    public ResponseEntity<DuongSuDTO> updateDuongSu(
        @PathVariable(value = "idDuongSu", required = false) final Long idDuongSu,
        @Valid @RequestBody DuongSuDTO duongSuDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DuongSu : {}, {}", idDuongSu, duongSuDTO);
        if (duongSuDTO.getIdDuongSu() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idDuongSu, duongSuDTO.getIdDuongSu())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!duongSuRepository.existsById(idDuongSu)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        duongSuDTO = duongSuService.update(duongSuDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, duongSuDTO.getIdDuongSu().toString()))
            .body(duongSuDTO);
    }

    /**
     * {@code PATCH  /duong-sus/:idDuongSu} : Partial updates given fields of an existing duongSu, field will ignore if it is null
     *
     * @param idDuongSu the id of the duongSuDTO to save.
     * @param duongSuDTO the duongSuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated duongSuDTO,
     * or with status {@code 400 (Bad Request)} if the duongSuDTO is not valid,
     * or with status {@code 404 (Not Found)} if the duongSuDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the duongSuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idDuongSu}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DuongSuDTO> partialUpdateDuongSu(
        @PathVariable(value = "idDuongSu", required = false) final Long idDuongSu,
        @NotNull @RequestBody DuongSuDTO duongSuDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DuongSu partially : {}, {}", idDuongSu, duongSuDTO);
        if (duongSuDTO.getIdDuongSu() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idDuongSu, duongSuDTO.getIdDuongSu())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!duongSuRepository.existsById(idDuongSu)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DuongSuDTO> result = duongSuService.partialUpdate(duongSuDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, duongSuDTO.getIdDuongSu().toString())
        );
    }

    /**
     * {@code GET  /duong-sus} : get all the duongSus.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of duongSus in body.
     */
    @GetMapping("")
    public List<DuongSuDTO> getAllDuongSus() {
        LOG.debug("REST request to get all DuongSus");
        return duongSuService.findAll();
    }

    /**
     * {@code GET  /duong-sus/:id} : get the "id" duongSu.
     *
     * @param id the id of the duongSuDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the duongSuDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DuongSuDTO> getDuongSu(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DuongSu : {}", id);
        Optional<DuongSuDTO> duongSuDTO = duongSuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(duongSuDTO);
    }

    /**
     * {@code DELETE  /duong-sus/:id} : delete the "id" duongSu.
     *
     * @param id the id of the duongSuDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDuongSu(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DuongSu : {}", id);
        duongSuService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/search")
    public ResponseEntity<DuongSuDTO> getDuongSuBySoGiayTo(@RequestParam String soGiayTo) {
        LOG.debug("REST request to get DuongSu by soGiayTo : {}", soGiayTo);
        Optional<DuongSuDTO> duongSuDTO = duongSuService.findBySoGiayTo(soGiayTo);
        return ResponseUtil.wrapOrNotFound(duongSuDTO);
    }
}
