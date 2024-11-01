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
import vn.vnpt.repository.DanhMucLoaiHopDongRepository;
import vn.vnpt.service.DanhMucLoaiHopDongService;
import vn.vnpt.service.dto.DanhMucLoaiHopDongDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DanhMucLoaiHopDong}.
 */
@RestController
@RequestMapping("/api/danh-muc-loai-hop-dongs")
public class DanhMucLoaiHopDongResource {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucLoaiHopDongResource.class);

    private static final String ENTITY_NAME = "hopDongDanhMucLoaiHopDong";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhMucLoaiHopDongService danhMucLoaiHopDongService;

    private final DanhMucLoaiHopDongRepository danhMucLoaiHopDongRepository;

    public DanhMucLoaiHopDongResource(
        DanhMucLoaiHopDongService danhMucLoaiHopDongService,
        DanhMucLoaiHopDongRepository danhMucLoaiHopDongRepository
    ) {
        this.danhMucLoaiHopDongService = danhMucLoaiHopDongService;
        this.danhMucLoaiHopDongRepository = danhMucLoaiHopDongRepository;
    }

    /**
     * {@code POST  /danh-muc-loai-hop-dongs} : Create a new danhMucLoaiHopDong.
     *
     * @param danhMucLoaiHopDongDTO the danhMucLoaiHopDongDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhMucLoaiHopDongDTO, or with status {@code 400 (Bad Request)} if the danhMucLoaiHopDong has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DanhMucLoaiHopDongDTO> createDanhMucLoaiHopDong(@RequestBody DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save DanhMucLoaiHopDong : {}", danhMucLoaiHopDongDTO);
        if (danhMucLoaiHopDongDTO.getId() != null) {
            throw new BadRequestAlertException("A new danhMucLoaiHopDong cannot already have an ID", ENTITY_NAME, "idexists");
        }
        danhMucLoaiHopDongDTO = danhMucLoaiHopDongService.save(danhMucLoaiHopDongDTO);
        return ResponseEntity.created(new URI("/api/danh-muc-loai-hop-dongs/" + danhMucLoaiHopDongDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, danhMucLoaiHopDongDTO.getId()))
            .body(danhMucLoaiHopDongDTO);
    }

    /**
     * {@code PUT  /danh-muc-loai-hop-dongs/:id} : Updates an existing danhMucLoaiHopDong.
     *
     * @param id the id of the danhMucLoaiHopDongDTO to save.
     * @param danhMucLoaiHopDongDTO the danhMucLoaiHopDongDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucLoaiHopDongDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucLoaiHopDongDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhMucLoaiHopDongDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DanhMucLoaiHopDongDTO> updateDanhMucLoaiHopDong(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DanhMucLoaiHopDong : {}, {}", id, danhMucLoaiHopDongDTO);
        if (danhMucLoaiHopDongDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhMucLoaiHopDongDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucLoaiHopDongRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        danhMucLoaiHopDongDTO = danhMucLoaiHopDongService.update(danhMucLoaiHopDongDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucLoaiHopDongDTO.getId()))
            .body(danhMucLoaiHopDongDTO);
    }

    /**
     * {@code PATCH  /danh-muc-loai-hop-dongs/:id} : Partial updates given fields of an existing danhMucLoaiHopDong, field will ignore if it is null
     *
     * @param id the id of the danhMucLoaiHopDongDTO to save.
     * @param danhMucLoaiHopDongDTO the danhMucLoaiHopDongDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucLoaiHopDongDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucLoaiHopDongDTO is not valid,
     * or with status {@code 404 (Not Found)} if the danhMucLoaiHopDongDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhMucLoaiHopDongDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhMucLoaiHopDongDTO> partialUpdateDanhMucLoaiHopDong(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DanhMucLoaiHopDong partially : {}, {}", id, danhMucLoaiHopDongDTO);
        if (danhMucLoaiHopDongDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhMucLoaiHopDongDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucLoaiHopDongRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhMucLoaiHopDongDTO> result = danhMucLoaiHopDongService.partialUpdate(danhMucLoaiHopDongDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucLoaiHopDongDTO.getId())
        );
    }

    /**
     * {@code GET  /danh-muc-loai-hop-dongs} : get all the danhMucLoaiHopDongs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhMucLoaiHopDongs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DanhMucLoaiHopDongDTO>> getAllDanhMucLoaiHopDongs(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of DanhMucLoaiHopDongs");
        Page<DanhMucLoaiHopDongDTO> page = danhMucLoaiHopDongService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /danh-muc-loai-hop-dongs/:id} : get the "id" danhMucLoaiHopDong.
     *
     * @param id the id of the danhMucLoaiHopDongDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhMucLoaiHopDongDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DanhMucLoaiHopDongDTO> getDanhMucLoaiHopDong(@PathVariable("id") String id) {
        LOG.debug("REST request to get DanhMucLoaiHopDong : {}", id);
        Optional<DanhMucLoaiHopDongDTO> danhMucLoaiHopDongDTO = danhMucLoaiHopDongService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhMucLoaiHopDongDTO);
    }

    /**
     * {@code DELETE  /danh-muc-loai-hop-dongs/:id} : delete the "id" danhMucLoaiHopDong.
     *
     * @param id the id of the danhMucLoaiHopDongDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanhMucLoaiHopDong(@PathVariable("id") String id) {
        LOG.debug("REST request to delete DanhMucLoaiHopDong : {}", id);
        danhMucLoaiHopDongService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    @GetMapping("/by-donvi/{idDonVi}")
    public ResponseEntity<List<DanhMucLoaiHopDongDTO>> getAllByDonVi(@PathVariable Long idDonVi) {
        LOG.debug("REST request to get all DanhMucLoaiHopDongs by idDonVi : {}", idDonVi);
        List<DanhMucLoaiHopDongDTO> list = danhMucLoaiHopDongService.findByIdDonVi(idDonVi);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DanhMucLoaiHopDongDTO>> search(
        @RequestParam(required = false) String dienGiai,
        @RequestParam(required = false) String idNhomHopDong,
        @RequestParam(required = false) String idVaiTro1,
        @RequestParam(required = false) String idVaiTro2,
        @RequestParam(required = false) Long idDonVi) {
        LOG.debug("REST request to search DanhMucLoaiHopDong");
        List<DanhMucLoaiHopDongDTO> results = danhMucLoaiHopDongService.search(dienGiai, idNhomHopDong, idVaiTro1, idVaiTro2, idDonVi);
        return ResponseEntity.ok().body(results);
    }

    @GetMapping("/loai-hop-dong")
    public ResponseEntity<List<DanhMucLoaiHopDongDTO>> findByGroup(@RequestParam String nhom) {
        List<DanhMucLoaiHopDongDTO> result = danhMucLoaiHopDongService.findByGroup(nhom);
        return ResponseEntity.ok().body(result);
    }
}
