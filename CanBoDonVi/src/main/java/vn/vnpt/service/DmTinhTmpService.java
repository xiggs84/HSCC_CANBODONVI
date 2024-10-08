package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.DmTinhTmpDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DmTinhTmp}.
 */
public interface DmTinhTmpService {
    /**
     * Save a dmTinhTmp.
     *
     * @param dmTinhTmpDTO the entity to save.
     * @return the persisted entity.
     */
    DmTinhTmpDTO save(DmTinhTmpDTO dmTinhTmpDTO);

    /**
     * Updates a dmTinhTmp.
     *
     * @param dmTinhTmpDTO the entity to update.
     * @return the persisted entity.
     */
    DmTinhTmpDTO update(DmTinhTmpDTO dmTinhTmpDTO);

    /**
     * Partially updates a dmTinhTmp.
     *
     * @param dmTinhTmpDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DmTinhTmpDTO> partialUpdate(DmTinhTmpDTO dmTinhTmpDTO);

    /**
     * Get the "id" dmTinhTmp.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DmTinhTmpDTO> findOne(Long id);

    /**
     * Delete the "id" dmTinhTmp.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
