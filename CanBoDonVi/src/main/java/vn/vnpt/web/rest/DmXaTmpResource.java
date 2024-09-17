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
import vn.vnpt.repository.DmXaTmpRepository;
import vn.vnpt.service.DmXaTmpService;
import vn.vnpt.service.dto.DmXaTmpDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DmXaTmp}.
 */
@RestController
@RequestMapping("/api/dm-xa-tmps")
public class DmXaTmpResource {

    private static final Logger LOG = LoggerFactory.getLogger(DmXaTmpResource.class);

    private static final String ENTITY_NAME = "canBoDonViDmXaTmp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DmXaTmpService dmXaTmpService;

    private final DmXaTmpRepository dmXaTmpRepository;

    public DmXaTmpResource(DmXaTmpService dmXaTmpService, DmXaTmpRepository dmXaTmpRepository) {
        this.dmXaTmpService = dmXaTmpService;
        this.dmXaTmpRepository = dmXaTmpRepository;
    }

    /**
     * {@code POST  /dm-xa-tmps} : Create a new dmXaTmp.
     *
     * @param dmXaTmpDTO the dmXaTmpDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dmXaTmpDTO, or with status {@code 400 (Bad Request)} if the dmXaTmp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DmXaTmpDTO> createDmXaTmp(@RequestBody DmXaTmpDTO dmXaTmpDTO) throws URISyntaxException {
        LOG.debug("REST request to save DmXaTmp : {}", dmXaTmpDTO);
        if (dmXaTmpDTO.getMaXa() != null) {
            throw new BadRequestAlertException("A new dmXaTmp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        dmXaTmpDTO = dmXaTmpService.save(dmXaTmpDTO);
        return ResponseEntity.created(new URI("/api/dm-xa-tmps/" + dmXaTmpDTO.getMaXa()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, dmXaTmpDTO.getMaXa().toString()))
            .body(dmXaTmpDTO);
    }

    /**
     * {@code PUT  /dm-xa-tmps/:maXa} : Updates an existing dmXaTmp.
     *
     * @param maXa the id of the dmXaTmpDTO to save.
     * @param dmXaTmpDTO the dmXaTmpDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmXaTmpDTO,
     * or with status {@code 400 (Bad Request)} if the dmXaTmpDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dmXaTmpDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{maXa}")
    public ResponseEntity<DmXaTmpDTO> updateDmXaTmp(
        @PathVariable(value = "maXa", required = false) final Long maXa,
        @RequestBody DmXaTmpDTO dmXaTmpDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DmXaTmp : {}, {}", maXa, dmXaTmpDTO);
        if (dmXaTmpDTO.getMaXa() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(maXa, dmXaTmpDTO.getMaXa())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dmXaTmpRepository.existsById(maXa)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        dmXaTmpDTO = dmXaTmpService.update(dmXaTmpDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmXaTmpDTO.getMaXa().toString()))
            .body(dmXaTmpDTO);
    }

    /**
     * {@code PATCH  /dm-xa-tmps/:maXa} : Partial updates given fields of an existing dmXaTmp, field will ignore if it is null
     *
     * @param maXa the id of the dmXaTmpDTO to save.
     * @param dmXaTmpDTO the dmXaTmpDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmXaTmpDTO,
     * or with status {@code 400 (Bad Request)} if the dmXaTmpDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dmXaTmpDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dmXaTmpDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{maXa}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DmXaTmpDTO> partialUpdateDmXaTmp(
        @PathVariable(value = "maXa", required = false) final Long maXa,
        @RequestBody DmXaTmpDTO dmXaTmpDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DmXaTmp partially : {}, {}", maXa, dmXaTmpDTO);
        if (dmXaTmpDTO.getMaXa() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(maXa, dmXaTmpDTO.getMaXa())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dmXaTmpRepository.existsById(maXa)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DmXaTmpDTO> result = dmXaTmpService.partialUpdate(dmXaTmpDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmXaTmpDTO.getMaXa().toString())
        );
    }

    /**
     * {@code GET  /dm-xa-tmps} : get all the dmXaTmps.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dmXaTmps in body.
     */
    @GetMapping("")
    public List<DmXaTmpDTO> getAllDmXaTmps() {
        LOG.debug("REST request to get all DmXaTmps");
        return dmXaTmpService.findAll();
    }

    /**
     * {@code GET  /dm-xa-tmps/:id} : get the "id" dmXaTmp.
     *
     * @param id the id of the dmXaTmpDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dmXaTmpDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DmXaTmpDTO> getDmXaTmp(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DmXaTmp : {}", id);
        Optional<DmXaTmpDTO> dmXaTmpDTO = dmXaTmpService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dmXaTmpDTO);
    }

    /**
     * {@code DELETE  /dm-xa-tmps/:id} : delete the "id" dmXaTmp.
     *
     * @param id the id of the dmXaTmpDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDmXaTmp(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DmXaTmp : {}", id);
        dmXaTmpService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
