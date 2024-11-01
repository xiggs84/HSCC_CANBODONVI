package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.ChiTietNganChanTaiSan;
import vn.vnpt.repository.ChiTietNganChanTaiSanRepository;
import vn.vnpt.service.ChiTietNganChanTaiSanService;
import vn.vnpt.service.dto.ChiTietNganChanTaiSanDTO;
import vn.vnpt.service.mapper.ChiTietNganChanTaiSanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.ChiTietNganChanTaiSan}.
 */
@Service
@Transactional
public class ChiTietNganChanTaiSanServiceImpl implements ChiTietNganChanTaiSanService {

    private static final Logger LOG = LoggerFactory.getLogger(ChiTietNganChanTaiSanServiceImpl.class);

    private final ChiTietNganChanTaiSanRepository chiTietNganChanTaiSanRepository;

    private final ChiTietNganChanTaiSanMapper chiTietNganChanTaiSanMapper;

    public ChiTietNganChanTaiSanServiceImpl(
        ChiTietNganChanTaiSanRepository chiTietNganChanTaiSanRepository,
        ChiTietNganChanTaiSanMapper chiTietNganChanTaiSanMapper
    ) {
        this.chiTietNganChanTaiSanRepository = chiTietNganChanTaiSanRepository;
        this.chiTietNganChanTaiSanMapper = chiTietNganChanTaiSanMapper;
    }

    @Override
    public ChiTietNganChanTaiSanDTO save(ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO) {
        LOG.debug("Request to save ChiTietNganChanTaiSan : {}", chiTietNganChanTaiSanDTO);
        ChiTietNganChanTaiSan chiTietNganChanTaiSan = chiTietNganChanTaiSanMapper.toEntity(chiTietNganChanTaiSanDTO);
        chiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.save(chiTietNganChanTaiSan);
        return chiTietNganChanTaiSanMapper.toDto(chiTietNganChanTaiSan);
    }

    @Override
    public ChiTietNganChanTaiSanDTO update(ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO) {
        LOG.debug("Request to update ChiTietNganChanTaiSan : {}", chiTietNganChanTaiSanDTO);
        ChiTietNganChanTaiSan chiTietNganChanTaiSan = chiTietNganChanTaiSanMapper.toEntity(chiTietNganChanTaiSanDTO);
        chiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.save(chiTietNganChanTaiSan);
        return chiTietNganChanTaiSanMapper.toDto(chiTietNganChanTaiSan);
    }

    @Override
    public Optional<ChiTietNganChanTaiSanDTO> partialUpdate(ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO) {
        LOG.debug("Request to partially update ChiTietNganChanTaiSan : {}", chiTietNganChanTaiSanDTO);

        return chiTietNganChanTaiSanRepository
            .findById(chiTietNganChanTaiSanDTO.getIdNganChan())
            .map(existingChiTietNganChanTaiSan -> {
                chiTietNganChanTaiSanMapper.partialUpdate(existingChiTietNganChanTaiSan, chiTietNganChanTaiSanDTO);

                return existingChiTietNganChanTaiSan;
            })
            .map(chiTietNganChanTaiSanRepository::save)
            .map(chiTietNganChanTaiSanMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChiTietNganChanTaiSanDTO> findOne(Long id) {
        LOG.debug("Request to get ChiTietNganChanTaiSan : {}", id);
        return chiTietNganChanTaiSanRepository.findById(id).map(chiTietNganChanTaiSanMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete ChiTietNganChanTaiSan : {}", id);
        chiTietNganChanTaiSanRepository.deleteById(id);
    }
}
