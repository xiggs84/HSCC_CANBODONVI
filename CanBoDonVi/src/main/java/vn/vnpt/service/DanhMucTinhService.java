package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucTinh;
import vn.vnpt.repository.DanhMucTinhRepository;
import vn.vnpt.service.dto.DanhMucTinhDTO;
import vn.vnpt.service.mapper.DanhMucTinhMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucTinh}.
 */
@Service
@Transactional
public class DanhMucTinhService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucTinhService.class);

    private final DanhMucTinhRepository danhMucTinhRepository;

    private final DanhMucTinhMapper danhMucTinhMapper;

    public DanhMucTinhService(DanhMucTinhRepository danhMucTinhRepository, DanhMucTinhMapper danhMucTinhMapper) {
        this.danhMucTinhRepository = danhMucTinhRepository;
        this.danhMucTinhMapper = danhMucTinhMapper;
    }

    /**
     * Save a danhMucTinh.
     *
     * @param danhMucTinhDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucTinhDTO save(DanhMucTinhDTO danhMucTinhDTO) {
        LOG.debug("Request to save DanhMucTinh : {}", danhMucTinhDTO);
        DanhMucTinh danhMucTinh = danhMucTinhMapper.toEntity(danhMucTinhDTO);
        danhMucTinh = danhMucTinhRepository.save(danhMucTinh);
        return danhMucTinhMapper.toDto(danhMucTinh);
    }

    /**
     * Update a danhMucTinh.
     *
     * @param danhMucTinhDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucTinhDTO update(DanhMucTinhDTO danhMucTinhDTO) {
        LOG.debug("Request to update DanhMucTinh : {}", danhMucTinhDTO);
        DanhMucTinh danhMucTinh = danhMucTinhMapper.toEntity(danhMucTinhDTO);
        danhMucTinh.setIsPersisted();
        danhMucTinh = danhMucTinhRepository.save(danhMucTinh);
        return danhMucTinhMapper.toDto(danhMucTinh);
    }

    /**
     * Partially update a danhMucTinh.
     *
     * @param danhMucTinhDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DanhMucTinhDTO> partialUpdate(DanhMucTinhDTO danhMucTinhDTO) {
        LOG.debug("Request to partially update DanhMucTinh : {}", danhMucTinhDTO);

        return danhMucTinhRepository
            .findById(danhMucTinhDTO.getMaTinh())
            .map(existingDanhMucTinh -> {
                danhMucTinhMapper.partialUpdate(existingDanhMucTinh, danhMucTinhDTO);

                return existingDanhMucTinh;
            })
            .map(danhMucTinhRepository::save)
            .map(danhMucTinhMapper::toDto);
    }

    /**
     * Get all the danhMucTinhs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DanhMucTinhDTO> findAll() {
        LOG.debug("Request to get all DanhMucTinhs");
        return danhMucTinhRepository.findAll().stream().map(danhMucTinhMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one danhMucTinh by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DanhMucTinhDTO> findOne(String id) {
        LOG.debug("Request to get DanhMucTinh : {}", id);
        return danhMucTinhRepository.findById(id).map(danhMucTinhMapper::toDto);
    }

    /**
     * Delete the danhMucTinh by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        LOG.debug("Request to delete DanhMucTinh : {}", id);
        danhMucTinhRepository.deleteById(id);
    }
}
