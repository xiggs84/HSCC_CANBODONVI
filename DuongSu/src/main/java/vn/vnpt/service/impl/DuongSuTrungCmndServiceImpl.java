package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DuongSuTrungCmnd;
import vn.vnpt.repository.DuongSuTrungCmndRepository;
import vn.vnpt.service.DuongSuTrungCmndService;
import vn.vnpt.service.dto.DuongSuTrungCmndDTO;
import vn.vnpt.service.mapper.DuongSuTrungCmndMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DuongSuTrungCmnd}.
 */
@Service
@Transactional
public class DuongSuTrungCmndServiceImpl implements DuongSuTrungCmndService {

    private static final Logger LOG = LoggerFactory.getLogger(DuongSuTrungCmndServiceImpl.class);

    private final DuongSuTrungCmndRepository duongSuTrungCmndRepository;

    private final DuongSuTrungCmndMapper duongSuTrungCmndMapper;

    public DuongSuTrungCmndServiceImpl(
        DuongSuTrungCmndRepository duongSuTrungCmndRepository,
        DuongSuTrungCmndMapper duongSuTrungCmndMapper
    ) {
        this.duongSuTrungCmndRepository = duongSuTrungCmndRepository;
        this.duongSuTrungCmndMapper = duongSuTrungCmndMapper;
    }

    @Override
    public DuongSuTrungCmndDTO save(DuongSuTrungCmndDTO duongSuTrungCmndDTO) {
        LOG.debug("Request to save DuongSuTrungCmnd : {}", duongSuTrungCmndDTO);
        DuongSuTrungCmnd duongSuTrungCmnd = duongSuTrungCmndMapper.toEntity(duongSuTrungCmndDTO);
        duongSuTrungCmnd = duongSuTrungCmndRepository.save(duongSuTrungCmnd);
        return duongSuTrungCmndMapper.toDto(duongSuTrungCmnd);
    }

    @Override
    public DuongSuTrungCmndDTO update(DuongSuTrungCmndDTO duongSuTrungCmndDTO) {
        LOG.debug("Request to update DuongSuTrungCmnd : {}", duongSuTrungCmndDTO);
        DuongSuTrungCmnd duongSuTrungCmnd = duongSuTrungCmndMapper.toEntity(duongSuTrungCmndDTO);
        duongSuTrungCmnd = duongSuTrungCmndRepository.save(duongSuTrungCmnd);
        return duongSuTrungCmndMapper.toDto(duongSuTrungCmnd);
    }

    @Override
    public Optional<DuongSuTrungCmndDTO> partialUpdate(DuongSuTrungCmndDTO duongSuTrungCmndDTO) {
        LOG.debug("Request to partially update DuongSuTrungCmnd : {}", duongSuTrungCmndDTO);

        return duongSuTrungCmndRepository
            .findById(duongSuTrungCmndDTO.getId())
            .map(existingDuongSuTrungCmnd -> {
                duongSuTrungCmndMapper.partialUpdate(existingDuongSuTrungCmnd, duongSuTrungCmndDTO);

                return existingDuongSuTrungCmnd;
            })
            .map(duongSuTrungCmndRepository::save)
            .map(duongSuTrungCmndMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DuongSuTrungCmndDTO> findOne(Long id) {
        LOG.debug("Request to get DuongSuTrungCmnd : {}", id);
        return duongSuTrungCmndRepository.findById(id).map(duongSuTrungCmndMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete DuongSuTrungCmnd : {}", id);
        duongSuTrungCmndRepository.deleteById(id);
    }
}
