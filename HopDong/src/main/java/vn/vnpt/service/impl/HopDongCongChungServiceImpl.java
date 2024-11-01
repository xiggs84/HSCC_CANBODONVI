package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.vnpt.domain.HopDongCongChung;
import vn.vnpt.repository.HopDongCongChungRepository;
import vn.vnpt.service.HopDongCongChungService;
import vn.vnpt.service.dto.HopDongCongChungDTO;
import vn.vnpt.service.mapper.HopDongCongChungMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.HopDongCongChung}.
 */
@Service
public class HopDongCongChungServiceImpl implements HopDongCongChungService {

    private static final Logger LOG = LoggerFactory.getLogger(HopDongCongChungServiceImpl.class);

    private final HopDongCongChungRepository hopDongCongChungRepository;

    private final HopDongCongChungMapper hopDongCongChungMapper;

    public HopDongCongChungServiceImpl(
        HopDongCongChungRepository hopDongCongChungRepository,
        HopDongCongChungMapper hopDongCongChungMapper
    ) {
        this.hopDongCongChungRepository = hopDongCongChungRepository;
        this.hopDongCongChungMapper = hopDongCongChungMapper;
    }

    @Override
    public HopDongCongChungDTO save(HopDongCongChungDTO hopDongCongChungDTO) {
        LOG.debug("Request to save HopDongCongChung : {}", hopDongCongChungDTO);
        HopDongCongChung hopDongCongChung = hopDongCongChungMapper.toEntity(hopDongCongChungDTO);
        hopDongCongChung = hopDongCongChungRepository.save(hopDongCongChung);
        return hopDongCongChungMapper.toDto(hopDongCongChung);
    }

    @Override
    public HopDongCongChungDTO update(HopDongCongChungDTO hopDongCongChungDTO) {
        LOG.debug("Request to update HopDongCongChung : {}", hopDongCongChungDTO);
        HopDongCongChung hopDongCongChung = hopDongCongChungMapper.toEntity(hopDongCongChungDTO);
        hopDongCongChung = hopDongCongChungRepository.save(hopDongCongChung);
        return hopDongCongChungMapper.toDto(hopDongCongChung);
    }

    @Override
    public Optional<HopDongCongChungDTO> partialUpdate(HopDongCongChungDTO hopDongCongChungDTO) {
        LOG.debug("Request to partially update HopDongCongChung : {}", hopDongCongChungDTO);

        return hopDongCongChungRepository
            .findById(hopDongCongChungDTO.getId())
            .map(existingHopDongCongChung -> {
                hopDongCongChungMapper.partialUpdate(existingHopDongCongChung, hopDongCongChungDTO);

                return existingHopDongCongChung;
            })
            .map(hopDongCongChungRepository::save)
            .map(hopDongCongChungMapper::toDto);
    }

    @Override
    public Page<HopDongCongChungDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all HopDongCongChungs");
        return hopDongCongChungRepository.findAll(pageable).map(hopDongCongChungMapper::toDto);
    }

    @Override
    public Optional<HopDongCongChungDTO> findOne(String id) {
        LOG.debug("Request to get HopDongCongChung : {}", id);
        return hopDongCongChungRepository.findById(id).map(hopDongCongChungMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete HopDongCongChung : {}", id);
        hopDongCongChungRepository.deleteById(id);
    }
}
