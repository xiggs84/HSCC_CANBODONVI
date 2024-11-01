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
import vn.vnpt.repository.LoaiHopDongCongChungRepository;
import vn.vnpt.service.LoaiHopDongCongChungService;
import vn.vnpt.service.dto.LoaiHopDongCongChungDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.LoaiHopDongCongChung}.
 */
@RestController
@RequestMapping("/api/loai-hop-dong-cong-chungs")
public class LoaiHopDongCongChungResource {

    private static final Logger LOG = LoggerFactory.getLogger(LoaiHopDongCongChungResource.class);

    private static final String ENTITY_NAME = "hopDongLoaiHopDongCongChung";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LoaiHopDongCongChungService loaiHopDongCongChungService;

    private final LoaiHopDongCongChungRepository loaiHopDongCongChungRepository;

    public LoaiHopDongCongChungResource(
        LoaiHopDongCongChungService loaiHopDongCongChungService,
        LoaiHopDongCongChungRepository loaiHopDongCongChungRepository
    ) {
        this.loaiHopDongCongChungService = loaiHopDongCongChungService;
        this.loaiHopDongCongChungRepository = loaiHopDongCongChungRepository;
    }

    /**
     * {@code POST  /loai-hop-dong-cong-chungs} : Create a new loaiHopDongCongChung.
     *
     * @param loaiHopDongCongChungDTO the loaiHopDongCongChungDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new loaiHopDongCongChungDTO, or with status {@code 400 (Bad Request)} if the loaiHopDongCongChung has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<LoaiHopDongCongChungDTO> createLoaiHopDongCongChung(@RequestBody LoaiHopDongCongChungDTO loaiHopDongCongChungDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save LoaiHopDongCongChung : {}", loaiHopDongCongChungDTO);
        if (loaiHopDongCongChungDTO.getId() != null) {
            throw new BadRequestAlertException("A new loaiHopDongCongChung cannot already have an ID", ENTITY_NAME, "idexists");
        }
        loaiHopDongCongChungDTO = loaiHopDongCongChungService.save(loaiHopDongCongChungDTO);
        return ResponseEntity.created(new URI("/api/loai-hop-dong-cong-chungs/" + loaiHopDongCongChungDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, loaiHopDongCongChungDTO.getId()))
            .body(loaiHopDongCongChungDTO);
    }

    /**
     * {@code PUT  /loai-hop-dong-cong-chungs/:id} : Updates an existing loaiHopDongCongChung.
     *
     * @param id the id of the loaiHopDongCongChungDTO to save.
     * @param loaiHopDongCongChungDTO the loaiHopDongCongChungDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loaiHopDongCongChungDTO,
     * or with status {@code 400 (Bad Request)} if the loaiHopDongCongChungDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the loaiHopDongCongChungDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<LoaiHopDongCongChungDTO> updateLoaiHopDongCongChung(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody LoaiHopDongCongChungDTO loaiHopDongCongChungDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update LoaiHopDongCongChung : {}, {}", id, loaiHopDongCongChungDTO);
        if (loaiHopDongCongChungDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, loaiHopDongCongChungDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!loaiHopDongCongChungRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        loaiHopDongCongChungDTO = loaiHopDongCongChungService.update(loaiHopDongCongChungDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, loaiHopDongCongChungDTO.getId()))
            .body(loaiHopDongCongChungDTO);
    }

    /**
     * {@code PATCH  /loai-hop-dong-cong-chungs/:id} : Partial updates given fields of an existing loaiHopDongCongChung, field will ignore if it is null
     *
     * @param id the id of the loaiHopDongCongChungDTO to save.
     * @param loaiHopDongCongChungDTO the loaiHopDongCongChungDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loaiHopDongCongChungDTO,
     * or with status {@code 400 (Bad Request)} if the loaiHopDongCongChungDTO is not valid,
     * or with status {@code 404 (Not Found)} if the loaiHopDongCongChungDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the loaiHopDongCongChungDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LoaiHopDongCongChungDTO> partialUpdateLoaiHopDongCongChung(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody LoaiHopDongCongChungDTO loaiHopDongCongChungDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update LoaiHopDongCongChung partially : {}, {}", id, loaiHopDongCongChungDTO);
        if (loaiHopDongCongChungDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, loaiHopDongCongChungDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!loaiHopDongCongChungRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LoaiHopDongCongChungDTO> result = loaiHopDongCongChungService.partialUpdate(loaiHopDongCongChungDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, loaiHopDongCongChungDTO.getId())
        );
    }

    /**
     * {@code GET  /loai-hop-dong-cong-chungs} : get all the loaiHopDongCongChungs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of loaiHopDongCongChungs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<LoaiHopDongCongChungDTO>> getAllLoaiHopDongCongChungs(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of LoaiHopDongCongChungs");
        Page<LoaiHopDongCongChungDTO> page = loaiHopDongCongChungService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /loai-hop-dong-cong-chungs/:id} : get the "id" loaiHopDongCongChung.
     *
     * @param id the id of the loaiHopDongCongChungDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the loaiHopDongCongChungDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LoaiHopDongCongChungDTO> getLoaiHopDongCongChung(@PathVariable("id") String id) {
        LOG.debug("REST request to get LoaiHopDongCongChung : {}", id);
        Optional<LoaiHopDongCongChungDTO> loaiHopDongCongChungDTO = loaiHopDongCongChungService.findOne(id);
        return ResponseUtil.wrapOrNotFound(loaiHopDongCongChungDTO);
    }

    /**
     * {@code DELETE  /loai-hop-dong-cong-chungs/:id} : delete the "id" loaiHopDongCongChung.
     *
     * @param id the id of the loaiHopDongCongChungDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoaiHopDongCongChung(@PathVariable("id") String id) {
        LOG.debug("REST request to delete LoaiHopDongCongChung : {}", id);
        loaiHopDongCongChungService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
