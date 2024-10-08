package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.LoaiDonViDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.LoaiDonVi}.
 */
public interface LoaiDonViService {
    /**
     * Save a loaiDonVi.
     *
     * @param loaiDonViDTO the entity to save.
     * @return the persisted entity.
     */
    LoaiDonViDTO save(LoaiDonViDTO loaiDonViDTO);

    /**
     * Updates a loaiDonVi.
     *
     * @param loaiDonViDTO the entity to update.
     * @return the persisted entity.
     */
    LoaiDonViDTO update(LoaiDonViDTO loaiDonViDTO);

    /**
     * Partially updates a loaiDonVi.
     *
     * @param loaiDonViDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LoaiDonViDTO> partialUpdate(LoaiDonViDTO loaiDonViDTO);

    /**
     * Get the "id" loaiDonVi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LoaiDonViDTO> findOne(String id);

    /**
     * Delete the "id" loaiDonVi.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
