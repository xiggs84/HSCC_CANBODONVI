package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.DanhSachDuongSuDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DanhSachDuongSu}.
 */
public interface DanhSachDuongSuService {
    /**
     * Save a danhSachDuongSu.
     *
     * @param danhSachDuongSuDTO the entity to save.
     * @return the persisted entity.
     */
    DanhSachDuongSuDTO save(DanhSachDuongSuDTO danhSachDuongSuDTO);

    /**
     * Updates a danhSachDuongSu.
     *
     * @param danhSachDuongSuDTO the entity to update.
     * @return the persisted entity.
     */
    DanhSachDuongSuDTO update(DanhSachDuongSuDTO danhSachDuongSuDTO);

    /**
     * Partially updates a danhSachDuongSu.
     *
     * @param danhSachDuongSuDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DanhSachDuongSuDTO> partialUpdate(DanhSachDuongSuDTO danhSachDuongSuDTO);

    /**
     * Get the "id" danhSachDuongSu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhSachDuongSuDTO> findOne(Long id);

    /**
     * Delete the "id" danhSachDuongSu.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
