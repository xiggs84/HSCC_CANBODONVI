package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.CapQuanLy;
import vn.vnpt.repository.CapQuanLyRepository;
import vn.vnpt.service.CapQuanLyService;
import vn.vnpt.service.dto.CapQuanLyDTO;
import vn.vnpt.service.mapper.CapQuanLyMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.CapQuanLy}.
 */
@Service
@Transactional
public class CapQuanLyServiceImpl implements CapQuanLyService {

    private static final Logger LOG = LoggerFactory.getLogger(CapQuanLyServiceImpl.class);

    private final CapQuanLyRepository capQuanLyRepository;

    private final CapQuanLyMapper capQuanLyMapper;

    public CapQuanLyServiceImpl(CapQuanLyRepository capQuanLyRepository, CapQuanLyMapper capQuanLyMapper) {
        this.capQuanLyRepository = capQuanLyRepository;
        this.capQuanLyMapper = capQuanLyMapper;
    }

    @Override
    public CapQuanLyDTO save(CapQuanLyDTO capQuanLyDTO) {
        LOG.debug("Request to save CapQuanLy : {}", capQuanLyDTO);
        CapQuanLy capQuanLy = capQuanLyMapper.toEntity(capQuanLyDTO);
        capQuanLy = capQuanLyRepository.save(capQuanLy);
        return capQuanLyMapper.toDto(capQuanLy);
    }

    @Override
    public CapQuanLyDTO update(CapQuanLyDTO capQuanLyDTO) {
        LOG.debug("Request to update CapQuanLy : {}", capQuanLyDTO);
        CapQuanLy capQuanLy = capQuanLyMapper.toEntity(capQuanLyDTO);
        capQuanLy.setIsPersisted();
        capQuanLy = capQuanLyRepository.save(capQuanLy);
        return capQuanLyMapper.toDto(capQuanLy);
    }

    @Override
    public Optional<CapQuanLyDTO> partialUpdate(CapQuanLyDTO capQuanLyDTO) {
        LOG.debug("Request to partially update CapQuanLy : {}", capQuanLyDTO);

        return capQuanLyRepository
            .findById(capQuanLyDTO.getIdCapQl())
            .map(existingCapQuanLy -> {
                capQuanLyMapper.partialUpdate(existingCapQuanLy, capQuanLyDTO);

                return existingCapQuanLy;
            })
            .map(capQuanLyRepository::save)
            .map(capQuanLyMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CapQuanLyDTO> findOne(String id) {
        LOG.debug("Request to get CapQuanLy : {}", id);
        return capQuanLyRepository.findById(id).map(capQuanLyMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete CapQuanLy : {}", id);
        capQuanLyRepository.deleteById(id);
    }
}
