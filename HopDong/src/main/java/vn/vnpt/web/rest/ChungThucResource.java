package vn.vnpt.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import vn.vnpt.repository.ChungThucRepository;
import vn.vnpt.service.ChungThucService;
import vn.vnpt.service.dto.ChungThucDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.ChungThuc}.
 */
@RestController
@RequestMapping("/api/chung-thucs")
public class ChungThucResource {

    private static final Logger LOG = LoggerFactory.getLogger(ChungThucResource.class);

    private static final String ENTITY_NAME = "hopDongChungThuc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChungThucService chungThucService;

    private final ChungThucRepository chungThucRepository;

    public ChungThucResource(ChungThucService chungThucService, ChungThucRepository chungThucRepository) {
        this.chungThucService = chungThucService;
        this.chungThucRepository = chungThucRepository;
    }

    /**
     * {@code POST  /chung-thucs} : Create a new chungThuc.
     *
     * @param chungThucDTO the chungThucDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chungThucDTO, or with status {@code 400 (Bad Request)} if the chungThuc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ChungThucDTO> createChungThuc(@RequestBody ChungThucDTO chungThucDTO) throws URISyntaxException {
        LOG.debug("REST request to save ChungThuc : {}", chungThucDTO);
        if (chungThucDTO.getId() != null) {
            throw new BadRequestAlertException("A new chungThuc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        chungThucDTO = chungThucService.save(chungThucDTO);
        return ResponseEntity.created(new URI("/api/chung-thucs/" + chungThucDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, chungThucDTO.getId()))
            .body(chungThucDTO);
    }

    /**
     * {@code PUT  /chung-thucs/:id} : Updates an existing chungThuc.
     *
     * @param id the id of the chungThucDTO to save.
     * @param chungThucDTO the chungThucDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chungThucDTO,
     * or with status {@code 400 (Bad Request)} if the chungThucDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chungThucDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ChungThucDTO> updateChungThuc(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ChungThucDTO chungThucDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update ChungThuc : {}, {}", id, chungThucDTO);
        if (chungThucDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chungThucDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chungThucRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        chungThucDTO = chungThucService.update(chungThucDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chungThucDTO.getId()))
            .body(chungThucDTO);
    }

    /**
     * {@code PATCH  /chung-thucs/:id} : Partial updates given fields of an existing chungThuc, field will ignore if it is null
     *
     * @param id the id of the chungThucDTO to save.
     * @param chungThucDTO the chungThucDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chungThucDTO,
     * or with status {@code 400 (Bad Request)} if the chungThucDTO is not valid,
     * or with status {@code 404 (Not Found)} if the chungThucDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the chungThucDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ChungThucDTO> partialUpdateChungThuc(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ChungThucDTO chungThucDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update ChungThuc partially : {}, {}", id, chungThucDTO);
        if (chungThucDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chungThucDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chungThucRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ChungThucDTO> result = chungThucService.partialUpdate(chungThucDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chungThucDTO.getId())
        );
    }

    /**
     * {@code GET  /chung-thucs} : get all the chungThucs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chungThucs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ChungThucDTO>> getAllChungThucs(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of ChungThucs");
        Page<ChungThucDTO> page = chungThucService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /chung-thucs/:id} : get the "id" chungThuc.
     *
     * @param id the id of the chungThucDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chungThucDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ChungThucDTO> getChungThuc(@PathVariable("id") String id) {
        LOG.debug("REST request to get ChungThuc : {}", id);
        Optional<ChungThucDTO> chungThucDTO = chungThucService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chungThucDTO);
    }

    /**
     * {@code DELETE  /chung-thucs/:id} : delete the "id" chungThuc.
     *
     * @param id the id of the chungThucDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChungThuc(@PathVariable("id") String id) {
        LOG.debug("REST request to delete ChungThuc : {}", id);
        chungThucService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
