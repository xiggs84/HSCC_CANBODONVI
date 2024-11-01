package vn.vnpt.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.vnpt.service.dto.LoaiChungThucDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.LoaiChungThuc}.
 */
public interface LoaiChungThucService {
    /**
     * Save a loaiChungThuc.
     *
     * @param loaiChungThucDTO the entity to save.
     * @return the persisted entity.
     */
    LoaiChungThucDTO save(LoaiChungThucDTO loaiChungThucDTO);

    /**
     * Updates a loaiChungThuc.
     *
     * @param loaiChungThucDTO the entity to update.
     * @return the persisted entity.
     */
    LoaiChungThucDTO update(LoaiChungThucDTO loaiChungThucDTO);

    /**
     * Partially updates a loaiChungThuc.
     *
     * @param loaiChungThucDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LoaiChungThucDTO> partialUpdate(LoaiChungThucDTO loaiChungThucDTO);

    /**
     * Get all the loaiChungThucs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LoaiChungThucDTO> findAll(Pageable pageable);

    /**
     * Get the "id" loaiChungThuc.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LoaiChungThucDTO> findOne(String id);

    /**
     * Delete the "id" loaiChungThuc.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
