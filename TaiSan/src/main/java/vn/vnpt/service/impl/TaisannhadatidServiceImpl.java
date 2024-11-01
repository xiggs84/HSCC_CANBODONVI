package vn.vnpt.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.Taisannhadatid;
import vn.vnpt.repository.TaisannhadatidRepository;
import vn.vnpt.service.TaisannhadatidService;
import vn.vnpt.service.dto.TaisannhadatidDTO;
import vn.vnpt.service.mapper.TaisannhadatidMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.Taisannhadatid}.
 */
@Service
@Transactional
public class TaisannhadatidServiceImpl implements TaisannhadatidService {

    private static final Logger LOG = LoggerFactory.getLogger(TaisannhadatidServiceImpl.class);

    private final TaisannhadatidRepository taisannhadatidRepository;

    private final TaisannhadatidMapper taisannhadatidMapper;

    public TaisannhadatidServiceImpl(TaisannhadatidRepository taisannhadatidRepository, TaisannhadatidMapper taisannhadatidMapper) {
        this.taisannhadatidRepository = taisannhadatidRepository;
        this.taisannhadatidMapper = taisannhadatidMapper;
    }

    @Override
    public TaisannhadatidDTO save(TaisannhadatidDTO taisannhadatidDTO) {
        LOG.debug("Request to save Taisannhadatid : {}", taisannhadatidDTO);
        Taisannhadatid taisannhadatid = taisannhadatidMapper.toEntity(taisannhadatidDTO);
        taisannhadatid = taisannhadatidRepository.save(taisannhadatid);
        return taisannhadatidMapper.toDto(taisannhadatid);
    }

    @Override
    public TaisannhadatidDTO update(TaisannhadatidDTO taisannhadatidDTO) {
        LOG.debug("Request to update Taisannhadatid : {}", taisannhadatidDTO);
        Taisannhadatid taisannhadatid = taisannhadatidMapper.toEntity(taisannhadatidDTO);
        taisannhadatid = taisannhadatidRepository.save(taisannhadatid);
        return taisannhadatidMapper.toDto(taisannhadatid);
    }

    @Override
    public Optional<TaisannhadatidDTO> partialUpdate(TaisannhadatidDTO taisannhadatidDTO) {
        LOG.debug("Request to partially update Taisannhadatid : {}", taisannhadatidDTO);

        return taisannhadatidRepository
            .findById(taisannhadatidDTO.getIdTaiSan())
            .map(existingTaisannhadatid -> {
                taisannhadatidMapper.partialUpdate(existingTaisannhadatid, taisannhadatidDTO);

                return existingTaisannhadatid;
            })
            .map(taisannhadatidRepository::save)
            .map(taisannhadatidMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaisannhadatidDTO> findAll() {
        LOG.debug("Request to get all Taisannhadatids");
        return taisannhadatidRepository
            .findAll()
            .stream()
            .map(taisannhadatidMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaisannhadatidDTO> findOne(Long id) {
        LOG.debug("Request to get Taisannhadatid : {}", id);
        return taisannhadatidRepository.findById(id).map(taisannhadatidMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Taisannhadatid : {}", id);
        taisannhadatidRepository.deleteById(id);
    }
}
