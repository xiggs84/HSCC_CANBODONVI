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
import vn.vnpt.repository.DanhMucTuVietTatRepository;
import vn.vnpt.service.DanhMucTuVietTatService;
import vn.vnpt.service.dto.DanhMucTuVietTatDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DanhMucTuVietTat}.
 */
@RestController
@RequestMapping("/api/danh-muc-tu-viet-tats")
public class DanhMucTuVietTatResource {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucTuVietTatResource.class);

    private static final String ENTITY_NAME = "hopDongDanhMucTuVietTat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhMucTuVietTatService danhMucTuVietTatService;

    private final DanhMucTuVietTatRepository danhMucTuVietTatRepository;

    public DanhMucTuVietTatResource(
        DanhMucTuVietTatService danhMucTuVietTatService,
        DanhMucTuVietTatRepository danhMucTuVietTatRepository
    ) {
        this.danhMucTuVietTatService = danhMucTuVietTatService;
        this.danhMucTuVietTatRepository = danhMucTuVietTatRepository;
    }

    /**
     * {@code POST  /danh-muc-tu-viet-tats} : Create a new danhMucTuVietTat.
     *
     * @param danhMucTuVietTatDTO the danhMucTuVietTatDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhMucTuVietTatDTO, or with status {@code 400 (Bad Request)} if the danhMucTuVietTat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DanhMucTuVietTatDTO> createDanhMucTuVietTat(@RequestBody DanhMucTuVietTatDTO danhMucTuVietTatDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save DanhMucTuVietTat : {}", danhMucTuVietTatDTO);
        if (danhMucTuVietTatDTO.getId() != null) {
            throw new BadRequestAlertException("A new danhMucTuVietTat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        danhMucTuVietTatDTO = danhMucTuVietTatService.save(danhMucTuVietTatDTO);
        return ResponseEntity.created(new URI("/api/danh-muc-tu-viet-tats/" + danhMucTuVietTatDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, danhMucTuVietTatDTO.getId()))
            .body(danhMucTuVietTatDTO);
    }

    /**
     * {@code PUT  /danh-muc-tu-viet-tats/:id} : Updates an existing danhMucTuVietTat.
     *
     * @param id the id of the danhMucTuVietTatDTO to save.
     * @param danhMucTuVietTatDTO the danhMucTuVietTatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucTuVietTatDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucTuVietTatDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhMucTuVietTatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DanhMucTuVietTatDTO> updateDanhMucTuVietTat(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DanhMucTuVietTatDTO danhMucTuVietTatDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DanhMucTuVietTat : {}, {}", id, danhMucTuVietTatDTO);
        if (danhMucTuVietTatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhMucTuVietTatDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucTuVietTatRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        danhMucTuVietTatDTO = danhMucTuVietTatService.update(danhMucTuVietTatDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucTuVietTatDTO.getId()))
            .body(danhMucTuVietTatDTO);
    }

    /**
     * {@code PATCH  /danh-muc-tu-viet-tats/:id} : Partial updates given fields of an existing danhMucTuVietTat, field will ignore if it is null
     *
     * @param id the id of the danhMucTuVietTatDTO to save.
     * @param danhMucTuVietTatDTO the danhMucTuVietTatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucTuVietTatDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucTuVietTatDTO is not valid,
     * or with status {@code 404 (Not Found)} if the danhMucTuVietTatDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhMucTuVietTatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhMucTuVietTatDTO> partialUpdateDanhMucTuVietTat(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DanhMucTuVietTatDTO danhMucTuVietTatDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DanhMucTuVietTat partially : {}, {}", id, danhMucTuVietTatDTO);
        if (danhMucTuVietTatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhMucTuVietTatDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucTuVietTatRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhMucTuVietTatDTO> result = danhMucTuVietTatService.partialUpdate(danhMucTuVietTatDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucTuVietTatDTO.getId())
        );
    }

    /**
     * {@code GET  /danh-muc-tu-viet-tats} : get all the danhMucTuVietTats.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhMucTuVietTats in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DanhMucTuVietTatDTO>> getAllDanhMucTuVietTats(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of DanhMucTuVietTats");
        Page<DanhMucTuVietTatDTO> page = danhMucTuVietTatService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /danh-muc-tu-viet-tats/:id} : get the "id" danhMucTuVietTat.
     *
     * @param id the id of the danhMucTuVietTatDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhMucTuVietTatDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DanhMucTuVietTatDTO> getDanhMucTuVietTat(@PathVariable("id") String id) {
        LOG.debug("REST request to get DanhMucTuVietTat : {}", id);
        Optional<DanhMucTuVietTatDTO> danhMucTuVietTatDTO = danhMucTuVietTatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhMucTuVietTatDTO);
    }

    /**
     * {@code DELETE  /danh-muc-tu-viet-tats/:id} : delete the "id" danhMucTuVietTat.
     *
     * @param id the id of the danhMucTuVietTatDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanhMucTuVietTat(@PathVariable("id") String id) {
        LOG.debug("REST request to delete DanhMucTuVietTat : {}", id);
        danhMucTuVietTatService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    @GetMapping("/by-donvi/{idDonVi}")
    public ResponseEntity<List<DanhMucTuVietTatDTO>> getTuVietTatsByDonVi(@PathVariable Long idDonVi) {
        LOG.debug("REST request to get TuVietTats by idDonVi : {}", idDonVi);
        List<DanhMucTuVietTatDTO> tuVietTatDTOs = danhMucTuVietTatService.findByDonVi(idDonVi);
        return ResponseEntity.ok().body(tuVietTatDTOs);
    }
}
