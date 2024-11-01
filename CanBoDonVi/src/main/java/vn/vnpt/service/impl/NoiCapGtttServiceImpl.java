package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.NoiCapGttt;
import vn.vnpt.repository.NoiCapGtttRepository;
import vn.vnpt.service.NoiCapGtttService;
import vn.vnpt.service.dto.NoiCapGtttDTO;
import vn.vnpt.service.mapper.NoiCapGtttMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.NoiCapGttt}.
 */
@Service
@Transactional
public class NoiCapGtttServiceImpl implements NoiCapGtttService {

    private static final Logger LOG = LoggerFactory.getLogger(NoiCapGtttServiceImpl.class);

    private final NoiCapGtttRepository noiCapGtttRepository;

    private final NoiCapGtttMapper noiCapGtttMapper;

    public NoiCapGtttServiceImpl(NoiCapGtttRepository noiCapGtttRepository, NoiCapGtttMapper noiCapGtttMapper) {
        this.noiCapGtttRepository = noiCapGtttRepository;
        this.noiCapGtttMapper = noiCapGtttMapper;
    }

    @Override
    public NoiCapGtttDTO save(NoiCapGtttDTO noiCapGtttDTO) {
        LOG.debug("Request to save NoiCapGttt : {}", noiCapGtttDTO);
        NoiCapGttt noiCapGttt = noiCapGtttMapper.toEntity(noiCapGtttDTO);
        noiCapGttt = noiCapGtttRepository.save(noiCapGttt);
        return noiCapGtttMapper.toDto(noiCapGttt);
    }

    @Override
    public NoiCapGtttDTO update(NoiCapGtttDTO noiCapGtttDTO) {
        LOG.debug("Request to update NoiCapGttt : {}", noiCapGtttDTO);
        NoiCapGttt noiCapGttt = noiCapGtttMapper.toEntity(noiCapGtttDTO);
        noiCapGttt = noiCapGtttRepository.save(noiCapGttt);
        return noiCapGtttMapper.toDto(noiCapGttt);
    }

    @Override
    public Optional<NoiCapGtttDTO> partialUpdate(NoiCapGtttDTO noiCapGtttDTO) {
        LOG.debug("Request to partially update NoiCapGttt : {}", noiCapGtttDTO);

        return noiCapGtttRepository
            .findById(noiCapGtttDTO.getIdNoiCap())
            .map(existingNoiCapGttt -> {
                noiCapGtttMapper.partialUpdate(existingNoiCapGttt, noiCapGtttDTO);

                return existingNoiCapGttt;
            })
            .map(noiCapGtttRepository::save)
            .map(noiCapGtttMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NoiCapGtttDTO> findOne(Long id) {
        LOG.debug("Request to get NoiCapGttt : {}", id);
        return noiCapGtttRepository.findById(id).map(noiCapGtttMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete NoiCapGttt : {}", id);
        noiCapGtttRepository.deleteById(id);
    }
}
