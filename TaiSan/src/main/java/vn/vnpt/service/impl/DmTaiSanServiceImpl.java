package vn.vnpt.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DmTaiSan;
import vn.vnpt.repository.DmTaiSanRepository;
import vn.vnpt.service.DmTaiSanService;
import vn.vnpt.service.dto.DmTaiSanDTO;
import vn.vnpt.service.mapper.DmTaiSanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DmTaiSan}.
 */
@Service
@Transactional
public class DmTaiSanServiceImpl implements DmTaiSanService {

    private static final Logger LOG = LoggerFactory.getLogger(DmTaiSanServiceImpl.class);

    private final DmTaiSanRepository dmTaiSanRepository;

    private final DmTaiSanMapper dmTaiSanMapper;

    public DmTaiSanServiceImpl(DmTaiSanRepository dmTaiSanRepository, DmTaiSanMapper dmTaiSanMapper) {
        this.dmTaiSanRepository = dmTaiSanRepository;
        this.dmTaiSanMapper = dmTaiSanMapper;
    }

    @Override
    public DmTaiSanDTO save(DmTaiSanDTO dmTaiSanDTO) {
        LOG.debug("Request to save DmTaiSan : {}", dmTaiSanDTO);
        DmTaiSan dmTaiSan = dmTaiSanMapper.toEntity(dmTaiSanDTO);
        dmTaiSan = dmTaiSanRepository.save(dmTaiSan);
        return dmTaiSanMapper.toDto(dmTaiSan);
    }

    @Override
    public DmTaiSanDTO update(DmTaiSanDTO dmTaiSanDTO) {
        LOG.debug("Request to update DmTaiSan : {}", dmTaiSanDTO);
        DmTaiSan dmTaiSan = dmTaiSanMapper.toEntity(dmTaiSanDTO);
        dmTaiSan = dmTaiSanRepository.save(dmTaiSan);
        return dmTaiSanMapper.toDto(dmTaiSan);
    }

    @Override
    public Optional<DmTaiSanDTO> partialUpdate(DmTaiSanDTO dmTaiSanDTO) {
        LOG.debug("Request to partially update DmTaiSan : {}", dmTaiSanDTO);

        return dmTaiSanRepository
            .findById(dmTaiSanDTO.getIdTaiSan())
            .map(existingDmTaiSan -> {
                dmTaiSanMapper.partialUpdate(existingDmTaiSan, dmTaiSanDTO);

                return existingDmTaiSan;
            })
            .map(dmTaiSanRepository::save)
            .map(dmTaiSanMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DmTaiSanDTO> findAll() {
        LOG.debug("Request to get all DmTaiSans");
        return dmTaiSanRepository.findAll().stream().map(dmTaiSanMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DmTaiSanDTO> findOne(Long id) {
        LOG.debug("Request to get DmTaiSan : {}", id);
        return dmTaiSanRepository.findById(id).map(dmTaiSanMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete DmTaiSan : {}", id);
        dmTaiSanRepository.deleteById(id);
    }
}
