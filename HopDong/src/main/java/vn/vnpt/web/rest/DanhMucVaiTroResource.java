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
import vn.vnpt.repository.DanhMucVaiTroRepository;
import vn.vnpt.service.DanhMucVaiTroService;
import vn.vnpt.service.dto.DanhMucVaiTroDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DanhMucVaiTro}.
 */
@RestController
@RequestMapping("/api/danh-muc-vai-tros")
public class DanhMucVaiTroResource {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucVaiTroResource.class);

    private static final String ENTITY_NAME = "hopDongDanhMucVaiTro";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhMucVaiTroService danhMucVaiTroService;

    private final DanhMucVaiTroRepository danhMucVaiTroRepository;

    public DanhMucVaiTroResource(DanhMucVaiTroService danhMucVaiTroService, DanhMucVaiTroRepository danhMucVaiTroRepository) {
        this.danhMucVaiTroService = danhMucVaiTroService;
        this.danhMucVaiTroRepository = danhMucVaiTroRepository;
    }

    /**
     * {@code POST  /danh-muc-vai-tros} : Create a new danhMucVaiTro.
     *
     * @param danhMucVaiTroDTO the danhMucVaiTroDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhMucVaiTroDTO, or with status {@code 400 (Bad Request)} if the danhMucVaiTro has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DanhMucVaiTroDTO> createDanhMucVaiTro(@RequestBody DanhMucVaiTroDTO danhMucVaiTroDTO) throws URISyntaxException {
        LOG.debug("REST request to save DanhMucVaiTro : {}", danhMucVaiTroDTO);
        if (danhMucVaiTroDTO.getId() != null) {
            throw new BadRequestAlertException("A new danhMucVaiTro cannot already have an ID", ENTITY_NAME, "idexists");
        }
        danhMucVaiTroDTO = danhMucVaiTroService.save(danhMucVaiTroDTO);
        return ResponseEntity.created(new URI("/api/danh-muc-vai-tros/" + danhMucVaiTroDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, danhMucVaiTroDTO.getId()))
            .body(danhMucVaiTroDTO);
    }

    /**
     * {@code PUT  /danh-muc-vai-tros/:id} : Updates an existing danhMucVaiTro.
     *
     * @param id the id of the danhMucVaiTroDTO to save.
     * @param danhMucVaiTroDTO the danhMucVaiTroDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucVaiTroDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucVaiTroDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhMucVaiTroDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DanhMucVaiTroDTO> updateDanhMucVaiTro(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DanhMucVaiTroDTO danhMucVaiTroDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DanhMucVaiTro : {}, {}", id, danhMucVaiTroDTO);
        if (danhMucVaiTroDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhMucVaiTroDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucVaiTroRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        danhMucVaiTroDTO = danhMucVaiTroService.update(danhMucVaiTroDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucVaiTroDTO.getId()))
            .body(danhMucVaiTroDTO);
    }

    /**
     * {@code PATCH  /danh-muc-vai-tros/:id} : Partial updates given fields of an existing danhMucVaiTro, field will ignore if it is null
     *
     * @param id the id of the danhMucVaiTroDTO to save.
     * @param danhMucVaiTroDTO the danhMucVaiTroDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhMucVaiTroDTO,
     * or with status {@code 400 (Bad Request)} if the danhMucVaiTroDTO is not valid,
     * or with status {@code 404 (Not Found)} if the danhMucVaiTroDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhMucVaiTroDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhMucVaiTroDTO> partialUpdateDanhMucVaiTro(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DanhMucVaiTroDTO danhMucVaiTroDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DanhMucVaiTro partially : {}, {}", id, danhMucVaiTroDTO);
        if (danhMucVaiTroDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhMucVaiTroDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhMucVaiTroRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhMucVaiTroDTO> result = danhMucVaiTroService.partialUpdate(danhMucVaiTroDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhMucVaiTroDTO.getId())
        );
    }

    /**
     * {@code GET  /danh-muc-vai-tros} : get all the danhMucVaiTros.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhMucVaiTros in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DanhMucVaiTroDTO>> getAllDanhMucVaiTros(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of DanhMucVaiTros");
        Page<DanhMucVaiTroDTO> page = danhMucVaiTroService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /danh-muc-vai-tros/:id} : get the "id" danhMucVaiTro.
     *
     * @param id the id of the danhMucVaiTroDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhMucVaiTroDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DanhMucVaiTroDTO> getDanhMucVaiTro(@PathVariable("id") String id) {
        LOG.debug("REST request to get DanhMucVaiTro : {}", id);
        Optional<DanhMucVaiTroDTO> danhMucVaiTroDTO = danhMucVaiTroService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhMucVaiTroDTO);
    }

    /**
     * {@code DELETE  /danh-muc-vai-tros/:id} : delete the "id" danhMucVaiTro.
     *
     * @param id the id of the danhMucVaiTroDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanhMucVaiTro(@PathVariable("id") String id) {
        LOG.debug("REST request to delete DanhMucVaiTro : {}", id);
        danhMucVaiTroService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    @GetMapping("/{id}/dien-giai")
    public ResponseEntity<String> getDienGiaiById(@PathVariable("id") String id) {
        LOG.debug("REST request to get dienGiai of DanhMucVaiTro : {}", id);
        Optional<String> dienGiai = danhMucVaiTroService.findDienGiaiById(id);
        return ResponseUtil.wrapOrNotFound(dienGiai);
    }
}
