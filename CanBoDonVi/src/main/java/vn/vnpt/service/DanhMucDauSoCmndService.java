package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.DanhMucDauSoCmndDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DanhMucDauSoCmnd}.
 */
public interface DanhMucDauSoCmndService {
    /**
     * Save a danhMucDauSoCmnd.
     *
     * @param danhMucDauSoCmndDTO the entity to save.
     * @return the persisted entity.
     */
    DanhMucDauSoCmndDTO save(DanhMucDauSoCmndDTO danhMucDauSoCmndDTO);

    /**
     * Updates a danhMucDauSoCmnd.
     *
     * @param danhMucDauSoCmndDTO the entity to update.
     * @return the persisted entity.
     */
    DanhMucDauSoCmndDTO update(DanhMucDauSoCmndDTO danhMucDauSoCmndDTO);

    /**
     * Partially updates a danhMucDauSoCmnd.
     *
     * @param danhMucDauSoCmndDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DanhMucDauSoCmndDTO> partialUpdate(DanhMucDauSoCmndDTO danhMucDauSoCmndDTO);

    /**
     * Get the "id" danhMucDauSoCmnd.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhMucDauSoCmndDTO> findOne(Long id);

    /**
     * Delete the "id" danhMucDauSoCmnd.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
