package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhSachDuongSu;
import vn.vnpt.repository.DanhSachDuongSuRepository;
import vn.vnpt.service.dto.DanhSachDuongSuDTO;
import vn.vnpt.service.mapper.DanhSachDuongSuMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhSachDuongSu}.
 */
@Service
@Transactional
public class DanhSachDuongSuService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhSachDuongSuService.class);

    private final DanhSachDuongSuRepository danhSachDuongSuRepository;

    private final DanhSachDuongSuMapper danhSachDuongSuMapper;

    public DanhSachDuongSuService(DanhSachDuongSuRepository danhSachDuongSuRepository, DanhSachDuongSuMapper danhSachDuongSuMapper) {
        this.danhSachDuongSuRepository = danhSachDuongSuRepository;
        this.danhSachDuongSuMapper = danhSachDuongSuMapper;
    }

    /**
     * Save a danhSachDuongSu.
     *
     * @param danhSachDuongSuDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhSachDuongSuDTO save(DanhSachDuongSuDTO danhSachDuongSuDTO) {
        LOG.debug("Request to save DanhSachDuongSu : {}", danhSachDuongSuDTO);
        DanhSachDuongSu danhSachDuongSu = danhSachDuongSuMapper.toEntity(danhSachDuongSuDTO);
        danhSachDuongSu = danhSachDuongSuRepository.save(danhSachDuongSu);
        return danhSachDuongSuMapper.toDto(danhSachDuongSu);
    }

    /**
     * Update a danhSachDuongSu.
     *
     * @param danhSachDuongSuDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhSachDuongSuDTO update(DanhSachDuongSuDTO danhSachDuongSuDTO) {
        LOG.debug("Request to update DanhSachDuongSu : {}", danhSachDuongSuDTO);
        DanhSachDuongSu danhSachDuongSu = danhSachDuongSuMapper.toEntity(danhSachDuongSuDTO);
        danhSachDuongSu = danhSachDuongSuRepository.save(danhSachDuongSu);
        return danhSachDuongSuMapper.toDto(danhSachDuongSu);
    }

    /**
     * Partially update a danhSachDuongSu.
     *
     * @param danhSachDuongSuDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DanhSachDuongSuDTO> partialUpdate(DanhSachDuongSuDTO danhSachDuongSuDTO) {
        LOG.debug("Request to partially update DanhSachDuongSu : {}", danhSachDuongSuDTO);

        return danhSachDuongSuRepository
            .findById(danhSachDuongSuDTO.getId())
            .map(existingDanhSachDuongSu -> {
                danhSachDuongSuMapper.partialUpdate(existingDanhSachDuongSu, danhSachDuongSuDTO);

                return existingDanhSachDuongSu;
            })
            .map(danhSachDuongSuRepository::save)
            .map(danhSachDuongSuMapper::toDto);
    }

    /**
     * Get all the danhSachDuongSus.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DanhSachDuongSuDTO> findAll() {
        LOG.debug("Request to get all DanhSachDuongSus");
        return danhSachDuongSuRepository
            .findAll()
            .stream()
            .map(danhSachDuongSuMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one danhSachDuongSu by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DanhSachDuongSuDTO> findOne(Long id) {
        LOG.debug("Request to get DanhSachDuongSu : {}", id);
        return danhSachDuongSuRepository.findById(id).map(danhSachDuongSuMapper::toDto);
    }

    /**
     * Delete the danhSachDuongSu by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DanhSachDuongSu : {}", id);
        danhSachDuongSuRepository.deleteById(id);
    }
}
