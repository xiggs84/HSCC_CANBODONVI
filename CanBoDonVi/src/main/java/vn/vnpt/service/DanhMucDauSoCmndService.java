package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucDauSoCmnd;
import vn.vnpt.repository.DanhMucDauSoCmndRepository;
import vn.vnpt.service.dto.DanhMucDauSoCmndDTO;
import vn.vnpt.service.mapper.DanhMucDauSoCmndMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucDauSoCmnd}.
 */
@Service
@Transactional
public class DanhMucDauSoCmndService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucDauSoCmndService.class);

    private final DanhMucDauSoCmndRepository danhMucDauSoCmndRepository;

    private final DanhMucDauSoCmndMapper danhMucDauSoCmndMapper;

    public DanhMucDauSoCmndService(DanhMucDauSoCmndRepository danhMucDauSoCmndRepository, DanhMucDauSoCmndMapper danhMucDauSoCmndMapper) {
        this.danhMucDauSoCmndRepository = danhMucDauSoCmndRepository;
        this.danhMucDauSoCmndMapper = danhMucDauSoCmndMapper;
    }

    /**
     * Save a danhMucDauSoCmnd.
     *
     * @param danhMucDauSoCmndDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucDauSoCmndDTO save(DanhMucDauSoCmndDTO danhMucDauSoCmndDTO) {
        LOG.debug("Request to save DanhMucDauSoCmnd : {}", danhMucDauSoCmndDTO);
        DanhMucDauSoCmnd danhMucDauSoCmnd = danhMucDauSoCmndMapper.toEntity(danhMucDauSoCmndDTO);
        danhMucDauSoCmnd = danhMucDauSoCmndRepository.save(danhMucDauSoCmnd);
        return danhMucDauSoCmndMapper.toDto(danhMucDauSoCmnd);
    }

    /**
     * Update a danhMucDauSoCmnd.
     *
     * @param danhMucDauSoCmndDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucDauSoCmndDTO update(DanhMucDauSoCmndDTO danhMucDauSoCmndDTO) {
        LOG.debug("Request to update DanhMucDauSoCmnd : {}", danhMucDauSoCmndDTO);
        DanhMucDauSoCmnd danhMucDauSoCmnd = danhMucDauSoCmndMapper.toEntity(danhMucDauSoCmndDTO);
        danhMucDauSoCmnd = danhMucDauSoCmndRepository.save(danhMucDauSoCmnd);
        return danhMucDauSoCmndMapper.toDto(danhMucDauSoCmnd);
    }

    /**
     * Partially update a danhMucDauSoCmnd.
     *
     * @param danhMucDauSoCmndDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DanhMucDauSoCmndDTO> partialUpdate(DanhMucDauSoCmndDTO danhMucDauSoCmndDTO) {
        LOG.debug("Request to partially update DanhMucDauSoCmnd : {}", danhMucDauSoCmndDTO);

        return danhMucDauSoCmndRepository
            .findById(danhMucDauSoCmndDTO.getIdDauSo())
            .map(existingDanhMucDauSoCmnd -> {
                danhMucDauSoCmndMapper.partialUpdate(existingDanhMucDauSoCmnd, danhMucDauSoCmndDTO);

                return existingDanhMucDauSoCmnd;
            })
            .map(danhMucDauSoCmndRepository::save)
            .map(danhMucDauSoCmndMapper::toDto);
    }

    /**
     * Get all the danhMucDauSoCmnds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DanhMucDauSoCmndDTO> findAll() {
        LOG.debug("Request to get all DanhMucDauSoCmnds");
        return danhMucDauSoCmndRepository
            .findAll()
            .stream()
            .map(danhMucDauSoCmndMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one danhMucDauSoCmnd by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DanhMucDauSoCmndDTO> findOne(Long id) {
        LOG.debug("Request to get DanhMucDauSoCmnd : {}", id);
        return danhMucDauSoCmndRepository.findById(id).map(danhMucDauSoCmndMapper::toDto);
    }

    /**
     * Delete the danhMucDauSoCmnd by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DanhMucDauSoCmnd : {}", id);
        danhMucDauSoCmndRepository.deleteById(id);
    }
}
