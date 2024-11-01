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
import vn.vnpt.repository.SoChungThucRepository;
import vn.vnpt.service.SoChungThucService;
import vn.vnpt.service.dto.SoChungThucDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.SoChungThuc}.
 */
@RestController
@RequestMapping("/api/so-chung-thucs")
public class SoChungThucResource {

    private static final Logger LOG = LoggerFactory.getLogger(SoChungThucResource.class);

    private static final String ENTITY_NAME = "hopDongSoChungThuc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SoChungThucService soChungThucService;

    private final SoChungThucRepository soChungThucRepository;

    public SoChungThucResource(SoChungThucService soChungThucService, SoChungThucRepository soChungThucRepository) {
        this.soChungThucService = soChungThucService;
        this.soChungThucRepository = soChungThucRepository;
    }

    /**
     * {@code POST  /so-chung-thucs} : Create a new soChungThuc.
     *
     * @param soChungThucDTO the soChungThucDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new soChungThucDTO, or with status {@code 400 (Bad Request)} if the soChungThuc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<SoChungThucDTO> createSoChungThuc(@RequestBody SoChungThucDTO soChungThucDTO) throws URISyntaxException {
        LOG.debug("REST request to save SoChungThuc : {}", soChungThucDTO);
        if (soChungThucDTO.getId() != null) {
            throw new BadRequestAlertException("A new soChungThuc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        soChungThucDTO = soChungThucService.save(soChungThucDTO);
        return ResponseEntity.created(new URI("/api/so-chung-thucs/" + soChungThucDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, soChungThucDTO.getId()))
            .body(soChungThucDTO);
    }

    /**
     * {@code PUT  /so-chung-thucs/:id} : Updates an existing soChungThuc.
     *
     * @param id the id of the soChungThucDTO to save.
     * @param soChungThucDTO the soChungThucDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated soChungThucDTO,
     * or with status {@code 400 (Bad Request)} if the soChungThucDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the soChungThucDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SoChungThucDTO> updateSoChungThuc(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody SoChungThucDTO soChungThucDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update SoChungThuc : {}, {}", id, soChungThucDTO);
        if (soChungThucDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, soChungThucDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!soChungThucRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        soChungThucDTO = soChungThucService.update(soChungThucDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, soChungThucDTO.getId()))
            .body(soChungThucDTO);
    }

    /**
     * {@code PATCH  /so-chung-thucs/:id} : Partial updates given fields of an existing soChungThuc, field will ignore if it is null
     *
     * @param id the id of the soChungThucDTO to save.
     * @param soChungThucDTO the soChungThucDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated soChungThucDTO,
     * or with status {@code 400 (Bad Request)} if the soChungThucDTO is not valid,
     * or with status {@code 404 (Not Found)} if the soChungThucDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the soChungThucDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SoChungThucDTO> partialUpdateSoChungThuc(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody SoChungThucDTO soChungThucDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update SoChungThuc partially : {}, {}", id, soChungThucDTO);
        if (soChungThucDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, soChungThucDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!soChungThucRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SoChungThucDTO> result = soChungThucService.partialUpdate(soChungThucDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, soChungThucDTO.getId())
        );
    }

    /**
     * {@code GET  /so-chung-thucs} : get all the soChungThucs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of soChungThucs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<SoChungThucDTO>> getAllSoChungThucs(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of SoChungThucs");
        Page<SoChungThucDTO> page = soChungThucService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /so-chung-thucs/:id} : get the "id" soChungThuc.
     *
     * @param id the id of the soChungThucDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the soChungThucDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SoChungThucDTO> getSoChungThuc(@PathVariable("id") String id) {
        LOG.debug("REST request to get SoChungThuc : {}", id);
        Optional<SoChungThucDTO> soChungThucDTO = soChungThucService.findOne(id);
        return ResponseUtil.wrapOrNotFound(soChungThucDTO);
    }

    /**
     * {@code DELETE  /so-chung-thucs/:id} : delete the "id" soChungThuc.
     *
     * @param id the id of the soChungThucDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSoChungThuc(@PathVariable("id") String id) {
        LOG.debug("REST request to delete SoChungThuc : {}", id);
        soChungThucService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    @GetMapping("/by-donvi/{idDonVi}")
    public ResponseEntity<List<SoChungThucDTO>> getSoChungThucsByDonVi(@PathVariable Long idDonVi) {
        LOG.debug("REST request to get SoChungThucs by idDonVi : {}", idDonVi);
        List<SoChungThucDTO> soChungThucDTOs = soChungThucService.findByDonVi(idDonVi);
        return ResponseEntity.ok().body(soChungThucDTOs);
    }
}
