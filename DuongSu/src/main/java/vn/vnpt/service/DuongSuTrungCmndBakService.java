package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.DuongSuTrungCmndBakDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DuongSuTrungCmndBak}.
 */
public interface DuongSuTrungCmndBakService {
    /**
     * Save a duongSuTrungCmndBak.
     *
     * @param duongSuTrungCmndBakDTO the entity to save.
     * @return the persisted entity.
     */
    DuongSuTrungCmndBakDTO save(DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO);

    /**
     * Updates a duongSuTrungCmndBak.
     *
     * @param duongSuTrungCmndBakDTO the entity to update.
     * @return the persisted entity.
     */
    DuongSuTrungCmndBakDTO update(DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO);

    /**
     * Partially updates a duongSuTrungCmndBak.
     *
     * @param duongSuTrungCmndBakDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DuongSuTrungCmndBakDTO> partialUpdate(DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO);

    /**
     * Get the "id" duongSuTrungCmndBak.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DuongSuTrungCmndBakDTO> findOne(Long id);

    /**
     * Delete the "id" duongSuTrungCmndBak.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
