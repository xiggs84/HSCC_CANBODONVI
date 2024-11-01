package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.QuanHeNhanThan;
import vn.vnpt.repository.QuanHeNhanThanRepository;
import vn.vnpt.service.QuanHeNhanThanService;
import vn.vnpt.service.dto.QuanHeNhanThanDTO;
import vn.vnpt.service.mapper.QuanHeNhanThanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.QuanHeNhanThan}.
 */
@Service
@Transactional
public class QuanHeNhanThanServiceImpl implements QuanHeNhanThanService {

    private static final Logger LOG = LoggerFactory.getLogger(QuanHeNhanThanServiceImpl.class);

    private final QuanHeNhanThanRepository quanHeNhanThanRepository;

    private final QuanHeNhanThanMapper quanHeNhanThanMapper;

    public QuanHeNhanThanServiceImpl(QuanHeNhanThanRepository quanHeNhanThanRepository, QuanHeNhanThanMapper quanHeNhanThanMapper) {
        this.quanHeNhanThanRepository = quanHeNhanThanRepository;
        this.quanHeNhanThanMapper = quanHeNhanThanMapper;
    }

    @Override
    public QuanHeNhanThanDTO save(QuanHeNhanThanDTO quanHeNhanThanDTO) {
        LOG.debug("Request to save QuanHeNhanThan : {}", quanHeNhanThanDTO);
        QuanHeNhanThan quanHeNhanThan = quanHeNhanThanMapper.toEntity(quanHeNhanThanDTO);
        quanHeNhanThan = quanHeNhanThanRepository.save(quanHeNhanThan);
        return quanHeNhanThanMapper.toDto(quanHeNhanThan);
    }

    @Override
    public QuanHeNhanThanDTO update(QuanHeNhanThanDTO quanHeNhanThanDTO) {
        LOG.debug("Request to update QuanHeNhanThan : {}", quanHeNhanThanDTO);
        QuanHeNhanThan quanHeNhanThan = quanHeNhanThanMapper.toEntity(quanHeNhanThanDTO);
        quanHeNhanThan = quanHeNhanThanRepository.save(quanHeNhanThan);
        return quanHeNhanThanMapper.toDto(quanHeNhanThan);
    }

    @Override
    public Optional<QuanHeNhanThanDTO> partialUpdate(QuanHeNhanThanDTO quanHeNhanThanDTO) {
        LOG.debug("Request to partially update QuanHeNhanThan : {}", quanHeNhanThanDTO);

        return quanHeNhanThanRepository
            .findById(quanHeNhanThanDTO.getIdQuanHe())
            .map(existingQuanHeNhanThan -> {
                quanHeNhanThanMapper.partialUpdate(existingQuanHeNhanThan, quanHeNhanThanDTO);

                return existingQuanHeNhanThan;
            })
            .map(quanHeNhanThanRepository::save)
            .map(quanHeNhanThanMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuanHeNhanThanDTO> findOne(Long id) {
        LOG.debug("Request to get QuanHeNhanThan : {}", id);
        return quanHeNhanThanRepository.findById(id).map(quanHeNhanThanMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete QuanHeNhanThan : {}", id);
        quanHeNhanThanRepository.deleteById(id);
    }
}
