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
import vn.vnpt.repository.TaiSanDuongSuRepository;
import vn.vnpt.service.TaiSanDuongSuQueryService;
import vn.vnpt.service.TaiSanDuongSuService;
import vn.vnpt.service.criteria.TaiSanDuongSuCriteria;
import vn.vnpt.service.dto.TaiSanDuongSuDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.TaiSanDuongSu}.
 */
@RestController
@RequestMapping("/api/tai-san-duong-sus")
public class TaiSanDuongSuResource {

    private static final Logger LOG = LoggerFactory.getLogger(TaiSanDuongSuResource.class);

    private static final String ENTITY_NAME = "duongSuTaiSanDuongSu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaiSanDuongSuService taiSanDuongSuService;

    private final TaiSanDuongSuRepository taiSanDuongSuRepository;

    private final TaiSanDuongSuQueryService taiSanDuongSuQueryService;

    public TaiSanDuongSuResource(
        TaiSanDuongSuService taiSanDuongSuService,
        TaiSanDuongSuRepository taiSanDuongSuRepository,
        TaiSanDuongSuQueryService taiSanDuongSuQueryService
    ) {
        this.taiSanDuongSuService = taiSanDuongSuService;
        this.taiSanDuongSuRepository = taiSanDuongSuRepository;
        this.taiSanDuongSuQueryService = taiSanDuongSuQueryService;
    }

    /**
     * {@code POST  /tai-san-duong-sus} : Create a new taiSanDuongSu.
     *
     * @param taiSanDuongSuDTO the taiSanDuongSuDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taiSanDuongSuDTO, or with status {@code 400 (Bad Request)} if the taiSanDuongSu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TaiSanDuongSuDTO> createTaiSanDuongSu(@Valid @RequestBody TaiSanDuongSuDTO taiSanDuongSuDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save TaiSanDuongSu : {}", taiSanDuongSuDTO);
        if (taiSanDuongSuDTO.getId() != null) {
            throw new BadRequestAlertException("A new taiSanDuongSu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        taiSanDuongSuDTO = taiSanDuongSuService.save(taiSanDuongSuDTO);
        return ResponseEntity.created(new URI("/api/tai-san-duong-sus/" + taiSanDuongSuDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, taiSanDuongSuDTO.getId().toString()))
            .body(taiSanDuongSuDTO);
    }

    /**
     * {@code PUT  /tai-san-duong-sus/:id} : Updates an existing taiSanDuongSu.
     *
     * @param id the id of the taiSanDuongSuDTO to save.
     * @param taiSanDuongSuDTO the taiSanDuongSuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taiSanDuongSuDTO,
     * or with status {@code 400 (Bad Request)} if the taiSanDuongSuDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taiSanDuongSuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TaiSanDuongSuDTO> updateTaiSanDuongSu(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TaiSanDuongSuDTO taiSanDuongSuDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update TaiSanDuongSu : {}, {}", id, taiSanDuongSuDTO);
        if (taiSanDuongSuDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taiSanDuongSuDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taiSanDuongSuRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        taiSanDuongSuDTO = taiSanDuongSuService.update(taiSanDuongSuDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taiSanDuongSuDTO.getId().toString()))
            .body(taiSanDuongSuDTO);
    }

    /**
     * {@code PATCH  /tai-san-duong-sus/:id} : Partial updates given fields of an existing taiSanDuongSu, field will ignore if it is null
     *
     * @param id the id of the taiSanDuongSuDTO to save.
     * @param taiSanDuongSuDTO the taiSanDuongSuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taiSanDuongSuDTO,
     * or with status {@code 400 (Bad Request)} if the taiSanDuongSuDTO is not valid,
     * or with status {@code 404 (Not Found)} if the taiSanDuongSuDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the taiSanDuongSuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TaiSanDuongSuDTO> partialUpdateTaiSanDuongSu(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TaiSanDuongSuDTO taiSanDuongSuDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update TaiSanDuongSu partially : {}, {}", id, taiSanDuongSuDTO);
        if (taiSanDuongSuDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taiSanDuongSuDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taiSanDuongSuRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TaiSanDuongSuDTO> result = taiSanDuongSuService.partialUpdate(taiSanDuongSuDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taiSanDuongSuDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tai-san-duong-sus} : get all the taiSanDuongSus.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taiSanDuongSus in body.
     */
    @GetMapping("")
    public ResponseEntity<List<TaiSanDuongSuDTO>> getAllTaiSanDuongSus(
        TaiSanDuongSuCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get TaiSanDuongSus by criteria: {}", criteria);

        Page<TaiSanDuongSuDTO> page = taiSanDuongSuQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tai-san-duong-sus/count} : count all the taiSanDuongSus.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countTaiSanDuongSus(TaiSanDuongSuCriteria criteria) {
        LOG.debug("REST request to count TaiSanDuongSus by criteria: {}", criteria);
        return ResponseEntity.ok().body(taiSanDuongSuQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /tai-san-duong-sus/:id} : get the "id" taiSanDuongSu.
     *
     * @param id the id of the taiSanDuongSuDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taiSanDuongSuDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaiSanDuongSuDTO> getTaiSanDuongSu(@PathVariable("id") Long id) {
        LOG.debug("REST request to get TaiSanDuongSu : {}", id);
        Optional<TaiSanDuongSuDTO> taiSanDuongSuDTO = taiSanDuongSuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taiSanDuongSuDTO);
    }

    /**
     * {@code DELETE  /tai-san-duong-sus/:id} : delete the "id" taiSanDuongSu.
     *
     * @param id the id of the taiSanDuongSuDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaiSanDuongSu(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete TaiSanDuongSu : {}", id);
        taiSanDuongSuService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
