package vn.vnpt.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.vnpt.service.dto.DanhMucLoaiGiayToChungThucDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DanhMucLoaiGiayToChungThuc}.
 */
public interface DanhMucLoaiGiayToChungThucService {
    /**
     * Save a danhMucLoaiGiayToChungThuc.
     *
     * @param danhMucLoaiGiayToChungThucDTO the entity to save.
     * @return the persisted entity.
     */
    DanhMucLoaiGiayToChungThucDTO save(DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO);

    /**
     * Updates a danhMucLoaiGiayToChungThuc.
     *
     * @param danhMucLoaiGiayToChungThucDTO the entity to update.
     * @return the persisted entity.
     */
    DanhMucLoaiGiayToChungThucDTO update(DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO);

    /**
     * Partially updates a danhMucLoaiGiayToChungThuc.
     *
     * @param danhMucLoaiGiayToChungThucDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DanhMucLoaiGiayToChungThucDTO> partialUpdate(DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO);

    /**
     * Get all the danhMucLoaiGiayToChungThucs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DanhMucLoaiGiayToChungThucDTO> findAll(Pageable pageable);

    /**
     * Get the "id" danhMucLoaiGiayToChungThuc.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhMucLoaiGiayToChungThucDTO> findOne(String id);

    /**
     * Delete the "id" danhMucLoaiGiayToChungThuc.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    List<DanhMucLoaiGiayToChungThucDTO> findByIdLoaiSo(String idLoaiSo);
}
