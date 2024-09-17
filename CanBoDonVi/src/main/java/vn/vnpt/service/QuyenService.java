package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.Quyen;
import vn.vnpt.repository.QuyenRepository;
import vn.vnpt.service.dto.QuyenDTO;
import vn.vnpt.service.mapper.QuyenMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.Quyen}.
 */
@Service
@Transactional
public class QuyenService {

    private static final Logger LOG = LoggerFactory.getLogger(QuyenService.class);

    private final QuyenRepository quyenRepository;

    private final QuyenMapper quyenMapper;

    public QuyenService(QuyenRepository quyenRepository, QuyenMapper quyenMapper) {
        this.quyenRepository = quyenRepository;
        this.quyenMapper = quyenMapper;
    }

    /**
     * Save a quyen.
     *
     * @param quyenDTO the entity to save.
     * @return the persisted entity.
     */
    public QuyenDTO save(QuyenDTO quyenDTO) {
        LOG.debug("Request to save Quyen : {}", quyenDTO);
        Quyen quyen = quyenMapper.toEntity(quyenDTO);
        quyen = quyenRepository.save(quyen);
        return quyenMapper.toDto(quyen);
    }

    /**
     * Update a quyen.
     *
     * @param quyenDTO the entity to save.
     * @return the persisted entity.
     */
    public QuyenDTO update(QuyenDTO quyenDTO) {
        LOG.debug("Request to update Quyen : {}", quyenDTO);
        Quyen quyen = quyenMapper.toEntity(quyenDTO);
        quyen = quyenRepository.save(quyen);
        return quyenMapper.toDto(quyen);
    }

    /**
     * Partially update a quyen.
     *
     * @param quyenDTO the entity to update partially.
     * @return the persisted entity.
     */
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

    /**
     * Get all the quyens.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<QuyenDTO> findAll() {
        LOG.debug("Request to get all Quyens");
        return quyenRepository.findAll().stream().map(quyenMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one quyen by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QuyenDTO> findOne(Long id) {
        LOG.debug("Request to get Quyen : {}", id);
        return quyenRepository.findById(id).map(quyenMapper::toDto);
    }

    /**
     * Delete the quyen by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Quyen : {}", id);
        quyenRepository.deleteById(id);
    }
}
