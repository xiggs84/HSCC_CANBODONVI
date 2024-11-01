package vn.vnpt.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.vnpt.service.dto.DanhSachChungThucDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DanhSachChungThuc}.
 */
public interface DanhSachChungThucService {
    /**
     * Save a danhSachChungThuc.
     *
     * @param danhSachChungThucDTO the entity to save.
     * @return the persisted entity.
     */
    DanhSachChungThucDTO save(DanhSachChungThucDTO danhSachChungThucDTO);

    /**
     * Updates a danhSachChungThuc.
     *
     * @param danhSachChungThucDTO the entity to update.
     * @return the persisted entity.
     */
    DanhSachChungThucDTO update(DanhSachChungThucDTO danhSachChungThucDTO);

    /**
     * Partially updates a danhSachChungThuc.
     *
     * @param danhSachChungThucDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DanhSachChungThucDTO> partialUpdate(DanhSachChungThucDTO danhSachChungThucDTO);

    /**
     * Get all the danhSachChungThucs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DanhSachChungThucDTO> findAll(Pageable pageable);

    /**
     * Get the "id" danhSachChungThuc.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhSachChungThucDTO> findOne(String id);

    /**
     * Delete the "id" danhSachChungThuc.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
