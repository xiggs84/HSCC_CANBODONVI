package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DmTinhTmp;
import vn.vnpt.repository.DmTinhTmpRepository;
import vn.vnpt.service.dto.DmTinhTmpDTO;
import vn.vnpt.service.mapper.DmTinhTmpMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DmTinhTmp}.
 */
@Service
@Transactional
public class DmTinhTmpService {

    private static final Logger LOG = LoggerFactory.getLogger(DmTinhTmpService.class);

    private final DmTinhTmpRepository dmTinhTmpRepository;

    private final DmTinhTmpMapper dmTinhTmpMapper;

    public DmTinhTmpService(DmTinhTmpRepository dmTinhTmpRepository, DmTinhTmpMapper dmTinhTmpMapper) {
        this.dmTinhTmpRepository = dmTinhTmpRepository;
        this.dmTinhTmpMapper = dmTinhTmpMapper;
    }

    /**
     * Save a dmTinhTmp.
     *
     * @param dmTinhTmpDTO the entity to save.
     * @return the persisted entity.
     */
    public DmTinhTmpDTO save(DmTinhTmpDTO dmTinhTmpDTO) {
        LOG.debug("Request to save DmTinhTmp : {}", dmTinhTmpDTO);
        DmTinhTmp dmTinhTmp = dmTinhTmpMapper.toEntity(dmTinhTmpDTO);
        dmTinhTmp = dmTinhTmpRepository.save(dmTinhTmp);
        return dmTinhTmpMapper.toDto(dmTinhTmp);
    }

    /**
     * Update a dmTinhTmp.
     *
     * @param dmTinhTmpDTO the entity to save.
     * @return the persisted entity.
     */
    public DmTinhTmpDTO update(DmTinhTmpDTO dmTinhTmpDTO) {
        LOG.debug("Request to update DmTinhTmp : {}", dmTinhTmpDTO);
        DmTinhTmp dmTinhTmp = dmTinhTmpMapper.toEntity(dmTinhTmpDTO);
        dmTinhTmp = dmTinhTmpRepository.save(dmTinhTmp);
        return dmTinhTmpMapper.toDto(dmTinhTmp);
    }

    /**
     * Partially update a dmTinhTmp.
     *
     * @param dmTinhTmpDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DmTinhTmpDTO> partialUpdate(DmTinhTmpDTO dmTinhTmpDTO) {
        LOG.debug("Request to partially update DmTinhTmp : {}", dmTinhTmpDTO);

        return dmTinhTmpRepository
            .findById(dmTinhTmpDTO.getMaTinh())
            .map(existingDmTinhTmp -> {
                dmTinhTmpMapper.partialUpdate(existingDmTinhTmp, dmTinhTmpDTO);

                return existingDmTinhTmp;
            })
            .map(dmTinhTmpRepository::save)
            .map(dmTinhTmpMapper::toDto);
    }

    /**
     * Get all the dmTinhTmps.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DmTinhTmpDTO> findAll() {
        LOG.debug("Request to get all DmTinhTmps");
        return dmTinhTmpRepository.findAll().stream().map(dmTinhTmpMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one dmTinhTmp by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DmTinhTmpDTO> findOne(Long id) {
        LOG.debug("Request to get DmTinhTmp : {}", id);
        return dmTinhTmpRepository.findById(id).map(dmTinhTmpMapper::toDto);
    }

    /**
     * Delete the dmTinhTmp by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DmTinhTmp : {}", id);
        dmTinhTmpRepository.deleteById(id);
    }
}
