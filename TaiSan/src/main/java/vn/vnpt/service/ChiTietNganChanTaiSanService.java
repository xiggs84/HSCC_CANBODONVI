package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.ChiTietNganChanTaiSanDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.ChiTietNganChanTaiSan}.
 */
public interface ChiTietNganChanTaiSanService {
    /**
     * Save a chiTietNganChanTaiSan.
     *
     * @param chiTietNganChanTaiSanDTO the entity to save.
     * @return the persisted entity.
     */
    ChiTietNganChanTaiSanDTO save(ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO);

    /**
     * Updates a chiTietNganChanTaiSan.
     *
     * @param chiTietNganChanTaiSanDTO the entity to update.
     * @return the persisted entity.
     */
    ChiTietNganChanTaiSanDTO update(ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO);

    /**
     * Partially updates a chiTietNganChanTaiSan.
     *
     * @param chiTietNganChanTaiSanDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ChiTietNganChanTaiSanDTO> partialUpdate(ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO);

    /**
     * Get the "id" chiTietNganChanTaiSan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ChiTietNganChanTaiSanDTO> findOne(Long id);

    /**
     * Delete the "id" chiTietNganChanTaiSan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
