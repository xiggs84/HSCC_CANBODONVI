package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.NhiemVuDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.NhiemVu}.
 */
public interface NhiemVuService {
    /**
     * Save a nhiemVu.
     *
     * @param nhiemVuDTO the entity to save.
     * @return the persisted entity.
     */
    NhiemVuDTO save(NhiemVuDTO nhiemVuDTO);

    /**
     * Updates a nhiemVu.
     *
     * @param nhiemVuDTO the entity to update.
     * @return the persisted entity.
     */
    NhiemVuDTO update(NhiemVuDTO nhiemVuDTO);

    /**
     * Partially updates a nhiemVu.
     *
     * @param nhiemVuDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<NhiemVuDTO> partialUpdate(NhiemVuDTO nhiemVuDTO);

    /**
     * Get the "id" nhiemVu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NhiemVuDTO> findOne(String id);

    /**
     * Delete the "id" nhiemVu.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
