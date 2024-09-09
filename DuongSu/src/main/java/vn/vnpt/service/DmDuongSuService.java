package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DmDuongSu;
import vn.vnpt.repository.DmDuongSuRepository;
import vn.vnpt.service.dto.DmDuongSuDTO;
import vn.vnpt.service.mapper.DmDuongSuMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DmDuongSu}.
 */
@Service
@Transactional
public class DmDuongSuService {

    private static final Logger LOG = LoggerFactory.getLogger(DmDuongSuService.class);

    private final DmDuongSuRepository dmDuongSuRepository;

    private final DmDuongSuMapper dmDuongSuMapper;

    public DmDuongSuService(DmDuongSuRepository dmDuongSuRepository, DmDuongSuMapper dmDuongSuMapper) {
        this.dmDuongSuRepository = dmDuongSuRepository;
        this.dmDuongSuMapper = dmDuongSuMapper;
    }

    /**
     * Save a dmDuongSu.
     *
     * @param dmDuongSuDTO the entity to save.
     * @return the persisted entity.
     */
    public DmDuongSuDTO save(DmDuongSuDTO dmDuongSuDTO) {
        LOG.debug("Request to save DmDuongSu : {}", dmDuongSuDTO);
        DmDuongSu dmDuongSu = dmDuongSuMapper.toEntity(dmDuongSuDTO);
        dmDuongSu = dmDuongSuRepository.save(dmDuongSu);
        return dmDuongSuMapper.toDto(dmDuongSu);
    }

    /**
     * Update a dmDuongSu.
     *
     * @param dmDuongSuDTO the entity to save.
     * @return the persisted entity.
     */
    public DmDuongSuDTO update(DmDuongSuDTO dmDuongSuDTO) {
        LOG.debug("Request to update DmDuongSu : {}", dmDuongSuDTO);
        DmDuongSu dmDuongSu = dmDuongSuMapper.toEntity(dmDuongSuDTO);
        dmDuongSu = dmDuongSuRepository.save(dmDuongSu);
        return dmDuongSuMapper.toDto(dmDuongSu);
    }

    /**
     * Partially update a dmDuongSu.
     *
     * @param dmDuongSuDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DmDuongSuDTO> partialUpdate(DmDuongSuDTO dmDuongSuDTO) {
        LOG.debug("Request to partially update DmDuongSu : {}", dmDuongSuDTO);

        return dmDuongSuRepository
            .findById(dmDuongSuDTO.getIdDuongSu())
            .map(existingDmDuongSu -> {
                dmDuongSuMapper.partialUpdate(existingDmDuongSu, dmDuongSuDTO);

                return existingDmDuongSu;
            })
            .map(dmDuongSuRepository::save)
            .map(dmDuongSuMapper::toDto);
    }

    /**
     * Get all the dmDuongSus.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DmDuongSuDTO> findAll() {
        LOG.debug("Request to get all DmDuongSus");
        return dmDuongSuRepository.findAll().stream().map(dmDuongSuMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one dmDuongSu by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DmDuongSuDTO> findOne(Long id) {
        LOG.debug("Request to get DmDuongSu : {}", id);
        return dmDuongSuRepository.findById(id).map(dmDuongSuMapper::toDto);
    }

    /**
     * Delete the dmDuongSu by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DmDuongSu : {}", id);
        dmDuongSuRepository.deleteById(id);
    }
}
