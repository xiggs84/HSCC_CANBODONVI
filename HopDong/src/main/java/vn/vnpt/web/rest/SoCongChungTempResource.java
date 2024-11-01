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
import vn.vnpt.repository.SoCongChungTempRepository;
import vn.vnpt.service.SoCongChungTempService;
import vn.vnpt.service.dto.SoCongChungTempDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.SoCongChungTemp}.
 */
@RestController
@RequestMapping("/api/so-cong-chung-temps")
public class SoCongChungTempResource {

    private static final Logger LOG = LoggerFactory.getLogger(SoCongChungTempResource.class);

    private static final String ENTITY_NAME = "hopDongSoCongChungTemp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SoCongChungTempService soCongChungTempService;

    private final SoCongChungTempRepository soCongChungTempRepository;

    public SoCongChungTempResource(SoCongChungTempService soCongChungTempService, SoCongChungTempRepository soCongChungTempRepository) {
        this.soCongChungTempService = soCongChungTempService;
        this.soCongChungTempRepository = soCongChungTempRepository;
    }

    /**
     * {@code POST  /so-cong-chung-temps} : Create a new soCongChungTemp.
     *
     * @param soCongChungTempDTO the soCongChungTempDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new soCongChungTempDTO, or with status {@code 400 (Bad Request)} if the soCongChungTemp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<SoCongChungTempDTO> createSoCongChungTemp(@RequestBody SoCongChungTempDTO soCongChungTempDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save SoCongChungTemp : {}", soCongChungTempDTO);
        if (soCongChungTempDTO.getId() != null) {
            throw new BadRequestAlertException("A new soCongChungTemp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        soCongChungTempDTO = soCongChungTempService.save(soCongChungTempDTO);
        return ResponseEntity.created(new URI("/api/so-cong-chung-temps/" + soCongChungTempDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, soCongChungTempDTO.getId()))
            .body(soCongChungTempDTO);
    }

    /**
     * {@code PUT  /so-cong-chung-temps/:id} : Updates an existing soCongChungTemp.
     *
     * @param id the id of the soCongChungTempDTO to save.
     * @param soCongChungTempDTO the soCongChungTempDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated soCongChungTempDTO,
     * or with status {@code 400 (Bad Request)} if the soCongChungTempDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the soCongChungTempDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SoCongChungTempDTO> updateSoCongChungTemp(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody SoCongChungTempDTO soCongChungTempDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update SoCongChungTemp : {}, {}", id, soCongChungTempDTO);
        if (soCongChungTempDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, soCongChungTempDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!soCongChungTempRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        soCongChungTempDTO = soCongChungTempService.update(soCongChungTempDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, soCongChungTempDTO.getId()))
            .body(soCongChungTempDTO);
    }

    /**
     * {@code PATCH  /so-cong-chung-temps/:id} : Partial updates given fields of an existing soCongChungTemp, field will ignore if it is null
     *
     * @param id the id of the soCongChungTempDTO to save.
     * @param soCongChungTempDTO the soCongChungTempDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated soCongChungTempDTO,
     * or with status {@code 400 (Bad Request)} if the soCongChungTempDTO is not valid,
     * or with status {@code 404 (Not Found)} if the soCongChungTempDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the soCongChungTempDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SoCongChungTempDTO> partialUpdateSoCongChungTemp(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody SoCongChungTempDTO soCongChungTempDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update SoCongChungTemp partially : {}, {}", id, soCongChungTempDTO);
        if (soCongChungTempDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, soCongChungTempDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!soCongChungTempRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SoCongChungTempDTO> result = soCongChungTempService.partialUpdate(soCongChungTempDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, soCongChungTempDTO.getId())
        );
    }

    /**
     * {@code GET  /so-cong-chung-temps} : get all the soCongChungTemps.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of soCongChungTemps in body.
     */
    @GetMapping("")
    public ResponseEntity<List<SoCongChungTempDTO>> getAllSoCongChungTemps(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of SoCongChungTemps");
        Page<SoCongChungTempDTO> page = soCongChungTempService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /so-cong-chung-temps/:id} : get the "id" soCongChungTemp.
     *
     * @param id the id of the soCongChungTempDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the soCongChungTempDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SoCongChungTempDTO> getSoCongChungTemp(@PathVariable("id") String id) {
        LOG.debug("REST request to get SoCongChungTemp : {}", id);
        Optional<SoCongChungTempDTO> soCongChungTempDTO = soCongChungTempService.findOne(id);
        return ResponseUtil.wrapOrNotFound(soCongChungTempDTO);
    }

    /**
     * {@code DELETE  /so-cong-chung-temps/:id} : delete the "id" soCongChungTemp.
     *
     * @param id the id of the soCongChungTempDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSoCongChungTemp(@PathVariable("id") String id) {
        LOG.debug("REST request to delete SoCongChungTemp : {}", id);
        soCongChungTempService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
