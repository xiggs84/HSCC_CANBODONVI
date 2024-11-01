package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.DmDuongSuDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DmDuongSu}.
 */
public interface DmDuongSuService {
    /**
     * Save a dmDuongSu.
     *
     * @param dmDuongSuDTO the entity to save.
     * @return the persisted entity.
     */
    DmDuongSuDTO save(DmDuongSuDTO dmDuongSuDTO);

    /**
     * Updates a dmDuongSu.
     *
     * @param dmDuongSuDTO the entity to update.
     * @return the persisted entity.
     */
    DmDuongSuDTO update(DmDuongSuDTO dmDuongSuDTO);

    /**
     * Partially updates a dmDuongSu.
     *
     * @param dmDuongSuDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DmDuongSuDTO> partialUpdate(DmDuongSuDTO dmDuongSuDTO);

    /**
     * Get the "id" dmDuongSu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DmDuongSuDTO> findOne(Long id);

    /**
     * Delete the "id" dmDuongSu.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
