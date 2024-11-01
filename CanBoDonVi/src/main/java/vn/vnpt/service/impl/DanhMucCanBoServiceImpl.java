package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import vn.vnpt.domain.DanhMucCanBo;
import vn.vnpt.repository.DanhMucCanBoRepository;
import vn.vnpt.service.DanhMucCanBoService;
import vn.vnpt.service.dto.DanhMucCanBoDTO;
import vn.vnpt.service.dto.UserDTO;
import vn.vnpt.service.mapper.DanhMucCanBoMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucCanBo}.
 */
@Service
@Transactional
public class DanhMucCanBoServiceImpl implements DanhMucCanBoService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucCanBoServiceImpl.class);

    private final DanhMucCanBoRepository danhMucCanBoRepository;

    private final DanhMucCanBoMapper danhMucCanBoMapper;

    public DanhMucCanBoServiceImpl(DanhMucCanBoRepository danhMucCanBoRepository, DanhMucCanBoMapper danhMucCanBoMapper) {
        this.danhMucCanBoRepository = danhMucCanBoRepository;
        this.danhMucCanBoMapper = danhMucCanBoMapper;
    }

    @Override
    public DanhMucCanBoDTO save(DanhMucCanBoDTO danhMucCanBoDTO) {
        LOG.debug("Request to save DanhMucCanBo : {}", danhMucCanBoDTO);
        DanhMucCanBo danhMucCanBo = danhMucCanBoMapper.toEntity(danhMucCanBoDTO);
        danhMucCanBo = danhMucCanBoRepository.save(danhMucCanBo);
        return danhMucCanBoMapper.toDto(danhMucCanBo);
    }

    @Override
    public DanhMucCanBoDTO update(DanhMucCanBoDTO danhMucCanBoDTO) {
        LOG.debug("Request to update DanhMucCanBo : {}", danhMucCanBoDTO);
        DanhMucCanBo danhMucCanBo = danhMucCanBoMapper.toEntity(danhMucCanBoDTO);
        danhMucCanBo = danhMucCanBoRepository.save(danhMucCanBo);
        return danhMucCanBoMapper.toDto(danhMucCanBo);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public Optional<DanhMucCanBoDTO> findOne(Long id) {
        LOG.debug("Request to get DanhMucCanBo : {}", id);
        return danhMucCanBoRepository.findById(id).map(danhMucCanBoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete DanhMucCanBo : {}", id);
        danhMucCanBoRepository.deleteById(id);
    }
}
