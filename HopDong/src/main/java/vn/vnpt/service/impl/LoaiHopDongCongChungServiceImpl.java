package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.vnpt.domain.LoaiHopDongCongChung;
import vn.vnpt.repository.LoaiHopDongCongChungRepository;
import vn.vnpt.service.LoaiHopDongCongChungService;
import vn.vnpt.service.dto.LoaiHopDongCongChungDTO;
import vn.vnpt.service.mapper.LoaiHopDongCongChungMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.LoaiHopDongCongChung}.
 */
@Service
public class LoaiHopDongCongChungServiceImpl implements LoaiHopDongCongChungService {

    private static final Logger LOG = LoggerFactory.getLogger(LoaiHopDongCongChungServiceImpl.class);

    private final LoaiHopDongCongChungRepository loaiHopDongCongChungRepository;

    private final LoaiHopDongCongChungMapper loaiHopDongCongChungMapper;

    public LoaiHopDongCongChungServiceImpl(
        LoaiHopDongCongChungRepository loaiHopDongCongChungRepository,
        LoaiHopDongCongChungMapper loaiHopDongCongChungMapper
    ) {
        this.loaiHopDongCongChungRepository = loaiHopDongCongChungRepository;
        this.loaiHopDongCongChungMapper = loaiHopDongCongChungMapper;
    }

    @Override
    public LoaiHopDongCongChungDTO save(LoaiHopDongCongChungDTO loaiHopDongCongChungDTO) {
        LOG.debug("Request to save LoaiHopDongCongChung : {}", loaiHopDongCongChungDTO);
        LoaiHopDongCongChung loaiHopDongCongChung = loaiHopDongCongChungMapper.toEntity(loaiHopDongCongChungDTO);
        loaiHopDongCongChung = loaiHopDongCongChungRepository.save(loaiHopDongCongChung);
        return loaiHopDongCongChungMapper.toDto(loaiHopDongCongChung);
    }

    @Override
    public LoaiHopDongCongChungDTO update(LoaiHopDongCongChungDTO loaiHopDongCongChungDTO) {
        LOG.debug("Request to update LoaiHopDongCongChung : {}", loaiHopDongCongChungDTO);
        LoaiHopDongCongChung loaiHopDongCongChung = loaiHopDongCongChungMapper.toEntity(loaiHopDongCongChungDTO);
        loaiHopDongCongChung = loaiHopDongCongChungRepository.save(loaiHopDongCongChung);
        return loaiHopDongCongChungMapper.toDto(loaiHopDongCongChung);
    }

    @Override
    public Optional<LoaiHopDongCongChungDTO> partialUpdate(LoaiHopDongCongChungDTO loaiHopDongCongChungDTO) {
        LOG.debug("Request to partially update LoaiHopDongCongChung : {}", loaiHopDongCongChungDTO);

        return loaiHopDongCongChungRepository
            .findById(loaiHopDongCongChungDTO.getId())
            .map(existingLoaiHopDongCongChung -> {
                loaiHopDongCongChungMapper.partialUpdate(existingLoaiHopDongCongChung, loaiHopDongCongChungDTO);

                return existingLoaiHopDongCongChung;
            })
            .map(loaiHopDongCongChungRepository::save)
            .map(loaiHopDongCongChungMapper::toDto);
    }

    @Override
    public Page<LoaiHopDongCongChungDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all LoaiHopDongCongChungs");
        return loaiHopDongCongChungRepository.findAll(pageable).map(loaiHopDongCongChungMapper::toDto);
    }

    @Override
    public Optional<LoaiHopDongCongChungDTO> findOne(String id) {
        LOG.debug("Request to get LoaiHopDongCongChung : {}", id);
        return loaiHopDongCongChungRepository.findById(id).map(loaiHopDongCongChungMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete LoaiHopDongCongChung : {}", id);
        loaiHopDongCongChungRepository.deleteById(id);
    }
}
