package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DuongSuTrungCmndBak;
import vn.vnpt.repository.DuongSuTrungCmndBakRepository;
import vn.vnpt.service.dto.DuongSuTrungCmndBakDTO;
import vn.vnpt.service.mapper.DuongSuTrungCmndBakMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DuongSuTrungCmndBak}.
 */
@Service
@Transactional
public class DuongSuTrungCmndBakService {

    private static final Logger LOG = LoggerFactory.getLogger(DuongSuTrungCmndBakService.class);

    private final DuongSuTrungCmndBakRepository duongSuTrungCmndBakRepository;

    private final DuongSuTrungCmndBakMapper duongSuTrungCmndBakMapper;

    public DuongSuTrungCmndBakService(
        DuongSuTrungCmndBakRepository duongSuTrungCmndBakRepository,
        DuongSuTrungCmndBakMapper duongSuTrungCmndBakMapper
    ) {
        this.duongSuTrungCmndBakRepository = duongSuTrungCmndBakRepository;
        this.duongSuTrungCmndBakMapper = duongSuTrungCmndBakMapper;
    }

    /**
     * Save a duongSuTrungCmndBak.
     *
     * @param duongSuTrungCmndBakDTO the entity to save.
     * @return the persisted entity.
     */
    public DuongSuTrungCmndBakDTO save(DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO) {
        LOG.debug("Request to save DuongSuTrungCmndBak : {}", duongSuTrungCmndBakDTO);
        DuongSuTrungCmndBak duongSuTrungCmndBak = duongSuTrungCmndBakMapper.toEntity(duongSuTrungCmndBakDTO);
        duongSuTrungCmndBak = duongSuTrungCmndBakRepository.save(duongSuTrungCmndBak);
        return duongSuTrungCmndBakMapper.toDto(duongSuTrungCmndBak);
    }

    /**
     * Update a duongSuTrungCmndBak.
     *
     * @param duongSuTrungCmndBakDTO the entity to save.
     * @return the persisted entity.
     */
    public DuongSuTrungCmndBakDTO update(DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO) {
        LOG.debug("Request to update DuongSuTrungCmndBak : {}", duongSuTrungCmndBakDTO);
        DuongSuTrungCmndBak duongSuTrungCmndBak = duongSuTrungCmndBakMapper.toEntity(duongSuTrungCmndBakDTO);
        duongSuTrungCmndBak = duongSuTrungCmndBakRepository.save(duongSuTrungCmndBak);
        return duongSuTrungCmndBakMapper.toDto(duongSuTrungCmndBak);
    }

    /**
     * Partially update a duongSuTrungCmndBak.
     *
     * @param duongSuTrungCmndBakDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DuongSuTrungCmndBakDTO> partialUpdate(DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO) {
        LOG.debug("Request to partially update DuongSuTrungCmndBak : {}", duongSuTrungCmndBakDTO);

        return duongSuTrungCmndBakRepository
            .findById(duongSuTrungCmndBakDTO.getId())
            .map(existingDuongSuTrungCmndBak -> {
                duongSuTrungCmndBakMapper.partialUpdate(existingDuongSuTrungCmndBak, duongSuTrungCmndBakDTO);

                return existingDuongSuTrungCmndBak;
            })
            .map(duongSuTrungCmndBakRepository::save)
            .map(duongSuTrungCmndBakMapper::toDto);
    }

    /**
     * Get all the duongSuTrungCmndBaks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DuongSuTrungCmndBakDTO> findAll() {
        LOG.debug("Request to get all DuongSuTrungCmndBaks");
        return duongSuTrungCmndBakRepository
            .findAll()
            .stream()
            .map(duongSuTrungCmndBakMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one duongSuTrungCmndBak by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DuongSuTrungCmndBakDTO> findOne(Long id) {
        LOG.debug("Request to get DuongSuTrungCmndBak : {}", id);
        return duongSuTrungCmndBakRepository.findById(id).map(duongSuTrungCmndBakMapper::toDto);
    }

    /**
     * Delete the duongSuTrungCmndBak by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DuongSuTrungCmndBak : {}", id);
        duongSuTrungCmndBakRepository.deleteById(id);
    }
}
