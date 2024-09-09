package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.QuanHeMaster;
import vn.vnpt.repository.QuanHeMasterRepository;
import vn.vnpt.service.dto.QuanHeMasterDTO;
import vn.vnpt.service.mapper.QuanHeMasterMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.QuanHeMaster}.
 */
@Service
@Transactional
public class QuanHeMasterService {

    private static final Logger LOG = LoggerFactory.getLogger(QuanHeMasterService.class);

    private final QuanHeMasterRepository quanHeMasterRepository;

    private final QuanHeMasterMapper quanHeMasterMapper;

    public QuanHeMasterService(QuanHeMasterRepository quanHeMasterRepository, QuanHeMasterMapper quanHeMasterMapper) {
        this.quanHeMasterRepository = quanHeMasterRepository;
        this.quanHeMasterMapper = quanHeMasterMapper;
    }

    /**
     * Save a quanHeMaster.
     *
     * @param quanHeMasterDTO the entity to save.
     * @return the persisted entity.
     */
    public QuanHeMasterDTO save(QuanHeMasterDTO quanHeMasterDTO) {
        LOG.debug("Request to save QuanHeMaster : {}", quanHeMasterDTO);
        QuanHeMaster quanHeMaster = quanHeMasterMapper.toEntity(quanHeMasterDTO);
        quanHeMaster = quanHeMasterRepository.save(quanHeMaster);
        return quanHeMasterMapper.toDto(quanHeMaster);
    }

    /**
     * Update a quanHeMaster.
     *
     * @param quanHeMasterDTO the entity to save.
     * @return the persisted entity.
     */
    public QuanHeMasterDTO update(QuanHeMasterDTO quanHeMasterDTO) {
        LOG.debug("Request to update QuanHeMaster : {}", quanHeMasterDTO);
        QuanHeMaster quanHeMaster = quanHeMasterMapper.toEntity(quanHeMasterDTO);
        quanHeMaster = quanHeMasterRepository.save(quanHeMaster);
        return quanHeMasterMapper.toDto(quanHeMaster);
    }

    /**
     * Partially update a quanHeMaster.
     *
     * @param quanHeMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
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

    /**
     * Get all the quanHeMasters.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<QuanHeMasterDTO> findAll() {
        LOG.debug("Request to get all QuanHeMasters");
        return quanHeMasterRepository.findAll().stream().map(quanHeMasterMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one quanHeMaster by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QuanHeMasterDTO> findOne(Long id) {
        LOG.debug("Request to get QuanHeMaster : {}", id);
        return quanHeMasterRepository.findById(id).map(quanHeMasterMapper::toDto);
    }

    /**
     * Delete the quanHeMaster by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete QuanHeMaster : {}", id);
        quanHeMasterRepository.deleteById(id);
    }
}
