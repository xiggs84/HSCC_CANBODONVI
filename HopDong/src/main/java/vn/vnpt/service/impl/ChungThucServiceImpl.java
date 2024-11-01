package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.vnpt.domain.ChungThuc;
import vn.vnpt.repository.ChungThucRepository;
import vn.vnpt.service.ChungThucService;
import vn.vnpt.service.dto.ChungThucDTO;
import vn.vnpt.service.mapper.ChungThucMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.ChungThuc}.
 */
@Service
public class ChungThucServiceImpl implements ChungThucService {

    private static final Logger LOG = LoggerFactory.getLogger(ChungThucServiceImpl.class);

    private final ChungThucRepository chungThucRepository;

    private final ChungThucMapper chungThucMapper;

    public ChungThucServiceImpl(ChungThucRepository chungThucRepository, ChungThucMapper chungThucMapper) {
        this.chungThucRepository = chungThucRepository;
        this.chungThucMapper = chungThucMapper;
    }

    @Override
    public ChungThucDTO save(ChungThucDTO chungThucDTO) {
        LOG.debug("Request to save ChungThuc : {}", chungThucDTO);
        ChungThuc chungThuc = chungThucMapper.toEntity(chungThucDTO);
        chungThuc = chungThucRepository.save(chungThuc);
        return chungThucMapper.toDto(chungThuc);
    }

    @Override
    public ChungThucDTO update(ChungThucDTO chungThucDTO) {
        LOG.debug("Request to update ChungThuc : {}", chungThucDTO);
        ChungThuc chungThuc = chungThucMapper.toEntity(chungThucDTO);
        chungThuc = chungThucRepository.save(chungThuc);
        return chungThucMapper.toDto(chungThuc);
    }

    @Override
    public Optional<ChungThucDTO> partialUpdate(ChungThucDTO chungThucDTO) {
        LOG.debug("Request to partially update ChungThuc : {}", chungThucDTO);

        return chungThucRepository
            .findById(chungThucDTO.getId())
            .map(existingChungThuc -> {
                chungThucMapper.partialUpdate(existingChungThuc, chungThucDTO);

                return existingChungThuc;
            })
            .map(chungThucRepository::save)
            .map(chungThucMapper::toDto);
    }

    @Override
    public Page<ChungThucDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all ChungThucs");
        return chungThucRepository.findAll(pageable).map(chungThucMapper::toDto);
    }

    @Override
    public Optional<ChungThucDTO> findOne(String id) {
        LOG.debug("Request to get ChungThuc : {}", id);
        return chungThucRepository.findById(id).map(chungThucMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete ChungThuc : {}", id);
        chungThucRepository.deleteById(id);
    }
}
