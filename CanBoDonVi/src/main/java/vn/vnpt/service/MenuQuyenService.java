package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.MenuQuyen;
import vn.vnpt.repository.MenuQuyenRepository;
import vn.vnpt.service.dto.MenuQuyenDTO;
import vn.vnpt.service.mapper.MenuQuyenMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.MenuQuyen}.
 */
@Service
@Transactional
public class MenuQuyenService {

    private static final Logger LOG = LoggerFactory.getLogger(MenuQuyenService.class);

    private final MenuQuyenRepository menuQuyenRepository;

    private final MenuQuyenMapper menuQuyenMapper;

    public MenuQuyenService(MenuQuyenRepository menuQuyenRepository, MenuQuyenMapper menuQuyenMapper) {
        this.menuQuyenRepository = menuQuyenRepository;
        this.menuQuyenMapper = menuQuyenMapper;
    }

    /**
     * Save a menuQuyen.
     *
     * @param menuQuyenDTO the entity to save.
     * @return the persisted entity.
     */
    public MenuQuyenDTO save(MenuQuyenDTO menuQuyenDTO) {
        LOG.debug("Request to save MenuQuyen : {}", menuQuyenDTO);
        MenuQuyen menuQuyen = menuQuyenMapper.toEntity(menuQuyenDTO);
        menuQuyen = menuQuyenRepository.save(menuQuyen);
        return menuQuyenMapper.toDto(menuQuyen);
    }

    /**
     * Update a menuQuyen.
     *
     * @param menuQuyenDTO the entity to save.
     * @return the persisted entity.
     */
    public MenuQuyenDTO update(MenuQuyenDTO menuQuyenDTO) {
        LOG.debug("Request to update MenuQuyen : {}", menuQuyenDTO);
        MenuQuyen menuQuyen = menuQuyenMapper.toEntity(menuQuyenDTO);
        menuQuyen = menuQuyenRepository.save(menuQuyen);
        return menuQuyenMapper.toDto(menuQuyen);
    }

    /**
     * Partially update a menuQuyen.
     *
     * @param menuQuyenDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MenuQuyenDTO> partialUpdate(MenuQuyenDTO menuQuyenDTO) {
        LOG.debug("Request to partially update MenuQuyen : {}", menuQuyenDTO);

        return menuQuyenRepository
            .findById(menuQuyenDTO.getId())
            .map(existingMenuQuyen -> {
                menuQuyenMapper.partialUpdate(existingMenuQuyen, menuQuyenDTO);

                return existingMenuQuyen;
            })
            .map(menuQuyenRepository::save)
            .map(menuQuyenMapper::toDto);
    }

    /**
     * Get all the menuQuyens.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MenuQuyenDTO> findAll() {
        LOG.debug("Request to get all MenuQuyens");
        return menuQuyenRepository.findAll().stream().map(menuQuyenMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one menuQuyen by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MenuQuyenDTO> findOne(Long id) {
        LOG.debug("Request to get MenuQuyen : {}", id);
        return menuQuyenRepository.findById(id).map(menuQuyenMapper::toDto);
    }

    /**
     * Delete the menuQuyen by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete MenuQuyen : {}", id);
        menuQuyenRepository.deleteById(id);
    }
}
