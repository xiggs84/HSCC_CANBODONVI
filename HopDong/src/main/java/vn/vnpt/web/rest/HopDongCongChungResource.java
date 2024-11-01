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
import vn.vnpt.repository.HopDongCongChungRepository;
import vn.vnpt.service.HopDongCongChungService;
import vn.vnpt.service.dto.HopDongCongChungDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.HopDongCongChung}.
 */
@RestController
@RequestMapping("/api/hop-dong-cong-chungs")
public class HopDongCongChungResource {

    private static final Logger LOG = LoggerFactory.getLogger(HopDongCongChungResource.class);

    private static final String ENTITY_NAME = "hopDongHopDongCongChung";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HopDongCongChungService hopDongCongChungService;

    private final HopDongCongChungRepository hopDongCongChungRepository;

    public HopDongCongChungResource(
        HopDongCongChungService hopDongCongChungService,
        HopDongCongChungRepository hopDongCongChungRepository
    ) {
        this.hopDongCongChungService = hopDongCongChungService;
        this.hopDongCongChungRepository = hopDongCongChungRepository;
    }

    /**
     * {@code POST  /hop-dong-cong-chungs} : Create a new hopDongCongChung.
     *
     * @param hopDongCongChungDTO the hopDongCongChungDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hopDongCongChungDTO, or with status {@code 400 (Bad Request)} if the hopDongCongChung has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<HopDongCongChungDTO> createHopDongCongChung(@RequestBody HopDongCongChungDTO hopDongCongChungDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save HopDongCongChung : {}", hopDongCongChungDTO);
        if (hopDongCongChungDTO.getId() != null) {
            throw new BadRequestAlertException("A new hopDongCongChung cannot already have an ID", ENTITY_NAME, "idexists");
        }
        hopDongCongChungDTO = hopDongCongChungService.save(hopDongCongChungDTO);
        return ResponseEntity.created(new URI("/api/hop-dong-cong-chungs/" + hopDongCongChungDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, hopDongCongChungDTO.getId()))
            .body(hopDongCongChungDTO);
    }

    /**
     * {@code PUT  /hop-dong-cong-chungs/:id} : Updates an existing hopDongCongChung.
     *
     * @param id the id of the hopDongCongChungDTO to save.
     * @param hopDongCongChungDTO the hopDongCongChungDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hopDongCongChungDTO,
     * or with status {@code 400 (Bad Request)} if the hopDongCongChungDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hopDongCongChungDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<HopDongCongChungDTO> updateHopDongCongChung(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody HopDongCongChungDTO hopDongCongChungDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update HopDongCongChung : {}, {}", id, hopDongCongChungDTO);
        if (hopDongCongChungDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hopDongCongChungDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hopDongCongChungRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        hopDongCongChungDTO = hopDongCongChungService.update(hopDongCongChungDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, hopDongCongChungDTO.getId()))
            .body(hopDongCongChungDTO);
    }

    /**
     * {@code PATCH  /hop-dong-cong-chungs/:id} : Partial updates given fields of an existing hopDongCongChung, field will ignore if it is null
     *
     * @param id the id of the hopDongCongChungDTO to save.
     * @param hopDongCongChungDTO the hopDongCongChungDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hopDongCongChungDTO,
     * or with status {@code 400 (Bad Request)} if the hopDongCongChungDTO is not valid,
     * or with status {@code 404 (Not Found)} if the hopDongCongChungDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the hopDongCongChungDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HopDongCongChungDTO> partialUpdateHopDongCongChung(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody HopDongCongChungDTO hopDongCongChungDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update HopDongCongChung partially : {}, {}", id, hopDongCongChungDTO);
        if (hopDongCongChungDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hopDongCongChungDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hopDongCongChungRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HopDongCongChungDTO> result = hopDongCongChungService.partialUpdate(hopDongCongChungDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, hopDongCongChungDTO.getId())
        );
    }

    /**
     * {@code GET  /hop-dong-cong-chungs} : get all the hopDongCongChungs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hopDongCongChungs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<HopDongCongChungDTO>> getAllHopDongCongChungs(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of HopDongCongChungs");
        Page<HopDongCongChungDTO> page = hopDongCongChungService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /hop-dong-cong-chungs/:id} : get the "id" hopDongCongChung.
     *
     * @param id the id of the hopDongCongChungDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hopDongCongChungDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<HopDongCongChungDTO> getHopDongCongChung(@PathVariable("id") String id) {
        LOG.debug("REST request to get HopDongCongChung : {}", id);
        Optional<HopDongCongChungDTO> hopDongCongChungDTO = hopDongCongChungService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hopDongCongChungDTO);
    }

    /**
     * {@code DELETE  /hop-dong-cong-chungs/:id} : delete the "id" hopDongCongChung.
     *
     * @param id the id of the hopDongCongChungDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHopDongCongChung(@PathVariable("id") String id) {
        LOG.debug("REST request to delete HopDongCongChung : {}", id);
        hopDongCongChungService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
