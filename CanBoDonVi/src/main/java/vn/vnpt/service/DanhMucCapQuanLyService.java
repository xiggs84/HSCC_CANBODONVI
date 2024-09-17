package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucCapQuanLy;
import vn.vnpt.repository.DanhMucCapQuanLyRepository;
import vn.vnpt.service.dto.DanhMucCapQuanLyDTO;
import vn.vnpt.service.mapper.DanhMucCapQuanLyMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucCapQuanLy}.
 */
@Service
@Transactional
public class DanhMucCapQuanLyService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucCapQuanLyService.class);

    private final DanhMucCapQuanLyRepository danhMucCapQuanLyRepository;

    private final DanhMucCapQuanLyMapper danhMucCapQuanLyMapper;

    public DanhMucCapQuanLyService(DanhMucCapQuanLyRepository danhMucCapQuanLyRepository, DanhMucCapQuanLyMapper danhMucCapQuanLyMapper) {
        this.danhMucCapQuanLyRepository = danhMucCapQuanLyRepository;
        this.danhMucCapQuanLyMapper = danhMucCapQuanLyMapper;
    }

    /**
     * Save a danhMucCapQuanLy.
     *
     * @param danhMucCapQuanLyDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucCapQuanLyDTO save(DanhMucCapQuanLyDTO danhMucCapQuanLyDTO) {
        LOG.debug("Request to save DanhMucCapQuanLy : {}", danhMucCapQuanLyDTO);
        DanhMucCapQuanLy danhMucCapQuanLy = danhMucCapQuanLyMapper.toEntity(danhMucCapQuanLyDTO);
        danhMucCapQuanLy = danhMucCapQuanLyRepository.save(danhMucCapQuanLy);
        return danhMucCapQuanLyMapper.toDto(danhMucCapQuanLy);
    }

    /**
     * Update a danhMucCapQuanLy.
     *
     * @param danhMucCapQuanLyDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucCapQuanLyDTO update(DanhMucCapQuanLyDTO danhMucCapQuanLyDTO) {
        LOG.debug("Request to update DanhMucCapQuanLy : {}", danhMucCapQuanLyDTO);
        DanhMucCapQuanLy danhMucCapQuanLy = danhMucCapQuanLyMapper.toEntity(danhMucCapQuanLyDTO);
        danhMucCapQuanLy = danhMucCapQuanLyRepository.save(danhMucCapQuanLy);
        return danhMucCapQuanLyMapper.toDto(danhMucCapQuanLy);
    }

    /**
     * Partially update a danhMucCapQuanLy.
     *
     * @param danhMucCapQuanLyDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DanhMucCapQuanLyDTO> partialUpdate(DanhMucCapQuanLyDTO danhMucCapQuanLyDTO) {
        LOG.debug("Request to partially update DanhMucCapQuanLy : {}", danhMucCapQuanLyDTO);

        return danhMucCapQuanLyRepository
            .findById(danhMucCapQuanLyDTO.getIdCapQl())
            .map(existingDanhMucCapQuanLy -> {
                danhMucCapQuanLyMapper.partialUpdate(existingDanhMucCapQuanLy, danhMucCapQuanLyDTO);

                return existingDanhMucCapQuanLy;
            })
            .map(danhMucCapQuanLyRepository::save)
            .map(danhMucCapQuanLyMapper::toDto);
    }

    /**
     * Get all the danhMucCapQuanLies.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DanhMucCapQuanLyDTO> findAll() {
        LOG.debug("Request to get all DanhMucCapQuanLies");
        return danhMucCapQuanLyRepository
            .findAll()
            .stream()
            .map(danhMucCapQuanLyMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one danhMucCapQuanLy by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DanhMucCapQuanLyDTO> findOne(Long id) {
        LOG.debug("Request to get DanhMucCapQuanLy : {}", id);
        return danhMucCapQuanLyRepository.findById(id).map(danhMucCapQuanLyMapper::toDto);
    }

    /**
     * Delete the danhMucCapQuanLy by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DanhMucCapQuanLy : {}", id);
        danhMucCapQuanLyRepository.deleteById(id);
    }
}
