package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.DmHuyenTmpDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DmHuyenTmp}.
 */
public interface DmHuyenTmpService {
    /**
     * Save a dmHuyenTmp.
     *
     * @param dmHuyenTmpDTO the entity to save.
     * @return the persisted entity.
     */
    DmHuyenTmpDTO save(DmHuyenTmpDTO dmHuyenTmpDTO);

    /**
     * Updates a dmHuyenTmp.
     *
     * @param dmHuyenTmpDTO the entity to update.
     * @return the persisted entity.
     */
    DmHuyenTmpDTO update(DmHuyenTmpDTO dmHuyenTmpDTO);

    /**
     * Partially updates a dmHuyenTmp.
     *
     * @param dmHuyenTmpDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DmHuyenTmpDTO> partialUpdate(DmHuyenTmpDTO dmHuyenTmpDTO);

    /**
     * Get the "id" dmHuyenTmp.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DmHuyenTmpDTO> findOne(Long id);

    /**
     * Delete the "id" dmHuyenTmp.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
