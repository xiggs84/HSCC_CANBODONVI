package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.DanhMucTinhDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DanhMucTinh}.
 */
public interface DanhMucTinhService {
    /**
     * Save a danhMucTinh.
     *
     * @param danhMucTinhDTO the entity to save.
     * @return the persisted entity.
     */
    DanhMucTinhDTO save(DanhMucTinhDTO danhMucTinhDTO);

    /**
     * Updates a danhMucTinh.
     *
     * @param danhMucTinhDTO the entity to update.
     * @return the persisted entity.
     */
    DanhMucTinhDTO update(DanhMucTinhDTO danhMucTinhDTO);

    /**
     * Partially updates a danhMucTinh.
     *
     * @param danhMucTinhDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DanhMucTinhDTO> partialUpdate(DanhMucTinhDTO danhMucTinhDTO);

    /**
     * Get the "id" danhMucTinh.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhMucTinhDTO> findOne(String id);

    /**
     * Delete the "id" danhMucTinh.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
