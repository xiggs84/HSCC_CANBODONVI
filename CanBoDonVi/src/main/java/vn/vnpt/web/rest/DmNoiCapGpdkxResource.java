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
import vn.vnpt.repository.DmNoiCapGpdkxRepository;
import vn.vnpt.service.DmNoiCapGpdkxQueryService;
import vn.vnpt.service.DmNoiCapGpdkxService;
import vn.vnpt.service.criteria.DmNoiCapGpdkxCriteria;
import vn.vnpt.service.dto.DmNoiCapGpdkxDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DmNoiCapGpdkx}.
 */
@RestController
@RequestMapping("/api/dm-noi-cap-gpdkxes")
public class DmNoiCapGpdkxResource {

    private static final Logger LOG = LoggerFactory.getLogger(DmNoiCapGpdkxResource.class);

    private static final String ENTITY_NAME = "canBoDonViDmNoiCapGpdkx";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DmNoiCapGpdkxService dmNoiCapGpdkxService;

    private final DmNoiCapGpdkxRepository dmNoiCapGpdkxRepository;

    private final DmNoiCapGpdkxQueryService dmNoiCapGpdkxQueryService;

    public DmNoiCapGpdkxResource(
        DmNoiCapGpdkxService dmNoiCapGpdkxService,
        DmNoiCapGpdkxRepository dmNoiCapGpdkxRepository,
        DmNoiCapGpdkxQueryService dmNoiCapGpdkxQueryService
    ) {
        this.dmNoiCapGpdkxService = dmNoiCapGpdkxService;
        this.dmNoiCapGpdkxRepository = dmNoiCapGpdkxRepository;
        this.dmNoiCapGpdkxQueryService = dmNoiCapGpdkxQueryService;
    }

    /**
     * {@code POST  /dm-noi-cap-gpdkxes} : Create a new dmNoiCapGpdkx.
     *
     * @param dmNoiCapGpdkxDTO the dmNoiCapGpdkxDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dmNoiCapGpdkxDTO, or with status {@code 400 (Bad Request)} if the dmNoiCapGpdkx has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DmNoiCapGpdkxDTO> createDmNoiCapGpdkx(@RequestBody DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO) throws URISyntaxException {
        LOG.debug("REST request to save DmNoiCapGpdkx : {}", dmNoiCapGpdkxDTO);
        if (dmNoiCapGpdkxDTO.getIdNoiCap() != null) {
            throw new BadRequestAlertException("A new dmNoiCapGpdkx cannot already have an ID", ENTITY_NAME, "idexists");
        }
        dmNoiCapGpdkxDTO = dmNoiCapGpdkxService.save(dmNoiCapGpdkxDTO);
        return ResponseEntity.created(new URI("/api/dm-noi-cap-gpdkxes/" + dmNoiCapGpdkxDTO.getIdNoiCap()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, dmNoiCapGpdkxDTO.getIdNoiCap().toString()))
            .body(dmNoiCapGpdkxDTO);
    }

    /**
     * {@code PUT  /dm-noi-cap-gpdkxes/:idNoiCap} : Updates an existing dmNoiCapGpdkx.
     *
     * @param idNoiCap the id of the dmNoiCapGpdkxDTO to save.
     * @param dmNoiCapGpdkxDTO the dmNoiCapGpdkxDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmNoiCapGpdkxDTO,
     * or with status {@code 400 (Bad Request)} if the dmNoiCapGpdkxDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dmNoiCapGpdkxDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idNoiCap}")
    public ResponseEntity<DmNoiCapGpdkxDTO> updateDmNoiCapGpdkx(
        @PathVariable(value = "idNoiCap", required = false) final Long idNoiCap,
        @RequestBody DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DmNoiCapGpdkx : {}, {}", idNoiCap, dmNoiCapGpdkxDTO);
        if (dmNoiCapGpdkxDTO.getIdNoiCap() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idNoiCap, dmNoiCapGpdkxDTO.getIdNoiCap())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dmNoiCapGpdkxRepository.existsById(idNoiCap)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        dmNoiCapGpdkxDTO = dmNoiCapGpdkxService.update(dmNoiCapGpdkxDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmNoiCapGpdkxDTO.getIdNoiCap().toString()))
            .body(dmNoiCapGpdkxDTO);
    }

    /**
     * {@code PATCH  /dm-noi-cap-gpdkxes/:idNoiCap} : Partial updates given fields of an existing dmNoiCapGpdkx, field will ignore if it is null
     *
     * @param idNoiCap the id of the dmNoiCapGpdkxDTO to save.
     * @param dmNoiCapGpdkxDTO the dmNoiCapGpdkxDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmNoiCapGpdkxDTO,
     * or with status {@code 400 (Bad Request)} if the dmNoiCapGpdkxDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dmNoiCapGpdkxDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dmNoiCapGpdkxDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idNoiCap}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DmNoiCapGpdkxDTO> partialUpdateDmNoiCapGpdkx(
        @PathVariable(value = "idNoiCap", required = false) final Long idNoiCap,
        @RequestBody DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DmNoiCapGpdkx partially : {}, {}", idNoiCap, dmNoiCapGpdkxDTO);
        if (dmNoiCapGpdkxDTO.getIdNoiCap() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idNoiCap, dmNoiCapGpdkxDTO.getIdNoiCap())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dmNoiCapGpdkxRepository.existsById(idNoiCap)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DmNoiCapGpdkxDTO> result = dmNoiCapGpdkxService.partialUpdate(dmNoiCapGpdkxDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmNoiCapGpdkxDTO.getIdNoiCap().toString())
        );
    }

    /**
     * {@code GET  /dm-noi-cap-gpdkxes} : get all the dmNoiCapGpdkxes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dmNoiCapGpdkxes in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DmNoiCapGpdkxDTO>> getAllDmNoiCapGpdkxes(
        DmNoiCapGpdkxCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get DmNoiCapGpdkxes by criteria: {}", criteria);

        Page<DmNoiCapGpdkxDTO> page = dmNoiCapGpdkxQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dm-noi-cap-gpdkxes/count} : count all the dmNoiCapGpdkxes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDmNoiCapGpdkxes(DmNoiCapGpdkxCriteria criteria) {
        LOG.debug("REST request to count DmNoiCapGpdkxes by criteria: {}", criteria);
        return ResponseEntity.ok().body(dmNoiCapGpdkxQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dm-noi-cap-gpdkxes/:id} : get the "id" dmNoiCapGpdkx.
     *
     * @param id the id of the dmNoiCapGpdkxDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dmNoiCapGpdkxDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DmNoiCapGpdkxDTO> getDmNoiCapGpdkx(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DmNoiCapGpdkx : {}", id);
        Optional<DmNoiCapGpdkxDTO> dmNoiCapGpdkxDTO = dmNoiCapGpdkxService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dmNoiCapGpdkxDTO);
    }

    /**
     * {@code DELETE  /dm-noi-cap-gpdkxes/:id} : delete the "id" dmNoiCapGpdkx.
     *
     * @param id the id of the dmNoiCapGpdkxDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDmNoiCapGpdkx(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DmNoiCapGpdkx : {}", id);
        dmNoiCapGpdkxService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
