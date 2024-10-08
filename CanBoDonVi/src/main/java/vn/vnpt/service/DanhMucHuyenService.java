package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.DanhMucHuyenDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DanhMucHuyen}.
 */
public interface DanhMucHuyenService {
    /**
     * Save a danhMucHuyen.
     *
     * @param danhMucHuyenDTO the entity to save.
     * @return the persisted entity.
     */
    DanhMucHuyenDTO save(DanhMucHuyenDTO danhMucHuyenDTO);

    /**
     * Updates a danhMucHuyen.
     *
     * @param danhMucHuyenDTO the entity to update.
     * @return the persisted entity.
     */
    DanhMucHuyenDTO update(DanhMucHuyenDTO danhMucHuyenDTO);

    /**
     * Partially updates a danhMucHuyen.
     *
     * @param danhMucHuyenDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DanhMucHuyenDTO> partialUpdate(DanhMucHuyenDTO danhMucHuyenDTO);

    /**
     * Get the "id" danhMucHuyen.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhMucHuyenDTO> findOne(String id);

    /**
     * Delete the "id" danhMucHuyen.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
