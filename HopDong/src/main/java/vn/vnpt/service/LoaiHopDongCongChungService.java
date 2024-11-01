package vn.vnpt.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.vnpt.service.dto.LoaiHopDongCongChungDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.LoaiHopDongCongChung}.
 */
public interface LoaiHopDongCongChungService {
    /**
     * Save a loaiHopDongCongChung.
     *
     * @param loaiHopDongCongChungDTO the entity to save.
     * @return the persisted entity.
     */
    LoaiHopDongCongChungDTO save(LoaiHopDongCongChungDTO loaiHopDongCongChungDTO);

    /**
     * Updates a loaiHopDongCongChung.
     *
     * @param loaiHopDongCongChungDTO the entity to update.
     * @return the persisted entity.
     */
    LoaiHopDongCongChungDTO update(LoaiHopDongCongChungDTO loaiHopDongCongChungDTO);

    /**
     * Partially updates a loaiHopDongCongChung.
     *
     * @param loaiHopDongCongChungDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LoaiHopDongCongChungDTO> partialUpdate(LoaiHopDongCongChungDTO loaiHopDongCongChungDTO);

    /**
     * Get all the loaiHopDongCongChungs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LoaiHopDongCongChungDTO> findAll(Pageable pageable);

    /**
     * Get the "id" loaiHopDongCongChung.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LoaiHopDongCongChungDTO> findOne(String id);

    /**
     * Delete the "id" loaiHopDongCongChung.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
