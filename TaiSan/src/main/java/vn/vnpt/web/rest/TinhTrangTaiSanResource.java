package vn.vnpt.web.rest;

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
import vn.vnpt.repository.TinhTrangTaiSanRepository;
import vn.vnpt.service.TinhTrangTaiSanService;
import vn.vnpt.service.dto.TinhTrangTaiSanDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.TinhTrangTaiSan}.
 */
@RestController
@RequestMapping("/api/tinh-trang-tai-sans")
public class TinhTrangTaiSanResource {

    private static final Logger LOG = LoggerFactory.getLogger(TinhTrangTaiSanResource.class);

    private static final String ENTITY_NAME = "taiSanTinhTrangTaiSan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TinhTrangTaiSanService tinhTrangTaiSanService;

    private final TinhTrangTaiSanRepository tinhTrangTaiSanRepository;

    public TinhTrangTaiSanResource(TinhTrangTaiSanService tinhTrangTaiSanService, TinhTrangTaiSanRepository tinhTrangTaiSanRepository) {
        this.tinhTrangTaiSanService = tinhTrangTaiSanService;
        this.tinhTrangTaiSanRepository = tinhTrangTaiSanRepository;
    }

    /**
     * {@code POST  /tinh-trang-tai-sans} : Create a new tinhTrangTaiSan.
     *
     * @param tinhTrangTaiSanDTO the tinhTrangTaiSanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tinhTrangTaiSanDTO, or with status {@code 400 (Bad Request)} if the tinhTrangTaiSan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TinhTrangTaiSanDTO> createTinhTrangTaiSan(@RequestBody TinhTrangTaiSanDTO tinhTrangTaiSanDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save TinhTrangTaiSan : {}", tinhTrangTaiSanDTO);
        if (tinhTrangTaiSanDTO.getIdTinhTrang() != null) {
            throw new BadRequestAlertException("A new tinhTrangTaiSan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        tinhTrangTaiSanDTO = tinhTrangTaiSanService.save(tinhTrangTaiSanDTO);
        return ResponseEntity.created(new URI("/api/tinh-trang-tai-sans/" + tinhTrangTaiSanDTO.getIdTinhTrang()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, tinhTrangTaiSanDTO.getIdTinhTrang().toString())
            )
            .body(tinhTrangTaiSanDTO);
    }

    /**
     * {@code PUT  /tinh-trang-tai-sans/:idTinhTrang} : Updates an existing tinhTrangTaiSan.
     *
     * @param idTinhTrang the id of the tinhTrangTaiSanDTO to save.
     * @param tinhTrangTaiSanDTO the tinhTrangTaiSanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tinhTrangTaiSanDTO,
     * or with status {@code 400 (Bad Request)} if the tinhTrangTaiSanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tinhTrangTaiSanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{idTinhTrang}")
    public ResponseEntity<TinhTrangTaiSanDTO> updateTinhTrangTaiSan(
        @PathVariable(value = "idTinhTrang", required = false) final Long idTinhTrang,
        @RequestBody TinhTrangTaiSanDTO tinhTrangTaiSanDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update TinhTrangTaiSan : {}, {}", idTinhTrang, tinhTrangTaiSanDTO);
        if (tinhTrangTaiSanDTO.getIdTinhTrang() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idTinhTrang, tinhTrangTaiSanDTO.getIdTinhTrang())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tinhTrangTaiSanRepository.existsById(idTinhTrang)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        tinhTrangTaiSanDTO = tinhTrangTaiSanService.update(tinhTrangTaiSanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tinhTrangTaiSanDTO.getIdTinhTrang().toString()))
            .body(tinhTrangTaiSanDTO);
    }

    /**
     * {@code PATCH  /tinh-trang-tai-sans/:idTinhTrang} : Partial updates given fields of an existing tinhTrangTaiSan, field will ignore if it is null
     *
     * @param idTinhTrang the id of the tinhTrangTaiSanDTO to save.
     * @param tinhTrangTaiSanDTO the tinhTrangTaiSanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tinhTrangTaiSanDTO,
     * or with status {@code 400 (Bad Request)} if the tinhTrangTaiSanDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tinhTrangTaiSanDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tinhTrangTaiSanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{idTinhTrang}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TinhTrangTaiSanDTO> partialUpdateTinhTrangTaiSan(
        @PathVariable(value = "idTinhTrang", required = false) final Long idTinhTrang,
        @RequestBody TinhTrangTaiSanDTO tinhTrangTaiSanDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update TinhTrangTaiSan partially : {}, {}", idTinhTrang, tinhTrangTaiSanDTO);
        if (tinhTrangTaiSanDTO.getIdTinhTrang() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idTinhTrang, tinhTrangTaiSanDTO.getIdTinhTrang())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tinhTrangTaiSanRepository.existsById(idTinhTrang)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TinhTrangTaiSanDTO> result = tinhTrangTaiSanService.partialUpdate(tinhTrangTaiSanDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tinhTrangTaiSanDTO.getIdTinhTrang().toString())
        );
    }

    /**
     * {@code GET  /tinh-trang-tai-sans} : get all the tinhTrangTaiSans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tinhTrangTaiSans in body.
     */
    @GetMapping("")
    public List<TinhTrangTaiSanDTO> getAllTinhTrangTaiSans() {
        LOG.debug("REST request to get all TinhTrangTaiSans");
        return tinhTrangTaiSanService.findAll();
    }

    /**
     * {@code GET  /tinh-trang-tai-sans/:id} : get the "id" tinhTrangTaiSan.
     *
     * @param id the id of the tinhTrangTaiSanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tinhTrangTaiSanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TinhTrangTaiSanDTO> getTinhTrangTaiSan(@PathVariable("id") Long id) {
        LOG.debug("REST request to get TinhTrangTaiSan : {}", id);
        Optional<TinhTrangTaiSanDTO> tinhTrangTaiSanDTO = tinhTrangTaiSanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tinhTrangTaiSanDTO);
    }

    /**
     * {@code DELETE  /tinh-trang-tai-sans/:id} : delete the "id" tinhTrangTaiSan.
     *
     * @param id the id of the tinhTrangTaiSanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTinhTrangTaiSan(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete TinhTrangTaiSan : {}", id);
        tinhTrangTaiSanService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
