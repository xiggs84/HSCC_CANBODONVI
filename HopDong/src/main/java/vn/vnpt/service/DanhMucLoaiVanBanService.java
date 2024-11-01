package vn.vnpt.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.vnpt.service.dto.DanhMucLoaiVanBanDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DanhMucLoaiVanBan}.
 */
public interface DanhMucLoaiVanBanService {
    /**
     * Save a danhMucLoaiVanBan.
     *
     * @param danhMucLoaiVanBanDTO the entity to save.
     * @return the persisted entity.
     */
    DanhMucLoaiVanBanDTO save(DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO);

    /**
     * Updates a danhMucLoaiVanBan.
     *
     * @param danhMucLoaiVanBanDTO the entity to update.
     * @return the persisted entity.
     */
    DanhMucLoaiVanBanDTO update(DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO);

    /**
     * Partially updates a danhMucLoaiVanBan.
     *
     * @param danhMucLoaiVanBanDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DanhMucLoaiVanBanDTO> partialUpdate(DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO);

    /**
     * Get all the danhMucLoaiVanBans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DanhMucLoaiVanBanDTO> findAll(Pageable pageable);

    /**
     * Get the "id" danhMucLoaiVanBan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhMucLoaiVanBanDTO> findOne(String id);

    /**
     * Delete the "id" danhMucLoaiVanBan.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    List<DanhMucLoaiVanBanDTO> findByIdLoaiHopDong(String idLoaiHopDong);
}
