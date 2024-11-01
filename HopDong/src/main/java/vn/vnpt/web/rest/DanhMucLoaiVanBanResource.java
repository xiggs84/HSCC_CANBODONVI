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
import vn.vnpt.repository.DanhMucLoaiVanBanRepository;
import vn.vnpt.service.DanhMucLoaiVanBanService;
import vn.vnpt.service.dto.DanhMucLoaiVanBanDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DanhMucLoaiVanBan}.
 */
@RestController
@RequestMapping("/api/danh-muc-loai-van-bans")
public class DanhMucLoaiVanBanResource {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucLoaiVanBanResource.class);

    private static final String ENTITY_NAME = "hopDongDanhMucLoaiVanBan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhMucLoaiVanBanService danhMucLoaiVanBanService;

    private final DanhMucLoaiVanBanRepository danhMucLoaiVanBanRepository;

    public DanhMucLoaiVanBanResource(
        DanhMucLoaiVanBanService danhMucLoaiVanBanService,
        DanhMucLoaiVanBanRepository danhMucLoaiVanBanRepository
    ) {
        this.danhMucLoaiVanBanService = danhMucLoaiVanBanService;
        this.danhMucLoaiVanBanRepository = danhMucLoaiVanBanRepository;
    }

    /**
     * {@code POST  /danh-muc-loai-van-bans} : Create a new danhMucLoaiVanBan.
     *
     * @param danhMucLoaiVanBanDTO the danhMucLoaiVanBanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhMucLoaiVanBanDTO, or with status {@code 400 (Bad Request)} if the danhMucLoaiVanBan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DanhMucLoaiVanBanDTO> createDanhMucLoaiVanBan(@RequestBody DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save DanhMucLoaiVanBan : {}", danhMucLoaiVanBanDTO);
        if (danhMucLoaiVanBanDTO.getId() != null) {
            throw new BadRequestAlertException("A new danhMucLoaiVanBan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        danhMucLoaiVanBanDTO = danhMucLoaiVanBanService.save(danhMucLoaiVanBanDTO);
        return ResponseEntity.created(new URI("/api/danh-muc-loai-van-bans/" + danhMucLoaiVanBanDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, danhMucLoaiVanBanDTO.getId()))
            .body(danhMucLoaiVanBanDTO);
    }

    /**
     * {@code PUT  /danh-muc-loai-van-bans/:id} : Updates an existing danhMucLoaiVanBan.
     *
     * @param id the id of the danhMucLoaiVanBanDTO to save.
     * @param danhMucLoaiVanBanDTO the danhMucLoaiVanBanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucLoaiVanBanDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucLoaiVanBanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhMucLoaiVanBanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DanhMucLoaiVanBanDTO> updateDanhMucLoaiVanBan(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DanhMucLoaiVanBan : {}, {}", id, danhMucLoaiVanBanDTO);
        if (danhMucLoaiVanBanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhMucLoaiVanBanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucLoaiVanBanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        danhMucLoaiVanBanDTO = danhMucLoaiVanBanService.update(danhMucLoaiVanBanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucLoaiVanBanDTO.getId()))
            .body(danhMucLoaiVanBanDTO);
    }

    /**
     * {@code PATCH  /danh-muc-loai-van-bans/:id} : Partial updates given fields of an existing danhMucLoaiVanBan, field will ignore if it is null
     *
     * @param id the id of the danhMucLoaiVanBanDTO to save.
     * @param danhMucLoaiVanBanDTO the danhMucLoaiVanBanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucLoaiVanBanDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucLoaiVanBanDTO is not valid,
     * or with status {@code 404 (Not Found)} if the danhMucLoaiVanBanDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhMucLoaiVanBanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhMucLoaiVanBanDTO> partialUpdateDanhMucLoaiVanBan(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DanhMucLoaiVanBan partially : {}, {}", id, danhMucLoaiVanBanDTO);
        if (danhMucLoaiVanBanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhMucLoaiVanBanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucLoaiVanBanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhMucLoaiVanBanDTO> result = danhMucLoaiVanBanService.partialUpdate(danhMucLoaiVanBanDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucLoaiVanBanDTO.getId())
        );
    }

    /**
     * {@code GET  /danh-muc-loai-van-bans} : get all the danhMucLoaiVanBans.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhMucLoaiVanBans in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DanhMucLoaiVanBanDTO>> getAllDanhMucLoaiVanBans(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of DanhMucLoaiVanBans");
        Page<DanhMucLoaiVanBanDTO> page = danhMucLoaiVanBanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /danh-muc-loai-van-bans/:id} : get the "id" danhMucLoaiVanBan.
     *
     * @param id the id of the danhMucLoaiVanBanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhMucLoaiVanBanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DanhMucLoaiVanBanDTO> getDanhMucLoaiVanBan(@PathVariable("id") String id) {
        LOG.debug("REST request to get DanhMucLoaiVanBan : {}", id);
        Optional<DanhMucLoaiVanBanDTO> danhMucLoaiVanBanDTO = danhMucLoaiVanBanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhMucLoaiVanBanDTO);
    }

    /**
     * {@code DELETE  /danh-muc-loai-van-bans/:id} : delete the "id" danhMucLoaiVanBan.
     *
     * @param id the id of the danhMucLoaiVanBanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanhMucLoaiVanBan(@PathVariable("id") String id) {
        LOG.debug("REST request to delete DanhMucLoaiVanBan : {}", id);
        danhMucLoaiVanBanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    @GetMapping("/loai-hop-dong/{idLoaiHopDong}")
    public ResponseEntity<List<DanhMucLoaiVanBanDTO>> getDanhMucLoaiVanBanByIdLoaiHopDong(@PathVariable String idLoaiHopDong) {
        LOG.debug("REST request to get DanhMucLoaiVanBan by idLoaiHopDong : {}", idLoaiHopDong);
        List<DanhMucLoaiVanBanDTO> danhMucLoaiVanBans = danhMucLoaiVanBanService.findByIdLoaiHopDong(idLoaiHopDong);
        return ResponseEntity.ok().body(danhMucLoaiVanBans);
    }
}
