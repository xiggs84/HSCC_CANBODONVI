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
import vn.vnpt.repository.TaiSanRepository;
import vn.vnpt.service.TaiSanQueryService;
import vn.vnpt.service.TaiSanService;
import vn.vnpt.service.criteria.TaiSanCriteria;
import vn.vnpt.service.dto.TaiSanDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.TaiSan}.
 */
@RestController
@RequestMapping("/api/tai-sans")
public class TaiSanResource {

    private static final Logger LOG = LoggerFactory.getLogger(TaiSanResource.class);

    private static final String ENTITY_NAME = "taiSanTaiSan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaiSanService taiSanService;

    private final TaiSanRepository taiSanRepository;

    private final TaiSanQueryService taiSanQueryService;

    public TaiSanResource(TaiSanService taiSanService, TaiSanRepository taiSanRepository, TaiSanQueryService taiSanQueryService) {
        this.taiSanService = taiSanService;
        this.taiSanRepository = taiSanRepository;
        this.taiSanQueryService = taiSanQueryService;
    }

    /**
     * {@code POST  /tai-sans} : Create a new taiSan.
     *
     * @param taiSanDTO the taiSanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taiSanDTO, or with status {@code 400 (Bad Request)} if the taiSan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TaiSanDTO> createTaiSan(@RequestBody TaiSanDTO taiSanDTO) throws URISyntaxException {
        LOG.debug("REST request to save TaiSan : {}", taiSanDTO);
        if (taiSanDTO.getIdTaiSan() != null) {
            throw new BadRequestAlertException("A new taiSan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        taiSanDTO = taiSanService.save(taiSanDTO);
        return ResponseEntity.created(new URI("/api/tai-sans/" + taiSanDTO.getIdTaiSan()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, taiSanDTO.getIdTaiSan().toString()))
            .body(taiSanDTO);
    }

    /**
     * {@code PUT  /tai-sans/:idTaiSan} : Updates an existing taiSan.
     *
     * @param idTaiSan the id of the taiSanDTO to save.
     * @param taiSanDTO the taiSanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taiSanDTO,
     * or with status {@code 400 (Bad Request)} if the taiSanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taiSanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idTaiSan}")
    public ResponseEntity<TaiSanDTO> updateTaiSan(
        @PathVariable(value = "idTaiSan", required = false) final Long idTaiSan,
        @RequestBody TaiSanDTO taiSanDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update TaiSan : {}, {}", idTaiSan, taiSanDTO);
        if (taiSanDTO.getIdTaiSan() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idTaiSan, taiSanDTO.getIdTaiSan())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taiSanRepository.existsById(idTaiSan)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        taiSanDTO = taiSanService.update(taiSanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taiSanDTO.getIdTaiSan().toString()))
            .body(taiSanDTO);
    }

    /**
     * {@code PATCH  /tai-sans/:idTaiSan} : Partial updates given fields of an existing taiSan, field will ignore if it is null
     *
     * @param idTaiSan the id of the taiSanDTO to save.
     * @param taiSanDTO the taiSanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taiSanDTO,
     * or with status {@code 400 (Bad Request)} if the taiSanDTO is not valid,
     * or with status {@code 404 (Not Found)} if the taiSanDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the taiSanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idTaiSan}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TaiSanDTO> partialUpdateTaiSan(
        @PathVariable(value = "idTaiSan", required = false) final Long idTaiSan,
        @RequestBody TaiSanDTO taiSanDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update TaiSan partially : {}, {}", idTaiSan, taiSanDTO);
        if (taiSanDTO.getIdTaiSan() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idTaiSan, taiSanDTO.getIdTaiSan())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taiSanRepository.existsById(idTaiSan)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TaiSanDTO> result = taiSanService.partialUpdate(taiSanDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taiSanDTO.getIdTaiSan().toString())
        );
    }

    /**
     * {@code GET  /tai-sans} : get all the taiSans.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taiSans in body.
     */
    @GetMapping("")
    public ResponseEntity<List<TaiSanDTO>> getAllTaiSans(
        TaiSanCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get TaiSans by criteria: {}", criteria);

        Page<TaiSanDTO> page = taiSanQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tai-sans/count} : count all the taiSans.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countTaiSans(TaiSanCriteria criteria) {
        LOG.debug("REST request to count TaiSans by criteria: {}", criteria);
        return ResponseEntity.ok().body(taiSanQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /tai-sans/:id} : get the "id" taiSan.
     *
     * @param id the id of the taiSanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taiSanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaiSanDTO> getTaiSan(@PathVariable("id") Long id) {
        LOG.debug("REST request to get TaiSan : {}", id);
        Optional<TaiSanDTO> taiSanDTO = taiSanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taiSanDTO);
    }

    /**
     * {@code DELETE  /tai-sans/:id} : delete the "id" taiSan.
     *
     * @param id the id of the taiSanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaiSan(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete TaiSan : {}", id);
        taiSanService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
