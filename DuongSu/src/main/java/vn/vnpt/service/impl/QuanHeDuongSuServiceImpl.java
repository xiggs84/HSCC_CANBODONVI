package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.QuanHeDuongSu;
import vn.vnpt.repository.QuanHeDuongSuRepository;
import vn.vnpt.service.QuanHeDuongSuService;
import vn.vnpt.service.dto.QuanHeDuongSuDTO;
import vn.vnpt.service.mapper.QuanHeDuongSuMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.QuanHeDuongSu}.
 */
@Service
@Transactional
public class QuanHeDuongSuServiceImpl implements QuanHeDuongSuService {

    private static final Logger LOG = LoggerFactory.getLogger(QuanHeDuongSuServiceImpl.class);

    private final QuanHeDuongSuRepository quanHeDuongSuRepository;

    private final QuanHeDuongSuMapper quanHeDuongSuMapper;

    public QuanHeDuongSuServiceImpl(QuanHeDuongSuRepository quanHeDuongSuRepository, QuanHeDuongSuMapper quanHeDuongSuMapper) {
        this.quanHeDuongSuRepository = quanHeDuongSuRepository;
        this.quanHeDuongSuMapper = quanHeDuongSuMapper;
    }

    @Override
    public QuanHeDuongSuDTO save(QuanHeDuongSuDTO quanHeDuongSuDTO) {
        LOG.debug("Request to save QuanHeDuongSu : {}", quanHeDuongSuDTO);
        QuanHeDuongSu quanHeDuongSu = quanHeDuongSuMapper.toEntity(quanHeDuongSuDTO);
        quanHeDuongSu = quanHeDuongSuRepository.save(quanHeDuongSu);
        return quanHeDuongSuMapper.toDto(quanHeDuongSu);
    }

    @Override
    public QuanHeDuongSuDTO update(QuanHeDuongSuDTO quanHeDuongSuDTO) {
        LOG.debug("Request to update QuanHeDuongSu : {}", quanHeDuongSuDTO);
        QuanHeDuongSu quanHeDuongSu = quanHeDuongSuMapper.toEntity(quanHeDuongSuDTO);
        quanHeDuongSu = quanHeDuongSuRepository.save(quanHeDuongSu);
        return quanHeDuongSuMapper.toDto(quanHeDuongSu);
    }

    @Override
    public Optional<QuanHeDuongSuDTO> partialUpdate(QuanHeDuongSuDTO quanHeDuongSuDTO) {
        LOG.debug("Request to partially update QuanHeDuongSu : {}", quanHeDuongSuDTO);

        return quanHeDuongSuRepository
            .findById(quanHeDuongSuDTO.getIdQuanHe())
            .map(existingQuanHeDuongSu -> {
                quanHeDuongSuMapper.partialUpdate(existingQuanHeDuongSu, quanHeDuongSuDTO);

                return existingQuanHeDuongSu;
            })
            .map(quanHeDuongSuRepository::save)
            .map(quanHeDuongSuMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuanHeDuongSuDTO> findOne(Long id) {
        LOG.debug("Request to get QuanHeDuongSu : {}", id);
        return quanHeDuongSuRepository.findById(id).map(quanHeDuongSuMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete QuanHeDuongSu : {}", id);
        quanHeDuongSuRepository.deleteById(id);
    }
}
