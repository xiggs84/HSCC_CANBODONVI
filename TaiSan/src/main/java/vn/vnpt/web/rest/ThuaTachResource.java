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
import vn.vnpt.repository.ThuaTachRepository;
import vn.vnpt.service.ThuaTachService;
import vn.vnpt.service.dto.ThuaTachDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.ThuaTach}.
 */
@RestController
@RequestMapping("/api/thua-taches")
public class ThuaTachResource {

    private static final Logger LOG = LoggerFactory.getLogger(ThuaTachResource.class);

    private static final String ENTITY_NAME = "taiSanThuaTach";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ThuaTachService thuaTachService;

    private final ThuaTachRepository thuaTachRepository;

    public ThuaTachResource(ThuaTachService thuaTachService, ThuaTachRepository thuaTachRepository) {
        this.thuaTachService = thuaTachService;
        this.thuaTachRepository = thuaTachRepository;
    }

    /**
     * {@code POST  /thua-taches} : Create a new thuaTach.
     *
     * @param thuaTachDTO the thuaTachDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new thuaTachDTO, or with status {@code 400 (Bad Request)} if the thuaTach has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ThuaTachDTO> createThuaTach(@RequestBody ThuaTachDTO thuaTachDTO) throws URISyntaxException {
        LOG.debug("REST request to save ThuaTach : {}", thuaTachDTO);
        if (thuaTachDTO.getIdThuaTach() != null) {
            throw new BadRequestAlertException("A new thuaTach cannot already have an ID", ENTITY_NAME, "idexists");
        }
        thuaTachDTO = thuaTachService.save(thuaTachDTO);
        return ResponseEntity.created(new URI("/api/thua-taches/" + thuaTachDTO.getIdThuaTach()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, thuaTachDTO.getIdThuaTach().toString()))
            .body(thuaTachDTO);
    }

    /**
     * {@code PUT  /thua-taches/:idThuaTach} : Updates an existing thuaTach.
     *
     * @param idThuaTach the id of the thuaTachDTO to save.
     * @param thuaTachDTO the thuaTachDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated thuaTachDTO,
     * or with status {@code 400 (Bad Request)} if the thuaTachDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the thuaTachDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idThuaTach}")
    public ResponseEntity<ThuaTachDTO> updateThuaTach(
        @PathVariable(value = "idThuaTach", required = false) final Long idThuaTach,
        @RequestBody ThuaTachDTO thuaTachDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update ThuaTach : {}, {}", idThuaTach, thuaTachDTO);
        if (thuaTachDTO.getIdThuaTach() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idThuaTach, thuaTachDTO.getIdThuaTach())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!thuaTachRepository.existsById(idThuaTach)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        thuaTachDTO = thuaTachService.update(thuaTachDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, thuaTachDTO.getIdThuaTach().toString()))
            .body(thuaTachDTO);
    }

    /**
     * {@code PATCH  /thua-taches/:idThuaTach} : Partial updates given fields of an existing thuaTach, field will ignore if it is null
     *
     * @param idThuaTach the id of the thuaTachDTO to save.
     * @param thuaTachDTO the thuaTachDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated thuaTachDTO,
     * or with status {@code 400 (Bad Request)} if the thuaTachDTO is not valid,
     * or with status {@code 404 (Not Found)} if the thuaTachDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the thuaTachDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idThuaTach}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ThuaTachDTO> partialUpdateThuaTach(
        @PathVariable(value = "idThuaTach", required = false) final Long idThuaTach,
        @RequestBody ThuaTachDTO thuaTachDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update ThuaTach partially : {}, {}", idThuaTach, thuaTachDTO);
        if (thuaTachDTO.getIdThuaTach() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idThuaTach, thuaTachDTO.getIdThuaTach())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!thuaTachRepository.existsById(idThuaTach)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ThuaTachDTO> result = thuaTachService.partialUpdate(thuaTachDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, thuaTachDTO.getIdThuaTach().toString())
        );
    }

    /**
     * {@code GET  /thua-taches} : get all the thuaTaches.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of thuaTaches in body.
     */
    @GetMapping("")
    public List<ThuaTachDTO> getAllThuaTaches() {
        LOG.debug("REST request to get all ThuaTaches");
        return thuaTachService.findAll();
    }

    /**
     * {@code GET  /thua-taches/:id} : get the "id" thuaTach.
     *
     * @param id the id of the thuaTachDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the thuaTachDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ThuaTachDTO> getThuaTach(@PathVariable("id") Long id) {
        LOG.debug("REST request to get ThuaTach : {}", id);
        Optional<ThuaTachDTO> thuaTachDTO = thuaTachService.findOne(id);
        return ResponseUtil.wrapOrNotFound(thuaTachDTO);
    }

    /**
     * {@code DELETE  /thua-taches/:id} : delete the "id" thuaTach.
     *
     * @param id the id of the thuaTachDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThuaTach(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete ThuaTach : {}", id);
        thuaTachService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
