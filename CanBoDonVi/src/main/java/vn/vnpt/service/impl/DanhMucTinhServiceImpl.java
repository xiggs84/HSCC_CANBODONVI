package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucTinh;
import vn.vnpt.repository.DanhMucTinhRepository;
import vn.vnpt.service.DanhMucTinhService;
import vn.vnpt.service.dto.DanhMucTinhDTO;
import vn.vnpt.service.mapper.DanhMucTinhMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucTinh}.
 */
@Service
@Transactional
public class DanhMucTinhServiceImpl implements DanhMucTinhService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucTinhServiceImpl.class);

    private final DanhMucTinhRepository danhMucTinhRepository;

    private final DanhMucTinhMapper danhMucTinhMapper;

    public DanhMucTinhServiceImpl(DanhMucTinhRepository danhMucTinhRepository, DanhMucTinhMapper danhMucTinhMapper) {
        this.danhMucTinhRepository = danhMucTinhRepository;
        this.danhMucTinhMapper = danhMucTinhMapper;
    }

    @Override
    public DanhMucTinhDTO save(DanhMucTinhDTO danhMucTinhDTO) {
        LOG.debug("Request to save DanhMucTinh : {}", danhMucTinhDTO);
        DanhMucTinh danhMucTinh = danhMucTinhMapper.toEntity(danhMucTinhDTO);
        danhMucTinh = danhMucTinhRepository.save(danhMucTinh);
        return danhMucTinhMapper.toDto(danhMucTinh);
    }

    @Override
    public DanhMucTinhDTO update(DanhMucTinhDTO danhMucTinhDTO) {
        LOG.debug("Request to update DanhMucTinh : {}", danhMucTinhDTO);
        DanhMucTinh danhMucTinh = danhMucTinhMapper.toEntity(danhMucTinhDTO);
        danhMucTinh.setIsPersisted();
        danhMucTinh = danhMucTinhRepository.save(danhMucTinh);
        return danhMucTinhMapper.toDto(danhMucTinh);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public Optional<DanhMucTinhDTO> findOne(String id) {
        LOG.debug("Request to get DanhMucTinh : {}", id);
        return danhMucTinhRepository.findById(id).map(danhMucTinhMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete DanhMucTinh : {}", id);
        danhMucTinhRepository.deleteById(id);
    }
}
