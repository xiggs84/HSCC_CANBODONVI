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
import vn.vnpt.repository.ChiTietNganChanRepository;
import vn.vnpt.service.ChiTietNganChanQueryService;
import vn.vnpt.service.ChiTietNganChanService;
import vn.vnpt.service.criteria.ChiTietNganChanCriteria;
import vn.vnpt.service.dto.ChiTietNganChanDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.ChiTietNganChan}.
 */
@RestController
@RequestMapping("/api/chi-tiet-ngan-chans")
public class ChiTietNganChanResource {

    private static final Logger LOG = LoggerFactory.getLogger(ChiTietNganChanResource.class);

    private static final String ENTITY_NAME = "taiSanChiTietNganChan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChiTietNganChanService chiTietNganChanService;

    private final ChiTietNganChanRepository chiTietNganChanRepository;

    private final ChiTietNganChanQueryService chiTietNganChanQueryService;

    public ChiTietNganChanResource(
        ChiTietNganChanService chiTietNganChanService,
        ChiTietNganChanRepository chiTietNganChanRepository,
        ChiTietNganChanQueryService chiTietNganChanQueryService
    ) {
        this.chiTietNganChanService = chiTietNganChanService;
        this.chiTietNganChanRepository = chiTietNganChanRepository;
        this.chiTietNganChanQueryService = chiTietNganChanQueryService;
    }

    /**
     * {@code POST  /chi-tiet-ngan-chans} : Create a new chiTietNganChan.
     *
     * @param chiTietNganChanDTO the chiTietNganChanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chiTietNganChanDTO, or with status {@code 400 (Bad Request)} if the chiTietNganChan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ChiTietNganChanDTO> createChiTietNganChan(@RequestBody ChiTietNganChanDTO chiTietNganChanDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save ChiTietNganChan : {}", chiTietNganChanDTO);
        if (chiTietNganChanDTO.getId() != null) {
            throw new BadRequestAlertException("A new chiTietNganChan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        chiTietNganChanDTO = chiTietNganChanService.save(chiTietNganChanDTO);
        return ResponseEntity.created(new URI("/api/chi-tiet-ngan-chans/" + chiTietNganChanDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, chiTietNganChanDTO.getId().toString()))
            .body(chiTietNganChanDTO);
    }

    /**
     * {@code PUT  /chi-tiet-ngan-chans/:id} : Updates an existing chiTietNganChan.
     *
     * @param id the id of the chiTietNganChanDTO to save.
     * @param chiTietNganChanDTO the chiTietNganChanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chiTietNganChanDTO,
     * or with status {@code 400 (Bad Request)} if the chiTietNganChanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chiTietNganChanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ChiTietNganChanDTO> updateChiTietNganChan(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ChiTietNganChanDTO chiTietNganChanDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update ChiTietNganChan : {}, {}", id, chiTietNganChanDTO);
        if (chiTietNganChanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chiTietNganChanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chiTietNganChanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        chiTietNganChanDTO = chiTietNganChanService.update(chiTietNganChanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chiTietNganChanDTO.getId().toString()))
            .body(chiTietNganChanDTO);
    }

    /**
     * {@code PATCH  /chi-tiet-ngan-chans/:id} : Partial updates given fields of an existing chiTietNganChan, field will ignore if it is null
     *
     * @param id the id of the chiTietNganChanDTO to save.
     * @param chiTietNganChanDTO the chiTietNganChanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chiTietNganChanDTO,
     * or with status {@code 400 (Bad Request)} if the chiTietNganChanDTO is not valid,
     * or with status {@code 404 (Not Found)} if the chiTietNganChanDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the chiTietNganChanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ChiTietNganChanDTO> partialUpdateChiTietNganChan(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ChiTietNganChanDTO chiTietNganChanDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update ChiTietNganChan partially : {}, {}", id, chiTietNganChanDTO);
        if (chiTietNganChanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chiTietNganChanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chiTietNganChanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ChiTietNganChanDTO> result = chiTietNganChanService.partialUpdate(chiTietNganChanDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chiTietNganChanDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /chi-tiet-ngan-chans} : get all the chiTietNganChans.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chiTietNganChans in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ChiTietNganChanDTO>> getAllChiTietNganChans(ChiTietNganChanCriteria criteria) {
        LOG.debug("REST request to get ChiTietNganChans by criteria: {}", criteria);

        List<ChiTietNganChanDTO> entityList = chiTietNganChanQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /chi-tiet-ngan-chans/count} : count all the chiTietNganChans.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countChiTietNganChans(ChiTietNganChanCriteria criteria) {
        LOG.debug("REST request to count ChiTietNganChans by criteria: {}", criteria);
        return ResponseEntity.ok().body(chiTietNganChanQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /chi-tiet-ngan-chans/:id} : get the "id" chiTietNganChan.
     *
     * @param id the id of the chiTietNganChanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chiTietNganChanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ChiTietNganChanDTO> getChiTietNganChan(@PathVariable("id") Long id) {
        LOG.debug("REST request to get ChiTietNganChan : {}", id);
        Optional<ChiTietNganChanDTO> chiTietNganChanDTO = chiTietNganChanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chiTietNganChanDTO);
    }

    /**
     * {@code DELETE  /chi-tiet-ngan-chans/:id} : delete the "id" chiTietNganChan.
     *
     * @param id the id of the chiTietNganChanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChiTietNganChan(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete ChiTietNganChan : {}", id);
        chiTietNganChanService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
