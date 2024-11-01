package vn.vnpt.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.vnpt.service.dto.DanhMucLoaiHopDongDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DanhMucLoaiHopDong}.
 */
public interface DanhMucLoaiHopDongService {
    /**
     * Save a danhMucLoaiHopDong.
     *
     * @param danhMucLoaiHopDongDTO the entity to save.
     * @return the persisted entity.
     */
    DanhMucLoaiHopDongDTO save(DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO);

    /**
     * Updates a danhMucLoaiHopDong.
     *
     * @param danhMucLoaiHopDongDTO the entity to update.
     * @return the persisted entity.
     */
    DanhMucLoaiHopDongDTO update(DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO);

    /**
     * Partially updates a danhMucLoaiHopDong.
     *
     * @param danhMucLoaiHopDongDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DanhMucLoaiHopDongDTO> partialUpdate(DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO);

    /**
     * Get all the danhMucLoaiHopDongs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DanhMucLoaiHopDongDTO> findAll(Pageable pageable);

    /**
     * Get the "id" danhMucLoaiHopDong.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhMucLoaiHopDongDTO> findOne(String id);

    /**
     * Delete the "id" danhMucLoaiHopDong.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    List<DanhMucLoaiHopDongDTO> findByIdDonVi(Long idDonVi);

    List<DanhMucLoaiHopDongDTO> search(String dienGiai, String idNhomHopDong, String idVaiTro1, String idVaiTro2, Long idDonVi);

    List<DanhMucLoaiHopDongDTO> findByGroup(String grp);
}
