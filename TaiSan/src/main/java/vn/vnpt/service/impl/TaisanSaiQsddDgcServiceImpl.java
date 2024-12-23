package vn.vnpt.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.TaisanSaiQsddDgc;
import vn.vnpt.repository.TaisanSaiQsddDgcRepository;
import vn.vnpt.service.TaisanSaiQsddDgcService;
import vn.vnpt.service.dto.TaisanSaiQsddDgcDTO;
import vn.vnpt.service.mapper.TaisanSaiQsddDgcMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.TaisanSaiQsddDgc}.
 */
@Service
@Transactional
public class TaisanSaiQsddDgcServiceImpl implements TaisanSaiQsddDgcService {

    private static final Logger LOG = LoggerFactory.getLogger(TaisanSaiQsddDgcServiceImpl.class);

    private final TaisanSaiQsddDgcRepository taisanSaiQsddDgcRepository;

    private final TaisanSaiQsddDgcMapper taisanSaiQsddDgcMapper;

    public TaisanSaiQsddDgcServiceImpl(
        TaisanSaiQsddDgcRepository taisanSaiQsddDgcRepository,
        TaisanSaiQsddDgcMapper taisanSaiQsddDgcMapper
    ) {
        this.taisanSaiQsddDgcRepository = taisanSaiQsddDgcRepository;
        this.taisanSaiQsddDgcMapper = taisanSaiQsddDgcMapper;
    }

    @Override
    public TaisanSaiQsddDgcDTO save(TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO) {
        LOG.debug("Request to save TaisanSaiQsddDgc : {}", taisanSaiQsddDgcDTO);
        TaisanSaiQsddDgc taisanSaiQsddDgc = taisanSaiQsddDgcMapper.toEntity(taisanSaiQsddDgcDTO);
        taisanSaiQsddDgc = taisanSaiQsddDgcRepository.save(taisanSaiQsddDgc);
        return taisanSaiQsddDgcMapper.toDto(taisanSaiQsddDgc);
    }

    @Override
    public TaisanSaiQsddDgcDTO update(TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO) {
        LOG.debug("Request to update TaisanSaiQsddDgc : {}", taisanSaiQsddDgcDTO);
        TaisanSaiQsddDgc taisanSaiQsddDgc = taisanSaiQsddDgcMapper.toEntity(taisanSaiQsddDgcDTO);
        taisanSaiQsddDgc = taisanSaiQsddDgcRepository.save(taisanSaiQsddDgc);
        return taisanSaiQsddDgcMapper.toDto(taisanSaiQsddDgc);
    }

    @Override
    public Optional<TaisanSaiQsddDgcDTO> partialUpdate(TaisanSaiQsddDgcDTO taisanSaiQsddDgcDTO) {
        LOG.debug("Request to partially update TaisanSaiQsddDgc : {}", taisanSaiQsddDgcDTO);

        return taisanSaiQsddDgcRepository
            .findById(taisanSaiQsddDgcDTO.getId())
            .map(existingTaisanSaiQsddDgc -> {
                taisanSaiQsddDgcMapper.partialUpdate(existingTaisanSaiQsddDgc, taisanSaiQsddDgcDTO);

                return existingTaisanSaiQsddDgc;
            })
            .map(taisanSaiQsddDgcRepository::save)
            .map(taisanSaiQsddDgcMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaisanSaiQsddDgcDTO> findAll() {
        LOG.debug("Request to get all TaisanSaiQsddDgcs");
        return taisanSaiQsddDgcRepository
            .findAll()
            .stream()
            .map(taisanSaiQsddDgcMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaisanSaiQsddDgcDTO> findOne(Long id) {
        LOG.debug("Request to get TaisanSaiQsddDgc : {}", id);
        return taisanSaiQsddDgcRepository.findById(id).map(taisanSaiQsddDgcMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete TaisanSaiQsddDgc : {}", id);
        taisanSaiQsddDgcRepository.deleteById(id);
    }
}
