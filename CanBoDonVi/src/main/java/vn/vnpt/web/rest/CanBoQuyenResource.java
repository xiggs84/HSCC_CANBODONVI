package vn.vnpt.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;
import vn.vnpt.repository.CanBoQuyenRepository;
import vn.vnpt.service.CanBoQuyenService;
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

    public CanBoQuyenResource(CanBoQuyenService canBoQuyenService, CanBoQuyenRepository canBoQuyenRepository) {
        this.canBoQuyenService = canBoQuyenService;
        this.canBoQuyenRepository = canBoQuyenRepository;
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
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of canBoQuyens in body.
     */
    @GetMapping("")
    public List<CanBoQuyenDTO> getAllCanBoQuyens() {
        LOG.debug("REST request to get all CanBoQuyens");
        return canBoQuyenService.findAll();
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
