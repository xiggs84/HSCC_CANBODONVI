package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.QuanHeDuongSuDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.QuanHeDuongSu}.
 */
public interface QuanHeDuongSuService {
    /**
     * Save a quanHeDuongSu.
     *
     * @param quanHeDuongSuDTO the entity to save.
     * @return the persisted entity.
     */
    QuanHeDuongSuDTO save(QuanHeDuongSuDTO quanHeDuongSuDTO);

    /**
     * Updates a quanHeDuongSu.
     *
     * @param quanHeDuongSuDTO the entity to update.
     * @return the persisted entity.
     */
    QuanHeDuongSuDTO update(QuanHeDuongSuDTO quanHeDuongSuDTO);

    /**
     * Partially updates a quanHeDuongSu.
     *
     * @param quanHeDuongSuDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<QuanHeDuongSuDTO> partialUpdate(QuanHeDuongSuDTO quanHeDuongSuDTO);

    /**
     * Get the "id" quanHeDuongSu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QuanHeDuongSuDTO> findOne(Long id);

    /**
     * Delete the "id" quanHeDuongSu.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
