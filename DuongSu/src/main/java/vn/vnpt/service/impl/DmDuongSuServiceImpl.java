package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DmDuongSu;
import vn.vnpt.repository.DmDuongSuRepository;
import vn.vnpt.service.DmDuongSuService;
import vn.vnpt.service.dto.DmDuongSuDTO;
import vn.vnpt.service.mapper.DmDuongSuMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DmDuongSu}.
 */
@Service
@Transactional
public class DmDuongSuServiceImpl implements DmDuongSuService {

    private static final Logger LOG = LoggerFactory.getLogger(DmDuongSuServiceImpl.class);

    private final DmDuongSuRepository dmDuongSuRepository;

    private final DmDuongSuMapper dmDuongSuMapper;

    public DmDuongSuServiceImpl(DmDuongSuRepository dmDuongSuRepository, DmDuongSuMapper dmDuongSuMapper) {
        this.dmDuongSuRepository = dmDuongSuRepository;
        this.dmDuongSuMapper = dmDuongSuMapper;
    }

    @Override
    public DmDuongSuDTO save(DmDuongSuDTO dmDuongSuDTO) {
        LOG.debug("Request to save DmDuongSu : {}", dmDuongSuDTO);
        DmDuongSu dmDuongSu = dmDuongSuMapper.toEntity(dmDuongSuDTO);
        dmDuongSu = dmDuongSuRepository.save(dmDuongSu);
        return dmDuongSuMapper.toDto(dmDuongSu);
    }

    @Override
    public DmDuongSuDTO update(DmDuongSuDTO dmDuongSuDTO) {
        LOG.debug("Request to update DmDuongSu : {}", dmDuongSuDTO);
        DmDuongSu dmDuongSu = dmDuongSuMapper.toEntity(dmDuongSuDTO);
        dmDuongSu = dmDuongSuRepository.save(dmDuongSu);
        return dmDuongSuMapper.toDto(dmDuongSu);
    }

    @Override
    public Optional<DmDuongSuDTO> partialUpdate(DmDuongSuDTO dmDuongSuDTO) {
        LOG.debug("Request to partially update DmDuongSu : {}", dmDuongSuDTO);

        return dmDuongSuRepository
            .findById(dmDuongSuDTO.getIdDuongSu())
            .map(existingDmDuongSu -> {
                dmDuongSuMapper.partialUpdate(existingDmDuongSu, dmDuongSuDTO);

                return existingDmDuongSu;
            })
            .map(dmDuongSuRepository::save)
            .map(dmDuongSuMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DmDuongSuDTO> findOne(Long id) {
        LOG.debug("Request to get DmDuongSu : {}", id);
        return dmDuongSuRepository.findById(id).map(dmDuongSuMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete DmDuongSu : {}", id);
        dmDuongSuRepository.deleteById(id);
    }
}
