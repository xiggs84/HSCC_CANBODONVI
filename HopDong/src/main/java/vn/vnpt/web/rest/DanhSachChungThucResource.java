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
import vn.vnpt.repository.DanhSachChungThucRepository;
import vn.vnpt.service.DanhSachChungThucService;
import vn.vnpt.service.dto.DanhSachChungThucDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DanhSachChungThuc}.
 */
@RestController
@RequestMapping("/api/danh-sach-chung-thucs")
public class DanhSachChungThucResource {

    private static final Logger LOG = LoggerFactory.getLogger(DanhSachChungThucResource.class);

    private static final String ENTITY_NAME = "hopDongDanhSachChungThuc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhSachChungThucService danhSachChungThucService;

    private final DanhSachChungThucRepository danhSachChungThucRepository;

    public DanhSachChungThucResource(
        DanhSachChungThucService danhSachChungThucService,
        DanhSachChungThucRepository danhSachChungThucRepository
    ) {
        this.danhSachChungThucService = danhSachChungThucService;
        this.danhSachChungThucRepository = danhSachChungThucRepository;
    }

    /**
     * {@code POST  /danh-sach-chung-thucs} : Create a new danhSachChungThuc.
     *
     * @param danhSachChungThucDTO the danhSachChungThucDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhSachChungThucDTO, or with status {@code 400 (Bad Request)} if the danhSachChungThuc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DanhSachChungThucDTO> createDanhSachChungThuc(@RequestBody DanhSachChungThucDTO danhSachChungThucDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save DanhSachChungThuc : {}", danhSachChungThucDTO);
        if (danhSachChungThucDTO.getId() != null) {
            throw new BadRequestAlertException("A new danhSachChungThuc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        danhSachChungThucDTO = danhSachChungThucService.save(danhSachChungThucDTO);
        return ResponseEntity.created(new URI("/api/danh-sach-chung-thucs/" + danhSachChungThucDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, danhSachChungThucDTO.getId()))
            .body(danhSachChungThucDTO);
    }

    /**
     * {@code PUT  /danh-sach-chung-thucs/:id} : Updates an existing danhSachChungThuc.
     *
     * @param id the id of the danhSachChungThucDTO to save.
     * @param danhSachChungThucDTO the danhSachChungThucDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhSachChungThucDTO,
     * or with status {@code 400 (Bad Request)} if the danhSachChungThucDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhSachChungThucDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DanhSachChungThucDTO> updateDanhSachChungThuc(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DanhSachChungThucDTO danhSachChungThucDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DanhSachChungThuc : {}, {}", id, danhSachChungThucDTO);
        if (danhSachChungThucDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhSachChungThucDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhSachChungThucRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        danhSachChungThucDTO = danhSachChungThucService.update(danhSachChungThucDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhSachChungThucDTO.getId()))
            .body(danhSachChungThucDTO);
    }

    /**
     * {@code PATCH  /danh-sach-chung-thucs/:id} : Partial updates given fields of an existing danhSachChungThuc, field will ignore if it is null
     *
     * @param id the id of the danhSachChungThucDTO to save.
     * @param danhSachChungThucDTO the danhSachChungThucDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhSachChungThucDTO,
     * or with status {@code 400 (Bad Request)} if the danhSachChungThucDTO is not valid,
     * or with status {@code 404 (Not Found)} if the danhSachChungThucDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhSachChungThucDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhSachChungThucDTO> partialUpdateDanhSachChungThuc(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DanhSachChungThucDTO danhSachChungThucDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DanhSachChungThuc partially : {}, {}", id, danhSachChungThucDTO);
        if (danhSachChungThucDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhSachChungThucDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhSachChungThucRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhSachChungThucDTO> result = danhSachChungThucService.partialUpdate(danhSachChungThucDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhSachChungThucDTO.getId())
        );
    }

    /**
     * {@code GET  /danh-sach-chung-thucs} : get all the danhSachChungThucs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhSachChungThucs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DanhSachChungThucDTO>> getAllDanhSachChungThucs(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of DanhSachChungThucs");
        Page<DanhSachChungThucDTO> page = danhSachChungThucService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /danh-sach-chung-thucs/:id} : get the "id" danhSachChungThuc.
     *
     * @param id the id of the danhSachChungThucDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhSachChungThucDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DanhSachChungThucDTO> getDanhSachChungThuc(@PathVariable("id") String id) {
        LOG.debug("REST request to get DanhSachChungThuc : {}", id);
        Optional<DanhSachChungThucDTO> danhSachChungThucDTO = danhSachChungThucService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhSachChungThucDTO);
    }

    /**
     * {@code DELETE  /danh-sach-chung-thucs/:id} : delete the "id" danhSachChungThuc.
     *
     * @param id the id of the danhSachChungThucDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanhSachChungThuc(@PathVariable("id") String id) {
        LOG.debug("REST request to delete DanhSachChungThuc : {}", id);
        danhSachChungThucService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
