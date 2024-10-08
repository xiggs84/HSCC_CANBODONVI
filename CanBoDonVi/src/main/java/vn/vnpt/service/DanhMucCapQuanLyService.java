package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.DanhMucCapQuanLyDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DanhMucCapQuanLy}.
 */
public interface DanhMucCapQuanLyService {
    /**
     * Save a danhMucCapQuanLy.
     *
     * @param danhMucCapQuanLyDTO the entity to save.
     * @return the persisted entity.
     */
    DanhMucCapQuanLyDTO save(DanhMucCapQuanLyDTO danhMucCapQuanLyDTO);

    /**
     * Updates a danhMucCapQuanLy.
     *
     * @param danhMucCapQuanLyDTO the entity to update.
     * @return the persisted entity.
     */
    DanhMucCapQuanLyDTO update(DanhMucCapQuanLyDTO danhMucCapQuanLyDTO);

    /**
     * Partially updates a danhMucCapQuanLy.
     *
     * @param danhMucCapQuanLyDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DanhMucCapQuanLyDTO> partialUpdate(DanhMucCapQuanLyDTO danhMucCapQuanLyDTO);

    /**
     * Get the "id" danhMucCapQuanLy.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhMucCapQuanLyDTO> findOne(Long id);

    /**
     * Delete the "id" danhMucCapQuanLy.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
