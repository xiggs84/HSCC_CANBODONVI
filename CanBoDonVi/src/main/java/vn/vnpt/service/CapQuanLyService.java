package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.CapQuanLy;
import vn.vnpt.repository.CapQuanLyRepository;
import vn.vnpt.service.dto.CapQuanLyDTO;
import vn.vnpt.service.mapper.CapQuanLyMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.CapQuanLy}.
 */
@Service
@Transactional
public class CapQuanLyService {

    private static final Logger LOG = LoggerFactory.getLogger(CapQuanLyService.class);

    private final CapQuanLyRepository capQuanLyRepository;

    private final CapQuanLyMapper capQuanLyMapper;

    public CapQuanLyService(CapQuanLyRepository capQuanLyRepository, CapQuanLyMapper capQuanLyMapper) {
        this.capQuanLyRepository = capQuanLyRepository;
        this.capQuanLyMapper = capQuanLyMapper;
    }

    /**
     * Save a capQuanLy.
     *
     * @param capQuanLyDTO the entity to save.
     * @return the persisted entity.
     */
    public CapQuanLyDTO save(CapQuanLyDTO capQuanLyDTO) {
        LOG.debug("Request to save CapQuanLy : {}", capQuanLyDTO);
        CapQuanLy capQuanLy = capQuanLyMapper.toEntity(capQuanLyDTO);
        capQuanLy = capQuanLyRepository.save(capQuanLy);
        return capQuanLyMapper.toDto(capQuanLy);
    }

    /**
     * Update a capQuanLy.
     *
     * @param capQuanLyDTO the entity to save.
     * @return the persisted entity.
     */
    public CapQuanLyDTO update(CapQuanLyDTO capQuanLyDTO) {
        LOG.debug("Request to update CapQuanLy : {}", capQuanLyDTO);
        CapQuanLy capQuanLy = capQuanLyMapper.toEntity(capQuanLyDTO);
        capQuanLy.setIsPersisted();
        capQuanLy = capQuanLyRepository.save(capQuanLy);
        return capQuanLyMapper.toDto(capQuanLy);
    }

    /**
     * Partially update a capQuanLy.
     *
     * @param capQuanLyDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CapQuanLyDTO> partialUpdate(CapQuanLyDTO capQuanLyDTO) {
        LOG.debug("Request to partially update CapQuanLy : {}", capQuanLyDTO);

        return capQuanLyRepository
            .findById(capQuanLyDTO.getIdCapQl())
            .map(existingCapQuanLy -> {
                capQuanLyMapper.partialUpdate(existingCapQuanLy, capQuanLyDTO);

                return existingCapQuanLy;
            })
            .map(capQuanLyRepository::save)
            .map(capQuanLyMapper::toDto);
    }

    /**
     * Get all the capQuanLies.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CapQuanLyDTO> findAll() {
        LOG.debug("Request to get all CapQuanLies");
        return capQuanLyRepository.findAll().stream().map(capQuanLyMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one capQuanLy by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CapQuanLyDTO> findOne(String id) {
        LOG.debug("Request to get CapQuanLy : {}", id);
        return capQuanLyRepository.findById(id).map(capQuanLyMapper::toDto);
    }

    /**
     * Delete the capQuanLy by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        LOG.debug("Request to delete CapQuanLy : {}", id);
        capQuanLyRepository.deleteById(id);
    }
}
