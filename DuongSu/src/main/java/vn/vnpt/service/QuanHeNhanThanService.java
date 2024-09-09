package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.QuanHeNhanThan;
import vn.vnpt.repository.QuanHeNhanThanRepository;
import vn.vnpt.service.dto.QuanHeNhanThanDTO;
import vn.vnpt.service.mapper.QuanHeNhanThanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.QuanHeNhanThan}.
 */
@Service
@Transactional
public class QuanHeNhanThanService {

    private static final Logger LOG = LoggerFactory.getLogger(QuanHeNhanThanService.class);

    private final QuanHeNhanThanRepository quanHeNhanThanRepository;

    private final QuanHeNhanThanMapper quanHeNhanThanMapper;

    public QuanHeNhanThanService(QuanHeNhanThanRepository quanHeNhanThanRepository, QuanHeNhanThanMapper quanHeNhanThanMapper) {
        this.quanHeNhanThanRepository = quanHeNhanThanRepository;
        this.quanHeNhanThanMapper = quanHeNhanThanMapper;
    }

    /**
     * Save a quanHeNhanThan.
     *
     * @param quanHeNhanThanDTO the entity to save.
     * @return the persisted entity.
     */
    public QuanHeNhanThanDTO save(QuanHeNhanThanDTO quanHeNhanThanDTO) {
        LOG.debug("Request to save QuanHeNhanThan : {}", quanHeNhanThanDTO);
        QuanHeNhanThan quanHeNhanThan = quanHeNhanThanMapper.toEntity(quanHeNhanThanDTO);
        quanHeNhanThan = quanHeNhanThanRepository.save(quanHeNhanThan);
        return quanHeNhanThanMapper.toDto(quanHeNhanThan);
    }

    /**
     * Update a quanHeNhanThan.
     *
     * @param quanHeNhanThanDTO the entity to save.
     * @return the persisted entity.
     */
    public QuanHeNhanThanDTO update(QuanHeNhanThanDTO quanHeNhanThanDTO) {
        LOG.debug("Request to update QuanHeNhanThan : {}", quanHeNhanThanDTO);
        QuanHeNhanThan quanHeNhanThan = quanHeNhanThanMapper.toEntity(quanHeNhanThanDTO);
        quanHeNhanThan = quanHeNhanThanRepository.save(quanHeNhanThan);
        return quanHeNhanThanMapper.toDto(quanHeNhanThan);
    }

    /**
     * Partially update a quanHeNhanThan.
     *
     * @param quanHeNhanThanDTO the entity to update partially.
     * @return the persisted entity.
     */
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

    /**
     * Get all the quanHeNhanThans.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<QuanHeNhanThanDTO> findAll() {
        LOG.debug("Request to get all QuanHeNhanThans");
        return quanHeNhanThanRepository
            .findAll()
            .stream()
            .map(quanHeNhanThanMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one quanHeNhanThan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QuanHeNhanThanDTO> findOne(Long id) {
        LOG.debug("Request to get QuanHeNhanThan : {}", id);
        return quanHeNhanThanRepository.findById(id).map(quanHeNhanThanMapper::toDto);
    }

    /**
     * Delete the quanHeNhanThan by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete QuanHeNhanThan : {}", id);
        quanHeNhanThanRepository.deleteById(id);
    }
}
