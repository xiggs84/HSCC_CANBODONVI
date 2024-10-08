package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.DonViScanQrDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DonViScanQr}.
 */
public interface DonViScanQrService {
    /**
     * Save a donViScanQr.
     *
     * @param donViScanQrDTO the entity to save.
     * @return the persisted entity.
     */
    DonViScanQrDTO save(DonViScanQrDTO donViScanQrDTO);

    /**
     * Updates a donViScanQr.
     *
     * @param donViScanQrDTO the entity to update.
     * @return the persisted entity.
     */
    DonViScanQrDTO update(DonViScanQrDTO donViScanQrDTO);

    /**
     * Partially updates a donViScanQr.
     *
     * @param donViScanQrDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DonViScanQrDTO> partialUpdate(DonViScanQrDTO donViScanQrDTO);

    /**
     * Get the "id" donViScanQr.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DonViScanQrDTO> findOne(Long id);

    /**
     * Delete the "id" donViScanQr.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
