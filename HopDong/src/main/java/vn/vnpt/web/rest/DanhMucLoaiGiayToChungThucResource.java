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
import vn.vnpt.repository.DanhMucLoaiGiayToChungThucRepository;
import vn.vnpt.service.DanhMucLoaiGiayToChungThucService;
import vn.vnpt.service.dto.DanhMucLoaiGiayToChungThucDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DanhMucLoaiGiayToChungThuc}.
 */
@RestController
@RequestMapping("/api/danh-muc-loai-giay-to-chung-thucs")
public class DanhMucLoaiGiayToChungThucResource {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucLoaiGiayToChungThucResource.class);

    private static final String ENTITY_NAME = "hopDongDanhMucLoaiGiayToChungThuc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhMucLoaiGiayToChungThucService danhMucLoaiGiayToChungThucService;

    private final DanhMucLoaiGiayToChungThucRepository danhMucLoaiGiayToChungThucRepository;

    public DanhMucLoaiGiayToChungThucResource(
        DanhMucLoaiGiayToChungThucService danhMucLoaiGiayToChungThucService,
        DanhMucLoaiGiayToChungThucRepository danhMucLoaiGiayToChungThucRepository
    ) {
        this.danhMucLoaiGiayToChungThucService = danhMucLoaiGiayToChungThucService;
        this.danhMucLoaiGiayToChungThucRepository = danhMucLoaiGiayToChungThucRepository;
    }

    /**
     * {@code POST  /danh-muc-loai-giay-to-chung-thucs} : Create a new danhMucLoaiGiayToChungThuc.
     *
     * @param danhMucLoaiGiayToChungThucDTO the danhMucLoaiGiayToChungThucDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhMucLoaiGiayToChungThucDTO, or with status {@code 400 (Bad Request)} if the danhMucLoaiGiayToChungThuc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DanhMucLoaiGiayToChungThucDTO> createDanhMucLoaiGiayToChungThuc(
        @RequestBody DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save DanhMucLoaiGiayToChungThuc : {}", danhMucLoaiGiayToChungThucDTO);
        if (danhMucLoaiGiayToChungThucDTO.getId() != null) {
            throw new BadRequestAlertException("A new danhMucLoaiGiayToChungThuc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        danhMucLoaiGiayToChungThucDTO = danhMucLoaiGiayToChungThucService.save(danhMucLoaiGiayToChungThucDTO);
        return ResponseEntity.created(new URI("/api/danh-muc-loai-giay-to-chung-thucs/" + danhMucLoaiGiayToChungThucDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, danhMucLoaiGiayToChungThucDTO.getId()))
            .body(danhMucLoaiGiayToChungThucDTO);
    }

    /**
     * {@code PUT  /danh-muc-loai-giay-to-chung-thucs/:id} : Updates an existing danhMucLoaiGiayToChungThuc.
     *
     * @param id the id of the danhMucLoaiGiayToChungThucDTO to save.
     * @param danhMucLoaiGiayToChungThucDTO the danhMucLoaiGiayToChungThucDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucLoaiGiayToChungThucDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucLoaiGiayToChungThucDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhMucLoaiGiayToChungThucDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DanhMucLoaiGiayToChungThucDTO> updateDanhMucLoaiGiayToChungThuc(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DanhMucLoaiGiayToChungThuc : {}, {}", id, danhMucLoaiGiayToChungThucDTO);
        if (danhMucLoaiGiayToChungThucDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhMucLoaiGiayToChungThucDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucLoaiGiayToChungThucRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        danhMucLoaiGiayToChungThucDTO = danhMucLoaiGiayToChungThucService.update(danhMucLoaiGiayToChungThucDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucLoaiGiayToChungThucDTO.getId()))
            .body(danhMucLoaiGiayToChungThucDTO);
    }

    /**
     * {@code PATCH  /danh-muc-loai-giay-to-chung-thucs/:id} : Partial updates given fields of an existing danhMucLoaiGiayToChungThuc, field will ignore if it is null
     *
     * @param id the id of the danhMucLoaiGiayToChungThucDTO to save.
     * @param danhMucLoaiGiayToChungThucDTO the danhMucLoaiGiayToChungThucDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucLoaiGiayToChungThucDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucLoaiGiayToChungThucDTO is not valid,
     * or with status {@code 404 (Not Found)} if the danhMucLoaiGiayToChungThucDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhMucLoaiGiayToChungThucDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhMucLoaiGiayToChungThucDTO> partialUpdateDanhMucLoaiGiayToChungThuc(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DanhMucLoaiGiayToChungThuc partially : {}, {}", id, danhMucLoaiGiayToChungThucDTO);
        if (danhMucLoaiGiayToChungThucDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhMucLoaiGiayToChungThucDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucLoaiGiayToChungThucRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhMucLoaiGiayToChungThucDTO> result = danhMucLoaiGiayToChungThucService.partialUpdate(danhMucLoaiGiayToChungThucDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucLoaiGiayToChungThucDTO.getId())
        );
    }

    /**
     * {@code GET  /danh-muc-loai-giay-to-chung-thucs} : get all the danhMucLoaiGiayToChungThucs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhMucLoaiGiayToChungThucs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DanhMucLoaiGiayToChungThucDTO>> getAllDanhMucLoaiGiayToChungThucs(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of DanhMucLoaiGiayToChungThucs");
        Page<DanhMucLoaiGiayToChungThucDTO> page = danhMucLoaiGiayToChungThucService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /danh-muc-loai-giay-to-chung-thucs/:id} : get the "id" danhMucLoaiGiayToChungThuc.
     *
     * @param id the id of the danhMucLoaiGiayToChungThucDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhMucLoaiGiayToChungThucDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DanhMucLoaiGiayToChungThucDTO> getDanhMucLoaiGiayToChungThuc(@PathVariable("id") String id) {
        LOG.debug("REST request to get DanhMucLoaiGiayToChungThuc : {}", id);
        Optional<DanhMucLoaiGiayToChungThucDTO> danhMucLoaiGiayToChungThucDTO = danhMucLoaiGiayToChungThucService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhMucLoaiGiayToChungThucDTO);
    }

    /**
     * {@code DELETE  /danh-muc-loai-giay-to-chung-thucs/:id} : delete the "id" danhMucLoaiGiayToChungThuc.
     *
     * @param id the id of the danhMucLoaiGiayToChungThucDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanhMucLoaiGiayToChungThuc(@PathVariable("id") String id) {
        LOG.debug("REST request to delete DanhMucLoaiGiayToChungThuc : {}", id);
        danhMucLoaiGiayToChungThucService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    @GetMapping("/by-loai-so/{idLoaiSo}")
    public ResponseEntity<List<DanhMucLoaiGiayToChungThucDTO>> getByIdLoaiSo(@PathVariable String idLoaiSo) {
        LOG.debug("REST request to get DanhMucLoaiGiayToChungThucs by idLoaiSo: {}", idLoaiSo);
        List<DanhMucLoaiGiayToChungThucDTO> result = danhMucLoaiGiayToChungThucService.findByIdLoaiSo(idLoaiSo);
        return ResponseEntity.ok().body(result);
    }
}
