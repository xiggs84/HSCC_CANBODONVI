package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.LoaiDonVi;
import vn.vnpt.repository.LoaiDonViRepository;
import vn.vnpt.service.dto.LoaiDonViDTO;
import vn.vnpt.service.mapper.LoaiDonViMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.LoaiDonVi}.
 */
@Service
@Transactional
public class LoaiDonViService {

    private static final Logger LOG = LoggerFactory.getLogger(LoaiDonViService.class);

    private final LoaiDonViRepository loaiDonViRepository;

    private final LoaiDonViMapper loaiDonViMapper;

    public LoaiDonViService(LoaiDonViRepository loaiDonViRepository, LoaiDonViMapper loaiDonViMapper) {
        this.loaiDonViRepository = loaiDonViRepository;
        this.loaiDonViMapper = loaiDonViMapper;
    }

    /**
     * Save a loaiDonVi.
     *
     * @param loaiDonViDTO the entity to save.
     * @return the persisted entity.
     */
    public LoaiDonViDTO save(LoaiDonViDTO loaiDonViDTO) {
        LOG.debug("Request to save LoaiDonVi : {}", loaiDonViDTO);
        LoaiDonVi loaiDonVi = loaiDonViMapper.toEntity(loaiDonViDTO);
        loaiDonVi = loaiDonViRepository.save(loaiDonVi);
        return loaiDonViMapper.toDto(loaiDonVi);
    }

    /**
     * Update a loaiDonVi.
     *
     * @param loaiDonViDTO the entity to save.
     * @return the persisted entity.
     */
    public LoaiDonViDTO update(LoaiDonViDTO loaiDonViDTO) {
        LOG.debug("Request to update LoaiDonVi : {}", loaiDonViDTO);
        LoaiDonVi loaiDonVi = loaiDonViMapper.toEntity(loaiDonViDTO);
        loaiDonVi.setIsPersisted();
        loaiDonVi = loaiDonViRepository.save(loaiDonVi);
        return loaiDonViMapper.toDto(loaiDonVi);
    }

    /**
     * Partially update a loaiDonVi.
     *
     * @param loaiDonViDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<LoaiDonViDTO> partialUpdate(LoaiDonViDTO loaiDonViDTO) {
        LOG.debug("Request to partially update LoaiDonVi : {}", loaiDonViDTO);

        return loaiDonViRepository
            .findById(loaiDonViDTO.getIdLoaiDv())
            .map(existingLoaiDonVi -> {
                loaiDonViMapper.partialUpdate(existingLoaiDonVi, loaiDonViDTO);

                return existingLoaiDonVi;
            })
            .map(loaiDonViRepository::save)
            .map(loaiDonViMapper::toDto);
    }

    /**
     * Get all the loaiDonVis.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<LoaiDonViDTO> findAll() {
        LOG.debug("Request to get all LoaiDonVis");
        return loaiDonViRepository.findAll().stream().map(loaiDonViMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one loaiDonVi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LoaiDonViDTO> findOne(String id) {
        LOG.debug("Request to get LoaiDonVi : {}", id);
        return loaiDonViRepository.findById(id).map(loaiDonViMapper::toDto);
    }

    /**
     * Delete the loaiDonVi by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        LOG.debug("Request to delete LoaiDonVi : {}", id);
        loaiDonViRepository.deleteById(id);
    }
}
