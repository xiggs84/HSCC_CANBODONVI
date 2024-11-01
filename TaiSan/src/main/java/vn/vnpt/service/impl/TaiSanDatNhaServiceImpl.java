package vn.vnpt.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.TaiSanDatNha;
import vn.vnpt.repository.TaiSanDatNhaRepository;
import vn.vnpt.service.TaiSanDatNhaService;
import vn.vnpt.service.dto.TaiSanDatNhaDTO;
import vn.vnpt.service.mapper.TaiSanDatNhaMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.TaiSanDatNha}.
 */
@Service
@Transactional
public class TaiSanDatNhaServiceImpl implements TaiSanDatNhaService {

    private static final Logger LOG = LoggerFactory.getLogger(TaiSanDatNhaServiceImpl.class);

    private final TaiSanDatNhaRepository taiSanDatNhaRepository;

    private final TaiSanDatNhaMapper taiSanDatNhaMapper;

    public TaiSanDatNhaServiceImpl(TaiSanDatNhaRepository taiSanDatNhaRepository, TaiSanDatNhaMapper taiSanDatNhaMapper) {
        this.taiSanDatNhaRepository = taiSanDatNhaRepository;
        this.taiSanDatNhaMapper = taiSanDatNhaMapper;
    }

    @Override
    public TaiSanDatNhaDTO save(TaiSanDatNhaDTO taiSanDatNhaDTO) {
        LOG.debug("Request to save TaiSanDatNha : {}", taiSanDatNhaDTO);
        TaiSanDatNha taiSanDatNha = taiSanDatNhaMapper.toEntity(taiSanDatNhaDTO);
        taiSanDatNha = taiSanDatNhaRepository.save(taiSanDatNha);
        return taiSanDatNhaMapper.toDto(taiSanDatNha);
    }

    @Override
    public TaiSanDatNhaDTO update(TaiSanDatNhaDTO taiSanDatNhaDTO) {
        LOG.debug("Request to update TaiSanDatNha : {}", taiSanDatNhaDTO);
        TaiSanDatNha taiSanDatNha = taiSanDatNhaMapper.toEntity(taiSanDatNhaDTO);
        taiSanDatNha = taiSanDatNhaRepository.save(taiSanDatNha);
        return taiSanDatNhaMapper.toDto(taiSanDatNha);
    }

    @Override
    public Optional<TaiSanDatNhaDTO> partialUpdate(TaiSanDatNhaDTO taiSanDatNhaDTO) {
        LOG.debug("Request to partially update TaiSanDatNha : {}", taiSanDatNhaDTO);

        return taiSanDatNhaRepository
            .findById(taiSanDatNhaDTO.getId())
            .map(existingTaiSanDatNha -> {
                taiSanDatNhaMapper.partialUpdate(existingTaiSanDatNha, taiSanDatNhaDTO);

                return existingTaiSanDatNha;
            })
            .map(taiSanDatNhaRepository::save)
            .map(taiSanDatNhaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaiSanDatNhaDTO> findAll() {
        LOG.debug("Request to get all TaiSanDatNhas");
        return taiSanDatNhaRepository.findAll().stream().map(taiSanDatNhaMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaiSanDatNhaDTO> findOne(Long id) {
        LOG.debug("Request to get TaiSanDatNha : {}", id);
        return taiSanDatNhaRepository.findById(id).map(taiSanDatNhaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete TaiSanDatNha : {}", id);
        taiSanDatNhaRepository.deleteById(id);
    }
}
