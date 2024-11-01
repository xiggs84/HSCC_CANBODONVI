package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.ChiTietNganChan;
import vn.vnpt.repository.ChiTietNganChanRepository;
import vn.vnpt.service.ChiTietNganChanService;
import vn.vnpt.service.dto.ChiTietNganChanDTO;
import vn.vnpt.service.mapper.ChiTietNganChanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.ChiTietNganChan}.
 */
@Service
@Transactional
public class ChiTietNganChanServiceImpl implements ChiTietNganChanService {

    private static final Logger LOG = LoggerFactory.getLogger(ChiTietNganChanServiceImpl.class);

    private final ChiTietNganChanRepository chiTietNganChanRepository;

    private final ChiTietNganChanMapper chiTietNganChanMapper;

    public ChiTietNganChanServiceImpl(ChiTietNganChanRepository chiTietNganChanRepository, ChiTietNganChanMapper chiTietNganChanMapper) {
        this.chiTietNganChanRepository = chiTietNganChanRepository;
        this.chiTietNganChanMapper = chiTietNganChanMapper;
    }

    @Override
    public ChiTietNganChanDTO save(ChiTietNganChanDTO chiTietNganChanDTO) {
        LOG.debug("Request to save ChiTietNganChan : {}", chiTietNganChanDTO);
        ChiTietNganChan chiTietNganChan = chiTietNganChanMapper.toEntity(chiTietNganChanDTO);
        chiTietNganChan = chiTietNganChanRepository.save(chiTietNganChan);
        return chiTietNganChanMapper.toDto(chiTietNganChan);
    }

    @Override
    public ChiTietNganChanDTO update(ChiTietNganChanDTO chiTietNganChanDTO) {
        LOG.debug("Request to update ChiTietNganChan : {}", chiTietNganChanDTO);
        ChiTietNganChan chiTietNganChan = chiTietNganChanMapper.toEntity(chiTietNganChanDTO);
        chiTietNganChan = chiTietNganChanRepository.save(chiTietNganChan);
        return chiTietNganChanMapper.toDto(chiTietNganChan);
    }

    @Override
    public Optional<ChiTietNganChanDTO> partialUpdate(ChiTietNganChanDTO chiTietNganChanDTO) {
        LOG.debug("Request to partially update ChiTietNganChan : {}", chiTietNganChanDTO);

        return chiTietNganChanRepository
            .findById(chiTietNganChanDTO.getId())
            .map(existingChiTietNganChan -> {
                chiTietNganChanMapper.partialUpdate(existingChiTietNganChan, chiTietNganChanDTO);

                return existingChiTietNganChan;
            })
            .map(chiTietNganChanRepository::save)
            .map(chiTietNganChanMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChiTietNganChanDTO> findOne(Long id) {
        LOG.debug("Request to get ChiTietNganChan : {}", id);
        return chiTietNganChanRepository.findById(id).map(chiTietNganChanMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete ChiTietNganChan : {}", id);
        chiTietNganChanRepository.deleteById(id);
    }
}
