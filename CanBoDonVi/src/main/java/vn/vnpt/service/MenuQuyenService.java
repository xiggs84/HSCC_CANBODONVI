package vn.vnpt.service;

import java.util.Optional;
import vn.vnpt.service.dto.MenuQuyenDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.MenuQuyen}.
 */
public interface MenuQuyenService {
    /**
     * Save a menuQuyen.
     *
     * @param menuQuyenDTO the entity to save.
     * @return the persisted entity.
     */
    MenuQuyenDTO save(MenuQuyenDTO menuQuyenDTO);

    /**
     * Updates a menuQuyen.
     *
     * @param menuQuyenDTO the entity to update.
     * @return the persisted entity.
     */
    MenuQuyenDTO update(MenuQuyenDTO menuQuyenDTO);

    /**
     * Partially updates a menuQuyen.
     *
     * @param menuQuyenDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<MenuQuyenDTO> partialUpdate(MenuQuyenDTO menuQuyenDTO);

    /**
     * Get the "id" menuQuyen.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MenuQuyenDTO> findOne(Long id);

    /**
     * Delete the "id" menuQuyen.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
