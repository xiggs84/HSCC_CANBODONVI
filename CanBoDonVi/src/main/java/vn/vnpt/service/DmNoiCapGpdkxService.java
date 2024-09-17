package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DmNoiCapGpdkx;
import vn.vnpt.repository.DmNoiCapGpdkxRepository;
import vn.vnpt.service.dto.DmNoiCapGpdkxDTO;
import vn.vnpt.service.mapper.DmNoiCapGpdkxMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DmNoiCapGpdkx}.
 */
@Service
@Transactional
public class DmNoiCapGpdkxService {

    private static final Logger LOG = LoggerFactory.getLogger(DmNoiCapGpdkxService.class);

    private final DmNoiCapGpdkxRepository dmNoiCapGpdkxRepository;

    private final DmNoiCapGpdkxMapper dmNoiCapGpdkxMapper;

    public DmNoiCapGpdkxService(DmNoiCapGpdkxRepository dmNoiCapGpdkxRepository, DmNoiCapGpdkxMapper dmNoiCapGpdkxMapper) {
        this.dmNoiCapGpdkxRepository = dmNoiCapGpdkxRepository;
        this.dmNoiCapGpdkxMapper = dmNoiCapGpdkxMapper;
    }

    /**
     * Save a dmNoiCapGpdkx.
     *
     * @param dmNoiCapGpdkxDTO the entity to save.
     * @return the persisted entity.
     */
    public DmNoiCapGpdkxDTO save(DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO) {
        LOG.debug("Request to save DmNoiCapGpdkx : {}", dmNoiCapGpdkxDTO);
        DmNoiCapGpdkx dmNoiCapGpdkx = dmNoiCapGpdkxMapper.toEntity(dmNoiCapGpdkxDTO);
        dmNoiCapGpdkx = dmNoiCapGpdkxRepository.save(dmNoiCapGpdkx);
        return dmNoiCapGpdkxMapper.toDto(dmNoiCapGpdkx);
    }

    /**
     * Update a dmNoiCapGpdkx.
     *
     * @param dmNoiCapGpdkxDTO the entity to save.
     * @return the persisted entity.
     */
    public DmNoiCapGpdkxDTO update(DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO) {
        LOG.debug("Request to update DmNoiCapGpdkx : {}", dmNoiCapGpdkxDTO);
        DmNoiCapGpdkx dmNoiCapGpdkx = dmNoiCapGpdkxMapper.toEntity(dmNoiCapGpdkxDTO);
        dmNoiCapGpdkx = dmNoiCapGpdkxRepository.save(dmNoiCapGpdkx);
        return dmNoiCapGpdkxMapper.toDto(dmNoiCapGpdkx);
    }

    /**
     * Partially update a dmNoiCapGpdkx.
     *
     * @param dmNoiCapGpdkxDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DmNoiCapGpdkxDTO> partialUpdate(DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO) {
        LOG.debug("Request to partially update DmNoiCapGpdkx : {}", dmNoiCapGpdkxDTO);

        return dmNoiCapGpdkxRepository
            .findById(dmNoiCapGpdkxDTO.getIdNoiCap())
            .map(existingDmNoiCapGpdkx -> {
                dmNoiCapGpdkxMapper.partialUpdate(existingDmNoiCapGpdkx, dmNoiCapGpdkxDTO);

                return existingDmNoiCapGpdkx;
            })
            .map(dmNoiCapGpdkxRepository::save)
            .map(dmNoiCapGpdkxMapper::toDto);
    }

    /**
     * Get all the dmNoiCapGpdkxes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DmNoiCapGpdkxDTO> findAll() {
        LOG.debug("Request to get all DmNoiCapGpdkxes");
        return dmNoiCapGpdkxRepository.findAll().stream().map(dmNoiCapGpdkxMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one dmNoiCapGpdkx by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DmNoiCapGpdkxDTO> findOne(Long id) {
        LOG.debug("Request to get DmNoiCapGpdkx : {}", id);
        return dmNoiCapGpdkxRepository.findById(id).map(dmNoiCapGpdkxMapper::toDto);
    }

    /**
     * Delete the dmNoiCapGpdkx by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DmNoiCapGpdkx : {}", id);
        dmNoiCapGpdkxRepository.deleteById(id);
    }
}
