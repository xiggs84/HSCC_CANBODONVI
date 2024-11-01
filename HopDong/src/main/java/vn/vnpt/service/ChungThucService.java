package vn.vnpt.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.vnpt.service.dto.ChungThucDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.ChungThuc}.
 */
public interface ChungThucService {
    /**
     * Save a chungThuc.
     *
     * @param chungThucDTO the entity to save.
     * @return the persisted entity.
     */
    ChungThucDTO save(ChungThucDTO chungThucDTO);

    /**
     * Updates a chungThuc.
     *
     * @param chungThucDTO the entity to update.
     * @return the persisted entity.
     */
    ChungThucDTO update(ChungThucDTO chungThucDTO);

    /**
     * Partially updates a chungThuc.
     *
     * @param chungThucDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ChungThucDTO> partialUpdate(ChungThucDTO chungThucDTO);

    /**
     * Get all the chungThucs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ChungThucDTO> findAll(Pageable pageable);

    /**
     * Get the "id" chungThuc.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ChungThucDTO> findOne(String id);

    /**
     * Delete the "id" chungThuc.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
