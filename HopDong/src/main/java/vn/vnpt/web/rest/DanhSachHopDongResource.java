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
import vn.vnpt.repository.DanhSachHopDongRepository;
import vn.vnpt.service.DanhSachHopDongService;
import vn.vnpt.service.dto.DanhSachHopDongDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DanhSachHopDong}.
 */
@RestController
@RequestMapping("/api/danh-sach-hop-dongs")
public class DanhSachHopDongResource {

    private static final Logger LOG = LoggerFactory.getLogger(DanhSachHopDongResource.class);

    private static final String ENTITY_NAME = "hopDongDanhSachHopDong";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhSachHopDongService danhSachHopDongService;

    private final DanhSachHopDongRepository danhSachHopDongRepository;

    public DanhSachHopDongResource(DanhSachHopDongService danhSachHopDongService, DanhSachHopDongRepository danhSachHopDongRepository) {
        this.danhSachHopDongService = danhSachHopDongService;
        this.danhSachHopDongRepository = danhSachHopDongRepository;
    }

    /**
     * {@code POST  /danh-sach-hop-dongs} : Create a new danhSachHopDong.
     *
     * @param danhSachHopDongDTO the danhSachHopDongDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhSachHopDongDTO, or with status {@code 400 (Bad Request)} if the danhSachHopDong has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DanhSachHopDongDTO> createDanhSachHopDong(@RequestBody DanhSachHopDongDTO danhSachHopDongDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save DanhSachHopDong : {}", danhSachHopDongDTO);
        if (danhSachHopDongDTO.getId() != null) {
            throw new BadRequestAlertException("A new danhSachHopDong cannot already have an ID", ENTITY_NAME, "idexists");
        }
        danhSachHopDongDTO = danhSachHopDongService.save(danhSachHopDongDTO);
        return ResponseEntity.created(new URI("/api/danh-sach-hop-dongs/" + danhSachHopDongDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, danhSachHopDongDTO.getId()))
            .body(danhSachHopDongDTO);
    }

    /**
     * {@code PUT  /danh-sach-hop-dongs/:id} : Updates an existing danhSachHopDong.
     *
     * @param id the id of the danhSachHopDongDTO to save.
     * @param danhSachHopDongDTO the danhSachHopDongDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhSachHopDongDTO,
     * or with status {@code 400 (Bad Request)} if the danhSachHopDongDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhSachHopDongDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DanhSachHopDongDTO> updateDanhSachHopDong(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DanhSachHopDongDTO danhSachHopDongDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DanhSachHopDong : {}, {}", id, danhSachHopDongDTO);
        if (danhSachHopDongDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhSachHopDongDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhSachHopDongRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        danhSachHopDongDTO = danhSachHopDongService.update(danhSachHopDongDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhSachHopDongDTO.getId()))
            .body(danhSachHopDongDTO);
    }

    /**
     * {@code PATCH  /danh-sach-hop-dongs/:id} : Partial updates given fields of an existing danhSachHopDong, field will ignore if it is null
     *
     * @param id the id of the danhSachHopDongDTO to save.
     * @param danhSachHopDongDTO the danhSachHopDongDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhSachHopDongDTO,
     * or with status {@code 400 (Bad Request)} if the danhSachHopDongDTO is not valid,
     * or with status {@code 404 (Not Found)} if the danhSachHopDongDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhSachHopDongDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhSachHopDongDTO> partialUpdateDanhSachHopDong(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DanhSachHopDongDTO danhSachHopDongDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DanhSachHopDong partially : {}, {}", id, danhSachHopDongDTO);
        if (danhSachHopDongDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhSachHopDongDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhSachHopDongRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhSachHopDongDTO> result = danhSachHopDongService.partialUpdate(danhSachHopDongDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhSachHopDongDTO.getId())
        );
    }

    /**
     * {@code GET  /danh-sach-hop-dongs} : get all the danhSachHopDongs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhSachHopDongs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DanhSachHopDongDTO>> getAllDanhSachHopDongs(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of DanhSachHopDongs");
        Page<DanhSachHopDongDTO> page = danhSachHopDongService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /danh-sach-hop-dongs/:id} : get the "id" danhSachHopDong.
     *
     * @param id the id of the danhSachHopDongDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhSachHopDongDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DanhSachHopDongDTO> getDanhSachHopDong(@PathVariable("id") String id) {
        LOG.debug("REST request to get DanhSachHopDong : {}", id);
        Optional<DanhSachHopDongDTO> danhSachHopDongDTO = danhSachHopDongService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhSachHopDongDTO);
    }

    /**
     * {@code DELETE  /danh-sach-hop-dongs/:id} : delete the "id" danhSachHopDong.
     *
     * @param id the id of the danhSachHopDongDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanhSachHopDong(@PathVariable("id") String id) {
        LOG.debug("REST request to delete DanhSachHopDong : {}", id);
        danhSachHopDongService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
