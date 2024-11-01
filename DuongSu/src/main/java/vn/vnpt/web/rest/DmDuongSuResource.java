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
import vn.vnpt.repository.DmDuongSuRepository;
import vn.vnpt.service.DmDuongSuQueryService;
import vn.vnpt.service.DmDuongSuService;
import vn.vnpt.service.criteria.DmDuongSuCriteria;
import vn.vnpt.service.dto.DmDuongSuDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DmDuongSu}.
 */
@RestController
@RequestMapping("/api/dm-duong-sus")
public class DmDuongSuResource {

    private static final Logger LOG = LoggerFactory.getLogger(DmDuongSuResource.class);

    private static final String ENTITY_NAME = "duongSuDmDuongSu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DmDuongSuService dmDuongSuService;

    private final DmDuongSuRepository dmDuongSuRepository;

    private final DmDuongSuQueryService dmDuongSuQueryService;

    public DmDuongSuResource(
        DmDuongSuService dmDuongSuService,
        DmDuongSuRepository dmDuongSuRepository,
        DmDuongSuQueryService dmDuongSuQueryService
    ) {
        this.dmDuongSuService = dmDuongSuService;
        this.dmDuongSuRepository = dmDuongSuRepository;
        this.dmDuongSuQueryService = dmDuongSuQueryService;
    }

    /**
     * {@code POST  /dm-duong-sus} : Create a new dmDuongSu.
     *
     * @param dmDuongSuDTO the dmDuongSuDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dmDuongSuDTO, or with status {@code 400 (Bad Request)} if the dmDuongSu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DmDuongSuDTO> createDmDuongSu(@Valid @RequestBody DmDuongSuDTO dmDuongSuDTO) throws URISyntaxException {
        LOG.debug("REST request to save DmDuongSu : {}", dmDuongSuDTO);
        if (dmDuongSuDTO.getIdDuongSu() != null) {
            throw new BadRequestAlertException("A new dmDuongSu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        dmDuongSuDTO = dmDuongSuService.save(dmDuongSuDTO);
        return ResponseEntity.created(new URI("/api/dm-duong-sus/" + dmDuongSuDTO.getIdDuongSu()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, dmDuongSuDTO.getIdDuongSu().toString()))
            .body(dmDuongSuDTO);
    }

    /**
     * {@code PUT  /dm-duong-sus/:idDuongSu} : Updates an existing dmDuongSu.
     *
     * @param idDuongSu the id of the dmDuongSuDTO to save.
     * @param dmDuongSuDTO the dmDuongSuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmDuongSuDTO,
     * or with status {@code 400 (Bad Request)} if the dmDuongSuDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dmDuongSuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idDuongSu}")
    public ResponseEntity<DmDuongSuDTO> updateDmDuongSu(
        @PathVariable(value = "idDuongSu", required = false) final Long idDuongSu,
        @Valid @RequestBody DmDuongSuDTO dmDuongSuDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DmDuongSu : {}, {}", idDuongSu, dmDuongSuDTO);
        if (dmDuongSuDTO.getIdDuongSu() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idDuongSu, dmDuongSuDTO.getIdDuongSu())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dmDuongSuRepository.existsById(idDuongSu)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        dmDuongSuDTO = dmDuongSuService.update(dmDuongSuDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmDuongSuDTO.getIdDuongSu().toString()))
            .body(dmDuongSuDTO);
    }

    /**
     * {@code PATCH  /dm-duong-sus/:idDuongSu} : Partial updates given fields of an existing dmDuongSu, field will ignore if it is null
     *
     * @param idDuongSu the id of the dmDuongSuDTO to save.
     * @param dmDuongSuDTO the dmDuongSuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmDuongSuDTO,
     * or with status {@code 400 (Bad Request)} if the dmDuongSuDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dmDuongSuDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dmDuongSuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idDuongSu}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DmDuongSuDTO> partialUpdateDmDuongSu(
        @PathVariable(value = "idDuongSu", required = false) final Long idDuongSu,
        @NotNull @RequestBody DmDuongSuDTO dmDuongSuDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DmDuongSu partially : {}, {}", idDuongSu, dmDuongSuDTO);
        if (dmDuongSuDTO.getIdDuongSu() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idDuongSu, dmDuongSuDTO.getIdDuongSu())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dmDuongSuRepository.existsById(idDuongSu)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DmDuongSuDTO> result = dmDuongSuService.partialUpdate(dmDuongSuDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmDuongSuDTO.getIdDuongSu().toString())
        );
    }

    /**
     * {@code GET  /dm-duong-sus} : get all the dmDuongSus.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dmDuongSus in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DmDuongSuDTO>> getAllDmDuongSus(
        DmDuongSuCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get DmDuongSus by criteria: {}", criteria);

        Page<DmDuongSuDTO> page = dmDuongSuQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dm-duong-sus/count} : count all the dmDuongSus.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDmDuongSus(DmDuongSuCriteria criteria) {
        LOG.debug("REST request to count DmDuongSus by criteria: {}", criteria);
        return ResponseEntity.ok().body(dmDuongSuQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dm-duong-sus/:id} : get the "id" dmDuongSu.
     *
     * @param id the id of the dmDuongSuDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dmDuongSuDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DmDuongSuDTO> getDmDuongSu(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DmDuongSu : {}", id);
        Optional<DmDuongSuDTO> dmDuongSuDTO = dmDuongSuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dmDuongSuDTO);
    }

    /**
     * {@code DELETE  /dm-duong-sus/:id} : delete the "id" dmDuongSu.
     *
     * @param id the id of the dmDuongSuDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDmDuongSu(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DmDuongSu : {}", id);
        dmDuongSuService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
