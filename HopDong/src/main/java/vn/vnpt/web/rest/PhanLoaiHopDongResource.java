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
import vn.vnpt.repository.PhanLoaiHopDongRepository;
import vn.vnpt.service.PhanLoaiHopDongService;
import vn.vnpt.service.dto.PhanLoaiHopDongDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.PhanLoaiHopDong}.
 */
@RestController
@RequestMapping("/api/phan-loai-hop-dongs")
public class PhanLoaiHopDongResource {

    private static final Logger LOG = LoggerFactory.getLogger(PhanLoaiHopDongResource.class);

    private static final String ENTITY_NAME = "hopDongPhanLoaiHopDong";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PhanLoaiHopDongService phanLoaiHopDongService;

    private final PhanLoaiHopDongRepository phanLoaiHopDongRepository;

    public PhanLoaiHopDongResource(PhanLoaiHopDongService phanLoaiHopDongService, PhanLoaiHopDongRepository phanLoaiHopDongRepository) {
        this.phanLoaiHopDongService = phanLoaiHopDongService;
        this.phanLoaiHopDongRepository = phanLoaiHopDongRepository;
    }

    /**
     * {@code POST  /phan-loai-hop-dongs} : Create a new phanLoaiHopDong.
     *
     * @param phanLoaiHopDongDTO the phanLoaiHopDongDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new phanLoaiHopDongDTO, or with status {@code 400 (Bad Request)} if the phanLoaiHopDong has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<PhanLoaiHopDongDTO> createPhanLoaiHopDong(@RequestBody PhanLoaiHopDongDTO phanLoaiHopDongDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save PhanLoaiHopDong : {}", phanLoaiHopDongDTO);
        if (phanLoaiHopDongDTO.getId() != null) {
            throw new BadRequestAlertException("A new phanLoaiHopDong cannot already have an ID", ENTITY_NAME, "idexists");
        }
        phanLoaiHopDongDTO = phanLoaiHopDongService.save(phanLoaiHopDongDTO);
        return ResponseEntity.created(new URI("/api/phan-loai-hop-dongs/" + phanLoaiHopDongDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, phanLoaiHopDongDTO.getId()))
            .body(phanLoaiHopDongDTO);
    }

    /**
     * {@code PUT  /phan-loai-hop-dongs/:id} : Updates an existing phanLoaiHopDong.
     *
     * @param id the id of the phanLoaiHopDongDTO to save.
     * @param phanLoaiHopDongDTO the phanLoaiHopDongDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated phanLoaiHopDongDTO,
     * or with status {@code 400 (Bad Request)} if the phanLoaiHopDongDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the phanLoaiHopDongDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PhanLoaiHopDongDTO> updatePhanLoaiHopDong(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody PhanLoaiHopDongDTO phanLoaiHopDongDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update PhanLoaiHopDong : {}, {}", id, phanLoaiHopDongDTO);
        if (phanLoaiHopDongDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, phanLoaiHopDongDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!phanLoaiHopDongRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        phanLoaiHopDongDTO = phanLoaiHopDongService.update(phanLoaiHopDongDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, phanLoaiHopDongDTO.getId()))
            .body(phanLoaiHopDongDTO);
    }

    /**
     * {@code PATCH  /phan-loai-hop-dongs/:id} : Partial updates given fields of an existing phanLoaiHopDong, field will ignore if it is null
     *
     * @param id the id of the phanLoaiHopDongDTO to save.
     * @param phanLoaiHopDongDTO the phanLoaiHopDongDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated phanLoaiHopDongDTO,
     * or with status {@code 400 (Bad Request)} if the phanLoaiHopDongDTO is not valid,
     * or with status {@code 404 (Not Found)} if the phanLoaiHopDongDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the phanLoaiHopDongDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PhanLoaiHopDongDTO> partialUpdatePhanLoaiHopDong(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody PhanLoaiHopDongDTO phanLoaiHopDongDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update PhanLoaiHopDong partially : {}, {}", id, phanLoaiHopDongDTO);
        if (phanLoaiHopDongDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, phanLoaiHopDongDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!phanLoaiHopDongRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PhanLoaiHopDongDTO> result = phanLoaiHopDongService.partialUpdate(phanLoaiHopDongDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, phanLoaiHopDongDTO.getId())
        );
    }

    /**
     * {@code GET  /phan-loai-hop-dongs} : get all the phanLoaiHopDongs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of phanLoaiHopDongs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<PhanLoaiHopDongDTO>> getAllPhanLoaiHopDongs(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of PhanLoaiHopDongs");
        Page<PhanLoaiHopDongDTO> page = phanLoaiHopDongService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /phan-loai-hop-dongs/:id} : get the "id" phanLoaiHopDong.
     *
     * @param id the id of the phanLoaiHopDongDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the phanLoaiHopDongDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PhanLoaiHopDongDTO> getPhanLoaiHopDong(@PathVariable("id") String id) {
        LOG.debug("REST request to get PhanLoaiHopDong : {}", id);
        Optional<PhanLoaiHopDongDTO> phanLoaiHopDongDTO = phanLoaiHopDongService.findOne(id);
        return ResponseUtil.wrapOrNotFound(phanLoaiHopDongDTO);
    }

    /**
     * {@code DELETE  /phan-loai-hop-dongs/:id} : delete the "id" phanLoaiHopDong.
     *
     * @param id the id of the phanLoaiHopDongDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhanLoaiHopDong(@PathVariable("id") String id) {
        LOG.debug("REST request to delete PhanLoaiHopDong : {}", id);
        phanLoaiHopDongService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    @GetMapping("/by-nhom-hop-dong/{idNhomHopDong}")
    public ResponseEntity<List<PhanLoaiHopDongDTO>> getPhanLoaiHopDongByIdNhomHopDong(@PathVariable("idNhomHopDong") String idNhomHopDong) {
        LOG.debug("REST request to get PhanLoaiHopDong by idNhomHopDong : {}", idNhomHopDong);
        List<PhanLoaiHopDongDTO> result = phanLoaiHopDongService.findByIdNhomHopDong(idNhomHopDong);
        return ResponseEntity.ok().body(result);
    }
}
