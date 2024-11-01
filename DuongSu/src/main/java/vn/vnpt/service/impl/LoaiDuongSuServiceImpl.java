package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.LoaiDuongSu;
import vn.vnpt.repository.LoaiDuongSuRepository;
import vn.vnpt.service.LoaiDuongSuService;
import vn.vnpt.service.dto.LoaiDuongSuDTO;
import vn.vnpt.service.mapper.LoaiDuongSuMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.LoaiDuongSu}.
 */
@Service
@Transactional
public class LoaiDuongSuServiceImpl implements LoaiDuongSuService {

    private static final Logger LOG = LoggerFactory.getLogger(LoaiDuongSuServiceImpl.class);

    private final LoaiDuongSuRepository loaiDuongSuRepository;

    private final LoaiDuongSuMapper loaiDuongSuMapper;

    public LoaiDuongSuServiceImpl(LoaiDuongSuRepository loaiDuongSuRepository, LoaiDuongSuMapper loaiDuongSuMapper) {
        this.loaiDuongSuRepository = loaiDuongSuRepository;
        this.loaiDuongSuMapper = loaiDuongSuMapper;
    }

    @Override
    public LoaiDuongSuDTO save(LoaiDuongSuDTO loaiDuongSuDTO) {
        LOG.debug("Request to save LoaiDuongSu : {}", loaiDuongSuDTO);
        LoaiDuongSu loaiDuongSu = loaiDuongSuMapper.toEntity(loaiDuongSuDTO);
        loaiDuongSu = loaiDuongSuRepository.save(loaiDuongSu);
        return loaiDuongSuMapper.toDto(loaiDuongSu);
    }

    @Override
    public LoaiDuongSuDTO update(LoaiDuongSuDTO loaiDuongSuDTO) {
        LOG.debug("Request to update LoaiDuongSu : {}", loaiDuongSuDTO);
        LoaiDuongSu loaiDuongSu = loaiDuongSuMapper.toEntity(loaiDuongSuDTO);
        loaiDuongSu.setIsPersisted();
        loaiDuongSu = loaiDuongSuRepository.save(loaiDuongSu);
        return loaiDuongSuMapper.toDto(loaiDuongSu);
    }

    @Override
    public Optional<LoaiDuongSuDTO> partialUpdate(LoaiDuongSuDTO loaiDuongSuDTO) {
        LOG.debug("Request to partially update LoaiDuongSu : {}", loaiDuongSuDTO);

        return loaiDuongSuRepository
            .findById(loaiDuongSuDTO.getIdLoaiDuongSu())
            .map(existingLoaiDuongSu -> {
                loaiDuongSuMapper.partialUpdate(existingLoaiDuongSu, loaiDuongSuDTO);

                return existingLoaiDuongSu;
            })
            .map(loaiDuongSuRepository::save)
            .map(loaiDuongSuMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LoaiDuongSuDTO> findOne(String id) {
        LOG.debug("Request to get LoaiDuongSu : {}", id);
        return loaiDuongSuRepository.findById(id).map(loaiDuongSuMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete LoaiDuongSu : {}", id);
        loaiDuongSuRepository.deleteById(id);
    }
}
