package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.ChiTietNganChan;
import vn.vnpt.repository.ChiTietNganChanRepository;
import vn.vnpt.service.dto.ChiTietNganChanDTO;
import vn.vnpt.service.mapper.ChiTietNganChanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.ChiTietNganChan}.
 */
@Service
@Transactional
public class ChiTietNganChanService {

    private static final Logger LOG = LoggerFactory.getLogger(ChiTietNganChanService.class);

    private final ChiTietNganChanRepository chiTietNganChanRepository;

    private final ChiTietNganChanMapper chiTietNganChanMapper;

    public ChiTietNganChanService(ChiTietNganChanRepository chiTietNganChanRepository, ChiTietNganChanMapper chiTietNganChanMapper) {
        this.chiTietNganChanRepository = chiTietNganChanRepository;
        this.chiTietNganChanMapper = chiTietNganChanMapper;
    }

    /**
     * Save a chiTietNganChan.
     *
     * @param chiTietNganChanDTO the entity to save.
     * @return the persisted entity.
     */
    public ChiTietNganChanDTO save(ChiTietNganChanDTO chiTietNganChanDTO) {
        LOG.debug("Request to save ChiTietNganChan : {}", chiTietNganChanDTO);
        ChiTietNganChan chiTietNganChan = chiTietNganChanMapper.toEntity(chiTietNganChanDTO);
        chiTietNganChan = chiTietNganChanRepository.save(chiTietNganChan);
        return chiTietNganChanMapper.toDto(chiTietNganChan);
    }

    /**
     * Update a chiTietNganChan.
     *
     * @param chiTietNganChanDTO the entity to save.
     * @return the persisted entity.
     */
    public ChiTietNganChanDTO update(ChiTietNganChanDTO chiTietNganChanDTO) {
        LOG.debug("Request to update ChiTietNganChan : {}", chiTietNganChanDTO);
        ChiTietNganChan chiTietNganChan = chiTietNganChanMapper.toEntity(chiTietNganChanDTO);
        chiTietNganChan = chiTietNganChanRepository.save(chiTietNganChan);
        return chiTietNganChanMapper.toDto(chiTietNganChan);
    }

    /**
     * Partially update a chiTietNganChan.
     *
     * @param chiTietNganChanDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ChiTietNganChanDTO> partialUpdate(ChiTietNganChanDTO chiTietNganChanDTO) {
        LOG.debug("Request to partially update ChiTietNganChan : {}", chiTietNganChanDTO);

        return chiTietNganChanRepository
            .findById(chiTietNganChanDTO.getId())
            .map(existingChiTietNganChan -> {
                chiTietNganChanMapper.partialUpdate(existingChiTietNganChan, chiTietNganChanDTO);

                return existingChiTietNganChan;
            })
            .map(chiTietNganChanRepository::save)
            .map(chiTietNganChanMapper::toDto);
    }

    /**
     * Get all the chiTietNganChans.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ChiTietNganChanDTO> findAll() {
        LOG.debug("Request to get all ChiTietNganChans");
        return chiTietNganChanRepository
            .findAll()
            .stream()
            .map(chiTietNganChanMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one chiTietNganChan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ChiTietNganChanDTO> findOne(Long id) {
        LOG.debug("Request to get ChiTietNganChan : {}", id);
        return chiTietNganChanRepository.findById(id).map(chiTietNganChanMapper::toDto);
    }

    /**
     * Delete the chiTietNganChan by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete ChiTietNganChan : {}", id);
        chiTietNganChanRepository.deleteById(id);
    }
}
