package vn.vnpt.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.TaiSanDgc;
import vn.vnpt.repository.TaiSanDgcRepository;
import vn.vnpt.service.TaiSanDgcService;
import vn.vnpt.service.dto.TaiSanDgcDTO;
import vn.vnpt.service.mapper.TaiSanDgcMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.TaiSanDgc}.
 */
@Service
@Transactional
public class TaiSanDgcServiceImpl implements TaiSanDgcService {

    private static final Logger LOG = LoggerFactory.getLogger(TaiSanDgcServiceImpl.class);

    private final TaiSanDgcRepository taiSanDgcRepository;

    private final TaiSanDgcMapper taiSanDgcMapper;

    public TaiSanDgcServiceImpl(TaiSanDgcRepository taiSanDgcRepository, TaiSanDgcMapper taiSanDgcMapper) {
        this.taiSanDgcRepository = taiSanDgcRepository;
        this.taiSanDgcMapper = taiSanDgcMapper;
    }

    @Override
    public TaiSanDgcDTO save(TaiSanDgcDTO taiSanDgcDTO) {
        LOG.debug("Request to save TaiSanDgc : {}", taiSanDgcDTO);
        TaiSanDgc taiSanDgc = taiSanDgcMapper.toEntity(taiSanDgcDTO);
        taiSanDgc = taiSanDgcRepository.save(taiSanDgc);
        return taiSanDgcMapper.toDto(taiSanDgc);
    }

    @Override
    public TaiSanDgcDTO update(TaiSanDgcDTO taiSanDgcDTO) {
        LOG.debug("Request to update TaiSanDgc : {}", taiSanDgcDTO);
        TaiSanDgc taiSanDgc = taiSanDgcMapper.toEntity(taiSanDgcDTO);
        taiSanDgc = taiSanDgcRepository.save(taiSanDgc);
        return taiSanDgcMapper.toDto(taiSanDgc);
    }

    @Override
    public Optional<TaiSanDgcDTO> partialUpdate(TaiSanDgcDTO taiSanDgcDTO) {
        LOG.debug("Request to partially update TaiSanDgc : {}", taiSanDgcDTO);

        return taiSanDgcRepository
            .findById(taiSanDgcDTO.getId())
            .map(existingTaiSanDgc -> {
                taiSanDgcMapper.partialUpdate(existingTaiSanDgc, taiSanDgcDTO);

                return existingTaiSanDgc;
            })
            .map(taiSanDgcRepository::save)
            .map(taiSanDgcMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaiSanDgcDTO> findAll() {
        LOG.debug("Request to get all TaiSanDgcs");
        return taiSanDgcRepository.findAll().stream().map(taiSanDgcMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaiSanDgcDTO> findOne(Long id) {
        LOG.debug("Request to get TaiSanDgc : {}", id);
        return taiSanDgcRepository.findById(id).map(taiSanDgcMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete TaiSanDgc : {}", id);
        taiSanDgcRepository.deleteById(id);
    }
}
