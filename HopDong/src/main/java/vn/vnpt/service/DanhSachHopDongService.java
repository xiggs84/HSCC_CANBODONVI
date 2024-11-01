package vn.vnpt.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.vnpt.service.dto.DanhSachHopDongDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DanhSachHopDong}.
 */
public interface DanhSachHopDongService {
    /**
     * Save a danhSachHopDong.
     *
     * @param danhSachHopDongDTO the entity to save.
     * @return the persisted entity.
     */
    DanhSachHopDongDTO save(DanhSachHopDongDTO danhSachHopDongDTO);

    /**
     * Updates a danhSachHopDong.
     *
     * @param danhSachHopDongDTO the entity to update.
     * @return the persisted entity.
     */
    DanhSachHopDongDTO update(DanhSachHopDongDTO danhSachHopDongDTO);

    /**
     * Partially updates a danhSachHopDong.
     *
     * @param danhSachHopDongDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DanhSachHopDongDTO> partialUpdate(DanhSachHopDongDTO danhSachHopDongDTO);

    /**
     * Get all the danhSachHopDongs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DanhSachHopDongDTO> findAll(Pageable pageable);

    /**
     * Get the "id" danhSachHopDong.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhSachHopDongDTO> findOne(String id);

    /**
     * Delete the "id" danhSachHopDong.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
