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
import vn.vnpt.repository.DanhMucLoaiSoCongChungRepository;
import vn.vnpt.service.DanhMucLoaiSoCongChungService;
import vn.vnpt.service.dto.DanhMucLoaiSoCongChungDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DanhMucLoaiSoCongChung}.
 */
@RestController
@RequestMapping("/api/danh-muc-loai-so-cong-chungs")
public class DanhMucLoaiSoCongChungResource {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucLoaiSoCongChungResource.class);

    private static final String ENTITY_NAME = "hopDongDanhMucLoaiSoCongChung";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhMucLoaiSoCongChungService danhMucLoaiSoCongChungService;

    private final DanhMucLoaiSoCongChungRepository danhMucLoaiSoCongChungRepository;

    public DanhMucLoaiSoCongChungResource(
        DanhMucLoaiSoCongChungService danhMucLoaiSoCongChungService,
        DanhMucLoaiSoCongChungRepository danhMucLoaiSoCongChungRepository
    ) {
        this.danhMucLoaiSoCongChungService = danhMucLoaiSoCongChungService;
        this.danhMucLoaiSoCongChungRepository = danhMucLoaiSoCongChungRepository;
    }

    /**
     * {@code POST  /danh-muc-loai-so-cong-chungs} : Create a new danhMucLoaiSoCongChung.
     *
     * @param danhMucLoaiSoCongChungDTO the danhMucLoaiSoCongChungDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhMucLoaiSoCongChungDTO, or with status {@code 400 (Bad Request)} if the danhMucLoaiSoCongChung has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DanhMucLoaiSoCongChungDTO> createDanhMucLoaiSoCongChung(
        @RequestBody DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save DanhMucLoaiSoCongChung : {}", danhMucLoaiSoCongChungDTO);
        if (danhMucLoaiSoCongChungDTO.getId() != null) {
            throw new BadRequestAlertException("A new danhMucLoaiSoCongChung cannot already have an ID", ENTITY_NAME, "idexists");
        }
        danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungService.save(danhMucLoaiSoCongChungDTO);
        return ResponseEntity.created(new URI("/api/danh-muc-loai-so-cong-chungs/" + danhMucLoaiSoCongChungDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, danhMucLoaiSoCongChungDTO.getId()))
            .body(danhMucLoaiSoCongChungDTO);
    }

    /**
     * {@code PUT  /danh-muc-loai-so-cong-chungs/:id} : Updates an existing danhMucLoaiSoCongChung.
     *
     * @param id the id of the danhMucLoaiSoCongChungDTO to save.
     * @param danhMucLoaiSoCongChungDTO the danhMucLoaiSoCongChungDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucLoaiSoCongChungDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucLoaiSoCongChungDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhMucLoaiSoCongChungDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DanhMucLoaiSoCongChungDTO> updateDanhMucLoaiSoCongChung(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DanhMucLoaiSoCongChung : {}, {}", id, danhMucLoaiSoCongChungDTO);
        if (danhMucLoaiSoCongChungDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhMucLoaiSoCongChungDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucLoaiSoCongChungRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungService.update(danhMucLoaiSoCongChungDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucLoaiSoCongChungDTO.getId()))
            .body(danhMucLoaiSoCongChungDTO);
    }

    /**
     * {@code PATCH  /danh-muc-loai-so-cong-chungs/:id} : Partial updates given fields of an existing danhMucLoaiSoCongChung, field will ignore if it is null
     *
     * @param id the id of the danhMucLoaiSoCongChungDTO to save.
     * @param danhMucLoaiSoCongChungDTO the danhMucLoaiSoCongChungDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucLoaiSoCongChungDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucLoaiSoCongChungDTO is not valid,
     * or with status {@code 404 (Not Found)} if the danhMucLoaiSoCongChungDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhMucLoaiSoCongChungDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhMucLoaiSoCongChungDTO> partialUpdateDanhMucLoaiSoCongChung(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DanhMucLoaiSoCongChung partially : {}, {}", id, danhMucLoaiSoCongChungDTO);
        if (danhMucLoaiSoCongChungDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhMucLoaiSoCongChungDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucLoaiSoCongChungRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhMucLoaiSoCongChungDTO> result = danhMucLoaiSoCongChungService.partialUpdate(danhMucLoaiSoCongChungDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucLoaiSoCongChungDTO.getId())
        );
    }

    /**
     * {@code GET  /danh-muc-loai-so-cong-chungs} : get all the danhMucLoaiSoCongChungs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhMucLoaiSoCongChungs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DanhMucLoaiSoCongChungDTO>> getAllDanhMucLoaiSoCongChungs(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of DanhMucLoaiSoCongChungs");
        Page<DanhMucLoaiSoCongChungDTO> page = danhMucLoaiSoCongChungService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /danh-muc-loai-so-cong-chungs/:id} : get the "id" danhMucLoaiSoCongChung.
     *
     * @param id the id of the danhMucLoaiSoCongChungDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhMucLoaiSoCongChungDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DanhMucLoaiSoCongChungDTO> getDanhMucLoaiSoCongChung(@PathVariable("id") String id) {
        LOG.debug("REST request to get DanhMucLoaiSoCongChung : {}", id);
        Optional<DanhMucLoaiSoCongChungDTO> danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhMucLoaiSoCongChungDTO);
    }

    /**
     * {@code DELETE  /danh-muc-loai-so-cong-chungs/:id} : delete the "id" danhMucLoaiSoCongChung.
     *
     * @param id the id of the danhMucLoaiSoCongChungDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanhMucLoaiSoCongChung(@PathVariable("id") String id) {
        LOG.debug("REST request to delete DanhMucLoaiSoCongChung : {}", id);
        danhMucLoaiSoCongChungService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
