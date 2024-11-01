package vn.vnpt.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.vnpt.service.dto.PhanLoaiHopDongDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.PhanLoaiHopDong}.
 */
public interface PhanLoaiHopDongService {
    /**
     * Save a phanLoaiHopDong.
     *
     * @param phanLoaiHopDongDTO the entity to save.
     * @return the persisted entity.
     */
    PhanLoaiHopDongDTO save(PhanLoaiHopDongDTO phanLoaiHopDongDTO);

    /**
     * Updates a phanLoaiHopDong.
     *
     * @param phanLoaiHopDongDTO the entity to update.
     * @return the persisted entity.
     */
    PhanLoaiHopDongDTO update(PhanLoaiHopDongDTO phanLoaiHopDongDTO);

    /**
     * Partially updates a phanLoaiHopDong.
     *
     * @param phanLoaiHopDongDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PhanLoaiHopDongDTO> partialUpdate(PhanLoaiHopDongDTO phanLoaiHopDongDTO);

    /**
     * Get all the phanLoaiHopDongs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PhanLoaiHopDongDTO> findAll(Pageable pageable);

    /**
     * Get the "id" phanLoaiHopDong.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PhanLoaiHopDongDTO> findOne(String id);

    /**
     * Delete the "id" phanLoaiHopDong.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    List<PhanLoaiHopDongDTO> findByIdNhomHopDong(String idNhomHopDong);
}
