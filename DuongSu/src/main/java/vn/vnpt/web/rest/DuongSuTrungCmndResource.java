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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import vn.vnpt.repository.DuongSuTrungCmndRepository;
import vn.vnpt.service.DuongSuTrungCmndQueryService;
import vn.vnpt.service.DuongSuTrungCmndService;
import vn.vnpt.service.criteria.DuongSuTrungCmndCriteria;
import vn.vnpt.service.dto.DuongSuTrungCmndDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DuongSuTrungCmnd}.
 */
@RestController
@RequestMapping("/api/duong-su-trung-cmnds")
public class DuongSuTrungCmndResource {

    private static final Logger LOG = LoggerFactory.getLogger(DuongSuTrungCmndResource.class);

    private static final String ENTITY_NAME = "duongSuDuongSuTrungCmnd";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DuongSuTrungCmndService duongSuTrungCmndService;

    private final DuongSuTrungCmndRepository duongSuTrungCmndRepository;

    private final DuongSuTrungCmndQueryService duongSuTrungCmndQueryService;

    public DuongSuTrungCmndResource(
        DuongSuTrungCmndService duongSuTrungCmndService,
        DuongSuTrungCmndRepository duongSuTrungCmndRepository,
        DuongSuTrungCmndQueryService duongSuTrungCmndQueryService
    ) {
        this.duongSuTrungCmndService = duongSuTrungCmndService;
        this.duongSuTrungCmndRepository = duongSuTrungCmndRepository;
        this.duongSuTrungCmndQueryService = duongSuTrungCmndQueryService;
    }

    /**
     * {@code POST  /duong-su-trung-cmnds} : Create a new duongSuTrungCmnd.
     *
     * @param duongSuTrungCmndDTO the duongSuTrungCmndDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new duongSuTrungCmndDTO, or with status {@code 400 (Bad Request)} if the duongSuTrungCmnd has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DuongSuTrungCmndDTO> createDuongSuTrungCmnd(@Valid @RequestBody DuongSuTrungCmndDTO duongSuTrungCmndDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save DuongSuTrungCmnd : {}", duongSuTrungCmndDTO);
        if (duongSuTrungCmndDTO.getId() != null) {
            throw new BadRequestAlertException("A new duongSuTrungCmnd cannot already have an ID", ENTITY_NAME, "idexists");
        }
        duongSuTrungCmndDTO = duongSuTrungCmndService.save(duongSuTrungCmndDTO);
        return ResponseEntity.created(new URI("/api/duong-su-trung-cmnds/" + duongSuTrungCmndDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, duongSuTrungCmndDTO.getId().toString()))
            .body(duongSuTrungCmndDTO);
    }

    /**
     * {@code PUT  /duong-su-trung-cmnds/:id} : Updates an existing duongSuTrungCmnd.
     *
     * @param id the id of the duongSuTrungCmndDTO to save.
     * @param duongSuTrungCmndDTO the duongSuTrungCmndDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated duongSuTrungCmndDTO,
     * or with status {@code 400 (Bad Request)} if the duongSuTrungCmndDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the duongSuTrungCmndDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DuongSuTrungCmndDTO> updateDuongSuTrungCmnd(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DuongSuTrungCmndDTO duongSuTrungCmndDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DuongSuTrungCmnd : {}, {}", id, duongSuTrungCmndDTO);
        if (duongSuTrungCmndDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, duongSuTrungCmndDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!duongSuTrungCmndRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        duongSuTrungCmndDTO = duongSuTrungCmndService.update(duongSuTrungCmndDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, duongSuTrungCmndDTO.getId().toString()))
            .body(duongSuTrungCmndDTO);
    }

    /**
     * {@code PATCH  /duong-su-trung-cmnds/:id} : Partial updates given fields of an existing duongSuTrungCmnd, field will ignore if it is null
     *
     * @param id the id of the duongSuTrungCmndDTO to save.
     * @param duongSuTrungCmndDTO the duongSuTrungCmndDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated duongSuTrungCmndDTO,
     * or with status {@code 400 (Bad Request)} if the duongSuTrungCmndDTO is not valid,
     * or with status {@code 404 (Not Found)} if the duongSuTrungCmndDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the duongSuTrungCmndDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DuongSuTrungCmndDTO> partialUpdateDuongSuTrungCmnd(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DuongSuTrungCmndDTO duongSuTrungCmndDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DuongSuTrungCmnd partially : {}, {}", id, duongSuTrungCmndDTO);
        if (duongSuTrungCmndDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, duongSuTrungCmndDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!duongSuTrungCmndRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DuongSuTrungCmndDTO> result = duongSuTrungCmndService.partialUpdate(duongSuTrungCmndDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, duongSuTrungCmndDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /duong-su-trung-cmnds} : get all the duongSuTrungCmnds.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of duongSuTrungCmnds in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DuongSuTrungCmndDTO>> getAllDuongSuTrungCmnds(
        DuongSuTrungCmndCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get DuongSuTrungCmnds by criteria: {}", criteria);

        Page<DuongSuTrungCmndDTO> page = duongSuTrungCmndQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /duong-su-trung-cmnds/count} : count all the duongSuTrungCmnds.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDuongSuTrungCmnds(DuongSuTrungCmndCriteria criteria) {
        LOG.debug("REST request to count DuongSuTrungCmnds by criteria: {}", criteria);
        return ResponseEntity.ok().body(duongSuTrungCmndQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /duong-su-trung-cmnds/:id} : get the "id" duongSuTrungCmnd.
     *
     * @param id the id of the duongSuTrungCmndDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the duongSuTrungCmndDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DuongSuTrungCmndDTO> getDuongSuTrungCmnd(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DuongSuTrungCmnd : {}", id);
        Optional<DuongSuTrungCmndDTO> duongSuTrungCmndDTO = duongSuTrungCmndService.findOne(id);
        return ResponseUtil.wrapOrNotFound(duongSuTrungCmndDTO);
    }

    /**
     * {@code DELETE  /duong-su-trung-cmnds/:id} : delete the "id" duongSuTrungCmnd.
     *
     * @param id the id of the duongSuTrungCmndDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDuongSuTrungCmnd(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DuongSuTrungCmnd : {}", id);
        duongSuTrungCmndService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
