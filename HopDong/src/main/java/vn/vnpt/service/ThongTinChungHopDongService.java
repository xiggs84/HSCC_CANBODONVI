package vn.vnpt.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.vnpt.service.dto.ThongTinChungHopDongDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.ThongTinChungHopDong}.
 */
public interface ThongTinChungHopDongService {
    /**
     * Save a thongTinChungHopDong.
     *
     * @param thongTinChungHopDongDTO the entity to save.
     * @return the persisted entity.
     */
    ThongTinChungHopDongDTO save(ThongTinChungHopDongDTO thongTinChungHopDongDTO);

    /**
     * Updates a thongTinChungHopDong.
     *
     * @param thongTinChungHopDongDTO the entity to update.
     * @return the persisted entity.
     */
    ThongTinChungHopDongDTO update(ThongTinChungHopDongDTO thongTinChungHopDongDTO);

    /**
     * Partially updates a thongTinChungHopDong.
     *
     * @param thongTinChungHopDongDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ThongTinChungHopDongDTO> partialUpdate(ThongTinChungHopDongDTO thongTinChungHopDongDTO);

    /**
     * Get all the thongTinChungHopDongs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ThongTinChungHopDongDTO> findAll(Pageable pageable);

    /**
     * Get the "id" thongTinChungHopDong.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ThongTinChungHopDongDTO> findOne(String id);

    /**
     * Delete the "id" thongTinChungHopDong.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
