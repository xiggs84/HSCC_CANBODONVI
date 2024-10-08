package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.CapQuanLyDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.CapQuanLy}.
 */
public interface CapQuanLyService {
    /**
     * Save a capQuanLy.
     *
     * @param capQuanLyDTO the entity to save.
     * @return the persisted entity.
     */
    CapQuanLyDTO save(CapQuanLyDTO capQuanLyDTO);

    /**
     * Updates a capQuanLy.
     *
     * @param capQuanLyDTO the entity to update.
     * @return the persisted entity.
     */
    CapQuanLyDTO update(CapQuanLyDTO capQuanLyDTO);

    /**
     * Partially updates a capQuanLy.
     *
     * @param capQuanLyDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CapQuanLyDTO> partialUpdate(CapQuanLyDTO capQuanLyDTO);

    /**
     * Get the "id" capQuanLy.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CapQuanLyDTO> findOne(String id);

    /**
     * Delete the "id" capQuanLy.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
