package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.DmNoiCapGpdkxDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DmNoiCapGpdkx}.
 */
public interface DmNoiCapGpdkxService {
    /**
     * Save a dmNoiCapGpdkx.
     *
     * @param dmNoiCapGpdkxDTO the entity to save.
     * @return the persisted entity.
     */
    DmNoiCapGpdkxDTO save(DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO);

    /**
     * Updates a dmNoiCapGpdkx.
     *
     * @param dmNoiCapGpdkxDTO the entity to update.
     * @return the persisted entity.
     */
    DmNoiCapGpdkxDTO update(DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO);

    /**
     * Partially updates a dmNoiCapGpdkx.
     *
     * @param dmNoiCapGpdkxDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DmNoiCapGpdkxDTO> partialUpdate(DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO);

    /**
     * Get the "id" dmNoiCapGpdkx.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DmNoiCapGpdkxDTO> findOne(Long id);

    /**
     * Delete the "id" dmNoiCapGpdkx.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
