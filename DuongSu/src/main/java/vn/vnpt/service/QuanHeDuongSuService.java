package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.QuanHeDuongSu;
import vn.vnpt.repository.QuanHeDuongSuRepository;
import vn.vnpt.service.dto.QuanHeDuongSuDTO;
import vn.vnpt.service.mapper.QuanHeDuongSuMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.QuanHeDuongSu}.
 */
@Service
@Transactional
public class QuanHeDuongSuService {

    private static final Logger LOG = LoggerFactory.getLogger(QuanHeDuongSuService.class);

    private final QuanHeDuongSuRepository quanHeDuongSuRepository;

    private final QuanHeDuongSuMapper quanHeDuongSuMapper;

    public QuanHeDuongSuService(QuanHeDuongSuRepository quanHeDuongSuRepository, QuanHeDuongSuMapper quanHeDuongSuMapper) {
        this.quanHeDuongSuRepository = quanHeDuongSuRepository;
        this.quanHeDuongSuMapper = quanHeDuongSuMapper;
    }

    /**
     * Save a quanHeDuongSu.
     *
     * @param quanHeDuongSuDTO the entity to save.
     * @return the persisted entity.
     */
    public QuanHeDuongSuDTO save(QuanHeDuongSuDTO quanHeDuongSuDTO) {
        LOG.debug("Request to save QuanHeDuongSu : {}", quanHeDuongSuDTO);
        QuanHeDuongSu quanHeDuongSu = quanHeDuongSuMapper.toEntity(quanHeDuongSuDTO);
        quanHeDuongSu = quanHeDuongSuRepository.save(quanHeDuongSu);
        return quanHeDuongSuMapper.toDto(quanHeDuongSu);
    }

    /**
     * Update a quanHeDuongSu.
     *
     * @param quanHeDuongSuDTO the entity to save.
     * @return the persisted entity.
     */
    public QuanHeDuongSuDTO update(QuanHeDuongSuDTO quanHeDuongSuDTO) {
        LOG.debug("Request to update QuanHeDuongSu : {}", quanHeDuongSuDTO);
        QuanHeDuongSu quanHeDuongSu = quanHeDuongSuMapper.toEntity(quanHeDuongSuDTO);
        quanHeDuongSu = quanHeDuongSuRepository.save(quanHeDuongSu);
        return quanHeDuongSuMapper.toDto(quanHeDuongSu);
    }

    /**
     * Partially update a quanHeDuongSu.
     *
     * @param quanHeDuongSuDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<QuanHeDuongSuDTO> partialUpdate(QuanHeDuongSuDTO quanHeDuongSuDTO) {
        LOG.debug("Request to partially update QuanHeDuongSu : {}", quanHeDuongSuDTO);

        return quanHeDuongSuRepository
            .findById(quanHeDuongSuDTO.getIdQuanHe())
            .map(existingQuanHeDuongSu -> {
                quanHeDuongSuMapper.partialUpdate(existingQuanHeDuongSu, quanHeDuongSuDTO);

                return existingQuanHeDuongSu;
            })
            .map(quanHeDuongSuRepository::save)
            .map(quanHeDuongSuMapper::toDto);
    }

    /**
     * Get all the quanHeDuongSus.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<QuanHeDuongSuDTO> findAll() {
        LOG.debug("Request to get all QuanHeDuongSus");
        return quanHeDuongSuRepository.findAll().stream().map(quanHeDuongSuMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one quanHeDuongSu by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QuanHeDuongSuDTO> findOne(Long id) {
        LOG.debug("Request to get QuanHeDuongSu : {}", id);
        return quanHeDuongSuRepository.findById(id).map(quanHeDuongSuMapper::toDto);
    }

    /**
     * Delete the quanHeDuongSu by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete QuanHeDuongSu : {}", id);
        quanHeDuongSuRepository.deleteById(id);
    }
}
