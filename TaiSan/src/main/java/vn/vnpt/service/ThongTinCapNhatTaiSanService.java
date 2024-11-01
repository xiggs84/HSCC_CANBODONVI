package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.ThongTinCapNhatTaiSanDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.ThongTinCapNhatTaiSan}.
 */
public interface ThongTinCapNhatTaiSanService {
    /**
     * Save a thongTinCapNhatTaiSan.
     *
     * @param thongTinCapNhatTaiSanDTO the entity to save.
     * @return the persisted entity.
     */
    ThongTinCapNhatTaiSanDTO save(ThongTinCapNhatTaiSanDTO thongTinCapNhatTaiSanDTO);

    /**
     * Updates a thongTinCapNhatTaiSan.
     *
     * @param thongTinCapNhatTaiSanDTO the entity to update.
     * @return the persisted entity.
     */
    ThongTinCapNhatTaiSanDTO update(ThongTinCapNhatTaiSanDTO thongTinCapNhatTaiSanDTO);

    /**
     * Partially updates a thongTinCapNhatTaiSan.
     *
     * @param thongTinCapNhatTaiSanDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ThongTinCapNhatTaiSanDTO> partialUpdate(ThongTinCapNhatTaiSanDTO thongTinCapNhatTaiSanDTO);

    /**
     * Get the "id" thongTinCapNhatTaiSan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ThongTinCapNhatTaiSanDTO> findOne(Long id);

    /**
     * Delete the "id" thongTinCapNhatTaiSan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
