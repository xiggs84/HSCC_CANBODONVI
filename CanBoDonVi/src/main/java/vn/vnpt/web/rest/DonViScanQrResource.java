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
import vn.vnpt.repository.DonViScanQrRepository;
import vn.vnpt.service.DonViScanQrQueryService;
import vn.vnpt.service.DonViScanQrService;
import vn.vnpt.service.criteria.DonViScanQrCriteria;
import vn.vnpt.service.dto.DonViScanQrDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DonViScanQr}.
 */
@RestController
@RequestMapping("/api/don-vi-scan-qrs")
public class DonViScanQrResource {

    private static final Logger LOG = LoggerFactory.getLogger(DonViScanQrResource.class);

    private static final String ENTITY_NAME = "canBoDonViDonViScanQr";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DonViScanQrService donViScanQrService;

    private final DonViScanQrRepository donViScanQrRepository;

    private final DonViScanQrQueryService donViScanQrQueryService;

    public DonViScanQrResource(
        DonViScanQrService donViScanQrService,
        DonViScanQrRepository donViScanQrRepository,
        DonViScanQrQueryService donViScanQrQueryService
    ) {
        this.donViScanQrService = donViScanQrService;
        this.donViScanQrRepository = donViScanQrRepository;
        this.donViScanQrQueryService = donViScanQrQueryService;
    }

    /**
     * {@code POST  /don-vi-scan-qrs} : Create a new donViScanQr.
     *
     * @param donViScanQrDTO the donViScanQrDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new donViScanQrDTO, or with status {@code 400 (Bad Request)} if the donViScanQr has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DonViScanQrDTO> createDonViScanQr(@RequestBody DonViScanQrDTO donViScanQrDTO) throws URISyntaxException {
        LOG.debug("REST request to save DonViScanQr : {}", donViScanQrDTO);
        if (donViScanQrDTO.getIdLuotQuet() != null) {
            throw new BadRequestAlertException("A new donViScanQr cannot already have an ID", ENTITY_NAME, "idexists");
        }
        donViScanQrDTO = donViScanQrService.save(donViScanQrDTO);
        return ResponseEntity.created(new URI("/api/don-vi-scan-qrs/" + donViScanQrDTO.getIdLuotQuet()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, donViScanQrDTO.getIdLuotQuet().toString()))
            .body(donViScanQrDTO);
    }

    /**
     * {@code PUT  /don-vi-scan-qrs/:idLuotQuet} : Updates an existing donViScanQr.
     *
     * @param idLuotQuet the id of the donViScanQrDTO to save.
     * @param donViScanQrDTO the donViScanQrDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated donViScanQrDTO,
     * or with status {@code 400 (Bad Request)} if the donViScanQrDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the donViScanQrDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idLuotQuet}")
    public ResponseEntity<DonViScanQrDTO> updateDonViScanQr(
        @PathVariable(value = "idLuotQuet", required = false) final Long idLuotQuet,
        @RequestBody DonViScanQrDTO donViScanQrDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DonViScanQr : {}, {}", idLuotQuet, donViScanQrDTO);
        if (donViScanQrDTO.getIdLuotQuet() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idLuotQuet, donViScanQrDTO.getIdLuotQuet())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!donViScanQrRepository.existsById(idLuotQuet)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        donViScanQrDTO = donViScanQrService.update(donViScanQrDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, donViScanQrDTO.getIdLuotQuet().toString()))
            .body(donViScanQrDTO);
    }

    /**
     * {@code PATCH  /don-vi-scan-qrs/:idLuotQuet} : Partial updates given fields of an existing donViScanQr, field will ignore if it is null
     *
     * @param idLuotQuet the id of the donViScanQrDTO to save.
     * @param donViScanQrDTO the donViScanQrDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated donViScanQrDTO,
     * or with status {@code 400 (Bad Request)} if the donViScanQrDTO is not valid,
     * or with status {@code 404 (Not Found)} if the donViScanQrDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the donViScanQrDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idLuotQuet}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DonViScanQrDTO> partialUpdateDonViScanQr(
        @PathVariable(value = "idLuotQuet", required = false) final Long idLuotQuet,
        @RequestBody DonViScanQrDTO donViScanQrDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DonViScanQr partially : {}, {}", idLuotQuet, donViScanQrDTO);
        if (donViScanQrDTO.getIdLuotQuet() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idLuotQuet, donViScanQrDTO.getIdLuotQuet())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!donViScanQrRepository.existsById(idLuotQuet)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DonViScanQrDTO> result = donViScanQrService.partialUpdate(donViScanQrDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, donViScanQrDTO.getIdLuotQuet().toString())
        );
    }

    /**
     * {@code GET  /don-vi-scan-qrs} : get all the donViScanQrs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of donViScanQrs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DonViScanQrDTO>> getAllDonViScanQrs(
        DonViScanQrCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get DonViScanQrs by criteria: {}", criteria);

        Page<DonViScanQrDTO> page = donViScanQrQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /don-vi-scan-qrs/count} : count all the donViScanQrs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDonViScanQrs(DonViScanQrCriteria criteria) {
        LOG.debug("REST request to count DonViScanQrs by criteria: {}", criteria);
        return ResponseEntity.ok().body(donViScanQrQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /don-vi-scan-qrs/:id} : get the "id" donViScanQr.
     *
     * @param id the id of the donViScanQrDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the donViScanQrDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DonViScanQrDTO> getDonViScanQr(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DonViScanQr : {}", id);
        Optional<DonViScanQrDTO> donViScanQrDTO = donViScanQrService.findOne(id);
        return ResponseUtil.wrapOrNotFound(donViScanQrDTO);
    }

    /**
     * {@code DELETE  /don-vi-scan-qrs/:id} : delete the "id" donViScanQr.
     *
     * @param id the id of the donViScanQrDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonViScanQr(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DonViScanQr : {}", id);
        donViScanQrService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
