package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.DanhMucQuocGiaDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DanhMucQuocGia}.
 */
public interface DanhMucQuocGiaService {
    /**
     * Save a danhMucQuocGia.
     *
     * @param danhMucQuocGiaDTO the entity to save.
     * @return the persisted entity.
     */
    DanhMucQuocGiaDTO save(DanhMucQuocGiaDTO danhMucQuocGiaDTO);

    /**
     * Updates a danhMucQuocGia.
     *
     * @param danhMucQuocGiaDTO the entity to update.
     * @return the persisted entity.
     */
    DanhMucQuocGiaDTO update(DanhMucQuocGiaDTO danhMucQuocGiaDTO);

    /**
     * Partially updates a danhMucQuocGia.
     *
     * @param danhMucQuocGiaDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DanhMucQuocGiaDTO> partialUpdate(DanhMucQuocGiaDTO danhMucQuocGiaDTO);

    /**
     * Get the "id" danhMucQuocGia.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhMucQuocGiaDTO> findOne(Long id);

    /**
     * Delete the "id" danhMucQuocGia.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
