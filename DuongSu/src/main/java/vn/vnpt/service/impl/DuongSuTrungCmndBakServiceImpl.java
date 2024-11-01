package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DuongSuTrungCmndBak;
import vn.vnpt.repository.DuongSuTrungCmndBakRepository;
import vn.vnpt.service.DuongSuTrungCmndBakService;
import vn.vnpt.service.dto.DuongSuTrungCmndBakDTO;
import vn.vnpt.service.mapper.DuongSuTrungCmndBakMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DuongSuTrungCmndBak}.
 */
@Service
@Transactional
public class DuongSuTrungCmndBakServiceImpl implements DuongSuTrungCmndBakService {

    private static final Logger LOG = LoggerFactory.getLogger(DuongSuTrungCmndBakServiceImpl.class);

    private final DuongSuTrungCmndBakRepository duongSuTrungCmndBakRepository;

    private final DuongSuTrungCmndBakMapper duongSuTrungCmndBakMapper;

    public DuongSuTrungCmndBakServiceImpl(
        DuongSuTrungCmndBakRepository duongSuTrungCmndBakRepository,
        DuongSuTrungCmndBakMapper duongSuTrungCmndBakMapper
    ) {
        this.duongSuTrungCmndBakRepository = duongSuTrungCmndBakRepository;
        this.duongSuTrungCmndBakMapper = duongSuTrungCmndBakMapper;
    }

    @Override
    public DuongSuTrungCmndBakDTO save(DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO) {
        LOG.debug("Request to save DuongSuTrungCmndBak : {}", duongSuTrungCmndBakDTO);
        DuongSuTrungCmndBak duongSuTrungCmndBak = duongSuTrungCmndBakMapper.toEntity(duongSuTrungCmndBakDTO);
        duongSuTrungCmndBak = duongSuTrungCmndBakRepository.save(duongSuTrungCmndBak);
        return duongSuTrungCmndBakMapper.toDto(duongSuTrungCmndBak);
    }

    @Override
    public DuongSuTrungCmndBakDTO update(DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO) {
        LOG.debug("Request to update DuongSuTrungCmndBak : {}", duongSuTrungCmndBakDTO);
        DuongSuTrungCmndBak duongSuTrungCmndBak = duongSuTrungCmndBakMapper.toEntity(duongSuTrungCmndBakDTO);
        duongSuTrungCmndBak = duongSuTrungCmndBakRepository.save(duongSuTrungCmndBak);
        return duongSuTrungCmndBakMapper.toDto(duongSuTrungCmndBak);
    }

    @Override
    public Optional<DuongSuTrungCmndBakDTO> partialUpdate(DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO) {
        LOG.debug("Request to partially update DuongSuTrungCmndBak : {}", duongSuTrungCmndBakDTO);

        return duongSuTrungCmndBakRepository
            .findById(duongSuTrungCmndBakDTO.getId())
            .map(existingDuongSuTrungCmndBak -> {
                duongSuTrungCmndBakMapper.partialUpdate(existingDuongSuTrungCmndBak, duongSuTrungCmndBakDTO);

                return existingDuongSuTrungCmndBak;
            })
            .map(duongSuTrungCmndBakRepository::save)
            .map(duongSuTrungCmndBakMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DuongSuTrungCmndBakDTO> findOne(Long id) {
        LOG.debug("Request to get DuongSuTrungCmndBak : {}", id);
        return duongSuTrungCmndBakRepository.findById(id).map(duongSuTrungCmndBakMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete DuongSuTrungCmndBak : {}", id);
        duongSuTrungCmndBakRepository.deleteById(id);
    }
}
