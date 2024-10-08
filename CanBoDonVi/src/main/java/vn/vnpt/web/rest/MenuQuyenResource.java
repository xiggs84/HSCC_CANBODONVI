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
import vn.vnpt.repository.MenuQuyenRepository;
import vn.vnpt.service.MenuQuyenQueryService;
import vn.vnpt.service.MenuQuyenService;
import vn.vnpt.service.criteria.MenuQuyenCriteria;
import vn.vnpt.service.dto.MenuQuyenDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.MenuQuyen}.
 */
@RestController
@RequestMapping("/api/menu-quyens")
public class MenuQuyenResource {

    private static final Logger LOG = LoggerFactory.getLogger(MenuQuyenResource.class);

    private static final String ENTITY_NAME = "canBoDonViMenuQuyen";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MenuQuyenService menuQuyenService;

    private final MenuQuyenRepository menuQuyenRepository;

    private final MenuQuyenQueryService menuQuyenQueryService;

    public MenuQuyenResource(
        MenuQuyenService menuQuyenService,
        MenuQuyenRepository menuQuyenRepository,
        MenuQuyenQueryService menuQuyenQueryService
    ) {
        this.menuQuyenService = menuQuyenService;
        this.menuQuyenRepository = menuQuyenRepository;
        this.menuQuyenQueryService = menuQuyenQueryService;
    }

    /**
     * {@code POST  /menu-quyens} : Create a new menuQuyen.
     *
     * @param menuQuyenDTO the menuQuyenDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new menuQuyenDTO, or with status {@code 400 (Bad Request)} if the menuQuyen has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<MenuQuyenDTO> createMenuQuyen(@RequestBody MenuQuyenDTO menuQuyenDTO) throws URISyntaxException {
        LOG.debug("REST request to save MenuQuyen : {}", menuQuyenDTO);
        if (menuQuyenDTO.getId() != null) {
            throw new BadRequestAlertException("A new menuQuyen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        menuQuyenDTO = menuQuyenService.save(menuQuyenDTO);
        return ResponseEntity.created(new URI("/api/menu-quyens/" + menuQuyenDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, menuQuyenDTO.getId().toString()))
            .body(menuQuyenDTO);
    }

    /**
     * {@code PUT  /menu-quyens/:id} : Updates an existing menuQuyen.
     *
     * @param id the id of the menuQuyenDTO to save.
     * @param menuQuyenDTO the menuQuyenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated menuQuyenDTO,
     * or with status {@code 400 (Bad Request)} if the menuQuyenDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the menuQuyenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MenuQuyenDTO> updateMenuQuyen(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MenuQuyenDTO menuQuyenDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update MenuQuyen : {}, {}", id, menuQuyenDTO);
        if (menuQuyenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, menuQuyenDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!menuQuyenRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        menuQuyenDTO = menuQuyenService.update(menuQuyenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, menuQuyenDTO.getId().toString()))
            .body(menuQuyenDTO);
    }

    /**
     * {@code PATCH  /menu-quyens/:id} : Partial updates given fields of an existing menuQuyen, field will ignore if it is null
     *
     * @param id the id of the menuQuyenDTO to save.
     * @param menuQuyenDTO the menuQuyenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated menuQuyenDTO,
     * or with status {@code 400 (Bad Request)} if the menuQuyenDTO is not valid,
     * or with status {@code 404 (Not Found)} if the menuQuyenDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the menuQuyenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MenuQuyenDTO> partialUpdateMenuQuyen(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MenuQuyenDTO menuQuyenDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update MenuQuyen partially : {}, {}", id, menuQuyenDTO);
        if (menuQuyenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, menuQuyenDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!menuQuyenRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MenuQuyenDTO> result = menuQuyenService.partialUpdate(menuQuyenDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, menuQuyenDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /menu-quyens} : get all the menuQuyens.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of menuQuyens in body.
     */
    @GetMapping("")
    public ResponseEntity<List<MenuQuyenDTO>> getAllMenuQuyens(
        MenuQuyenCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get MenuQuyens by criteria: {}", criteria);

        Page<MenuQuyenDTO> page = menuQuyenQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /menu-quyens/count} : count all the menuQuyens.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countMenuQuyens(MenuQuyenCriteria criteria) {
        LOG.debug("REST request to count MenuQuyens by criteria: {}", criteria);
        return ResponseEntity.ok().body(menuQuyenQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /menu-quyens/:id} : get the "id" menuQuyen.
     *
     * @param id the id of the menuQuyenDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the menuQuyenDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MenuQuyenDTO> getMenuQuyen(@PathVariable("id") Long id) {
        LOG.debug("REST request to get MenuQuyen : {}", id);
        Optional<MenuQuyenDTO> menuQuyenDTO = menuQuyenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(menuQuyenDTO);
    }

    /**
     * {@code DELETE  /menu-quyens/:id} : delete the "id" menuQuyen.
     *
     * @param id the id of the menuQuyenDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuQuyen(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete MenuQuyen : {}", id);
        menuQuyenService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
