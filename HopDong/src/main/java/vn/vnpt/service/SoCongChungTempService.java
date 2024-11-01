package vn.vnpt.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.vnpt.service.dto.SoCongChungTempDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.SoCongChungTemp}.
 */
public interface SoCongChungTempService {
    /**
     * Save a soCongChungTemp.
     *
     * @param soCongChungTempDTO the entity to save.
     * @return the persisted entity.
     */
    SoCongChungTempDTO save(SoCongChungTempDTO soCongChungTempDTO);

    /**
     * Updates a soCongChungTemp.
     *
     * @param soCongChungTempDTO the entity to update.
     * @return the persisted entity.
     */
    SoCongChungTempDTO update(SoCongChungTempDTO soCongChungTempDTO);

    /**
     * Partially updates a soCongChungTemp.
     *
     * @param soCongChungTempDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SoCongChungTempDTO> partialUpdate(SoCongChungTempDTO soCongChungTempDTO);

    /**
     * Get all the soCongChungTemps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SoCongChungTempDTO> findAll(Pageable pageable);

    /**
     * Get the "id" soCongChungTemp.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SoCongChungTempDTO> findOne(String id);

    /**
     * Delete the "id" soCongChungTemp.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
