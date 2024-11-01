package vn.vnpt.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.TaisanSaiDgc;
import vn.vnpt.repository.TaisanSaiDgcRepository;
import vn.vnpt.service.TaisanSaiDgcService;
import vn.vnpt.service.dto.TaisanSaiDgcDTO;
import vn.vnpt.service.mapper.TaisanSaiDgcMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.TaisanSaiDgc}.
 */
@Service
@Transactional
public class TaisanSaiDgcServiceImpl implements TaisanSaiDgcService {

    private static final Logger LOG = LoggerFactory.getLogger(TaisanSaiDgcServiceImpl.class);

    private final TaisanSaiDgcRepository taisanSaiDgcRepository;

    private final TaisanSaiDgcMapper taisanSaiDgcMapper;

    public TaisanSaiDgcServiceImpl(TaisanSaiDgcRepository taisanSaiDgcRepository, TaisanSaiDgcMapper taisanSaiDgcMapper) {
        this.taisanSaiDgcRepository = taisanSaiDgcRepository;
        this.taisanSaiDgcMapper = taisanSaiDgcMapper;
    }

    @Override
    public TaisanSaiDgcDTO save(TaisanSaiDgcDTO taisanSaiDgcDTO) {
        LOG.debug("Request to save TaisanSaiDgc : {}", taisanSaiDgcDTO);
        TaisanSaiDgc taisanSaiDgc = taisanSaiDgcMapper.toEntity(taisanSaiDgcDTO);
        taisanSaiDgc = taisanSaiDgcRepository.save(taisanSaiDgc);
        return taisanSaiDgcMapper.toDto(taisanSaiDgc);
    }

    @Override
    public TaisanSaiDgcDTO update(TaisanSaiDgcDTO taisanSaiDgcDTO) {
        LOG.debug("Request to update TaisanSaiDgc : {}", taisanSaiDgcDTO);
        TaisanSaiDgc taisanSaiDgc = taisanSaiDgcMapper.toEntity(taisanSaiDgcDTO);
        taisanSaiDgc = taisanSaiDgcRepository.save(taisanSaiDgc);
        return taisanSaiDgcMapper.toDto(taisanSaiDgc);
    }

    @Override
    public Optional<TaisanSaiDgcDTO> partialUpdate(TaisanSaiDgcDTO taisanSaiDgcDTO) {
        LOG.debug("Request to partially update TaisanSaiDgc : {}", taisanSaiDgcDTO);

        return taisanSaiDgcRepository
            .findById(taisanSaiDgcDTO.getId())
            .map(existingTaisanSaiDgc -> {
                taisanSaiDgcMapper.partialUpdate(existingTaisanSaiDgc, taisanSaiDgcDTO);

                return existingTaisanSaiDgc;
            })
            .map(taisanSaiDgcRepository::save)
            .map(taisanSaiDgcMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaisanSaiDgcDTO> findAll() {
        LOG.debug("Request to get all TaisanSaiDgcs");
        return taisanSaiDgcRepository.findAll().stream().map(taisanSaiDgcMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaisanSaiDgcDTO> findOne(Long id) {
        LOG.debug("Request to get TaisanSaiDgc : {}", id);
        return taisanSaiDgcRepository.findById(id).map(taisanSaiDgcMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete TaisanSaiDgc : {}", id);
        taisanSaiDgcRepository.deleteById(id);
    }
}
