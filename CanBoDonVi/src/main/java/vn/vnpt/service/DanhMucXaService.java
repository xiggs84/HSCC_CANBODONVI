package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.DanhMucXaDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DanhMucXa}.
 */
public interface DanhMucXaService {
    /**
     * Save a danhMucXa.
     *
     * @param danhMucXaDTO the entity to save.
     * @return the persisted entity.
     */
    DanhMucXaDTO save(DanhMucXaDTO danhMucXaDTO);

    /**
     * Updates a danhMucXa.
     *
     * @param danhMucXaDTO the entity to update.
     * @return the persisted entity.
     */
    DanhMucXaDTO update(DanhMucXaDTO danhMucXaDTO);

    /**
     * Partially updates a danhMucXa.
     *
     * @param danhMucXaDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DanhMucXaDTO> partialUpdate(DanhMucXaDTO danhMucXaDTO);

    /**
     * Get the "id" danhMucXa.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhMucXaDTO> findOne(String id);

    /**
     * Delete the "id" danhMucXa.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
