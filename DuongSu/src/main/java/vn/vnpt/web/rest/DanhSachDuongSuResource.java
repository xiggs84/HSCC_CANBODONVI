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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;
import vn.vnpt.repository.DanhSachDuongSuRepository;
import vn.vnpt.service.DanhSachDuongSuService;
import vn.vnpt.service.dto.DanhSachDuongSuDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DanhSachDuongSu}.
 */
@RestController
@RequestMapping("/api/danh-sach-duong-sus")
public class DanhSachDuongSuResource {

    private static final Logger LOG = LoggerFactory.getLogger(DanhSachDuongSuResource.class);

    private static final String ENTITY_NAME = "duongSuDanhSachDuongSu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhSachDuongSuService danhSachDuongSuService;

    private final DanhSachDuongSuRepository danhSachDuongSuRepository;

    public DanhSachDuongSuResource(DanhSachDuongSuService danhSachDuongSuService, DanhSachDuongSuRepository danhSachDuongSuRepository) {
        this.danhSachDuongSuService = danhSachDuongSuService;
        this.danhSachDuongSuRepository = danhSachDuongSuRepository;
    }

    /**
     * {@code POST  /danh-sach-duong-sus} : Create a new danhSachDuongSu.
     *
     * @param danhSachDuongSuDTO the danhSachDuongSuDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhSachDuongSuDTO, or with status {@code 400 (Bad Request)} if the danhSachDuongSu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DanhSachDuongSuDTO> createDanhSachDuongSu(@Valid @RequestBody DanhSachDuongSuDTO danhSachDuongSuDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save DanhSachDuongSu : {}", danhSachDuongSuDTO);
        if (danhSachDuongSuDTO.getId() != null) {
            throw new BadRequestAlertException("A new danhSachDuongSu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        danhSachDuongSuDTO = danhSachDuongSuService.save(danhSachDuongSuDTO);
        return ResponseEntity.created(new URI("/api/danh-sach-duong-sus/" + danhSachDuongSuDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, danhSachDuongSuDTO.getId().toString()))
            .body(danhSachDuongSuDTO);
    }

    /**
     * {@code PUT  /danh-sach-duong-sus/:id} : Updates an existing danhSachDuongSu.
     *
     * @param id the id of the danhSachDuongSuDTO to save.
     * @param danhSachDuongSuDTO the danhSachDuongSuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhSachDuongSuDTO,
     * or with status {@code 400 (Bad Request)} if the danhSachDuongSuDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhSachDuongSuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DanhSachDuongSuDTO> updateDanhSachDuongSu(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DanhSachDuongSuDTO danhSachDuongSuDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DanhSachDuongSu : {}, {}", id, danhSachDuongSuDTO);
        if (danhSachDuongSuDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhSachDuongSuDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhSachDuongSuRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        danhSachDuongSuDTO = danhSachDuongSuService.update(danhSachDuongSuDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhSachDuongSuDTO.getId().toString()))
            .body(danhSachDuongSuDTO);
    }

    /**
     * {@code PATCH  /danh-sach-duong-sus/:id} : Partial updates given fields of an existing danhSachDuongSu, field will ignore if it is null
     *
     * @param id the id of the danhSachDuongSuDTO to save.
     * @param danhSachDuongSuDTO the danhSachDuongSuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhSachDuongSuDTO,
     * or with status {@code 400 (Bad Request)} if the danhSachDuongSuDTO is not valid,
     * or with status {@code 404 (Not Found)} if the danhSachDuongSuDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhSachDuongSuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhSachDuongSuDTO> partialUpdateDanhSachDuongSu(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DanhSachDuongSuDTO danhSachDuongSuDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DanhSachDuongSu partially : {}, {}", id, danhSachDuongSuDTO);
        if (danhSachDuongSuDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhSachDuongSuDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhSachDuongSuRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhSachDuongSuDTO> result = danhSachDuongSuService.partialUpdate(danhSachDuongSuDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhSachDuongSuDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /danh-sach-duong-sus} : get all the danhSachDuongSus.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhSachDuongSus in body.
     */
    @GetMapping("")
    public List<DanhSachDuongSuDTO> getAllDanhSachDuongSus() {
        LOG.debug("REST request to get all DanhSachDuongSus");
        return danhSachDuongSuService.findAll();
    }

    /**
     * {@code GET  /danh-sach-duong-sus/:id} : get the "id" danhSachDuongSu.
     *
     * @param id the id of the danhSachDuongSuDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhSachDuongSuDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DanhSachDuongSuDTO> getDanhSachDuongSu(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DanhSachDuongSu : {}", id);
        Optional<DanhSachDuongSuDTO> danhSachDuongSuDTO = danhSachDuongSuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhSachDuongSuDTO);
    }

    /**
     * {@code DELETE  /danh-sach-duong-sus/:id} : delete the "id" danhSachDuongSu.
     *
     * @param id the id of the danhSachDuongSuDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanhSachDuongSu(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DanhSachDuongSu : {}", id);
        danhSachDuongSuService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
