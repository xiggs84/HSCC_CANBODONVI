package vn.vnpt.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.vnpt.service.dto.DanhMucTuVietTatDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DanhMucTuVietTat}.
 */
public interface DanhMucTuVietTatService {
    /**
     * Save a danhMucTuVietTat.
     *
     * @param danhMucTuVietTatDTO the entity to save.
     * @return the persisted entity.
     */
    DanhMucTuVietTatDTO save(DanhMucTuVietTatDTO danhMucTuVietTatDTO);

    /**
     * Updates a danhMucTuVietTat.
     *
     * @param danhMucTuVietTatDTO the entity to update.
     * @return the persisted entity.
     */
    DanhMucTuVietTatDTO update(DanhMucTuVietTatDTO danhMucTuVietTatDTO);

    /**
     * Partially updates a danhMucTuVietTat.
     *
     * @param danhMucTuVietTatDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DanhMucTuVietTatDTO> partialUpdate(DanhMucTuVietTatDTO danhMucTuVietTatDTO);

    /**
     * Get all the danhMucTuVietTats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DanhMucTuVietTatDTO> findAll(Pageable pageable);

    /**
     * Get the "id" danhMucTuVietTat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhMucTuVietTatDTO> findOne(String id);

    /**
     * Delete the "id" danhMucTuVietTat.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    List<DanhMucTuVietTatDTO> findByDonVi(Long idDonVi);
}
