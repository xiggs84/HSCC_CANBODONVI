package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.MenuQuyen;
import vn.vnpt.repository.MenuQuyenRepository;
import vn.vnpt.service.MenuQuyenService;
import vn.vnpt.service.dto.MenuQuyenDTO;
import vn.vnpt.service.mapper.MenuQuyenMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.MenuQuyen}.
 */
@Service
@Transactional
public class MenuQuyenServiceImpl implements MenuQuyenService {

    private static final Logger LOG = LoggerFactory.getLogger(MenuQuyenServiceImpl.class);

    private final MenuQuyenRepository menuQuyenRepository;

    private final MenuQuyenMapper menuQuyenMapper;

    public MenuQuyenServiceImpl(MenuQuyenRepository menuQuyenRepository, MenuQuyenMapper menuQuyenMapper) {
        this.menuQuyenRepository = menuQuyenRepository;
        this.menuQuyenMapper = menuQuyenMapper;
    }

    @Override
    public MenuQuyenDTO save(MenuQuyenDTO menuQuyenDTO) {
        LOG.debug("Request to save MenuQuyen : {}", menuQuyenDTO);
        MenuQuyen menuQuyen = menuQuyenMapper.toEntity(menuQuyenDTO);
        menuQuyen = menuQuyenRepository.save(menuQuyen);
        return menuQuyenMapper.toDto(menuQuyen);
    }

    @Override
    public MenuQuyenDTO update(MenuQuyenDTO menuQuyenDTO) {
        LOG.debug("Request to update MenuQuyen : {}", menuQuyenDTO);
        MenuQuyen menuQuyen = menuQuyenMapper.toEntity(menuQuyenDTO);
        menuQuyen = menuQuyenRepository.save(menuQuyen);
        return menuQuyenMapper.toDto(menuQuyen);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public Optional<MenuQuyenDTO> findOne(Long id) {
        LOG.debug("Request to get MenuQuyen : {}", id);
        return menuQuyenRepository.findById(id).map(menuQuyenMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete MenuQuyen : {}", id);
        menuQuyenRepository.deleteById(id);
    }
}
