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
import vn.vnpt.repository.DmHuyenTmpRepository;
import vn.vnpt.service.DmHuyenTmpQueryService;
import vn.vnpt.service.DmHuyenTmpService;
import vn.vnpt.service.criteria.DmHuyenTmpCriteria;
import vn.vnpt.service.dto.DmHuyenTmpDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.DmHuyenTmp}.
 */
@RestController
@RequestMapping("/api/dm-huyen-tmps")
public class DmHuyenTmpResource {

    private static final Logger LOG = LoggerFactory.getLogger(DmHuyenTmpResource.class);

    private static final String ENTITY_NAME = "canBoDonViDmHuyenTmp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DmHuyenTmpService dmHuyenTmpService;

    private final DmHuyenTmpRepository dmHuyenTmpRepository;

    private final DmHuyenTmpQueryService dmHuyenTmpQueryService;

    public DmHuyenTmpResource(
        DmHuyenTmpService dmHuyenTmpService,
        DmHuyenTmpRepository dmHuyenTmpRepository,
        DmHuyenTmpQueryService dmHuyenTmpQueryService
    ) {
        this.dmHuyenTmpService = dmHuyenTmpService;
        this.dmHuyenTmpRepository = dmHuyenTmpRepository;
        this.dmHuyenTmpQueryService = dmHuyenTmpQueryService;
    }

    /**
     * {@code POST  /dm-huyen-tmps} : Create a new dmHuyenTmp.
     *
     * @param dmHuyenTmpDTO the dmHuyenTmpDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dmHuyenTmpDTO, or with status {@code 400 (Bad Request)} if the dmHuyenTmp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DmHuyenTmpDTO> createDmHuyenTmp(@RequestBody DmHuyenTmpDTO dmHuyenTmpDTO) throws URISyntaxException {
        LOG.debug("REST request to save DmHuyenTmp : {}", dmHuyenTmpDTO);
        if (dmHuyenTmpDTO.getMaHuyen() != null) {
            throw new BadRequestAlertException("A new dmHuyenTmp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        dmHuyenTmpDTO = dmHuyenTmpService.save(dmHuyenTmpDTO);
        return ResponseEntity.created(new URI("/api/dm-huyen-tmps/" + dmHuyenTmpDTO.getMaHuyen()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, dmHuyenTmpDTO.getMaHuyen().toString()))
            .body(dmHuyenTmpDTO);
    }

    /**
     * {@code PUT  /dm-huyen-tmps/:maHuyen} : Updates an existing dmHuyenTmp.
     *
     * @param maHuyen the id of the dmHuyenTmpDTO to save.
     * @param dmHuyenTmpDTO the dmHuyenTmpDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmHuyenTmpDTO,
     * or with status {@code 400 (Bad Request)} if the dmHuyenTmpDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dmHuyenTmpDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{maHuyen}")
    public ResponseEntity<DmHuyenTmpDTO> updateDmHuyenTmp(
        @PathVariable(value = "maHuyen", required = false) final Long maHuyen,
        @RequestBody DmHuyenTmpDTO dmHuyenTmpDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DmHuyenTmp : {}, {}", maHuyen, dmHuyenTmpDTO);
        if (dmHuyenTmpDTO.getMaHuyen() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(maHuyen, dmHuyenTmpDTO.getMaHuyen())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dmHuyenTmpRepository.existsById(maHuyen)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        dmHuyenTmpDTO = dmHuyenTmpService.update(dmHuyenTmpDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmHuyenTmpDTO.getMaHuyen().toString()))
            .body(dmHuyenTmpDTO);
    }

    /**
     * {@code PATCH  /dm-huyen-tmps/:maHuyen} : Partial updates given fields of an existing dmHuyenTmp, field will ignore if it is null
     *
     * @param maHuyen the id of the dmHuyenTmpDTO to save.
     * @param dmHuyenTmpDTO the dmHuyenTmpDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmHuyenTmpDTO,
     * or with status {@code 400 (Bad Request)} if the dmHuyenTmpDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dmHuyenTmpDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dmHuyenTmpDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{maHuyen}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DmHuyenTmpDTO> partialUpdateDmHuyenTmp(
        @PathVariable(value = "maHuyen", required = false) final Long maHuyen,
        @RequestBody DmHuyenTmpDTO dmHuyenTmpDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DmHuyenTmp partially : {}, {}", maHuyen, dmHuyenTmpDTO);
        if (dmHuyenTmpDTO.getMaHuyen() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(maHuyen, dmHuyenTmpDTO.getMaHuyen())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dmHuyenTmpRepository.existsById(maHuyen)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DmHuyenTmpDTO> result = dmHuyenTmpService.partialUpdate(dmHuyenTmpDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmHuyenTmpDTO.getMaHuyen().toString())
        );
    }

    /**
     * {@code GET  /dm-huyen-tmps} : get all the dmHuyenTmps.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dmHuyenTmps in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DmHuyenTmpDTO>> getAllDmHuyenTmps(
        DmHuyenTmpCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get DmHuyenTmps by criteria: {}", criteria);

        Page<DmHuyenTmpDTO> page = dmHuyenTmpQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dm-huyen-tmps/count} : count all the dmHuyenTmps.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDmHuyenTmps(DmHuyenTmpCriteria criteria) {
        LOG.debug("REST request to count DmHuyenTmps by criteria: {}", criteria);
        return ResponseEntity.ok().body(dmHuyenTmpQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dm-huyen-tmps/:id} : get the "id" dmHuyenTmp.
     *
     * @param id the id of the dmHuyenTmpDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dmHuyenTmpDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DmHuyenTmpDTO> getDmHuyenTmp(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DmHuyenTmp : {}", id);
        Optional<DmHuyenTmpDTO> dmHuyenTmpDTO = dmHuyenTmpService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dmHuyenTmpDTO);
    }

    /**
     * {@code DELETE  /dm-huyen-tmps/:id} : delete the "id" dmHuyenTmp.
     *
     * @param id the id of the dmHuyenTmpDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDmHuyenTmp(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DmHuyenTmp : {}", id);
        dmHuyenTmpService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
