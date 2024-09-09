package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.CauHinhThongTinDuongSu;
import vn.vnpt.repository.CauHinhThongTinDuongSuRepository;
import vn.vnpt.service.dto.CauHinhThongTinDuongSuDTO;
import vn.vnpt.service.mapper.CauHinhThongTinDuongSuMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.CauHinhThongTinDuongSu}.
 */
@Service
@Transactional
public class CauHinhThongTinDuongSuService {

    private static final Logger LOG = LoggerFactory.getLogger(CauHinhThongTinDuongSuService.class);

    private final CauHinhThongTinDuongSuRepository cauHinhThongTinDuongSuRepository;

    private final CauHinhThongTinDuongSuMapper cauHinhThongTinDuongSuMapper;

    public CauHinhThongTinDuongSuService(
        CauHinhThongTinDuongSuRepository cauHinhThongTinDuongSuRepository,
        CauHinhThongTinDuongSuMapper cauHinhThongTinDuongSuMapper
    ) {
        this.cauHinhThongTinDuongSuRepository = cauHinhThongTinDuongSuRepository;
        this.cauHinhThongTinDuongSuMapper = cauHinhThongTinDuongSuMapper;
    }

    /**
     * Save a cauHinhThongTinDuongSu.
     *
     * @param cauHinhThongTinDuongSuDTO the entity to save.
     * @return the persisted entity.
     */
    public CauHinhThongTinDuongSuDTO save(CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO) {
        LOG.debug("Request to save CauHinhThongTinDuongSu : {}", cauHinhThongTinDuongSuDTO);
        CauHinhThongTinDuongSu cauHinhThongTinDuongSu = cauHinhThongTinDuongSuMapper.toEntity(cauHinhThongTinDuongSuDTO);
        cauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.save(cauHinhThongTinDuongSu);
        return cauHinhThongTinDuongSuMapper.toDto(cauHinhThongTinDuongSu);
    }

    /**
     * Update a cauHinhThongTinDuongSu.
     *
     * @param cauHinhThongTinDuongSuDTO the entity to save.
     * @return the persisted entity.
     */
    public CauHinhThongTinDuongSuDTO update(CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO) {
        LOG.debug("Request to update CauHinhThongTinDuongSu : {}", cauHinhThongTinDuongSuDTO);
        CauHinhThongTinDuongSu cauHinhThongTinDuongSu = cauHinhThongTinDuongSuMapper.toEntity(cauHinhThongTinDuongSuDTO);
        cauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.save(cauHinhThongTinDuongSu);
        return cauHinhThongTinDuongSuMapper.toDto(cauHinhThongTinDuongSu);
    }

    /**
     * Partially update a cauHinhThongTinDuongSu.
     *
     * @param cauHinhThongTinDuongSuDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CauHinhThongTinDuongSuDTO> partialUpdate(CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO) {
        LOG.debug("Request to partially update CauHinhThongTinDuongSu : {}", cauHinhThongTinDuongSuDTO);

        return cauHinhThongTinDuongSuRepository
            .findById(cauHinhThongTinDuongSuDTO.getIdCauHinh())
            .map(existingCauHinhThongTinDuongSu -> {
                cauHinhThongTinDuongSuMapper.partialUpdate(existingCauHinhThongTinDuongSu, cauHinhThongTinDuongSuDTO);

                return existingCauHinhThongTinDuongSu;
            })
            .map(cauHinhThongTinDuongSuRepository::save)
            .map(cauHinhThongTinDuongSuMapper::toDto);
    }

    /**
     * Get all the cauHinhThongTinDuongSus.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CauHinhThongTinDuongSuDTO> findAll() {
        LOG.debug("Request to get all CauHinhThongTinDuongSus");
        return cauHinhThongTinDuongSuRepository
            .findAll()
            .stream()
            .map(cauHinhThongTinDuongSuMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one cauHinhThongTinDuongSu by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CauHinhThongTinDuongSuDTO> findOne(Long id) {
        LOG.debug("Request to get CauHinhThongTinDuongSu : {}", id);
        return cauHinhThongTinDuongSuRepository.findById(id).map(cauHinhThongTinDuongSuMapper::toDto);
    }

    /**
     * Delete the cauHinhThongTinDuongSu by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete CauHinhThongTinDuongSu : {}", id);
        cauHinhThongTinDuongSuRepository.deleteById(id);
    }
}
