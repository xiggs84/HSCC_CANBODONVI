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
import vn.vnpt.repository.NhiemVuRepository;
import vn.vnpt.service.NhiemVuQueryService;
import vn.vnpt.service.NhiemVuService;
import vn.vnpt.service.criteria.NhiemVuCriteria;
import vn.vnpt.service.dto.NhiemVuDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.NhiemVu}.
 */
@RestController
@RequestMapping("/api/nhiem-vus")
public class NhiemVuResource {

    private static final Logger LOG = LoggerFactory.getLogger(NhiemVuResource.class);

    private static final String ENTITY_NAME = "canBoDonViNhiemVu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NhiemVuService nhiemVuService;

    private final NhiemVuRepository nhiemVuRepository;

    private final NhiemVuQueryService nhiemVuQueryService;

    public NhiemVuResource(NhiemVuService nhiemVuService, NhiemVuRepository nhiemVuRepository, NhiemVuQueryService nhiemVuQueryService) {
        this.nhiemVuService = nhiemVuService;
        this.nhiemVuRepository = nhiemVuRepository;
        this.nhiemVuQueryService = nhiemVuQueryService;
    }

    /**
     * {@code POST  /nhiem-vus} : Create a new nhiemVu.
     *
     * @param nhiemVuDTO the nhiemVuDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nhiemVuDTO, or with status {@code 400 (Bad Request)} if the nhiemVu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<NhiemVuDTO> createNhiemVu(@RequestBody NhiemVuDTO nhiemVuDTO) throws URISyntaxException {
        LOG.debug("REST request to save NhiemVu : {}", nhiemVuDTO);
        if (nhiemVuRepository.existsById(nhiemVuDTO.getIdNhiemVu())) {
            throw new BadRequestAlertException("nhiemVu already exists", ENTITY_NAME, "idexists");
        }
        nhiemVuDTO = nhiemVuService.save(nhiemVuDTO);
        return ResponseEntity.created(new URI("/api/nhiem-vus/" + nhiemVuDTO.getIdNhiemVu()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, nhiemVuDTO.getIdNhiemVu()))
            .body(nhiemVuDTO);
    }

    /**
     * {@code PUT  /nhiem-vus/:idNhiemVu} : Updates an existing nhiemVu.
     *
     * @param idNhiemVu the id of the nhiemVuDTO to save.
     * @param nhiemVuDTO the nhiemVuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nhiemVuDTO,
     * or with status {@code 400 (Bad Request)} if the nhiemVuDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nhiemVuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idNhiemVu}")
    public ResponseEntity<NhiemVuDTO> updateNhiemVu(
        @PathVariable(value = "idNhiemVu", required = false) final String idNhiemVu,
        @RequestBody NhiemVuDTO nhiemVuDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update NhiemVu : {}, {}", idNhiemVu, nhiemVuDTO);
        if (nhiemVuDTO.getIdNhiemVu() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idNhiemVu, nhiemVuDTO.getIdNhiemVu())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!nhiemVuRepository.existsById(idNhiemVu)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        nhiemVuDTO = nhiemVuService.update(nhiemVuDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nhiemVuDTO.getIdNhiemVu()))
            .body(nhiemVuDTO);
    }

    /**
     * {@code PATCH  /nhiem-vus/:idNhiemVu} : Partial updates given fields of an existing nhiemVu, field will ignore if it is null
     *
     * @param idNhiemVu the id of the nhiemVuDTO to save.
     * @param nhiemVuDTO the nhiemVuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nhiemVuDTO,
     * or with status {@code 400 (Bad Request)} if the nhiemVuDTO is not valid,
     * or with status {@code 404 (Not Found)} if the nhiemVuDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the nhiemVuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idNhiemVu}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NhiemVuDTO> partialUpdateNhiemVu(
        @PathVariable(value = "idNhiemVu", required = false) final String idNhiemVu,
        @RequestBody NhiemVuDTO nhiemVuDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update NhiemVu partially : {}, {}", idNhiemVu, nhiemVuDTO);
        if (nhiemVuDTO.getIdNhiemVu() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idNhiemVu, nhiemVuDTO.getIdNhiemVu())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!nhiemVuRepository.existsById(idNhiemVu)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NhiemVuDTO> result = nhiemVuService.partialUpdate(nhiemVuDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nhiemVuDTO.getIdNhiemVu())
        );
    }

    /**
     * {@code GET  /nhiem-vus} : get all the nhiemVus.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nhiemVus in body.
     */
    @GetMapping("")
    public ResponseEntity<List<NhiemVuDTO>> getAllNhiemVus(
        NhiemVuCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get NhiemVus by criteria: {}", criteria);

        Page<NhiemVuDTO> page = nhiemVuQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nhiem-vus/count} : count all the nhiemVus.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countNhiemVus(NhiemVuCriteria criteria) {
        LOG.debug("REST request to count NhiemVus by criteria: {}", criteria);
        return ResponseEntity.ok().body(nhiemVuQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /nhiem-vus/:id} : get the "id" nhiemVu.
     *
     * @param id the id of the nhiemVuDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nhiemVuDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<NhiemVuDTO> getNhiemVu(@PathVariable("id") String id) {
        LOG.debug("REST request to get NhiemVu : {}", id);
        Optional<NhiemVuDTO> nhiemVuDTO = nhiemVuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nhiemVuDTO);
    }

    /**
     * {@code DELETE  /nhiem-vus/:id} : delete the "id" nhiemVu.
     *
     * @param id the id of the nhiemVuDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNhiemVu(@PathVariable("id") String id) {
        LOG.debug("REST request to delete NhiemVu : {}", id);
        nhiemVuService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
