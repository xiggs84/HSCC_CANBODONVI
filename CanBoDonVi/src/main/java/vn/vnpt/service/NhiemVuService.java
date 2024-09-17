package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.NhiemVu;
import vn.vnpt.repository.NhiemVuRepository;
import vn.vnpt.service.dto.NhiemVuDTO;
import vn.vnpt.service.mapper.NhiemVuMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.NhiemVu}.
 */
@Service
@Transactional
public class NhiemVuService {

    private static final Logger LOG = LoggerFactory.getLogger(NhiemVuService.class);

    private final NhiemVuRepository nhiemVuRepository;

    private final NhiemVuMapper nhiemVuMapper;

    public NhiemVuService(NhiemVuRepository nhiemVuRepository, NhiemVuMapper nhiemVuMapper) {
        this.nhiemVuRepository = nhiemVuRepository;
        this.nhiemVuMapper = nhiemVuMapper;
    }

    /**
     * Save a nhiemVu.
     *
     * @param nhiemVuDTO the entity to save.
     * @return the persisted entity.
     */
    public NhiemVuDTO save(NhiemVuDTO nhiemVuDTO) {
        LOG.debug("Request to save NhiemVu : {}", nhiemVuDTO);
        NhiemVu nhiemVu = nhiemVuMapper.toEntity(nhiemVuDTO);
        nhiemVu = nhiemVuRepository.save(nhiemVu);
        return nhiemVuMapper.toDto(nhiemVu);
    }

    /**
     * Update a nhiemVu.
     *
     * @param nhiemVuDTO the entity to save.
     * @return the persisted entity.
     */
    public NhiemVuDTO update(NhiemVuDTO nhiemVuDTO) {
        LOG.debug("Request to update NhiemVu : {}", nhiemVuDTO);
        NhiemVu nhiemVu = nhiemVuMapper.toEntity(nhiemVuDTO);
        nhiemVu.setIsPersisted();
        nhiemVu = nhiemVuRepository.save(nhiemVu);
        return nhiemVuMapper.toDto(nhiemVu);
    }

    /**
     * Partially update a nhiemVu.
     *
     * @param nhiemVuDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<NhiemVuDTO> partialUpdate(NhiemVuDTO nhiemVuDTO) {
        LOG.debug("Request to partially update NhiemVu : {}", nhiemVuDTO);

        return nhiemVuRepository
            .findById(nhiemVuDTO.getIdNhiemVu())
            .map(existingNhiemVu -> {
                nhiemVuMapper.partialUpdate(existingNhiemVu, nhiemVuDTO);

                return existingNhiemVu;
            })
            .map(nhiemVuRepository::save)
            .map(nhiemVuMapper::toDto);
    }

    /**
     * Get all the nhiemVus.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NhiemVuDTO> findAll() {
        LOG.debug("Request to get all NhiemVus");
        return nhiemVuRepository.findAll().stream().map(nhiemVuMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one nhiemVu by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NhiemVuDTO> findOne(String id) {
        LOG.debug("Request to get NhiemVu : {}", id);
        return nhiemVuRepository.findById(id).map(nhiemVuMapper::toDto);
    }

    /**
     * Delete the nhiemVu by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        LOG.debug("Request to delete NhiemVu : {}", id);
        nhiemVuRepository.deleteById(id);
    }
}
