package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.Quyen;
import vn.vnpt.repository.QuyenRepository;
import vn.vnpt.service.QuyenService;
import vn.vnpt.service.dto.QuyenDTO;
import vn.vnpt.service.mapper.QuyenMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.Quyen}.
 */
@Service
@Transactional
public class QuyenServiceImpl implements QuyenService {

    private static final Logger LOG = LoggerFactory.getLogger(QuyenServiceImpl.class);

    private final QuyenRepository quyenRepository;

    private final QuyenMapper quyenMapper;

    public QuyenServiceImpl(QuyenRepository quyenRepository, QuyenMapper quyenMapper) {
        this.quyenRepository = quyenRepository;
        this.quyenMapper = quyenMapper;
    }

    @Override
    public QuyenDTO save(QuyenDTO quyenDTO) {
        LOG.debug("Request to save Quyen : {}", quyenDTO);
        Quyen quyen = quyenMapper.toEntity(quyenDTO);
        quyen = quyenRepository.save(quyen);
        return quyenMapper.toDto(quyen);
    }

    @Override
    public QuyenDTO update(QuyenDTO quyenDTO) {
        LOG.debug("Request to update Quyen : {}", quyenDTO);
        Quyen quyen = quyenMapper.toEntity(quyenDTO);
        quyen = quyenRepository.save(quyen);
        return quyenMapper.toDto(quyen);
    }

    @Override
    public Optional<QuyenDTO> partialUpdate(QuyenDTO quyenDTO) {
        LOG.debug("Request to partially update Quyen : {}", quyenDTO);

        return quyenRepository
            .findById(quyenDTO.getIdQuyen())
            .map(existingQuyen -> {
                quyenMapper.partialUpdate(existingQuyen, quyenDTO);

                return existingQuyen;
            })
            .map(quyenRepository::save)
            .map(quyenMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuyenDTO> findOne(Long id) {
        LOG.debug("Request to get Quyen : {}", id);
        return quyenRepository.findById(id).map(quyenMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Quyen : {}", id);
        quyenRepository.deleteById(id);
    }
}
