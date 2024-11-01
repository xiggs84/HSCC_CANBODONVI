package vn.vnpt.service;

import java.util.List;
import java.util.Optional;
import vn.vnpt.service.dto.DmTaiSanDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DmTaiSan}.
 */
public interface DmTaiSanService {
    /**
     * Save a dmTaiSan.
     *
     * @param dmTaiSanDTO the entity to save.
     * @return the persisted entity.
     */
    DmTaiSanDTO save(DmTaiSanDTO dmTaiSanDTO);

    /**
     * Updates a dmTaiSan.
     *
     * @param dmTaiSanDTO the entity to update.
     * @return the persisted entity.
     */
    DmTaiSanDTO update(DmTaiSanDTO dmTaiSanDTO);

    /**
     * Partially updates a dmTaiSan.
     *
     * @param dmTaiSanDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DmTaiSanDTO> partialUpdate(DmTaiSanDTO dmTaiSanDTO);

    /**
     * Get all the dmTaiSans.
     *
     * @return the list of entities.
     */
    List<DmTaiSanDTO> findAll();

    /**
     * Get the "id" dmTaiSan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DmTaiSanDTO> findOne(Long id);

    /**
     * Delete the "id" dmTaiSan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
