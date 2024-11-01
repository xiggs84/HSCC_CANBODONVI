package vn.vnpt.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.vnpt.service.dto.SoCongChungDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.SoCongChung}.
 */
public interface SoCongChungService {
    /**
     * Save a soCongChung.
     *
     * @param soCongChungDTO the entity to save.
     * @return the persisted entity.
     */
    SoCongChungDTO save(SoCongChungDTO soCongChungDTO);

    /**
     * Updates a soCongChung.
     *
     * @param soCongChungDTO the entity to update.
     * @return the persisted entity.
     */
    SoCongChungDTO update(SoCongChungDTO soCongChungDTO);

    /**
     * Partially updates a soCongChung.
     *
     * @param soCongChungDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SoCongChungDTO> partialUpdate(SoCongChungDTO soCongChungDTO);

    /**
     * Get all the soCongChungs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SoCongChungDTO> findAll(Pageable pageable);

    /**
     * Get the "id" soCongChung.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SoCongChungDTO> findOne(String id);

    /**
     * Delete the "id" soCongChung.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    List<SoCongChungDTO> findByDonVi(Long idDonVi);
}
