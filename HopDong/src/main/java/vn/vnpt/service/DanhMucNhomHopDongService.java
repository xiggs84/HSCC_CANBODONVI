package vn.vnpt.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.vnpt.service.dto.DanhMucNhomHopDongDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DanhMucNhomHopDong}.
 */
public interface DanhMucNhomHopDongService {
    /**
     * Save a danhMucNhomHopDong.
     *
     * @param danhMucNhomHopDongDTO the entity to save.
     * @return the persisted entity.
     */
    DanhMucNhomHopDongDTO save(DanhMucNhomHopDongDTO danhMucNhomHopDongDTO);

    /**
     * Updates a danhMucNhomHopDong.
     *
     * @param danhMucNhomHopDongDTO the entity to update.
     * @return the persisted entity.
     */
    DanhMucNhomHopDongDTO update(DanhMucNhomHopDongDTO danhMucNhomHopDongDTO);

    /**
     * Partially updates a danhMucNhomHopDong.
     *
     * @param danhMucNhomHopDongDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DanhMucNhomHopDongDTO> partialUpdate(DanhMucNhomHopDongDTO danhMucNhomHopDongDTO);

    /**
     * Get all the danhMucNhomHopDongs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DanhMucNhomHopDongDTO> findAll(Pageable pageable);

    /**
     * Get the "id" danhMucNhomHopDong.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhMucNhomHopDongDTO> findOne(String id);

    /**
     * Delete the "id" danhMucNhomHopDong.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    public Optional<String> findDienGiaiById(String id);
}
