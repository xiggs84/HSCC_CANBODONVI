package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DmHuyenTmp;
import vn.vnpt.repository.DmHuyenTmpRepository;
import vn.vnpt.service.DmHuyenTmpService;
import vn.vnpt.service.dto.DmHuyenTmpDTO;
import vn.vnpt.service.mapper.DmHuyenTmpMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DmHuyenTmp}.
 */
@Service
@Transactional
public class DmHuyenTmpServiceImpl implements DmHuyenTmpService {

    private static final Logger LOG = LoggerFactory.getLogger(DmHuyenTmpServiceImpl.class);

    private final DmHuyenTmpRepository dmHuyenTmpRepository;

    private final DmHuyenTmpMapper dmHuyenTmpMapper;

    public DmHuyenTmpServiceImpl(DmHuyenTmpRepository dmHuyenTmpRepository, DmHuyenTmpMapper dmHuyenTmpMapper) {
        this.dmHuyenTmpRepository = dmHuyenTmpRepository;
        this.dmHuyenTmpMapper = dmHuyenTmpMapper;
    }

    @Override
    public DmHuyenTmpDTO save(DmHuyenTmpDTO dmHuyenTmpDTO) {
        LOG.debug("Request to save DmHuyenTmp : {}", dmHuyenTmpDTO);
        DmHuyenTmp dmHuyenTmp = dmHuyenTmpMapper.toEntity(dmHuyenTmpDTO);
        dmHuyenTmp = dmHuyenTmpRepository.save(dmHuyenTmp);
        return dmHuyenTmpMapper.toDto(dmHuyenTmp);
    }

    @Override
    public DmHuyenTmpDTO update(DmHuyenTmpDTO dmHuyenTmpDTO) {
        LOG.debug("Request to update DmHuyenTmp : {}", dmHuyenTmpDTO);
        DmHuyenTmp dmHuyenTmp = dmHuyenTmpMapper.toEntity(dmHuyenTmpDTO);
        dmHuyenTmp = dmHuyenTmpRepository.save(dmHuyenTmp);
        return dmHuyenTmpMapper.toDto(dmHuyenTmp);
    }

    @Override
    public Optional<DmHuyenTmpDTO> partialUpdate(DmHuyenTmpDTO dmHuyenTmpDTO) {
        LOG.debug("Request to partially update DmHuyenTmp : {}", dmHuyenTmpDTO);

        return dmHuyenTmpRepository
            .findById(dmHuyenTmpDTO.getMaHuyen())
            .map(existingDmHuyenTmp -> {
                dmHuyenTmpMapper.partialUpdate(existingDmHuyenTmp, dmHuyenTmpDTO);

                return existingDmHuyenTmp;
            })
            .map(dmHuyenTmpRepository::save)
            .map(dmHuyenTmpMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DmHuyenTmpDTO> findOne(Long id) {
        LOG.debug("Request to get DmHuyenTmp : {}", id);
        return dmHuyenTmpRepository.findById(id).map(dmHuyenTmpMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete DmHuyenTmp : {}", id);
        dmHuyenTmpRepository.deleteById(id);
    }
}
