package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.QuanHeNhanThanDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.QuanHeNhanThan}.
 */
public interface QuanHeNhanThanService {
    /**
     * Save a quanHeNhanThan.
     *
     * @param quanHeNhanThanDTO the entity to save.
     * @return the persisted entity.
     */
    QuanHeNhanThanDTO save(QuanHeNhanThanDTO quanHeNhanThanDTO);

    /**
     * Updates a quanHeNhanThan.
     *
     * @param quanHeNhanThanDTO the entity to update.
     * @return the persisted entity.
     */
    QuanHeNhanThanDTO update(QuanHeNhanThanDTO quanHeNhanThanDTO);

    /**
     * Partially updates a quanHeNhanThan.
     *
     * @param quanHeNhanThanDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<QuanHeNhanThanDTO> partialUpdate(QuanHeNhanThanDTO quanHeNhanThanDTO);

    /**
     * Get the "id" quanHeNhanThan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QuanHeNhanThanDTO> findOne(Long id);

    /**
     * Delete the "id" quanHeNhanThan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
