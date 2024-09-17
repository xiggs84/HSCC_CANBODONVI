package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucHuyen;
import vn.vnpt.repository.DanhMucHuyenRepository;
import vn.vnpt.service.dto.DanhMucHuyenDTO;
import vn.vnpt.service.mapper.DanhMucHuyenMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucHuyen}.
 */
@Service
@Transactional
public class DanhMucHuyenService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucHuyenService.class);

    private final DanhMucHuyenRepository danhMucHuyenRepository;

    private final DanhMucHuyenMapper danhMucHuyenMapper;

    public DanhMucHuyenService(DanhMucHuyenRepository danhMucHuyenRepository, DanhMucHuyenMapper danhMucHuyenMapper) {
        this.danhMucHuyenRepository = danhMucHuyenRepository;
        this.danhMucHuyenMapper = danhMucHuyenMapper;
    }

    /**
     * Save a danhMucHuyen.
     *
     * @param danhMucHuyenDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucHuyenDTO save(DanhMucHuyenDTO danhMucHuyenDTO) {
        LOG.debug("Request to save DanhMucHuyen : {}", danhMucHuyenDTO);
        DanhMucHuyen danhMucHuyen = danhMucHuyenMapper.toEntity(danhMucHuyenDTO);
        danhMucHuyen = danhMucHuyenRepository.save(danhMucHuyen);
        return danhMucHuyenMapper.toDto(danhMucHuyen);
    }

    /**
     * Update a danhMucHuyen.
     *
     * @param danhMucHuyenDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucHuyenDTO update(DanhMucHuyenDTO danhMucHuyenDTO) {
        LOG.debug("Request to update DanhMucHuyen : {}", danhMucHuyenDTO);
        DanhMucHuyen danhMucHuyen = danhMucHuyenMapper.toEntity(danhMucHuyenDTO);
        danhMucHuyen.setIsPersisted();
        danhMucHuyen = danhMucHuyenRepository.save(danhMucHuyen);
        return danhMucHuyenMapper.toDto(danhMucHuyen);
    }

    /**
     * Partially update a danhMucHuyen.
     *
     * @param danhMucHuyenDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DanhMucHuyenDTO> partialUpdate(DanhMucHuyenDTO danhMucHuyenDTO) {
        LOG.debug("Request to partially update DanhMucHuyen : {}", danhMucHuyenDTO);

        return danhMucHuyenRepository
            .findById(danhMucHuyenDTO.getMaHuyen())
            .map(existingDanhMucHuyen -> {
                danhMucHuyenMapper.partialUpdate(existingDanhMucHuyen, danhMucHuyenDTO);

                return existingDanhMucHuyen;
            })
            .map(danhMucHuyenRepository::save)
            .map(danhMucHuyenMapper::toDto);
    }

    /**
     * Get all the danhMucHuyens.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DanhMucHuyenDTO> findAll() {
        LOG.debug("Request to get all DanhMucHuyens");
        return danhMucHuyenRepository.findAll().stream().map(danhMucHuyenMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one danhMucHuyen by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DanhMucHuyenDTO> findOne(String id) {
        LOG.debug("Request to get DanhMucHuyen : {}", id);
        return danhMucHuyenRepository.findById(id).map(danhMucHuyenMapper::toDto);
    }

    /**
     * Delete the danhMucHuyen by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        LOG.debug("Request to delete DanhMucHuyen : {}", id);
        danhMucHuyenRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<DanhMucHuyenDTO> findByMaTinh(String maTinh) {
        return danhMucHuyenRepository.findByMaTinh(maTinh)
            .stream()
            .map(danhMucHuyenMapper::toDto)
            .collect(Collectors.toList());
    }
}
