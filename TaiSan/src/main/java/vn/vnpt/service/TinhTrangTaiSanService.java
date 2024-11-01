package vn.vnpt.service;

import java.util.List;
import java.util.Optional;
import vn.vnpt.service.dto.TinhTrangTaiSanDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.TinhTrangTaiSan}.
 */
public interface TinhTrangTaiSanService {
    /**
     * Save a tinhTrangTaiSan.
     *
     * @param tinhTrangTaiSanDTO the entity to save.
     * @return the persisted entity.
     */
    TinhTrangTaiSanDTO save(TinhTrangTaiSanDTO tinhTrangTaiSanDTO);

    /**
     * Updates a tinhTrangTaiSan.
     *
     * @param tinhTrangTaiSanDTO the entity to update.
     * @return the persisted entity.
     */
    TinhTrangTaiSanDTO update(TinhTrangTaiSanDTO tinhTrangTaiSanDTO);

    /**
     * Partially updates a tinhTrangTaiSan.
     *
     * @param tinhTrangTaiSanDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TinhTrangTaiSanDTO> partialUpdate(TinhTrangTaiSanDTO tinhTrangTaiSanDTO);

    /**
     * Get all the tinhTrangTaiSans.
     *
     * @return the list of entities.
     */
    List<TinhTrangTaiSanDTO> findAll();

    /**
     * Get the "id" tinhTrangTaiSan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TinhTrangTaiSanDTO> findOne(Long id);

    /**
     * Delete the "id" tinhTrangTaiSan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
