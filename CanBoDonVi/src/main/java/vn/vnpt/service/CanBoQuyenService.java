package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.CanBoQuyen;
import vn.vnpt.repository.CanBoQuyenRepository;
import vn.vnpt.service.dto.CanBoQuyenDTO;
import vn.vnpt.service.mapper.CanBoQuyenMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.CanBoQuyen}.
 */
@Service
@Transactional
public class CanBoQuyenService {

    private static final Logger LOG = LoggerFactory.getLogger(CanBoQuyenService.class);

    private final CanBoQuyenRepository canBoQuyenRepository;

    private final CanBoQuyenMapper canBoQuyenMapper;

    public CanBoQuyenService(CanBoQuyenRepository canBoQuyenRepository, CanBoQuyenMapper canBoQuyenMapper) {
        this.canBoQuyenRepository = canBoQuyenRepository;
        this.canBoQuyenMapper = canBoQuyenMapper;
    }

    /**
     * Save a canBoQuyen.
     *
     * @param canBoQuyenDTO the entity to save.
     * @return the persisted entity.
     */
    public CanBoQuyenDTO save(CanBoQuyenDTO canBoQuyenDTO) {
        LOG.debug("Request to save CanBoQuyen : {}", canBoQuyenDTO);
        CanBoQuyen canBoQuyen = canBoQuyenMapper.toEntity(canBoQuyenDTO);
        canBoQuyen = canBoQuyenRepository.save(canBoQuyen);
        return canBoQuyenMapper.toDto(canBoQuyen);
    }

    /**
     * Get all the canBoQuyens.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CanBoQuyenDTO> findAll() {
        LOG.debug("Request to get all CanBoQuyens");
        return canBoQuyenRepository.findAll().stream().map(canBoQuyenMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one canBoQuyen by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CanBoQuyenDTO> findOne(Long id) {
        LOG.debug("Request to get CanBoQuyen : {}", id);
        return canBoQuyenRepository.findById(id).map(canBoQuyenMapper::toDto);
    }

    /**
     * Delete the canBoQuyen by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete CanBoQuyen : {}", id);
        canBoQuyenRepository.deleteById(id);
    }
}
