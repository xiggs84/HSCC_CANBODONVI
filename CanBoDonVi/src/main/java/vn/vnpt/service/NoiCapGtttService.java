package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.NoiCapGtttDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.NoiCapGttt}.
 */
public interface NoiCapGtttService {
    /**
     * Save a noiCapGttt.
     *
     * @param noiCapGtttDTO the entity to save.
     * @return the persisted entity.
     */
    NoiCapGtttDTO save(NoiCapGtttDTO noiCapGtttDTO);

    /**
     * Updates a noiCapGttt.
     *
     * @param noiCapGtttDTO the entity to update.
     * @return the persisted entity.
     */
    NoiCapGtttDTO update(NoiCapGtttDTO noiCapGtttDTO);

    /**
     * Partially updates a noiCapGttt.
     *
     * @param noiCapGtttDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<NoiCapGtttDTO> partialUpdate(NoiCapGtttDTO noiCapGtttDTO);

    /**
     * Get the "id" noiCapGttt.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NoiCapGtttDTO> findOne(Long id);

    /**
     * Delete the "id" noiCapGttt.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
