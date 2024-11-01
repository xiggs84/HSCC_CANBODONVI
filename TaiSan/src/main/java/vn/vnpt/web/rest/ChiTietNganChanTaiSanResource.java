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
import vn.vnpt.repository.ChiTietNganChanTaiSanRepository;
import vn.vnpt.service.ChiTietNganChanTaiSanQueryService;
import vn.vnpt.service.ChiTietNganChanTaiSanService;
import vn.vnpt.service.criteria.ChiTietNganChanTaiSanCriteria;
import vn.vnpt.service.dto.ChiTietNganChanTaiSanDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.ChiTietNganChanTaiSan}.
 */
@RestController
@RequestMapping("/api/chi-tiet-ngan-chan-tai-sans")
public class ChiTietNganChanTaiSanResource {

    private static final Logger LOG = LoggerFactory.getLogger(ChiTietNganChanTaiSanResource.class);

    private static final String ENTITY_NAME = "taiSanChiTietNganChanTaiSan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChiTietNganChanTaiSanService chiTietNganChanTaiSanService;

    private final ChiTietNganChanTaiSanRepository chiTietNganChanTaiSanRepository;

    private final ChiTietNganChanTaiSanQueryService chiTietNganChanTaiSanQueryService;

    public ChiTietNganChanTaiSanResource(
        ChiTietNganChanTaiSanService chiTietNganChanTaiSanService,
        ChiTietNganChanTaiSanRepository chiTietNganChanTaiSanRepository,
        ChiTietNganChanTaiSanQueryService chiTietNganChanTaiSanQueryService
    ) {
        this.chiTietNganChanTaiSanService = chiTietNganChanTaiSanService;
        this.chiTietNganChanTaiSanRepository = chiTietNganChanTaiSanRepository;
        this.chiTietNganChanTaiSanQueryService = chiTietNganChanTaiSanQueryService;
    }

    /**
     * {@code POST  /chi-tiet-ngan-chan-tai-sans} : Create a new chiTietNganChanTaiSan.
     *
     * @param chiTietNganChanTaiSanDTO the chiTietNganChanTaiSanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chiTietNganChanTaiSanDTO, or with status {@code 400 (Bad Request)} if the chiTietNganChanTaiSan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ChiTietNganChanTaiSanDTO> createChiTietNganChanTaiSan(
        @RequestBody ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save ChiTietNganChanTaiSan : {}", chiTietNganChanTaiSanDTO);
        if (chiTietNganChanTaiSanDTO.getIdNganChan() != null) {
            throw new BadRequestAlertException("A new chiTietNganChanTaiSan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        chiTietNganChanTaiSanDTO = chiTietNganChanTaiSanService.save(chiTietNganChanTaiSanDTO);
        return ResponseEntity.created(new URI("/api/chi-tiet-ngan-chan-tai-sans/" + chiTietNganChanTaiSanDTO.getIdNganChan()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    chiTietNganChanTaiSanDTO.getIdNganChan().toString()
                )
            )
            .body(chiTietNganChanTaiSanDTO);
    }

    /**
     * {@code PUT  /chi-tiet-ngan-chan-tai-sans/:idNganChan} : Updates an existing chiTietNganChanTaiSan.
     *
     * @param idNganChan the id of the chiTietNganChanTaiSanDTO to save.
     * @param chiTietNganChanTaiSanDTO the chiTietNganChanTaiSanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chiTietNganChanTaiSanDTO,
     * or with status {@code 400 (Bad Request)} if the chiTietNganChanTaiSanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chiTietNganChanTaiSanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idNganChan}")
    public ResponseEntity<ChiTietNganChanTaiSanDTO> updateChiTietNganChanTaiSan(
        @PathVariable(value = "idNganChan", required = false) final Long idNganChan,
        @RequestBody ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update ChiTietNganChanTaiSan : {}, {}", idNganChan, chiTietNganChanTaiSanDTO);
        if (chiTietNganChanTaiSanDTO.getIdNganChan() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idNganChan, chiTietNganChanTaiSanDTO.getIdNganChan())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chiTietNganChanTaiSanRepository.existsById(idNganChan)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        chiTietNganChanTaiSanDTO = chiTietNganChanTaiSanService.update(chiTietNganChanTaiSanDTO);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chiTietNganChanTaiSanDTO.getIdNganChan().toString())
            )
            .body(chiTietNganChanTaiSanDTO);
    }

    /**
     * {@code PATCH  /chi-tiet-ngan-chan-tai-sans/:idNganChan} : Partial updates given fields of an existing chiTietNganChanTaiSan, field will ignore if it is null
     *
     * @param idNganChan the id of the chiTietNganChanTaiSanDTO to save.
     * @param chiTietNganChanTaiSanDTO the chiTietNganChanTaiSanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chiTietNganChanTaiSanDTO,
     * or with status {@code 400 (Bad Request)} if the chiTietNganChanTaiSanDTO is not valid,
     * or with status {@code 404 (Not Found)} if the chiTietNganChanTaiSanDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the chiTietNganChanTaiSanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idNganChan}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ChiTietNganChanTaiSanDTO> partialUpdateChiTietNganChanTaiSan(
        @PathVariable(value = "idNganChan", required = false) final Long idNganChan,
        @RequestBody ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update ChiTietNganChanTaiSan partially : {}, {}", idNganChan, chiTietNganChanTaiSanDTO);
        if (chiTietNganChanTaiSanDTO.getIdNganChan() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idNganChan, chiTietNganChanTaiSanDTO.getIdNganChan())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chiTietNganChanTaiSanRepository.existsById(idNganChan)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ChiTietNganChanTaiSanDTO> result = chiTietNganChanTaiSanService.partialUpdate(chiTietNganChanTaiSanDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chiTietNganChanTaiSanDTO.getIdNganChan().toString())
        );
    }

    /**
     * {@code GET  /chi-tiet-ngan-chan-tai-sans} : get all the chiTietNganChanTaiSans.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chiTietNganChanTaiSans in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ChiTietNganChanTaiSanDTO>> getAllChiTietNganChanTaiSans(ChiTietNganChanTaiSanCriteria criteria) {
        LOG.debug("REST request to get ChiTietNganChanTaiSans by criteria: {}", criteria);

        List<ChiTietNganChanTaiSanDTO> entityList = chiTietNganChanTaiSanQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /chi-tiet-ngan-chan-tai-sans/count} : count all the chiTietNganChanTaiSans.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countChiTietNganChanTaiSans(ChiTietNganChanTaiSanCriteria criteria) {
        LOG.debug("REST request to count ChiTietNganChanTaiSans by criteria: {}", criteria);
        return ResponseEntity.ok().body(chiTietNganChanTaiSanQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /chi-tiet-ngan-chan-tai-sans/:id} : get the "id" chiTietNganChanTaiSan.
     *
     * @param id the id of the chiTietNganChanTaiSanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chiTietNganChanTaiSanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ChiTietNganChanTaiSanDTO> getChiTietNganChanTaiSan(@PathVariable("id") Long id) {
        LOG.debug("REST request to get ChiTietNganChanTaiSan : {}", id);
        Optional<ChiTietNganChanTaiSanDTO> chiTietNganChanTaiSanDTO = chiTietNganChanTaiSanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chiTietNganChanTaiSanDTO);
    }

    /**
     * {@code DELETE  /chi-tiet-ngan-chan-tai-sans/:id} : delete the "id" chiTietNganChanTaiSan.
     *
     * @param id the id of the chiTietNganChanTaiSanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChiTietNganChanTaiSan(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete ChiTietNganChanTaiSan : {}", id);
        chiTietNganChanTaiSanService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
