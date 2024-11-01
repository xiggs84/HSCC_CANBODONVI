package vn.vnpt.service;

import java.util.List;
import java.util.Optional;
import vn.vnpt.service.dto.TaisannhadatidDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.Taisannhadatid}.
 */
public interface TaisannhadatidService {
    /**
     * Save a taisannhadatid.
     *
     * @param taisannhadatidDTO the entity to save.
     * @return the persisted entity.
     */
    TaisannhadatidDTO save(TaisannhadatidDTO taisannhadatidDTO);

    /**
     * Updates a taisannhadatid.
     *
     * @param taisannhadatidDTO the entity to update.
     * @return the persisted entity.
     */
    TaisannhadatidDTO update(TaisannhadatidDTO taisannhadatidDTO);

    /**
     * Partially updates a taisannhadatid.
     *
     * @param taisannhadatidDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TaisannhadatidDTO> partialUpdate(TaisannhadatidDTO taisannhadatidDTO);

    /**
     * Get all the taisannhadatids.
     *
     * @return the list of entities.
     */
    List<TaisannhadatidDTO> findAll();

    /**
     * Get the "id" taisannhadatid.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaisannhadatidDTO> findOne(Long id);

    /**
     * Delete the "id" taisannhadatid.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
