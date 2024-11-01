package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.vnpt.domain.LoaiChungThuc;
import vn.vnpt.repository.LoaiChungThucRepository;
import vn.vnpt.service.LoaiChungThucService;
import vn.vnpt.service.dto.LoaiChungThucDTO;
import vn.vnpt.service.mapper.LoaiChungThucMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.LoaiChungThuc}.
 */
@Service
public class LoaiChungThucServiceImpl implements LoaiChungThucService {

    private static final Logger LOG = LoggerFactory.getLogger(LoaiChungThucServiceImpl.class);

    private final LoaiChungThucRepository loaiChungThucRepository;

    private final LoaiChungThucMapper loaiChungThucMapper;

    public LoaiChungThucServiceImpl(LoaiChungThucRepository loaiChungThucRepository, LoaiChungThucMapper loaiChungThucMapper) {
        this.loaiChungThucRepository = loaiChungThucRepository;
        this.loaiChungThucMapper = loaiChungThucMapper;
    }

    @Override
    public LoaiChungThucDTO save(LoaiChungThucDTO loaiChungThucDTO) {
        LOG.debug("Request to save LoaiChungThuc : {}", loaiChungThucDTO);
        LoaiChungThuc loaiChungThuc = loaiChungThucMapper.toEntity(loaiChungThucDTO);
        loaiChungThuc = loaiChungThucRepository.save(loaiChungThuc);
        return loaiChungThucMapper.toDto(loaiChungThuc);
    }

    @Override
    public LoaiChungThucDTO update(LoaiChungThucDTO loaiChungThucDTO) {
        LOG.debug("Request to update LoaiChungThuc : {}", loaiChungThucDTO);
        LoaiChungThuc loaiChungThuc = loaiChungThucMapper.toEntity(loaiChungThucDTO);
        loaiChungThuc = loaiChungThucRepository.save(loaiChungThuc);
        return loaiChungThucMapper.toDto(loaiChungThuc);
    }

    @Override
    public Optional<LoaiChungThucDTO> partialUpdate(LoaiChungThucDTO loaiChungThucDTO) {
        LOG.debug("Request to partially update LoaiChungThuc : {}", loaiChungThucDTO);

        return loaiChungThucRepository
            .findById(loaiChungThucDTO.getId())
            .map(existingLoaiChungThuc -> {
                loaiChungThucMapper.partialUpdate(existingLoaiChungThuc, loaiChungThucDTO);

                return existingLoaiChungThuc;
            })
            .map(loaiChungThucRepository::save)
            .map(loaiChungThucMapper::toDto);
    }

    @Override
    public Page<LoaiChungThucDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all LoaiChungThucs");
        return loaiChungThucRepository.findAll(pageable).map(loaiChungThucMapper::toDto);
    }

    @Override
    public Optional<LoaiChungThucDTO> findOne(String id) {
        LOG.debug("Request to get LoaiChungThuc : {}", id);
        return loaiChungThucRepository.findById(id).map(loaiChungThucMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete LoaiChungThuc : {}", id);
        loaiChungThucRepository.deleteById(id);
    }
}
