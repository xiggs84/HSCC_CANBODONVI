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
import vn.vnpt.repository.DanhMucNhomHopDongRepository;
import vn.vnpt.service.DanhMucNhomHopDongService;
import vn.vnpt.service.dto.DanhMucNhomHopDongDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DanhMucNhomHopDong}.
 */
@RestController
@RequestMapping("/api/danh-muc-nhom-hop-dongs")
public class DanhMucNhomHopDongResource {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucNhomHopDongResource.class);

    private static final String ENTITY_NAME = "hopDongDanhMucNhomHopDong";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhMucNhomHopDongService danhMucNhomHopDongService;

    private final DanhMucNhomHopDongRepository danhMucNhomHopDongRepository;

    public DanhMucNhomHopDongResource(
        DanhMucNhomHopDongService danhMucNhomHopDongService,
        DanhMucNhomHopDongRepository danhMucNhomHopDongRepository
    ) {
        this.danhMucNhomHopDongService = danhMucNhomHopDongService;
        this.danhMucNhomHopDongRepository = danhMucNhomHopDongRepository;
    }

    /**
     * {@code POST  /danh-muc-nhom-hop-dongs} : Create a new danhMucNhomHopDong.
     *
     * @param danhMucNhomHopDongDTO the danhMucNhomHopDongDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhMucNhomHopDongDTO, or with status {@code 400 (Bad Request)} if the danhMucNhomHopDong has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DanhMucNhomHopDongDTO> createDanhMucNhomHopDong(@RequestBody DanhMucNhomHopDongDTO danhMucNhomHopDongDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save DanhMucNhomHopDong : {}", danhMucNhomHopDongDTO);
        if (danhMucNhomHopDongDTO.getId() != null) {
            throw new BadRequestAlertException("A new danhMucNhomHopDong cannot already have an ID", ENTITY_NAME, "idexists");
        }
        danhMucNhomHopDongDTO = danhMucNhomHopDongService.save(danhMucNhomHopDongDTO);
        return ResponseEntity.created(new URI("/api/danh-muc-nhom-hop-dongs/" + danhMucNhomHopDongDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, danhMucNhomHopDongDTO.getId()))
            .body(danhMucNhomHopDongDTO);
    }

    /**
     * {@code PUT  /danh-muc-nhom-hop-dongs/:id} : Updates an existing danhMucNhomHopDong.
     *
     * @param id the id of the danhMucNhomHopDongDTO to save.
     * @param danhMucNhomHopDongDTO the danhMucNhomHopDongDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucNhomHopDongDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucNhomHopDongDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhMucNhomHopDongDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DanhMucNhomHopDongDTO> updateDanhMucNhomHopDong(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DanhMucNhomHopDongDTO danhMucNhomHopDongDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DanhMucNhomHopDong : {}, {}", id, danhMucNhomHopDongDTO);
        if (danhMucNhomHopDongDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhMucNhomHopDongDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucNhomHopDongRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        danhMucNhomHopDongDTO = danhMucNhomHopDongService.update(danhMucNhomHopDongDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucNhomHopDongDTO.getId()))
            .body(danhMucNhomHopDongDTO);
    }

    /**
     * {@code PATCH  /danh-muc-nhom-hop-dongs/:id} : Partial updates given fields of an existing danhMucNhomHopDong, field will ignore if it is null
     *
     * @param id the id of the danhMucNhomHopDongDTO to save.
     * @param danhMucNhomHopDongDTO the danhMucNhomHopDongDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucNhomHopDongDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucNhomHopDongDTO is not valid,
     * or with status {@code 404 (Not Found)} if the danhMucNhomHopDongDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhMucNhomHopDongDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhMucNhomHopDongDTO> partialUpdateDanhMucNhomHopDong(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DanhMucNhomHopDongDTO danhMucNhomHopDongDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DanhMucNhomHopDong partially : {}, {}", id, danhMucNhomHopDongDTO);
        if (danhMucNhomHopDongDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhMucNhomHopDongDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucNhomHopDongRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhMucNhomHopDongDTO> result = danhMucNhomHopDongService.partialUpdate(danhMucNhomHopDongDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucNhomHopDongDTO.getId())
        );
    }

    /**
     * {@code GET  /danh-muc-nhom-hop-dongs} : get all the danhMucNhomHopDongs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhMucNhomHopDongs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DanhMucNhomHopDongDTO>> getAllDanhMucNhomHopDongs(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of DanhMucNhomHopDongs");
        Page<DanhMucNhomHopDongDTO> page = danhMucNhomHopDongService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /danh-muc-nhom-hop-dongs/:id} : get the "id" danhMucNhomHopDong.
     *
     * @param id the id of the danhMucNhomHopDongDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhMucNhomHopDongDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DanhMucNhomHopDongDTO> getDanhMucNhomHopDong(@PathVariable("id") String id) {
        LOG.debug("REST request to get DanhMucNhomHopDong : {}", id);
        Optional<DanhMucNhomHopDongDTO> danhMucNhomHopDongDTO = danhMucNhomHopDongService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhMucNhomHopDongDTO);
    }

    /**
     * {@code DELETE  /danh-muc-nhom-hop-dongs/:id} : delete the "id" danhMucNhomHopDong.
     *
     * @param id the id of the danhMucNhomHopDongDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanhMucNhomHopDong(@PathVariable("id") String id) {
        LOG.debug("REST request to delete DanhMucNhomHopDong : {}", id);
        danhMucNhomHopDongService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    @GetMapping("/{id}/dien-giai")
    public ResponseEntity<String> getDienGiaiById(@PathVariable("id") String id) {
        LOG.debug("REST request to get dienGiai of DanhMucNhomHopDong : {}", id);
        Optional<String> dienGiai = danhMucNhomHopDongService.findDienGiaiById(id);
        return ResponseUtil.wrapOrNotFound(dienGiai);
    }
}
