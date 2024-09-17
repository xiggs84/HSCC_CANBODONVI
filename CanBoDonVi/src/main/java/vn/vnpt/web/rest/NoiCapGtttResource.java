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
import vn.vnpt.repository.NoiCapGtttRepository;
import vn.vnpt.service.NoiCapGtttService;
import vn.vnpt.service.dto.NoiCapGtttDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.NoiCapGttt}.
 */
@RestController
@RequestMapping("/api/noi-cap-gttts")
public class NoiCapGtttResource {

    private static final Logger LOG = LoggerFactory.getLogger(NoiCapGtttResource.class);

    private static final String ENTITY_NAME = "canBoDonViNoiCapGttt";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NoiCapGtttService noiCapGtttService;

    private final NoiCapGtttRepository noiCapGtttRepository;

    public NoiCapGtttResource(NoiCapGtttService noiCapGtttService, NoiCapGtttRepository noiCapGtttRepository) {
        this.noiCapGtttService = noiCapGtttService;
        this.noiCapGtttRepository = noiCapGtttRepository;
    }

    /**
     * {@code POST  /noi-cap-gttts} : Create a new noiCapGttt.
     *
     * @param noiCapGtttDTO the noiCapGtttDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new noiCapGtttDTO, or with status {@code 400 (Bad Request)} if the noiCapGttt has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<NoiCapGtttDTO> createNoiCapGttt(@RequestBody NoiCapGtttDTO noiCapGtttDTO) throws URISyntaxException {
        LOG.debug("REST request to save NoiCapGttt : {}", noiCapGtttDTO);
        if (noiCapGtttDTO.getIdNoiCap() != null) {
            throw new BadRequestAlertException("A new noiCapGttt cannot already have an ID", ENTITY_NAME, "idexists");
        }
        noiCapGtttDTO = noiCapGtttService.save(noiCapGtttDTO);
        return ResponseEntity.created(new URI("/api/noi-cap-gttts/" + noiCapGtttDTO.getIdNoiCap()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, noiCapGtttDTO.getIdNoiCap().toString()))
            .body(noiCapGtttDTO);
    }

    /**
     * {@code PUT  /noi-cap-gttts/:idNoiCap} : Updates an existing noiCapGttt.
     *
     * @param idNoiCap the id of the noiCapGtttDTO to save.
     * @param noiCapGtttDTO the noiCapGtttDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated noiCapGtttDTO,
     * or with status {@code 400 (Bad Request)} if the noiCapGtttDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the noiCapGtttDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idNoiCap}")
    public ResponseEntity<NoiCapGtttDTO> updateNoiCapGttt(
        @PathVariable(value = "idNoiCap", required = false) final Long idNoiCap,
        @RequestBody NoiCapGtttDTO noiCapGtttDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update NoiCapGttt : {}, {}", idNoiCap, noiCapGtttDTO);
        if (noiCapGtttDTO.getIdNoiCap() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idNoiCap, noiCapGtttDTO.getIdNoiCap())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!noiCapGtttRepository.existsById(idNoiCap)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        noiCapGtttDTO = noiCapGtttService.update(noiCapGtttDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, noiCapGtttDTO.getIdNoiCap().toString()))
            .body(noiCapGtttDTO);
    }

    /**
     * {@code PATCH  /noi-cap-gttts/:idNoiCap} : Partial updates given fields of an existing noiCapGttt, field will ignore if it is null
     *
     * @param idNoiCap the id of the noiCapGtttDTO to save.
     * @param noiCapGtttDTO the noiCapGtttDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated noiCapGtttDTO,
     * or with status {@code 400 (Bad Request)} if the noiCapGtttDTO is not valid,
     * or with status {@code 404 (Not Found)} if the noiCapGtttDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the noiCapGtttDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idNoiCap}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NoiCapGtttDTO> partialUpdateNoiCapGttt(
        @PathVariable(value = "idNoiCap", required = false) final Long idNoiCap,
        @RequestBody NoiCapGtttDTO noiCapGtttDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update NoiCapGttt partially : {}, {}", idNoiCap, noiCapGtttDTO);
        if (noiCapGtttDTO.getIdNoiCap() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idNoiCap, noiCapGtttDTO.getIdNoiCap())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!noiCapGtttRepository.existsById(idNoiCap)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NoiCapGtttDTO> result = noiCapGtttService.partialUpdate(noiCapGtttDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, noiCapGtttDTO.getIdNoiCap().toString())
        );
    }

    /**
     * {@code GET  /noi-cap-gttts} : get all the noiCapGttts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of noiCapGttts in body.
     */
    @GetMapping("")
    public List<NoiCapGtttDTO> getAllNoiCapGttts() {
        LOG.debug("REST request to get all NoiCapGttts");
        return noiCapGtttService.findAll();
    }

    /**
     * {@code GET  /noi-cap-gttts/:id} : get the "id" noiCapGttt.
     *
     * @param id the id of the noiCapGtttDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the noiCapGtttDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<NoiCapGtttDTO> getNoiCapGttt(@PathVariable("id") Long id) {
        LOG.debug("REST request to get NoiCapGttt : {}", id);
        Optional<NoiCapGtttDTO> noiCapGtttDTO = noiCapGtttService.findOne(id);
        return ResponseUtil.wrapOrNotFound(noiCapGtttDTO);
    }

    /**
     * {@code DELETE  /noi-cap-gttts/:id} : delete the "id" noiCapGttt.
     *
     * @param id the id of the noiCapGtttDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoiCapGttt(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete NoiCapGttt : {}", id);
        noiCapGtttService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
