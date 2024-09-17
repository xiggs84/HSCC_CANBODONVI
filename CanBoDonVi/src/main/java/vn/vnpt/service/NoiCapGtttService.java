package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.NoiCapGttt;
import vn.vnpt.repository.NoiCapGtttRepository;
import vn.vnpt.service.dto.NoiCapGtttDTO;
import vn.vnpt.service.mapper.NoiCapGtttMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.NoiCapGttt}.
 */
@Service
@Transactional
public class NoiCapGtttService {

    private static final Logger LOG = LoggerFactory.getLogger(NoiCapGtttService.class);

    private final NoiCapGtttRepository noiCapGtttRepository;

    private final NoiCapGtttMapper noiCapGtttMapper;

    public NoiCapGtttService(NoiCapGtttRepository noiCapGtttRepository, NoiCapGtttMapper noiCapGtttMapper) {
        this.noiCapGtttRepository = noiCapGtttRepository;
        this.noiCapGtttMapper = noiCapGtttMapper;
    }

    /**
     * Save a noiCapGttt.
     *
     * @param noiCapGtttDTO the entity to save.
     * @return the persisted entity.
     */
    public NoiCapGtttDTO save(NoiCapGtttDTO noiCapGtttDTO) {
        LOG.debug("Request to save NoiCapGttt : {}", noiCapGtttDTO);
        NoiCapGttt noiCapGttt = noiCapGtttMapper.toEntity(noiCapGtttDTO);
        noiCapGttt = noiCapGtttRepository.save(noiCapGttt);
        return noiCapGtttMapper.toDto(noiCapGttt);
    }

    /**
     * Update a noiCapGttt.
     *
     * @param noiCapGtttDTO the entity to save.
     * @return the persisted entity.
     */
    public NoiCapGtttDTO update(NoiCapGtttDTO noiCapGtttDTO) {
        LOG.debug("Request to update NoiCapGttt : {}", noiCapGtttDTO);
        NoiCapGttt noiCapGttt = noiCapGtttMapper.toEntity(noiCapGtttDTO);
        noiCapGttt = noiCapGtttRepository.save(noiCapGttt);
        return noiCapGtttMapper.toDto(noiCapGttt);
    }

    /**
     * Partially update a noiCapGttt.
     *
     * @param noiCapGtttDTO the entity to update partially.
     * @return the persisted entity.
     */
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

    /**
     * Get all the noiCapGttts.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NoiCapGtttDTO> findAll() {
        LOG.debug("Request to get all NoiCapGttts");
        return noiCapGtttRepository.findAll().stream().map(noiCapGtttMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one noiCapGttt by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NoiCapGtttDTO> findOne(Long id) {
        LOG.debug("Request to get NoiCapGttt : {}", id);
        return noiCapGtttRepository.findById(id).map(noiCapGtttMapper::toDto);
    }

    /**
     * Delete the noiCapGttt by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete NoiCapGttt : {}", id);
        noiCapGtttRepository.deleteById(id);
    }
}
