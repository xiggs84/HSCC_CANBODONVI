package vn.vnpt.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.vnpt.service.dto.HopDongCongChungDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.HopDongCongChung}.
 */
public interface HopDongCongChungService {
    /**
     * Save a hopDongCongChung.
     *
     * @param hopDongCongChungDTO the entity to save.
     * @return the persisted entity.
     */
    HopDongCongChungDTO save(HopDongCongChungDTO hopDongCongChungDTO);

    /**
     * Updates a hopDongCongChung.
     *
     * @param hopDongCongChungDTO the entity to update.
     * @return the persisted entity.
     */
    HopDongCongChungDTO update(HopDongCongChungDTO hopDongCongChungDTO);

    /**
     * Partially updates a hopDongCongChung.
     *
     * @param hopDongCongChungDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<HopDongCongChungDTO> partialUpdate(HopDongCongChungDTO hopDongCongChungDTO);

    /**
     * Get all the hopDongCongChungs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HopDongCongChungDTO> findAll(Pageable pageable);

    /**
     * Get the "id" hopDongCongChung.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HopDongCongChungDTO> findOne(String id);

    /**
     * Delete the "id" hopDongCongChung.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
