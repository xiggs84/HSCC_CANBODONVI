package vn.vnpt.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.vnpt.service.dto.SoChungThucDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.SoChungThuc}.
 */
public interface SoChungThucService {
    /**
     * Save a soChungThuc.
     *
     * @param soChungThucDTO the entity to save.
     * @return the persisted entity.
     */
    SoChungThucDTO save(SoChungThucDTO soChungThucDTO);

    /**
     * Updates a soChungThuc.
     *
     * @param soChungThucDTO the entity to update.
     * @return the persisted entity.
     */
    SoChungThucDTO update(SoChungThucDTO soChungThucDTO);

    /**
     * Partially updates a soChungThuc.
     *
     * @param soChungThucDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SoChungThucDTO> partialUpdate(SoChungThucDTO soChungThucDTO);

    /**
     * Get all the soChungThucs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SoChungThucDTO> findAll(Pageable pageable);

    /**
     * Get the "id" soChungThuc.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SoChungThucDTO> findOne(String id);

    /**
     * Delete the "id" soChungThuc.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    List<SoChungThucDTO> findByDonVi(Long idDonVi);
}
