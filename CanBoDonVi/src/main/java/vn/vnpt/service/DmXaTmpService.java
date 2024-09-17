package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DmXaTmp;
import vn.vnpt.repository.DmXaTmpRepository;
import vn.vnpt.service.dto.DmXaTmpDTO;
import vn.vnpt.service.mapper.DmXaTmpMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DmXaTmp}.
 */
@Service
@Transactional
public class DmXaTmpService {

    private static final Logger LOG = LoggerFactory.getLogger(DmXaTmpService.class);

    private final DmXaTmpRepository dmXaTmpRepository;

    private final DmXaTmpMapper dmXaTmpMapper;

    public DmXaTmpService(DmXaTmpRepository dmXaTmpRepository, DmXaTmpMapper dmXaTmpMapper) {
        this.dmXaTmpRepository = dmXaTmpRepository;
        this.dmXaTmpMapper = dmXaTmpMapper;
    }

    /**
     * Save a dmXaTmp.
     *
     * @param dmXaTmpDTO the entity to save.
     * @return the persisted entity.
     */
    public DmXaTmpDTO save(DmXaTmpDTO dmXaTmpDTO) {
        LOG.debug("Request to save DmXaTmp : {}", dmXaTmpDTO);
        DmXaTmp dmXaTmp = dmXaTmpMapper.toEntity(dmXaTmpDTO);
        dmXaTmp = dmXaTmpRepository.save(dmXaTmp);
        return dmXaTmpMapper.toDto(dmXaTmp);
    }

    /**
     * Update a dmXaTmp.
     *
     * @param dmXaTmpDTO the entity to save.
     * @return the persisted entity.
     */
    public DmXaTmpDTO update(DmXaTmpDTO dmXaTmpDTO) {
        LOG.debug("Request to update DmXaTmp : {}", dmXaTmpDTO);
        DmXaTmp dmXaTmp = dmXaTmpMapper.toEntity(dmXaTmpDTO);
        dmXaTmp = dmXaTmpRepository.save(dmXaTmp);
        return dmXaTmpMapper.toDto(dmXaTmp);
    }

    /**
     * Partially update a dmXaTmp.
     *
     * @param dmXaTmpDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DmXaTmpDTO> partialUpdate(DmXaTmpDTO dmXaTmpDTO) {
        LOG.debug("Request to partially update DmXaTmp : {}", dmXaTmpDTO);

        return dmXaTmpRepository
            .findById(dmXaTmpDTO.getMaXa())
            .map(existingDmXaTmp -> {
                dmXaTmpMapper.partialUpdate(existingDmXaTmp, dmXaTmpDTO);

                return existingDmXaTmp;
            })
            .map(dmXaTmpRepository::save)
            .map(dmXaTmpMapper::toDto);
    }

    /**
     * Get all the dmXaTmps.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DmXaTmpDTO> findAll() {
        LOG.debug("Request to get all DmXaTmps");
        return dmXaTmpRepository.findAll().stream().map(dmXaTmpMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one dmXaTmp by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DmXaTmpDTO> findOne(Long id) {
        LOG.debug("Request to get DmXaTmp : {}", id);
        return dmXaTmpRepository.findById(id).map(dmXaTmpMapper::toDto);
    }

    /**
     * Delete the dmXaTmp by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DmXaTmp : {}", id);
        dmXaTmpRepository.deleteById(id);
    }
}
