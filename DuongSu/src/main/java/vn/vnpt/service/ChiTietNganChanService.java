package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.ChiTietNganChanDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.ChiTietNganChan}.
 */
public interface ChiTietNganChanService {
    /**
     * Save a chiTietNganChan.
     *
     * @param chiTietNganChanDTO the entity to save.
     * @return the persisted entity.
     */
    ChiTietNganChanDTO save(ChiTietNganChanDTO chiTietNganChanDTO);

    /**
     * Updates a chiTietNganChan.
     *
     * @param chiTietNganChanDTO the entity to update.
     * @return the persisted entity.
     */
    ChiTietNganChanDTO update(ChiTietNganChanDTO chiTietNganChanDTO);

    /**
     * Partially updates a chiTietNganChan.
     *
     * @param chiTietNganChanDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ChiTietNganChanDTO> partialUpdate(ChiTietNganChanDTO chiTietNganChanDTO);

    /**
     * Get the "id" chiTietNganChan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ChiTietNganChanDTO> findOne(Long id);

    /**
     * Delete the "id" chiTietNganChan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
