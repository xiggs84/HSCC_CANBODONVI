package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucQuocGia;
import vn.vnpt.repository.DanhMucQuocGiaRepository;
import vn.vnpt.service.dto.DanhMucQuocGiaDTO;
import vn.vnpt.service.mapper.DanhMucQuocGiaMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucQuocGia}.
 */
@Service
@Transactional
public class DanhMucQuocGiaService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucQuocGiaService.class);

    private final DanhMucQuocGiaRepository danhMucQuocGiaRepository;

    private final DanhMucQuocGiaMapper danhMucQuocGiaMapper;

    public DanhMucQuocGiaService(DanhMucQuocGiaRepository danhMucQuocGiaRepository, DanhMucQuocGiaMapper danhMucQuocGiaMapper) {
        this.danhMucQuocGiaRepository = danhMucQuocGiaRepository;
        this.danhMucQuocGiaMapper = danhMucQuocGiaMapper;
    }

    /**
     * Save a danhMucQuocGia.
     *
     * @param danhMucQuocGiaDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucQuocGiaDTO save(DanhMucQuocGiaDTO danhMucQuocGiaDTO) {
        LOG.debug("Request to save DanhMucQuocGia : {}", danhMucQuocGiaDTO);
        DanhMucQuocGia danhMucQuocGia = danhMucQuocGiaMapper.toEntity(danhMucQuocGiaDTO);
        danhMucQuocGia = danhMucQuocGiaRepository.save(danhMucQuocGia);
        return danhMucQuocGiaMapper.toDto(danhMucQuocGia);
    }

    /**
     * Update a danhMucQuocGia.
     *
     * @param danhMucQuocGiaDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucQuocGiaDTO update(DanhMucQuocGiaDTO danhMucQuocGiaDTO) {
        LOG.debug("Request to update DanhMucQuocGia : {}", danhMucQuocGiaDTO);
        DanhMucQuocGia danhMucQuocGia = danhMucQuocGiaMapper.toEntity(danhMucQuocGiaDTO);
        danhMucQuocGia = danhMucQuocGiaRepository.save(danhMucQuocGia);
        return danhMucQuocGiaMapper.toDto(danhMucQuocGia);
    }

    /**
     * Partially update a danhMucQuocGia.
     *
     * @param danhMucQuocGiaDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DanhMucQuocGiaDTO> partialUpdate(DanhMucQuocGiaDTO danhMucQuocGiaDTO) {
        LOG.debug("Request to partially update DanhMucQuocGia : {}", danhMucQuocGiaDTO);

        return danhMucQuocGiaRepository
            .findById(danhMucQuocGiaDTO.getIdQuocGia())
            .map(existingDanhMucQuocGia -> {
                danhMucQuocGiaMapper.partialUpdate(existingDanhMucQuocGia, danhMucQuocGiaDTO);

                return existingDanhMucQuocGia;
            })
            .map(danhMucQuocGiaRepository::save)
            .map(danhMucQuocGiaMapper::toDto);
    }

    /**
     * Get all the danhMucQuocGias.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DanhMucQuocGiaDTO> findAll() {
        LOG.debug("Request to get all DanhMucQuocGias");
        return danhMucQuocGiaRepository
            .findAll()
            .stream()
            .map(danhMucQuocGiaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one danhMucQuocGia by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DanhMucQuocGiaDTO> findOne(Long id) {
        LOG.debug("Request to get DanhMucQuocGia : {}", id);
        return danhMucQuocGiaRepository.findById(id).map(danhMucQuocGiaMapper::toDto);
    }

    /**
     * Delete the danhMucQuocGia by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DanhMucQuocGia : {}", id);
        danhMucQuocGiaRepository.deleteById(id);
    }
}
