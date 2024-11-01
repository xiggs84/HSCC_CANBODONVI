package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.CanBoQuyen;
import vn.vnpt.repository.CanBoQuyenRepository;
import vn.vnpt.service.CanBoQuyenService;
import vn.vnpt.service.dto.CanBoQuyenDTO;
import vn.vnpt.service.mapper.CanBoQuyenMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.CanBoQuyen}.
 */
@Service
@Transactional
public class CanBoQuyenServiceImpl implements CanBoQuyenService {

    private static final Logger LOG = LoggerFactory.getLogger(CanBoQuyenServiceImpl.class);

    private final CanBoQuyenRepository canBoQuyenRepository;

    private final CanBoQuyenMapper canBoQuyenMapper;

    public CanBoQuyenServiceImpl(CanBoQuyenRepository canBoQuyenRepository, CanBoQuyenMapper canBoQuyenMapper) {
        this.canBoQuyenRepository = canBoQuyenRepository;
        this.canBoQuyenMapper = canBoQuyenMapper;
    }

    @Override
    public CanBoQuyenDTO save(CanBoQuyenDTO canBoQuyenDTO) {
        LOG.debug("Request to save CanBoQuyen : {}", canBoQuyenDTO);
        CanBoQuyen canBoQuyen = canBoQuyenMapper.toEntity(canBoQuyenDTO);
        canBoQuyen = canBoQuyenRepository.save(canBoQuyen);
        return canBoQuyenMapper.toDto(canBoQuyen);
    }

    @Override
    public CanBoQuyenDTO update(CanBoQuyenDTO canBoQuyenDTO) {
        LOG.debug("Request to update CanBoQuyen : {}", canBoQuyenDTO);
        CanBoQuyen canBoQuyen = canBoQuyenMapper.toEntity(canBoQuyenDTO);
        canBoQuyen = canBoQuyenRepository.save(canBoQuyen);
        return canBoQuyenMapper.toDto(canBoQuyen);
    }

    @Override
    public Optional<CanBoQuyenDTO> partialUpdate(CanBoQuyenDTO canBoQuyenDTO) {
        LOG.debug("Request to partially update CanBoQuyen : {}", canBoQuyenDTO);

        return canBoQuyenRepository
            .findById(canBoQuyenDTO.getId())
            .map(existingCanBoQuyen -> {
                canBoQuyenMapper.partialUpdate(existingCanBoQuyen, canBoQuyenDTO);

                return existingCanBoQuyen;
            })
            .map(canBoQuyenRepository::save)
            .map(canBoQuyenMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CanBoQuyenDTO> findOne(Long id) {
        LOG.debug("Request to get CanBoQuyen : {}", id);
        return canBoQuyenRepository.findById(id).map(canBoQuyenMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete CanBoQuyen : {}", id);
        canBoQuyenRepository.deleteById(id);
    }
}
