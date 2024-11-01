package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.LoaiGiayTo;
import vn.vnpt.repository.LoaiGiayToRepository;
import vn.vnpt.service.LoaiGiayToService;
import vn.vnpt.service.dto.LoaiGiayToDTO;
import vn.vnpt.service.mapper.LoaiGiayToMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.LoaiGiayTo}.
 */
@Service
@Transactional
public class LoaiGiayToServiceImpl implements LoaiGiayToService {

    private static final Logger LOG = LoggerFactory.getLogger(LoaiGiayToServiceImpl.class);

    private final LoaiGiayToRepository loaiGiayToRepository;

    private final LoaiGiayToMapper loaiGiayToMapper;

    public LoaiGiayToServiceImpl(LoaiGiayToRepository loaiGiayToRepository, LoaiGiayToMapper loaiGiayToMapper) {
        this.loaiGiayToRepository = loaiGiayToRepository;
        this.loaiGiayToMapper = loaiGiayToMapper;
    }

    @Override
    public LoaiGiayToDTO save(LoaiGiayToDTO loaiGiayToDTO) {
        LOG.debug("Request to save LoaiGiayTo : {}", loaiGiayToDTO);
        LoaiGiayTo loaiGiayTo = loaiGiayToMapper.toEntity(loaiGiayToDTO);
        loaiGiayTo = loaiGiayToRepository.save(loaiGiayTo);
        return loaiGiayToMapper.toDto(loaiGiayTo);
    }

    @Override
    public LoaiGiayToDTO update(LoaiGiayToDTO loaiGiayToDTO) {
        LOG.debug("Request to update LoaiGiayTo : {}", loaiGiayToDTO);
        LoaiGiayTo loaiGiayTo = loaiGiayToMapper.toEntity(loaiGiayToDTO);
        loaiGiayTo.setIsPersisted();
        loaiGiayTo = loaiGiayToRepository.save(loaiGiayTo);
        return loaiGiayToMapper.toDto(loaiGiayTo);
    }

    @Override
    public Optional<LoaiGiayToDTO> partialUpdate(LoaiGiayToDTO loaiGiayToDTO) {
        LOG.debug("Request to partially update LoaiGiayTo : {}", loaiGiayToDTO);

        return loaiGiayToRepository
            .findById(loaiGiayToDTO.getIdLoaiGiayTo())
            .map(existingLoaiGiayTo -> {
                loaiGiayToMapper.partialUpdate(existingLoaiGiayTo, loaiGiayToDTO);

                return existingLoaiGiayTo;
            })
            .map(loaiGiayToRepository::save)
            .map(loaiGiayToMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LoaiGiayToDTO> findOne(String id) {
        LOG.debug("Request to get LoaiGiayTo : {}", id);
        return loaiGiayToRepository.findById(id).map(loaiGiayToMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete LoaiGiayTo : {}", id);
        loaiGiayToRepository.deleteById(id);
    }
}
