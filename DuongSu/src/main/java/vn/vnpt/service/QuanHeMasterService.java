package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.QuanHeMasterDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.QuanHeMaster}.
 */
public interface QuanHeMasterService {
    /**
     * Save a quanHeMaster.
     *
     * @param quanHeMasterDTO the entity to save.
     * @return the persisted entity.
     */
    QuanHeMasterDTO save(QuanHeMasterDTO quanHeMasterDTO);

    /**
     * Updates a quanHeMaster.
     *
     * @param quanHeMasterDTO the entity to update.
     * @return the persisted entity.
     */
    QuanHeMasterDTO update(QuanHeMasterDTO quanHeMasterDTO);

    /**
     * Partially updates a quanHeMaster.
     *
     * @param quanHeMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<QuanHeMasterDTO> partialUpdate(QuanHeMasterDTO quanHeMasterDTO);

    /**
     * Get the "id" quanHeMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QuanHeMasterDTO> findOne(Long id);

    /**
     * Delete the "id" quanHeMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
