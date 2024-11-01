package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.NhiemVu;
import vn.vnpt.repository.NhiemVuRepository;
import vn.vnpt.service.NhiemVuService;
import vn.vnpt.service.dto.NhiemVuDTO;
import vn.vnpt.service.mapper.NhiemVuMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.NhiemVu}.
 */
@Service
@Transactional
public class NhiemVuServiceImpl implements NhiemVuService {

    private static final Logger LOG = LoggerFactory.getLogger(NhiemVuServiceImpl.class);

    private final NhiemVuRepository nhiemVuRepository;

    private final NhiemVuMapper nhiemVuMapper;

    public NhiemVuServiceImpl(NhiemVuRepository nhiemVuRepository, NhiemVuMapper nhiemVuMapper) {
        this.nhiemVuRepository = nhiemVuRepository;
        this.nhiemVuMapper = nhiemVuMapper;
    }

    @Override
    public NhiemVuDTO save(NhiemVuDTO nhiemVuDTO) {
        LOG.debug("Request to save NhiemVu : {}", nhiemVuDTO);
        NhiemVu nhiemVu = nhiemVuMapper.toEntity(nhiemVuDTO);
        nhiemVu = nhiemVuRepository.save(nhiemVu);
        return nhiemVuMapper.toDto(nhiemVu);
    }

    @Override
    public NhiemVuDTO update(NhiemVuDTO nhiemVuDTO) {
        LOG.debug("Request to update NhiemVu : {}", nhiemVuDTO);
        NhiemVu nhiemVu = nhiemVuMapper.toEntity(nhiemVuDTO);
        nhiemVu.setIsPersisted();
        nhiemVu = nhiemVuRepository.save(nhiemVu);
        return nhiemVuMapper.toDto(nhiemVu);
    }

    @Override
    public Optional<NhiemVuDTO> partialUpdate(NhiemVuDTO nhiemVuDTO) {
        LOG.debug("Request to partially update NhiemVu : {}", nhiemVuDTO);

        return nhiemVuRepository
            .findById(nhiemVuDTO.getIdNhiemVu())
            .map(existingNhiemVu -> {
                nhiemVuMapper.partialUpdate(existingNhiemVu, nhiemVuDTO);

                return existingNhiemVu;
            })
            .map(nhiemVuRepository::save)
            .map(nhiemVuMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NhiemVuDTO> findOne(String id) {
        LOG.debug("Request to get NhiemVu : {}", id);
        return nhiemVuRepository.findById(id).map(nhiemVuMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete NhiemVu : {}", id);
        nhiemVuRepository.deleteById(id);
    }
}
