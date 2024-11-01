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
import vn.vnpt.repository.ThongTinChungHopDongRepository;
import vn.vnpt.service.ThongTinChungHopDongService;
import vn.vnpt.service.dto.ThongTinChungHopDongDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.ThongTinChungHopDong}.
 */
@RestController
@RequestMapping("/api/thong-tin-chung-hop-dongs")
public class ThongTinChungHopDongResource {

    private static final Logger LOG = LoggerFactory.getLogger(ThongTinChungHopDongResource.class);

    private static final String ENTITY_NAME = "hopDongThongTinChungHopDong";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ThongTinChungHopDongService thongTinChungHopDongService;

    private final ThongTinChungHopDongRepository thongTinChungHopDongRepository;

    public ThongTinChungHopDongResource(
        ThongTinChungHopDongService thongTinChungHopDongService,
        ThongTinChungHopDongRepository thongTinChungHopDongRepository
    ) {
        this.thongTinChungHopDongService = thongTinChungHopDongService;
        this.thongTinChungHopDongRepository = thongTinChungHopDongRepository;
    }

    /**
     * {@code POST  /thong-tin-chung-hop-dongs} : Create a new thongTinChungHopDong.
     *
     * @param thongTinChungHopDongDTO the thongTinChungHopDongDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new thongTinChungHopDongDTO, or with status {@code 400 (Bad Request)} if the thongTinChungHopDong has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ThongTinChungHopDongDTO> createThongTinChungHopDong(@RequestBody ThongTinChungHopDongDTO thongTinChungHopDongDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save ThongTinChungHopDong : {}", thongTinChungHopDongDTO);
        if (thongTinChungHopDongDTO.getId() != null) {
            throw new BadRequestAlertException("A new thongTinChungHopDong cannot already have an ID", ENTITY_NAME, "idexists");
        }
        thongTinChungHopDongDTO = thongTinChungHopDongService.save(thongTinChungHopDongDTO);
        return ResponseEntity.created(new URI("/api/thong-tin-chung-hop-dongs/" + thongTinChungHopDongDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, thongTinChungHopDongDTO.getId()))
            .body(thongTinChungHopDongDTO);
    }

    /**
     * {@code PUT  /thong-tin-chung-hop-dongs/:id} : Updates an existing thongTinChungHopDong.
     *
     * @param id the id of the thongTinChungHopDongDTO to save.
     * @param thongTinChungHopDongDTO the thongTinChungHopDongDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated thongTinChungHopDongDTO,
     * or with status {@code 400 (Bad Request)} if the thongTinChungHopDongDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the thongTinChungHopDongDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ThongTinChungHopDongDTO> updateThongTinChungHopDong(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ThongTinChungHopDongDTO thongTinChungHopDongDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update ThongTinChungHopDong : {}, {}", id, thongTinChungHopDongDTO);
        if (thongTinChungHopDongDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, thongTinChungHopDongDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!thongTinChungHopDongRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        thongTinChungHopDongDTO = thongTinChungHopDongService.update(thongTinChungHopDongDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, thongTinChungHopDongDTO.getId()))
            .body(thongTinChungHopDongDTO);
    }

    /**
     * {@code PATCH  /thong-tin-chung-hop-dongs/:id} : Partial updates given fields of an existing thongTinChungHopDong, field will ignore if it is null
     *
     * @param id the id of the thongTinChungHopDongDTO to save.
     * @param thongTinChungHopDongDTO the thongTinChungHopDongDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated thongTinChungHopDongDTO,
     * or with status {@code 400 (Bad Request)} if the thongTinChungHopDongDTO is not valid,
     * or with status {@code 404 (Not Found)} if the thongTinChungHopDongDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the thongTinChungHopDongDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ThongTinChungHopDongDTO> partialUpdateThongTinChungHopDong(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ThongTinChungHopDongDTO thongTinChungHopDongDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update ThongTinChungHopDong partially : {}, {}", id, thongTinChungHopDongDTO);
        if (thongTinChungHopDongDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, thongTinChungHopDongDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!thongTinChungHopDongRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ThongTinChungHopDongDTO> result = thongTinChungHopDongService.partialUpdate(thongTinChungHopDongDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, thongTinChungHopDongDTO.getId())
        );
    }

    /**
     * {@code GET  /thong-tin-chung-hop-dongs} : get all the thongTinChungHopDongs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of thongTinChungHopDongs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ThongTinChungHopDongDTO>> getAllThongTinChungHopDongs(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of ThongTinChungHopDongs");
        Page<ThongTinChungHopDongDTO> page = thongTinChungHopDongService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /thong-tin-chung-hop-dongs/:id} : get the "id" thongTinChungHopDong.
     *
     * @param id the id of the thongTinChungHopDongDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the thongTinChungHopDongDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ThongTinChungHopDongDTO> getThongTinChungHopDong(@PathVariable("id") String id) {
        LOG.debug("REST request to get ThongTinChungHopDong : {}", id);
        Optional<ThongTinChungHopDongDTO> thongTinChungHopDongDTO = thongTinChungHopDongService.findOne(id);
        return ResponseUtil.wrapOrNotFound(thongTinChungHopDongDTO);
    }

    /**
     * {@code DELETE  /thong-tin-chung-hop-dongs/:id} : delete the "id" thongTinChungHopDong.
     *
     * @param id the id of the thongTinChungHopDongDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThongTinChungHopDong(@PathVariable("id") String id) {
        LOG.debug("REST request to delete ThongTinChungHopDong : {}", id);
        thongTinChungHopDongService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
