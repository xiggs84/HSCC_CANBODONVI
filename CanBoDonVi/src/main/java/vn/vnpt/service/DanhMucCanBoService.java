package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucCanBo;
import vn.vnpt.repository.DanhMucCanBoRepository;
import vn.vnpt.service.dto.DanhMucCanBoDTO;
import vn.vnpt.service.mapper.DanhMucCanBoMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucCanBo}.
 */
@Service
@Transactional
public class DanhMucCanBoService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucCanBoService.class);

    private final DanhMucCanBoRepository danhMucCanBoRepository;

    private final DanhMucCanBoMapper danhMucCanBoMapper;

    public DanhMucCanBoService(DanhMucCanBoRepository danhMucCanBoRepository, DanhMucCanBoMapper danhMucCanBoMapper) {
        this.danhMucCanBoRepository = danhMucCanBoRepository;
        this.danhMucCanBoMapper = danhMucCanBoMapper;
    }

    /**
     * Save a danhMucCanBo.
     *
     * @param danhMucCanBoDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucCanBoDTO save(DanhMucCanBoDTO danhMucCanBoDTO) {
        LOG.debug("Request to save DanhMucCanBo : {}", danhMucCanBoDTO);
        DanhMucCanBo danhMucCanBo = danhMucCanBoMapper.toEntity(danhMucCanBoDTO);
        danhMucCanBo = danhMucCanBoRepository.save(danhMucCanBo);
        return danhMucCanBoMapper.toDto(danhMucCanBo);
    }

    /**
     * Update a danhMucCanBo.
     *
     * @param danhMucCanBoDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucCanBoDTO update(DanhMucCanBoDTO danhMucCanBoDTO) {
        LOG.debug("Request to update DanhMucCanBo : {}", danhMucCanBoDTO);
        DanhMucCanBo danhMucCanBo = danhMucCanBoMapper.toEntity(danhMucCanBoDTO);
        danhMucCanBo = danhMucCanBoRepository.save(danhMucCanBo);
        return danhMucCanBoMapper.toDto(danhMucCanBo);
    }

    /**
     * Partially update a danhMucCanBo.
     *
     * @param danhMucCanBoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DanhMucCanBoDTO> partialUpdate(DanhMucCanBoDTO danhMucCanBoDTO) {
        LOG.debug("Request to partially update DanhMucCanBo : {}", danhMucCanBoDTO);

        return danhMucCanBoRepository
            .findById(danhMucCanBoDTO.getIdCanBo())
            .map(existingDanhMucCanBo -> {
                danhMucCanBoMapper.partialUpdate(existingDanhMucCanBo, danhMucCanBoDTO);

                return existingDanhMucCanBo;
            })
            .map(danhMucCanBoRepository::save)
            .map(danhMucCanBoMapper::toDto);
    }

    /**
     * Get all the danhMucCanBos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DanhMucCanBoDTO> findAll() {
        LOG.debug("Request to get all DanhMucCanBos");
        return danhMucCanBoRepository.findAll().stream().map(danhMucCanBoMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one danhMucCanBo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DanhMucCanBoDTO> findOne(Long id) {
        LOG.debug("Request to get DanhMucCanBo : {}", id);
        return danhMucCanBoRepository.findById(id).map(danhMucCanBoMapper::toDto);
    }

    /**
     * Delete the danhMucCanBo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DanhMucCanBo : {}", id);
        danhMucCanBoRepository.deleteById(id);
    }
}
