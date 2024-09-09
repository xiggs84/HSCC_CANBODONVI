package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.TaiSanDuongSu;
import vn.vnpt.repository.TaiSanDuongSuRepository;
import vn.vnpt.service.dto.TaiSanDuongSuDTO;
import vn.vnpt.service.mapper.TaiSanDuongSuMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.TaiSanDuongSu}.
 */
@Service
@Transactional
public class TaiSanDuongSuService {

    private static final Logger LOG = LoggerFactory.getLogger(TaiSanDuongSuService.class);

    private final TaiSanDuongSuRepository taiSanDuongSuRepository;

    private final TaiSanDuongSuMapper taiSanDuongSuMapper;

    public TaiSanDuongSuService(TaiSanDuongSuRepository taiSanDuongSuRepository, TaiSanDuongSuMapper taiSanDuongSuMapper) {
        this.taiSanDuongSuRepository = taiSanDuongSuRepository;
        this.taiSanDuongSuMapper = taiSanDuongSuMapper;
    }

    /**
     * Save a taiSanDuongSu.
     *
     * @param taiSanDuongSuDTO the entity to save.
     * @return the persisted entity.
     */
    public TaiSanDuongSuDTO save(TaiSanDuongSuDTO taiSanDuongSuDTO) {
        LOG.debug("Request to save TaiSanDuongSu : {}", taiSanDuongSuDTO);
        TaiSanDuongSu taiSanDuongSu = taiSanDuongSuMapper.toEntity(taiSanDuongSuDTO);
        taiSanDuongSu = taiSanDuongSuRepository.save(taiSanDuongSu);
        return taiSanDuongSuMapper.toDto(taiSanDuongSu);
    }

    /**
     * Update a taiSanDuongSu.
     *
     * @param taiSanDuongSuDTO the entity to save.
     * @return the persisted entity.
     */
    public TaiSanDuongSuDTO update(TaiSanDuongSuDTO taiSanDuongSuDTO) {
        LOG.debug("Request to update TaiSanDuongSu : {}", taiSanDuongSuDTO);
        TaiSanDuongSu taiSanDuongSu = taiSanDuongSuMapper.toEntity(taiSanDuongSuDTO);
        taiSanDuongSu = taiSanDuongSuRepository.save(taiSanDuongSu);
        return taiSanDuongSuMapper.toDto(taiSanDuongSu);
    }

    /**
     * Partially update a taiSanDuongSu.
     *
     * @param taiSanDuongSuDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TaiSanDuongSuDTO> partialUpdate(TaiSanDuongSuDTO taiSanDuongSuDTO) {
        LOG.debug("Request to partially update TaiSanDuongSu : {}", taiSanDuongSuDTO);

        return taiSanDuongSuRepository
            .findById(taiSanDuongSuDTO.getId())
            .map(existingTaiSanDuongSu -> {
                taiSanDuongSuMapper.partialUpdate(existingTaiSanDuongSu, taiSanDuongSuDTO);

                return existingTaiSanDuongSu;
            })
            .map(taiSanDuongSuRepository::save)
            .map(taiSanDuongSuMapper::toDto);
    }

    /**
     * Get all the taiSanDuongSus.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TaiSanDuongSuDTO> findAll() {
        LOG.debug("Request to get all TaiSanDuongSus");
        return taiSanDuongSuRepository.findAll().stream().map(taiSanDuongSuMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one taiSanDuongSu by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TaiSanDuongSuDTO> findOne(Long id) {
        LOG.debug("Request to get TaiSanDuongSu : {}", id);
        return taiSanDuongSuRepository.findById(id).map(taiSanDuongSuMapper::toDto);
    }

    /**
     * Delete the taiSanDuongSu by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete TaiSanDuongSu : {}", id);
        taiSanDuongSuRepository.deleteById(id);
    }
}
