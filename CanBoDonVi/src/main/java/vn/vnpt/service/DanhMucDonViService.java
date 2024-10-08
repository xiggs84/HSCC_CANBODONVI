package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.DanhMucDonViDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DanhMucDonVi}.
 */
public interface DanhMucDonViService {
    /**
     * Save a danhMucDonVi.
     *
     * @param danhMucDonViDTO the entity to save.
     * @return the persisted entity.
     */
    DanhMucDonViDTO save(DanhMucDonViDTO danhMucDonViDTO);

    /**
     * Updates a danhMucDonVi.
     *
     * @param danhMucDonViDTO the entity to update.
     * @return the persisted entity.
     */
    DanhMucDonViDTO update(DanhMucDonViDTO danhMucDonViDTO);

    /**
     * Partially updates a danhMucDonVi.
     *
     * @param danhMucDonViDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DanhMucDonViDTO> partialUpdate(DanhMucDonViDTO danhMucDonViDTO);

    /**
     * Get the "id" danhMucDonVi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhMucDonViDTO> findOne(Long id);

    /**
     * Delete the "id" danhMucDonVi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
