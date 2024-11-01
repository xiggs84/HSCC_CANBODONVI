package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.LoaiDonVi;
import vn.vnpt.repository.LoaiDonViRepository;
import vn.vnpt.service.LoaiDonViService;
import vn.vnpt.service.dto.LoaiDonViDTO;
import vn.vnpt.service.mapper.LoaiDonViMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.LoaiDonVi}.
 */
@Service
@Transactional
public class LoaiDonViServiceImpl implements LoaiDonViService {

    private static final Logger LOG = LoggerFactory.getLogger(LoaiDonViServiceImpl.class);

    private final LoaiDonViRepository loaiDonViRepository;

    private final LoaiDonViMapper loaiDonViMapper;

    public LoaiDonViServiceImpl(LoaiDonViRepository loaiDonViRepository, LoaiDonViMapper loaiDonViMapper) {
        this.loaiDonViRepository = loaiDonViRepository;
        this.loaiDonViMapper = loaiDonViMapper;
    }

    @Override
    public LoaiDonViDTO save(LoaiDonViDTO loaiDonViDTO) {
        LOG.debug("Request to save LoaiDonVi : {}", loaiDonViDTO);
        LoaiDonVi loaiDonVi = loaiDonViMapper.toEntity(loaiDonViDTO);
        loaiDonVi = loaiDonViRepository.save(loaiDonVi);
        return loaiDonViMapper.toDto(loaiDonVi);
    }

    @Override
    public LoaiDonViDTO update(LoaiDonViDTO loaiDonViDTO) {
        LOG.debug("Request to update LoaiDonVi : {}", loaiDonViDTO);
        LoaiDonVi loaiDonVi = loaiDonViMapper.toEntity(loaiDonViDTO);
        loaiDonVi.setIsPersisted();
        loaiDonVi = loaiDonViRepository.save(loaiDonVi);
        return loaiDonViMapper.toDto(loaiDonVi);
    }

    @Override
    public Optional<LoaiDonViDTO> partialUpdate(LoaiDonViDTO loaiDonViDTO) {
        LOG.debug("Request to partially update LoaiDonVi : {}", loaiDonViDTO);

        return loaiDonViRepository
            .findById(loaiDonViDTO.getIdLoaiDv())
            .map(existingLoaiDonVi -> {
                loaiDonViMapper.partialUpdate(existingLoaiDonVi, loaiDonViDTO);

                return existingLoaiDonVi;
            })
            .map(loaiDonViRepository::save)
            .map(loaiDonViMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LoaiDonViDTO> findOne(String id) {
        LOG.debug("Request to get LoaiDonVi : {}", id);
        return loaiDonViRepository.findById(id).map(loaiDonViMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete LoaiDonVi : {}", id);
        loaiDonViRepository.deleteById(id);
    }
}
