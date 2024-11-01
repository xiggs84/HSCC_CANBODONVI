package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DmXaTmp;
import vn.vnpt.repository.DmXaTmpRepository;
import vn.vnpt.service.DmXaTmpService;
import vn.vnpt.service.dto.DmXaTmpDTO;
import vn.vnpt.service.mapper.DmXaTmpMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DmXaTmp}.
 */
@Service
@Transactional
public class DmXaTmpServiceImpl implements DmXaTmpService {

    private static final Logger LOG = LoggerFactory.getLogger(DmXaTmpServiceImpl.class);

    private final DmXaTmpRepository dmXaTmpRepository;

    private final DmXaTmpMapper dmXaTmpMapper;

    public DmXaTmpServiceImpl(DmXaTmpRepository dmXaTmpRepository, DmXaTmpMapper dmXaTmpMapper) {
        this.dmXaTmpRepository = dmXaTmpRepository;
        this.dmXaTmpMapper = dmXaTmpMapper;
    }

    @Override
    public DmXaTmpDTO save(DmXaTmpDTO dmXaTmpDTO) {
        LOG.debug("Request to save DmXaTmp : {}", dmXaTmpDTO);
        DmXaTmp dmXaTmp = dmXaTmpMapper.toEntity(dmXaTmpDTO);
        dmXaTmp = dmXaTmpRepository.save(dmXaTmp);
        return dmXaTmpMapper.toDto(dmXaTmp);
    }

    @Override
    public DmXaTmpDTO update(DmXaTmpDTO dmXaTmpDTO) {
        LOG.debug("Request to update DmXaTmp : {}", dmXaTmpDTO);
        DmXaTmp dmXaTmp = dmXaTmpMapper.toEntity(dmXaTmpDTO);
        dmXaTmp = dmXaTmpRepository.save(dmXaTmp);
        return dmXaTmpMapper.toDto(dmXaTmp);
    }

    @Override
    public Optional<DmXaTmpDTO> partialUpdate(DmXaTmpDTO dmXaTmpDTO) {
        LOG.debug("Request to partially update DmXaTmp : {}", dmXaTmpDTO);

        return dmXaTmpRepository
            .findById(dmXaTmpDTO.getMaXa())
            .map(existingDmXaTmp -> {
                dmXaTmpMapper.partialUpdate(existingDmXaTmp, dmXaTmpDTO);

                return existingDmXaTmp;
            })
            .map(dmXaTmpRepository::save)
            .map(dmXaTmpMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DmXaTmpDTO> findOne(Long id) {
        LOG.debug("Request to get DmXaTmp : {}", id);
        return dmXaTmpRepository.findById(id).map(dmXaTmpMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete DmXaTmp : {}", id);
        dmXaTmpRepository.deleteById(id);
    }
}
