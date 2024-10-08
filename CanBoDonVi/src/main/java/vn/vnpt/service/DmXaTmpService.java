package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.DmXaTmpDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DmXaTmp}.
 */
public interface DmXaTmpService {
    /**
     * Save a dmXaTmp.
     *
     * @param dmXaTmpDTO the entity to save.
     * @return the persisted entity.
     */
    DmXaTmpDTO save(DmXaTmpDTO dmXaTmpDTO);

    /**
     * Updates a dmXaTmp.
     *
     * @param dmXaTmpDTO the entity to update.
     * @return the persisted entity.
     */
    DmXaTmpDTO update(DmXaTmpDTO dmXaTmpDTO);

    /**
     * Partially updates a dmXaTmp.
     *
     * @param dmXaTmpDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DmXaTmpDTO> partialUpdate(DmXaTmpDTO dmXaTmpDTO);

    /**
     * Get the "id" dmXaTmp.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DmXaTmpDTO> findOne(Long id);

    /**
     * Delete the "id" dmXaTmp.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
