package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.CanBoQuyenDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.CanBoQuyen}.
 */
public interface CanBoQuyenService {
    /**
     * Save a canBoQuyen.
     *
     * @param canBoQuyenDTO the entity to save.
     * @return the persisted entity.
     */
    CanBoQuyenDTO save(CanBoQuyenDTO canBoQuyenDTO);

    /**
     * Updates a canBoQuyen.
     *
     * @param canBoQuyenDTO the entity to update.
     * @return the persisted entity.
     */
    CanBoQuyenDTO update(CanBoQuyenDTO canBoQuyenDTO);

    /**
     * Partially updates a canBoQuyen.
     *
     * @param canBoQuyenDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CanBoQuyenDTO> partialUpdate(CanBoQuyenDTO canBoQuyenDTO);

    /**
     * Get the "id" canBoQuyen.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CanBoQuyenDTO> findOne(Long id);

    /**
     * Delete the "id" canBoQuyen.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
