package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DmTinhTmp;
import vn.vnpt.repository.DmTinhTmpRepository;
import vn.vnpt.service.DmTinhTmpService;
import vn.vnpt.service.dto.DmTinhTmpDTO;
import vn.vnpt.service.mapper.DmTinhTmpMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DmTinhTmp}.
 */
@Service
@Transactional
public class DmTinhTmpServiceImpl implements DmTinhTmpService {

    private static final Logger LOG = LoggerFactory.getLogger(DmTinhTmpServiceImpl.class);

    private final DmTinhTmpRepository dmTinhTmpRepository;

    private final DmTinhTmpMapper dmTinhTmpMapper;

    public DmTinhTmpServiceImpl(DmTinhTmpRepository dmTinhTmpRepository, DmTinhTmpMapper dmTinhTmpMapper) {
        this.dmTinhTmpRepository = dmTinhTmpRepository;
        this.dmTinhTmpMapper = dmTinhTmpMapper;
    }

    @Override
    public DmTinhTmpDTO save(DmTinhTmpDTO dmTinhTmpDTO) {
        LOG.debug("Request to save DmTinhTmp : {}", dmTinhTmpDTO);
        DmTinhTmp dmTinhTmp = dmTinhTmpMapper.toEntity(dmTinhTmpDTO);
        dmTinhTmp = dmTinhTmpRepository.save(dmTinhTmp);
        return dmTinhTmpMapper.toDto(dmTinhTmp);
    }

    @Override
    public DmTinhTmpDTO update(DmTinhTmpDTO dmTinhTmpDTO) {
        LOG.debug("Request to update DmTinhTmp : {}", dmTinhTmpDTO);
        DmTinhTmp dmTinhTmp = dmTinhTmpMapper.toEntity(dmTinhTmpDTO);
        dmTinhTmp = dmTinhTmpRepository.save(dmTinhTmp);
        return dmTinhTmpMapper.toDto(dmTinhTmp);
    }

    @Override
    public Optional<DmTinhTmpDTO> partialUpdate(DmTinhTmpDTO dmTinhTmpDTO) {
        LOG.debug("Request to partially update DmTinhTmp : {}", dmTinhTmpDTO);

        return dmTinhTmpRepository
            .findById(dmTinhTmpDTO.getMaTinh())
            .map(existingDmTinhTmp -> {
                dmTinhTmpMapper.partialUpdate(existingDmTinhTmp, dmTinhTmpDTO);

                return existingDmTinhTmp;
            })
            .map(dmTinhTmpRepository::save)
            .map(dmTinhTmpMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DmTinhTmpDTO> findOne(Long id) {
        LOG.debug("Request to get DmTinhTmp : {}", id);
        return dmTinhTmpRepository.findById(id).map(dmTinhTmpMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete DmTinhTmp : {}", id);
        dmTinhTmpRepository.deleteById(id);
    }
}
