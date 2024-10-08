package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.QuyenDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.Quyen}.
 */
public interface QuyenService {
    /**
     * Save a quyen.
     *
     * @param quyenDTO the entity to save.
     * @return the persisted entity.
     */
    QuyenDTO save(QuyenDTO quyenDTO);

    /**
     * Updates a quyen.
     *
     * @param quyenDTO the entity to update.
     * @return the persisted entity.
     */
    QuyenDTO update(QuyenDTO quyenDTO);

    /**
     * Partially updates a quyen.
     *
     * @param quyenDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<QuyenDTO> partialUpdate(QuyenDTO quyenDTO);

    /**
     * Get the "id" quyen.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QuyenDTO> findOne(Long id);

    /**
     * Delete the "id" quyen.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
