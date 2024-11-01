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
import vn.vnpt.repository.QuanHeDuongSuRepository;
import vn.vnpt.service.QuanHeDuongSuQueryService;
import vn.vnpt.service.QuanHeDuongSuService;
import vn.vnpt.service.criteria.QuanHeDuongSuCriteria;
import vn.vnpt.service.dto.QuanHeDuongSuDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.QuanHeDuongSu}.
 */
@RestController
@RequestMapping("/api/quan-he-duong-sus")
public class QuanHeDuongSuResource {

    private static final Logger LOG = LoggerFactory.getLogger(QuanHeDuongSuResource.class);

    private static final String ENTITY_NAME = "duongSuQuanHeDuongSu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuanHeDuongSuService quanHeDuongSuService;

    private final QuanHeDuongSuRepository quanHeDuongSuRepository;

    private final QuanHeDuongSuQueryService quanHeDuongSuQueryService;

    public QuanHeDuongSuResource(
        QuanHeDuongSuService quanHeDuongSuService,
        QuanHeDuongSuRepository quanHeDuongSuRepository,
        QuanHeDuongSuQueryService quanHeDuongSuQueryService
    ) {
        this.quanHeDuongSuService = quanHeDuongSuService;
        this.quanHeDuongSuRepository = quanHeDuongSuRepository;
        this.quanHeDuongSuQueryService = quanHeDuongSuQueryService;
    }

    /**
     * {@code POST  /quan-he-duong-sus} : Create a new quanHeDuongSu.
     *
     * @param quanHeDuongSuDTO the quanHeDuongSuDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new quanHeDuongSuDTO, or with status {@code 400 (Bad Request)} if the quanHeDuongSu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<QuanHeDuongSuDTO> createQuanHeDuongSu(@Valid @RequestBody QuanHeDuongSuDTO quanHeDuongSuDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save QuanHeDuongSu : {}", quanHeDuongSuDTO);
        if (quanHeDuongSuDTO.getIdQuanHe() != null) {
            throw new BadRequestAlertException("A new quanHeDuongSu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        quanHeDuongSuDTO = quanHeDuongSuService.save(quanHeDuongSuDTO);
        return ResponseEntity.created(new URI("/api/quan-he-duong-sus/" + quanHeDuongSuDTO.getIdQuanHe()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, quanHeDuongSuDTO.getIdQuanHe().toString()))
            .body(quanHeDuongSuDTO);
    }

    /**
     * {@code PUT  /quan-he-duong-sus/:idQuanHe} : Updates an existing quanHeDuongSu.
     *
     * @param idQuanHe the id of the quanHeDuongSuDTO to save.
     * @param quanHeDuongSuDTO the quanHeDuongSuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated quanHeDuongSuDTO,
     * or with status {@code 400 (Bad Request)} if the quanHeDuongSuDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the quanHeDuongSuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idQuanHe}")
    public ResponseEntity<QuanHeDuongSuDTO> updateQuanHeDuongSu(
        @PathVariable(value = "idQuanHe", required = false) final Long idQuanHe,
        @Valid @RequestBody QuanHeDuongSuDTO quanHeDuongSuDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update QuanHeDuongSu : {}, {}", idQuanHe, quanHeDuongSuDTO);
        if (quanHeDuongSuDTO.getIdQuanHe() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idQuanHe, quanHeDuongSuDTO.getIdQuanHe())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!quanHeDuongSuRepository.existsById(idQuanHe)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        quanHeDuongSuDTO = quanHeDuongSuService.update(quanHeDuongSuDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, quanHeDuongSuDTO.getIdQuanHe().toString()))
            .body(quanHeDuongSuDTO);
    }

    /**
     * {@code PATCH  /quan-he-duong-sus/:idQuanHe} : Partial updates given fields of an existing quanHeDuongSu, field will ignore if it is null
     *
     * @param idQuanHe the id of the quanHeDuongSuDTO to save.
     * @param quanHeDuongSuDTO the quanHeDuongSuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated quanHeDuongSuDTO,
     * or with status {@code 400 (Bad Request)} if the quanHeDuongSuDTO is not valid,
     * or with status {@code 404 (Not Found)} if the quanHeDuongSuDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the quanHeDuongSuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idQuanHe}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<QuanHeDuongSuDTO> partialUpdateQuanHeDuongSu(
        @PathVariable(value = "idQuanHe", required = false) final Long idQuanHe,
        @NotNull @RequestBody QuanHeDuongSuDTO quanHeDuongSuDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update QuanHeDuongSu partially : {}, {}", idQuanHe, quanHeDuongSuDTO);
        if (quanHeDuongSuDTO.getIdQuanHe() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idQuanHe, quanHeDuongSuDTO.getIdQuanHe())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!quanHeDuongSuRepository.existsById(idQuanHe)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QuanHeDuongSuDTO> result = quanHeDuongSuService.partialUpdate(quanHeDuongSuDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, quanHeDuongSuDTO.getIdQuanHe().toString())
        );
    }

    /**
     * {@code GET  /quan-he-duong-sus} : get all the quanHeDuongSus.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of quanHeDuongSus in body.
     */
    @GetMapping("")
    public ResponseEntity<List<QuanHeDuongSuDTO>> getAllQuanHeDuongSus(
        QuanHeDuongSuCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get QuanHeDuongSus by criteria: {}", criteria);

        Page<QuanHeDuongSuDTO> page = quanHeDuongSuQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /quan-he-duong-sus/count} : count all the quanHeDuongSus.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countQuanHeDuongSus(QuanHeDuongSuCriteria criteria) {
        LOG.debug("REST request to count QuanHeDuongSus by criteria: {}", criteria);
        return ResponseEntity.ok().body(quanHeDuongSuQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /quan-he-duong-sus/:id} : get the "id" quanHeDuongSu.
     *
     * @param id the id of the quanHeDuongSuDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the quanHeDuongSuDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<QuanHeDuongSuDTO> getQuanHeDuongSu(@PathVariable("id") Long id) {
        LOG.debug("REST request to get QuanHeDuongSu : {}", id);
        Optional<QuanHeDuongSuDTO> quanHeDuongSuDTO = quanHeDuongSuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(quanHeDuongSuDTO);
    }

    /**
     * {@code DELETE  /quan-he-duong-sus/:id} : delete the "id" quanHeDuongSu.
     *
     * @param id the id of the quanHeDuongSuDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuanHeDuongSu(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete QuanHeDuongSu : {}", id);
        quanHeDuongSuService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
