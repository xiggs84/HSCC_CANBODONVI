package vn.vnpt.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
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
import vn.vnpt.repository.CanBoQuyenRepository;
import vn.vnpt.service.CanBoQuyenQueryService;
import vn.vnpt.service.CanBoQuyenService;
import vn.vnpt.service.criteria.CanBoQuyenCriteria;
import vn.vnpt.service.dto.CanBoQuyenDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.CanBoQuyen}.
 */
@RestController
@RequestMapping("/api/can-bo-quyens")
public class CanBoQuyenResource {

    private static final Logger LOG = LoggerFactory.getLogger(CanBoQuyenResource.class);

    private static final String ENTITY_NAME = "canBoDonViCanBoQuyen";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CanBoQuyenService canBoQuyenService;

    private final CanBoQuyenRepository canBoQuyenRepository;

    private final CanBoQuyenQueryService canBoQuyenQueryService;

    public CanBoQuyenResource(
        CanBoQuyenService canBoQuyenService,
        CanBoQuyenRepository canBoQuyenRepository,
        CanBoQuyenQueryService canBoQuyenQueryService
    ) {
        this.canBoQuyenService = canBoQuyenService;
        this.canBoQuyenRepository = canBoQuyenRepository;
        this.canBoQuyenQueryService = canBoQuyenQueryService;
    }

    /**
     * {@code POST  /can-bo-quyens} : Create a new canBoQuyen.
     *
     * @param canBoQuyenDTO the canBoQuyenDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new canBoQuyenDTO, or with status {@code 400 (Bad Request)} if the canBoQuyen has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CanBoQuyenDTO> createCanBoQuyen(@RequestBody CanBoQuyenDTO canBoQuyenDTO) throws URISyntaxException {
        LOG.debug("REST request to save CanBoQuyen : {}", canBoQuyenDTO);
        if (canBoQuyenDTO.getId() != null) {
            throw new BadRequestAlertException("A new canBoQuyen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        canBoQuyenDTO = canBoQuyenService.save(canBoQuyenDTO);
        return ResponseEntity.created(new URI("/api/can-bo-quyens/" + canBoQuyenDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, canBoQuyenDTO.getId().toString()))
            .body(canBoQuyenDTO);
    }

    /**
     * {@code GET  /can-bo-quyens} : get all the canBoQuyens.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of canBoQuyens in body.
     */
    @GetMapping("")
    public ResponseEntity<List<CanBoQuyenDTO>> getAllCanBoQuyens(
        CanBoQuyenCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get CanBoQuyens by criteria: {}", criteria);

        Page<CanBoQuyenDTO> page = canBoQuyenQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /can-bo-quyens/count} : count all the canBoQuyens.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countCanBoQuyens(CanBoQuyenCriteria criteria) {
        LOG.debug("REST request to count CanBoQuyens by criteria: {}", criteria);
        return ResponseEntity.ok().body(canBoQuyenQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /can-bo-quyens/:id} : get the "id" canBoQuyen.
     *
     * @param id the id of the canBoQuyenDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the canBoQuyenDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CanBoQuyenDTO> getCanBoQuyen(@PathVariable("id") Long id) {
        LOG.debug("REST request to get CanBoQuyen : {}", id);
        Optional<CanBoQuyenDTO> canBoQuyenDTO = canBoQuyenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(canBoQuyenDTO);
    }

    /**
     * {@code DELETE  /can-bo-quyens/:id} : delete the "id" canBoQuyen.
     *
     * @param id the id of the canBoQuyenDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCanBoQuyen(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete CanBoQuyen : {}", id);
        canBoQuyenService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
