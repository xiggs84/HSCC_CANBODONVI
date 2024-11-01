package vn.vnpt.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.vnpt.service.dto.DanhMucVaiTroDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DanhMucVaiTro}.
 */
public interface DanhMucVaiTroService {
    /**
     * Save a danhMucVaiTro.
     *
     * @param danhMucVaiTroDTO the entity to save.
     * @return the persisted entity.
     */
    DanhMucVaiTroDTO save(DanhMucVaiTroDTO danhMucVaiTroDTO);

    /**
     * Updates a danhMucVaiTro.
     *
     * @param danhMucVaiTroDTO the entity to update.
     * @return the persisted entity.
     */
    DanhMucVaiTroDTO update(DanhMucVaiTroDTO danhMucVaiTroDTO);

    /**
     * Partially updates a danhMucVaiTro.
     *
     * @param danhMucVaiTroDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DanhMucVaiTroDTO> partialUpdate(DanhMucVaiTroDTO danhMucVaiTroDTO);

    /**
     * Get all the danhMucVaiTros.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DanhMucVaiTroDTO> findAll(Pageable pageable);

    /**
     * Get the "id" danhMucVaiTro.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhMucVaiTroDTO> findOne(String id);

    /**
     * Delete the "id" danhMucVaiTro.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    public Optional<String> findDienGiaiById(String id);
}
