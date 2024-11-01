package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.TaiSan;
import vn.vnpt.repository.TaiSanRepository;
import vn.vnpt.service.TaiSanService;
import vn.vnpt.service.dto.TaiSanDTO;
import vn.vnpt.service.mapper.TaiSanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.TaiSan}.
 */
@Service
@Transactional
public class TaiSanServiceImpl implements TaiSanService {

    private static final Logger LOG = LoggerFactory.getLogger(TaiSanServiceImpl.class);

    private final TaiSanRepository taiSanRepository;

    private final TaiSanMapper taiSanMapper;

    public TaiSanServiceImpl(TaiSanRepository taiSanRepository, TaiSanMapper taiSanMapper) {
        this.taiSanRepository = taiSanRepository;
        this.taiSanMapper = taiSanMapper;
    }

    @Override
    public TaiSanDTO save(TaiSanDTO taiSanDTO) {
        LOG.debug("Request to save TaiSan : {}", taiSanDTO);
        TaiSan taiSan = taiSanMapper.toEntity(taiSanDTO);
        taiSan = taiSanRepository.save(taiSan);
        return taiSanMapper.toDto(taiSan);
    }

    @Override
    public TaiSanDTO update(TaiSanDTO taiSanDTO) {
        LOG.debug("Request to update TaiSan : {}", taiSanDTO);
        TaiSan taiSan = taiSanMapper.toEntity(taiSanDTO);
        taiSan = taiSanRepository.save(taiSan);
        return taiSanMapper.toDto(taiSan);
    }

    @Override
    public Optional<TaiSanDTO> partialUpdate(TaiSanDTO taiSanDTO) {
        LOG.debug("Request to partially update TaiSan : {}", taiSanDTO);

        return taiSanRepository
            .findById(taiSanDTO.getIdTaiSan())
            .map(existingTaiSan -> {
                taiSanMapper.partialUpdate(existingTaiSan, taiSanDTO);

                return existingTaiSan;
            })
            .map(taiSanRepository::save)
            .map(taiSanMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaiSanDTO> findOne(Long id) {
        LOG.debug("Request to get TaiSan : {}", id);
        return taiSanRepository.findById(id).map(taiSanMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete TaiSan : {}", id);
        taiSanRepository.deleteById(id);
    }
}
