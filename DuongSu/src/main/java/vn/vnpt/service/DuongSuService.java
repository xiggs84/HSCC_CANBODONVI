package vn.vnpt.service;

import java.util.List;
import java.util.Optional;
import vn.vnpt.service.dto.DuongSuDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DuongSu}.
 */
public interface DuongSuService {
    /**
     * Save a duongSu.
     *
     * @param duongSuDTO the entity to save.
     * @return the persisted entity.
     */
    DuongSuDTO save(DuongSuDTO duongSuDTO);

    /**
     * Updates a duongSu.
     *
     * @param duongSuDTO the entity to update.
     * @return the persisted entity.
     */
    DuongSuDTO update(DuongSuDTO duongSuDTO);

    /**
     * Partially updates a duongSu.
     *
     * @param duongSuDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DuongSuDTO> partialUpdate(DuongSuDTO duongSuDTO);

    /**
     * Get the "id" duongSu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DuongSuDTO> findOne(Long id);

    /**
     * Delete the "id" duongSu.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<DuongSuDTO> searchByTenAndOppositeGender(String tenDuongSu, String gender);
}
