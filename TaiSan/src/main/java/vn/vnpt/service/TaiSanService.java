package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.TaiSanDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.TaiSan}.
 */
public interface TaiSanService {
    /**
     * Save a taiSan.
     *
     * @param taiSanDTO the entity to save.
     * @return the persisted entity.
     */
    TaiSanDTO save(TaiSanDTO taiSanDTO);

    /**
     * Updates a taiSan.
     *
     * @param taiSanDTO the entity to update.
     * @return the persisted entity.
     */
    TaiSanDTO update(TaiSanDTO taiSanDTO);

    /**
     * Partially updates a taiSan.
     *
     * @param taiSanDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TaiSanDTO> partialUpdate(TaiSanDTO taiSanDTO);

    /**
     * Get the "id" taiSan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaiSanDTO> findOne(Long id);

    /**
     * Delete the "id" taiSan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
