package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DmHuyenTmp;
import vn.vnpt.repository.DmHuyenTmpRepository;
import vn.vnpt.service.dto.DmHuyenTmpDTO;
import vn.vnpt.service.mapper.DmHuyenTmpMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DmHuyenTmp}.
 */
@Service
@Transactional
public class DmHuyenTmpService {

    private static final Logger LOG = LoggerFactory.getLogger(DmHuyenTmpService.class);

    private final DmHuyenTmpRepository dmHuyenTmpRepository;

    private final DmHuyenTmpMapper dmHuyenTmpMapper;

    public DmHuyenTmpService(DmHuyenTmpRepository dmHuyenTmpRepository, DmHuyenTmpMapper dmHuyenTmpMapper) {
        this.dmHuyenTmpRepository = dmHuyenTmpRepository;
        this.dmHuyenTmpMapper = dmHuyenTmpMapper;
    }

    /**
     * Save a dmHuyenTmp.
     *
     * @param dmHuyenTmpDTO the entity to save.
     * @return the persisted entity.
     */
    public DmHuyenTmpDTO save(DmHuyenTmpDTO dmHuyenTmpDTO) {
        LOG.debug("Request to save DmHuyenTmp : {}", dmHuyenTmpDTO);
        DmHuyenTmp dmHuyenTmp = dmHuyenTmpMapper.toEntity(dmHuyenTmpDTO);
        dmHuyenTmp = dmHuyenTmpRepository.save(dmHuyenTmp);
        return dmHuyenTmpMapper.toDto(dmHuyenTmp);
    }

    /**
     * Update a dmHuyenTmp.
     *
     * @param dmHuyenTmpDTO the entity to save.
     * @return the persisted entity.
     */
    public DmHuyenTmpDTO update(DmHuyenTmpDTO dmHuyenTmpDTO) {
        LOG.debug("Request to update DmHuyenTmp : {}", dmHuyenTmpDTO);
        DmHuyenTmp dmHuyenTmp = dmHuyenTmpMapper.toEntity(dmHuyenTmpDTO);
        dmHuyenTmp = dmHuyenTmpRepository.save(dmHuyenTmp);
        return dmHuyenTmpMapper.toDto(dmHuyenTmp);
    }

    /**
     * Partially update a dmHuyenTmp.
     *
     * @param dmHuyenTmpDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DmHuyenTmpDTO> partialUpdate(DmHuyenTmpDTO dmHuyenTmpDTO) {
        LOG.debug("Request to partially update DmHuyenTmp : {}", dmHuyenTmpDTO);

        return dmHuyenTmpRepository
            .findById(dmHuyenTmpDTO.getMaHuyen())
            .map(existingDmHuyenTmp -> {
                dmHuyenTmpMapper.partialUpdate(existingDmHuyenTmp, dmHuyenTmpDTO);

                return existingDmHuyenTmp;
            })
            .map(dmHuyenTmpRepository::save)
            .map(dmHuyenTmpMapper::toDto);
    }

    /**
     * Get all the dmHuyenTmps.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DmHuyenTmpDTO> findAll() {
        LOG.debug("Request to get all DmHuyenTmps");
        return dmHuyenTmpRepository.findAll().stream().map(dmHuyenTmpMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one dmHuyenTmp by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DmHuyenTmpDTO> findOne(Long id) {
        LOG.debug("Request to get DmHuyenTmp : {}", id);
        return dmHuyenTmpRepository.findById(id).map(dmHuyenTmpMapper::toDto);
    }

    /**
     * Delete the dmHuyenTmp by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DmHuyenTmp : {}", id);
        dmHuyenTmpRepository.deleteById(id);
    }
}
