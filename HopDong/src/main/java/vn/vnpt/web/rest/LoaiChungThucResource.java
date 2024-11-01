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
import vn.vnpt.repository.LoaiChungThucRepository;
import vn.vnpt.service.LoaiChungThucService;
import vn.vnpt.service.dto.LoaiChungThucDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.LoaiChungThuc}.
 */
@RestController
@RequestMapping("/api/loai-chung-thucs")
public class LoaiChungThucResource {

    private static final Logger LOG = LoggerFactory.getLogger(LoaiChungThucResource.class);

    private static final String ENTITY_NAME = "hopDongLoaiChungThuc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LoaiChungThucService loaiChungThucService;

    private final LoaiChungThucRepository loaiChungThucRepository;

    public LoaiChungThucResource(LoaiChungThucService loaiChungThucService, LoaiChungThucRepository loaiChungThucRepository) {
        this.loaiChungThucService = loaiChungThucService;
        this.loaiChungThucRepository = loaiChungThucRepository;
    }

    /**
     * {@code POST  /loai-chung-thucs} : Create a new loaiChungThuc.
     *
     * @param loaiChungThucDTO the loaiChungThucDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new loaiChungThucDTO, or with status {@code 400 (Bad Request)} if the loaiChungThuc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<LoaiChungThucDTO> createLoaiChungThuc(@RequestBody LoaiChungThucDTO loaiChungThucDTO) throws URISyntaxException {
        LOG.debug("REST request to save LoaiChungThuc : {}", loaiChungThucDTO);
        if (loaiChungThucDTO.getId() != null) {
            throw new BadRequestAlertException("A new loaiChungThuc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        loaiChungThucDTO = loaiChungThucService.save(loaiChungThucDTO);
        return ResponseEntity.created(new URI("/api/loai-chung-thucs/" + loaiChungThucDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, loaiChungThucDTO.getId()))
            .body(loaiChungThucDTO);
    }

    /**
     * {@code PUT  /loai-chung-thucs/:id} : Updates an existing loaiChungThuc.
     *
     * @param id the id of the loaiChungThucDTO to save.
     * @param loaiChungThucDTO the loaiChungThucDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loaiChungThucDTO,
     * or with status {@code 400 (Bad Request)} if the loaiChungThucDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the loaiChungThucDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<LoaiChungThucDTO> updateLoaiChungThuc(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody LoaiChungThucDTO loaiChungThucDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update LoaiChungThuc : {}, {}", id, loaiChungThucDTO);
        if (loaiChungThucDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, loaiChungThucDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!loaiChungThucRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        loaiChungThucDTO = loaiChungThucService.update(loaiChungThucDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, loaiChungThucDTO.getId()))
            .body(loaiChungThucDTO);
    }

    /**
     * {@code PATCH  /loai-chung-thucs/:id} : Partial updates given fields of an existing loaiChungThuc, field will ignore if it is null
     *
     * @param id the id of the loaiChungThucDTO to save.
     * @param loaiChungThucDTO the loaiChungThucDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loaiChungThucDTO,
     * or with status {@code 400 (Bad Request)} if the loaiChungThucDTO is not valid,
     * or with status {@code 404 (Not Found)} if the loaiChungThucDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the loaiChungThucDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LoaiChungThucDTO> partialUpdateLoaiChungThuc(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody LoaiChungThucDTO loaiChungThucDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update LoaiChungThuc partially : {}, {}", id, loaiChungThucDTO);
        if (loaiChungThucDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, loaiChungThucDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!loaiChungThucRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LoaiChungThucDTO> result = loaiChungThucService.partialUpdate(loaiChungThucDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, loaiChungThucDTO.getId())
        );
    }

    /**
     * {@code GET  /loai-chung-thucs} : get all the loaiChungThucs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of loaiChungThucs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<LoaiChungThucDTO>> getAllLoaiChungThucs(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of LoaiChungThucs");
        Page<LoaiChungThucDTO> page = loaiChungThucService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /loai-chung-thucs/:id} : get the "id" loaiChungThuc.
     *
     * @param id the id of the loaiChungThucDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the loaiChungThucDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LoaiChungThucDTO> getLoaiChungThuc(@PathVariable("id") String id) {
        LOG.debug("REST request to get LoaiChungThuc : {}", id);
        Optional<LoaiChungThucDTO> loaiChungThucDTO = loaiChungThucService.findOne(id);
        return ResponseUtil.wrapOrNotFound(loaiChungThucDTO);
    }

    /**
     * {@code DELETE  /loai-chung-thucs/:id} : delete the "id" loaiChungThuc.
     *
     * @param id the id of the loaiChungThucDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoaiChungThuc(@PathVariable("id") String id) {
        LOG.debug("REST request to delete LoaiChungThuc : {}", id);
        loaiChungThucService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
