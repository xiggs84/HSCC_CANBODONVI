package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.QuanHeMaster;
import vn.vnpt.repository.QuanHeMasterRepository;
import vn.vnpt.service.QuanHeMasterService;
import vn.vnpt.service.dto.QuanHeMasterDTO;
import vn.vnpt.service.mapper.QuanHeMasterMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.QuanHeMaster}.
 */
@Service
@Transactional
public class QuanHeMasterServiceImpl implements QuanHeMasterService {

    private static final Logger LOG = LoggerFactory.getLogger(QuanHeMasterServiceImpl.class);

    private final QuanHeMasterRepository quanHeMasterRepository;

    private final QuanHeMasterMapper quanHeMasterMapper;

    public QuanHeMasterServiceImpl(QuanHeMasterRepository quanHeMasterRepository, QuanHeMasterMapper quanHeMasterMapper) {
        this.quanHeMasterRepository = quanHeMasterRepository;
        this.quanHeMasterMapper = quanHeMasterMapper;
    }

    @Override
    public QuanHeMasterDTO save(QuanHeMasterDTO quanHeMasterDTO) {
        LOG.debug("Request to save QuanHeMaster : {}", quanHeMasterDTO);
        QuanHeMaster quanHeMaster = quanHeMasterMapper.toEntity(quanHeMasterDTO);
        quanHeMaster = quanHeMasterRepository.save(quanHeMaster);
        return quanHeMasterMapper.toDto(quanHeMaster);
    }

    @Override
    public QuanHeMasterDTO update(QuanHeMasterDTO quanHeMasterDTO) {
        LOG.debug("Request to update QuanHeMaster : {}", quanHeMasterDTO);
        QuanHeMaster quanHeMaster = quanHeMasterMapper.toEntity(quanHeMasterDTO);
        quanHeMaster = quanHeMasterRepository.save(quanHeMaster);
        return quanHeMasterMapper.toDto(quanHeMaster);
    }

    @Override
    public Optional<QuanHeMasterDTO> partialUpdate(QuanHeMasterDTO quanHeMasterDTO) {
        LOG.debug("Request to partially update QuanHeMaster : {}", quanHeMasterDTO);

        return quanHeMasterRepository
            .findById(quanHeMasterDTO.getId())
            .map(existingQuanHeMaster -> {
                quanHeMasterMapper.partialUpdate(existingQuanHeMaster, quanHeMasterDTO);

                return existingQuanHeMaster;
            })
            .map(quanHeMasterRepository::save)
            .map(quanHeMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuanHeMasterDTO> findOne(Long id) {
        LOG.debug("Request to get QuanHeMaster : {}", id);
        return quanHeMasterRepository.findById(id).map(quanHeMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete QuanHeMaster : {}", id);
        quanHeMasterRepository.deleteById(id);
    }
}
