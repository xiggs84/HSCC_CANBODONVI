package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.vnpt.domain.SoCongChungTemp;
import vn.vnpt.repository.SoCongChungTempRepository;
import vn.vnpt.service.SoCongChungTempService;
import vn.vnpt.service.dto.SoCongChungTempDTO;
import vn.vnpt.service.mapper.SoCongChungTempMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.SoCongChungTemp}.
 */
@Service
public class SoCongChungTempServiceImpl implements SoCongChungTempService {

    private static final Logger LOG = LoggerFactory.getLogger(SoCongChungTempServiceImpl.class);

    private final SoCongChungTempRepository soCongChungTempRepository;

    private final SoCongChungTempMapper soCongChungTempMapper;

    public SoCongChungTempServiceImpl(SoCongChungTempRepository soCongChungTempRepository, SoCongChungTempMapper soCongChungTempMapper) {
        this.soCongChungTempRepository = soCongChungTempRepository;
        this.soCongChungTempMapper = soCongChungTempMapper;
    }

    @Override
    public SoCongChungTempDTO save(SoCongChungTempDTO soCongChungTempDTO) {
        LOG.debug("Request to save SoCongChungTemp : {}", soCongChungTempDTO);
        SoCongChungTemp soCongChungTemp = soCongChungTempMapper.toEntity(soCongChungTempDTO);
        soCongChungTemp = soCongChungTempRepository.save(soCongChungTemp);
        return soCongChungTempMapper.toDto(soCongChungTemp);
    }

    @Override
    public SoCongChungTempDTO update(SoCongChungTempDTO soCongChungTempDTO) {
        LOG.debug("Request to update SoCongChungTemp : {}", soCongChungTempDTO);
        SoCongChungTemp soCongChungTemp = soCongChungTempMapper.toEntity(soCongChungTempDTO);
        soCongChungTemp = soCongChungTempRepository.save(soCongChungTemp);
        return soCongChungTempMapper.toDto(soCongChungTemp);
    }

    @Override
    public Optional<SoCongChungTempDTO> partialUpdate(SoCongChungTempDTO soCongChungTempDTO) {
        LOG.debug("Request to partially update SoCongChungTemp : {}", soCongChungTempDTO);

        return soCongChungTempRepository
            .findById(soCongChungTempDTO.getId())
            .map(existingSoCongChungTemp -> {
                soCongChungTempMapper.partialUpdate(existingSoCongChungTemp, soCongChungTempDTO);

                return existingSoCongChungTemp;
            })
            .map(soCongChungTempRepository::save)
            .map(soCongChungTempMapper::toDto);
    }

    @Override
    public Page<SoCongChungTempDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all SoCongChungTemps");
        return soCongChungTempRepository.findAll(pageable).map(soCongChungTempMapper::toDto);
    }

    @Override
    public Optional<SoCongChungTempDTO> findOne(String id) {
        LOG.debug("Request to get SoCongChungTemp : {}", id);
        return soCongChungTempRepository.findById(id).map(soCongChungTempMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete SoCongChungTemp : {}", id);
        soCongChungTempRepository.deleteById(id);
    }
}
