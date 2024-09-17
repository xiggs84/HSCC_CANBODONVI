package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucDonVi;
import vn.vnpt.repository.DanhMucDonViRepository;
import vn.vnpt.service.dto.DanhMucDonViDTO;
import vn.vnpt.service.mapper.DanhMucDonViMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucDonVi}.
 */
@Service
@Transactional
public class DanhMucDonViService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucDonViService.class);

    private final DanhMucDonViRepository danhMucDonViRepository;

    private final DanhMucDonViMapper danhMucDonViMapper;

    public DanhMucDonViService(DanhMucDonViRepository danhMucDonViRepository, DanhMucDonViMapper danhMucDonViMapper) {
        this.danhMucDonViRepository = danhMucDonViRepository;
        this.danhMucDonViMapper = danhMucDonViMapper;
    }

    /**
     * Save a danhMucDonVi.
     *
     * @param danhMucDonViDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucDonViDTO save(DanhMucDonViDTO danhMucDonViDTO) {
        LOG.debug("Request to save DanhMucDonVi : {}", danhMucDonViDTO);
        DanhMucDonVi danhMucDonVi = danhMucDonViMapper.toEntity(danhMucDonViDTO);
        danhMucDonVi = danhMucDonViRepository.save(danhMucDonVi);
        return danhMucDonViMapper.toDto(danhMucDonVi);
    }

    /**
     * Update a danhMucDonVi.
     *
     * @param danhMucDonViDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucDonViDTO update(DanhMucDonViDTO danhMucDonViDTO) {
        LOG.debug("Request to update DanhMucDonVi : {}", danhMucDonViDTO);
        DanhMucDonVi danhMucDonVi = danhMucDonViMapper.toEntity(danhMucDonViDTO);
        danhMucDonVi = danhMucDonViRepository.save(danhMucDonVi);
        return danhMucDonViMapper.toDto(danhMucDonVi);
    }

    /**
     * Partially update a danhMucDonVi.
     *
     * @param danhMucDonViDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DanhMucDonViDTO> partialUpdate(DanhMucDonViDTO danhMucDonViDTO) {
        LOG.debug("Request to partially update DanhMucDonVi : {}", danhMucDonViDTO);

        return danhMucDonViRepository
            .findById(danhMucDonViDTO.getIdDonVi())
            .map(existingDanhMucDonVi -> {
                danhMucDonViMapper.partialUpdate(existingDanhMucDonVi, danhMucDonViDTO);

                return existingDanhMucDonVi;
            })
            .map(danhMucDonViRepository::save)
            .map(danhMucDonViMapper::toDto);
    }

    /**
     * Get all the danhMucDonVis.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DanhMucDonViDTO> findAll() {
        LOG.debug("Request to get all DanhMucDonVis");
        return danhMucDonViRepository.findAll().stream().map(danhMucDonViMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one danhMucDonVi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DanhMucDonViDTO> findOne(Long id) {
        LOG.debug("Request to get DanhMucDonVi : {}", id);
        return danhMucDonViRepository.findById(id).map(danhMucDonViMapper::toDto);
    }

    /**
     * Delete the danhMucDonVi by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DanhMucDonVi : {}", id);
        danhMucDonViRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<DanhMucDonViDTO> findByTenDonVi(String tenDonVi) {
        LOG.debug("Request to get DanhMucDonVi by tenDonVi : {}", tenDonVi);
        return danhMucDonViRepository.findByTenDonViContainingIgnoreCase(tenDonVi)
            .stream()
            .map(danhMucDonViMapper::toDto)
            .collect(Collectors.toList());
    }
}
