package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucXa;
import vn.vnpt.repository.DanhMucXaRepository;
import vn.vnpt.service.DanhMucXaService;
import vn.vnpt.service.dto.DanhMucXaDTO;
import vn.vnpt.service.mapper.DanhMucXaMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucXa}.
 */
@Service
@Transactional
public class DanhMucXaServiceImpl implements DanhMucXaService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucXaServiceImpl.class);

    private final DanhMucXaRepository danhMucXaRepository;

    private final DanhMucXaMapper danhMucXaMapper;

    public DanhMucXaServiceImpl(DanhMucXaRepository danhMucXaRepository, DanhMucXaMapper danhMucXaMapper) {
        this.danhMucXaRepository = danhMucXaRepository;
        this.danhMucXaMapper = danhMucXaMapper;
    }

    @Override
    public DanhMucXaDTO save(DanhMucXaDTO danhMucXaDTO) {
        LOG.debug("Request to save DanhMucXa : {}", danhMucXaDTO);
        DanhMucXa danhMucXa = danhMucXaMapper.toEntity(danhMucXaDTO);
        danhMucXa = danhMucXaRepository.save(danhMucXa);
        return danhMucXaMapper.toDto(danhMucXa);
    }

    @Override
    public DanhMucXaDTO update(DanhMucXaDTO danhMucXaDTO) {
        LOG.debug("Request to update DanhMucXa : {}", danhMucXaDTO);
        DanhMucXa danhMucXa = danhMucXaMapper.toEntity(danhMucXaDTO);
        danhMucXa.setIsPersisted();
        danhMucXa = danhMucXaRepository.save(danhMucXa);
        return danhMucXaMapper.toDto(danhMucXa);
    }

    @Override
    public Optional<DanhMucXaDTO> partialUpdate(DanhMucXaDTO danhMucXaDTO) {
        LOG.debug("Request to partially update DanhMucXa : {}", danhMucXaDTO);

        return danhMucXaRepository
            .findById(danhMucXaDTO.getMaXa())
            .map(existingDanhMucXa -> {
                danhMucXaMapper.partialUpdate(existingDanhMucXa, danhMucXaDTO);

                return existingDanhMucXa;
            })
            .map(danhMucXaRepository::save)
            .map(danhMucXaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DanhMucXaDTO> findOne(String id) {
        LOG.debug("Request to get DanhMucXa : {}", id);
        return danhMucXaRepository.findById(id).map(danhMucXaMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete DanhMucXa : {}", id);
        danhMucXaRepository.deleteById(id);
    }
}
