package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.CauHinhThongTinDuongSuDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.CauHinhThongTinDuongSu}.
 */
public interface CauHinhThongTinDuongSuService {
    /**
     * Save a cauHinhThongTinDuongSu.
     *
     * @param cauHinhThongTinDuongSuDTO the entity to save.
     * @return the persisted entity.
     */
    CauHinhThongTinDuongSuDTO save(CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO);

    /**
     * Updates a cauHinhThongTinDuongSu.
     *
     * @param cauHinhThongTinDuongSuDTO the entity to update.
     * @return the persisted entity.
     */
    CauHinhThongTinDuongSuDTO update(CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO);

    /**
     * Partially updates a cauHinhThongTinDuongSu.
     *
     * @param cauHinhThongTinDuongSuDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CauHinhThongTinDuongSuDTO> partialUpdate(CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO);

    /**
     * Get the "id" cauHinhThongTinDuongSu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CauHinhThongTinDuongSuDTO> findOne(Long id);

    /**
     * Delete the "id" cauHinhThongTinDuongSu.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
