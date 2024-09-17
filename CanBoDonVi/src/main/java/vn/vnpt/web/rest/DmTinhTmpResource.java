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
import vn.vnpt.repository.DmTinhTmpRepository;
import vn.vnpt.service.DmTinhTmpService;
import vn.vnpt.service.dto.DmTinhTmpDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DmTinhTmp}.
 */
@RestController
@RequestMapping("/api/dm-tinh-tmps")
public class DmTinhTmpResource {

    private static final Logger LOG = LoggerFactory.getLogger(DmTinhTmpResource.class);

    private static final String ENTITY_NAME = "canBoDonViDmTinhTmp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DmTinhTmpService dmTinhTmpService;

    private final DmTinhTmpRepository dmTinhTmpRepository;

    public DmTinhTmpResource(DmTinhTmpService dmTinhTmpService, DmTinhTmpRepository dmTinhTmpRepository) {
        this.dmTinhTmpService = dmTinhTmpService;
        this.dmTinhTmpRepository = dmTinhTmpRepository;
    }

    /**
     * {@code POST  /dm-tinh-tmps} : Create a new dmTinhTmp.
     *
     * @param dmTinhTmpDTO the dmTinhTmpDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dmTinhTmpDTO, or with status {@code 400 (Bad Request)} if the dmTinhTmp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DmTinhTmpDTO> createDmTinhTmp(@RequestBody DmTinhTmpDTO dmTinhTmpDTO) throws URISyntaxException {
        LOG.debug("REST request to save DmTinhTmp : {}", dmTinhTmpDTO);
        if (dmTinhTmpDTO.getMaTinh() != null) {
            throw new BadRequestAlertException("A new dmTinhTmp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        dmTinhTmpDTO = dmTinhTmpService.save(dmTinhTmpDTO);
        return ResponseEntity.created(new URI("/api/dm-tinh-tmps/" + dmTinhTmpDTO.getMaTinh()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, dmTinhTmpDTO.getMaTinh().toString()))
            .body(dmTinhTmpDTO);
    }

    /**
     * {@code PUT  /dm-tinh-tmps/:maTinh} : Updates an existing dmTinhTmp.
     *
     * @param maTinh the id of the dmTinhTmpDTO to save.
     * @param dmTinhTmpDTO the dmTinhTmpDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmTinhTmpDTO,
     * or with status {@code 400 (Bad Request)} if the dmTinhTmpDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dmTinhTmpDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{maTinh}")
    public ResponseEntity<DmTinhTmpDTO> updateDmTinhTmp(
        @PathVariable(value = "maTinh", required = false) final Long maTinh,
        @RequestBody DmTinhTmpDTO dmTinhTmpDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DmTinhTmp : {}, {}", maTinh, dmTinhTmpDTO);
        if (dmTinhTmpDTO.getMaTinh() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(maTinh, dmTinhTmpDTO.getMaTinh())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dmTinhTmpRepository.existsById(maTinh)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        dmTinhTmpDTO = dmTinhTmpService.update(dmTinhTmpDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmTinhTmpDTO.getMaTinh().toString()))
            .body(dmTinhTmpDTO);
    }

    /**
     * {@code PATCH  /dm-tinh-tmps/:maTinh} : Partial updates given fields of an existing dmTinhTmp, field will ignore if it is null
     *
     * @param maTinh the id of the dmTinhTmpDTO to save.
     * @param dmTinhTmpDTO the dmTinhTmpDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmTinhTmpDTO,
     * or with status {@code 400 (Bad Request)} if the dmTinhTmpDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dmTinhTmpDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dmTinhTmpDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{maTinh}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DmTinhTmpDTO> partialUpdateDmTinhTmp(
        @PathVariable(value = "maTinh", required = false) final Long maTinh,
        @RequestBody DmTinhTmpDTO dmTinhTmpDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DmTinhTmp partially : {}, {}", maTinh, dmTinhTmpDTO);
        if (dmTinhTmpDTO.getMaTinh() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(maTinh, dmTinhTmpDTO.getMaTinh())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dmTinhTmpRepository.existsById(maTinh)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DmTinhTmpDTO> result = dmTinhTmpService.partialUpdate(dmTinhTmpDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmTinhTmpDTO.getMaTinh().toString())
        );
    }

    /**
     * {@code GET  /dm-tinh-tmps} : get all the dmTinhTmps.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dmTinhTmps in body.
     */
    @GetMapping("")
    public List<DmTinhTmpDTO> getAllDmTinhTmps() {
        LOG.debug("REST request to get all DmTinhTmps");
        return dmTinhTmpService.findAll();
    }

    /**
     * {@code GET  /dm-tinh-tmps/:id} : get the "id" dmTinhTmp.
     *
     * @param id the id of the dmTinhTmpDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dmTinhTmpDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DmTinhTmpDTO> getDmTinhTmp(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DmTinhTmp : {}", id);
        Optional<DmTinhTmpDTO> dmTinhTmpDTO = dmTinhTmpService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dmTinhTmpDTO);
    }

    /**
     * {@code DELETE  /dm-tinh-tmps/:id} : delete the "id" dmTinhTmp.
     *
     * @param id the id of the dmTinhTmpDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDmTinhTmp(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DmTinhTmp : {}", id);
        dmTinhTmpService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
