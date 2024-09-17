package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucXa;
import vn.vnpt.repository.DanhMucXaRepository;
import vn.vnpt.service.dto.DanhMucXaDTO;
import vn.vnpt.service.mapper.DanhMucXaMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucXa}.
 */
@Service
@Transactional
public class DanhMucXaService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucXaService.class);

    private final DanhMucXaRepository danhMucXaRepository;

    private final DanhMucXaMapper danhMucXaMapper;

    public DanhMucXaService(DanhMucXaRepository danhMucXaRepository, DanhMucXaMapper danhMucXaMapper) {
        this.danhMucXaRepository = danhMucXaRepository;
        this.danhMucXaMapper = danhMucXaMapper;
    }

    /**
     * Save a danhMucXa.
     *
     * @param danhMucXaDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucXaDTO save(DanhMucXaDTO danhMucXaDTO) {
        LOG.debug("Request to save DanhMucXa : {}", danhMucXaDTO);
        DanhMucXa danhMucXa = danhMucXaMapper.toEntity(danhMucXaDTO);
        danhMucXa = danhMucXaRepository.save(danhMucXa);
        return danhMucXaMapper.toDto(danhMucXa);
    }

    /**
     * Update a danhMucXa.
     *
     * @param danhMucXaDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucXaDTO update(DanhMucXaDTO danhMucXaDTO) {
        LOG.debug("Request to update DanhMucXa : {}", danhMucXaDTO);
        DanhMucXa danhMucXa = danhMucXaMapper.toEntity(danhMucXaDTO);
        danhMucXa.setIsPersisted();
        danhMucXa = danhMucXaRepository.save(danhMucXa);
        return danhMucXaMapper.toDto(danhMucXa);
    }

    /**
     * Partially update a danhMucXa.
     *
     * @param danhMucXaDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DanhMucXaDTO> partialUpdate(DanhMucXaDTO danhMucXaDTO) {
        LOG.debug("Request to partially update DanhMucXa : {}", danhMucXaDTO);

        return danhMucXaRepository
            .findById(danhMucXaDTO.getMaXa())
            .map(existingDanhMucXa -> {
                danhMucXaMapper.partialUpdate(existingDanhMucXa, danhMucXaDTO);

                return existingDanhMucXa;
            })
            .map(danhMucXaRepository::save)
            .map(danhMucXaMapper::toDto);
    }

    /**
     * Get all the danhMucXas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DanhMucXaDTO> findAll() {
        LOG.debug("Request to get all DanhMucXas");
        return danhMucXaRepository.findAll().stream().map(danhMucXaMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one danhMucXa by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DanhMucXaDTO> findOne(String id) {
        LOG.debug("Request to get DanhMucXa : {}", id);
        return danhMucXaRepository.findById(id).map(danhMucXaMapper::toDto);
    }

    /**
     * Delete the danhMucXa by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        LOG.debug("Request to delete DanhMucXa : {}", id);
        danhMucXaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<DanhMucXaDTO> findByMaHuyen(String maHuyen) {
        return danhMucXaRepository.findByMaHuyen(maHuyen)
            .stream()
            .map(danhMucXaMapper::toDto)
            .collect(Collectors.toList());
    }
}
