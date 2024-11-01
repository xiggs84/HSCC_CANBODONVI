package vn.vnpt.service;

import java.util.Optional;

import reactor.core.publisher.Mono;
import vn.vnpt.service.dto.DanhMucCanBoDTO;
import vn.vnpt.service.dto.UserDTO;

/**
 * Service Interface for managing {@link vn.vnpt.domain.DanhMucCanBo}.
 */
public interface DanhMucCanBoService {
    /**
     * Save a danhMucCanBo.
     *
     * @param danhMucCanBoDTO the entity to save.
     * @return the persisted entity.
     */
    DanhMucCanBoDTO save(DanhMucCanBoDTO danhMucCanBoDTO);

    /**
     * Updates a danhMucCanBo.
     *
     * @param danhMucCanBoDTO the entity to update.
     * @return the persisted entity.
     */
    DanhMucCanBoDTO update(DanhMucCanBoDTO danhMucCanBoDTO);

    /**
     * Partially updates a danhMucCanBo.
     *
     * @param danhMucCanBoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DanhMucCanBoDTO> partialUpdate(DanhMucCanBoDTO danhMucCanBoDTO);

    /**
     * Get the "id" danhMucCanBo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhMucCanBoDTO> findOne(Long id);

    /**
     * Delete the "id" danhMucCanBo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
