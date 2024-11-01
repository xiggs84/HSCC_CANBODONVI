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
import vn.vnpt.repository.SoCongChungRepository;
import vn.vnpt.service.SoCongChungService;
import vn.vnpt.service.dto.SoCongChungDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.SoCongChung}.
 */
@RestController
@RequestMapping("/api/so-cong-chungs")
public class SoCongChungResource {

    private static final Logger LOG = LoggerFactory.getLogger(SoCongChungResource.class);

    private static final String ENTITY_NAME = "hopDongSoCongChung";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SoCongChungService soCongChungService;

    private final SoCongChungRepository soCongChungRepository;

    public SoCongChungResource(SoCongChungService soCongChungService, SoCongChungRepository soCongChungRepository) {
        this.soCongChungService = soCongChungService;
        this.soCongChungRepository = soCongChungRepository;
    }

    /**
     * {@code POST  /so-cong-chungs} : Create a new soCongChung.
     *
     * @param soCongChungDTO the soCongChungDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new soCongChungDTO, or with status {@code 400 (Bad Request)} if the soCongChung has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<SoCongChungDTO> createSoCongChung(@RequestBody SoCongChungDTO soCongChungDTO) throws URISyntaxException {
        LOG.debug("REST request to save SoCongChung : {}", soCongChungDTO);
        if (soCongChungDTO.getId() != null) {
            throw new BadRequestAlertException("A new soCongChung cannot already have an ID", ENTITY_NAME, "idexists");
        }
        soCongChungDTO = soCongChungService.save(soCongChungDTO);
        return ResponseEntity.created(new URI("/api/so-cong-chungs/" + soCongChungDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, soCongChungDTO.getId()))
            .body(soCongChungDTO);
    }

    /**
     * {@code PUT  /so-cong-chungs/:id} : Updates an existing soCongChung.
     *
     * @param id the id of the soCongChungDTO to save.
     * @param soCongChungDTO the soCongChungDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated soCongChungDTO,
     * or with status {@code 400 (Bad Request)} if the soCongChungDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the soCongChungDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SoCongChungDTO> updateSoCongChung(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody SoCongChungDTO soCongChungDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update SoCongChung : {}, {}", id, soCongChungDTO);
        if (soCongChungDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, soCongChungDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!soCongChungRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        soCongChungDTO = soCongChungService.update(soCongChungDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, soCongChungDTO.getId()))
            .body(soCongChungDTO);
    }

    /**
     * {@code PATCH  /so-cong-chungs/:id} : Partial updates given fields of an existing soCongChung, field will ignore if it is null
     *
     * @param id the id of the soCongChungDTO to save.
     * @param soCongChungDTO the soCongChungDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated soCongChungDTO,
     * or with status {@code 400 (Bad Request)} if the soCongChungDTO is not valid,
     * or with status {@code 404 (Not Found)} if the soCongChungDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the soCongChungDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SoCongChungDTO> partialUpdateSoCongChung(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody SoCongChungDTO soCongChungDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update SoCongChung partially : {}, {}", id, soCongChungDTO);
        if (soCongChungDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, soCongChungDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!soCongChungRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SoCongChungDTO> result = soCongChungService.partialUpdate(soCongChungDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, soCongChungDTO.getId())
        );
    }

    /**
     * {@code GET  /so-cong-chungs} : get all the soCongChungs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of soCongChungs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<SoCongChungDTO>> getAllSoCongChungs(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of SoCongChungs");
        Page<SoCongChungDTO> page = soCongChungService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /so-cong-chungs/:id} : get the "id" soCongChung.
     *
     * @param id the id of the soCongChungDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the soCongChungDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SoCongChungDTO> getSoCongChung(@PathVariable("id") String id) {
        LOG.debug("REST request to get SoCongChung : {}", id);
        Optional<SoCongChungDTO> soCongChungDTO = soCongChungService.findOne(id);
        return ResponseUtil.wrapOrNotFound(soCongChungDTO);
    }

    /**
     * {@code DELETE  /so-cong-chungs/:id} : delete the "id" soCongChung.
     *
     * @param id the id of the soCongChungDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSoCongChung(@PathVariable("id") String id) {
        LOG.debug("REST request to delete SoCongChung : {}", id);
        soCongChungService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    @GetMapping("/by-donvi/{idDonVi}")
    public ResponseEntity<List<SoCongChungDTO>> getSoCongChungsByDonVi(@PathVariable Long idDonVi) {
        LOG.debug("REST request to get SoCongChungs by idDonVi : {}", idDonVi);
        List<SoCongChungDTO> soCongChungDTOs = soCongChungService.findByDonVi(idDonVi);
        return ResponseEntity.ok().body(soCongChungDTOs);
    }
}
