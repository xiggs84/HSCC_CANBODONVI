package vn.vnpt.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.vnpt.service.dto.DanhMucLoaiSoCongChungDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DanhMucLoaiSoCongChung}.
 */
public interface DanhMucLoaiSoCongChungService {
    /**
     * Save a danhMucLoaiSoCongChung.
     *
     * @param danhMucLoaiSoCongChungDTO the entity to save.
     * @return the persisted entity.
     */
    DanhMucLoaiSoCongChungDTO save(DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO);

    /**
     * Updates a danhMucLoaiSoCongChung.
     *
     * @param danhMucLoaiSoCongChungDTO the entity to update.
     * @return the persisted entity.
     */
    DanhMucLoaiSoCongChungDTO update(DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO);

    /**
     * Partially updates a danhMucLoaiSoCongChung.
     *
     * @param danhMucLoaiSoCongChungDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DanhMucLoaiSoCongChungDTO> partialUpdate(DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO);

    /**
     * Get all the danhMucLoaiSoCongChungs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DanhMucLoaiSoCongChungDTO> findAll(Pageable pageable);

    /**
     * Get the "id" danhMucLoaiSoCongChung.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhMucLoaiSoCongChungDTO> findOne(String id);

    /**
     * Delete the "id" danhMucLoaiSoCongChung.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
