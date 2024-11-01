package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.DuongSuTrungCmndDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DuongSuTrungCmnd}.
 */
public interface DuongSuTrungCmndService {
    /**
     * Save a duongSuTrungCmnd.
     *
     * @param duongSuTrungCmndDTO the entity to save.
     * @return the persisted entity.
     */
    DuongSuTrungCmndDTO save(DuongSuTrungCmndDTO duongSuTrungCmndDTO);

    /**
     * Updates a duongSuTrungCmnd.
     *
     * @param duongSuTrungCmndDTO the entity to update.
     * @return the persisted entity.
     */
    DuongSuTrungCmndDTO update(DuongSuTrungCmndDTO duongSuTrungCmndDTO);

    /**
     * Partially updates a duongSuTrungCmnd.
     *
     * @param duongSuTrungCmndDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DuongSuTrungCmndDTO> partialUpdate(DuongSuTrungCmndDTO duongSuTrungCmndDTO);

    /**
     * Get the "id" duongSuTrungCmnd.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DuongSuTrungCmndDTO> findOne(Long id);

    /**
     * Delete the "id" duongSuTrungCmnd.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
