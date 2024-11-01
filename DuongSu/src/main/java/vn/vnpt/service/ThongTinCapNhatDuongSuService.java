package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.ThongTinCapNhatDuongSuDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.ThongTinCapNhatDuongSu}.
 */
public interface ThongTinCapNhatDuongSuService {
    /**
     * Save a thongTinCapNhatDuongSu.
     *
     * @param thongTinCapNhatDuongSuDTO the entity to save.
     * @return the persisted entity.
     */
    ThongTinCapNhatDuongSuDTO save(ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO);

    /**
     * Updates a thongTinCapNhatDuongSu.
     *
     * @param thongTinCapNhatDuongSuDTO the entity to update.
     * @return the persisted entity.
     */
    ThongTinCapNhatDuongSuDTO update(ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO);

    /**
     * Partially updates a thongTinCapNhatDuongSu.
     *
     * @param thongTinCapNhatDuongSuDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ThongTinCapNhatDuongSuDTO> partialUpdate(ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO);

    /**
     * Get the "id" thongTinCapNhatDuongSu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ThongTinCapNhatDuongSuDTO> findOne(Long id);

    /**
     * Delete the "id" thongTinCapNhatDuongSu.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
