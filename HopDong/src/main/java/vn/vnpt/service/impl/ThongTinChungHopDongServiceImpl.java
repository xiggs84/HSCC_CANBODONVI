package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.vnpt.domain.ThongTinChungHopDong;
import vn.vnpt.repository.ThongTinChungHopDongRepository;
import vn.vnpt.service.ThongTinChungHopDongService;
import vn.vnpt.service.dto.ThongTinChungHopDongDTO;
import vn.vnpt.service.mapper.ThongTinChungHopDongMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.ThongTinChungHopDong}.
 */
@Service
public class ThongTinChungHopDongServiceImpl implements ThongTinChungHopDongService {

    private static final Logger LOG = LoggerFactory.getLogger(ThongTinChungHopDongServiceImpl.class);

    private final ThongTinChungHopDongRepository thongTinChungHopDongRepository;

    private final ThongTinChungHopDongMapper thongTinChungHopDongMapper;

    public ThongTinChungHopDongServiceImpl(
        ThongTinChungHopDongRepository thongTinChungHopDongRepository,
        ThongTinChungHopDongMapper thongTinChungHopDongMapper
    ) {
        this.thongTinChungHopDongRepository = thongTinChungHopDongRepository;
        this.thongTinChungHopDongMapper = thongTinChungHopDongMapper;
    }

    @Override
    public ThongTinChungHopDongDTO save(ThongTinChungHopDongDTO thongTinChungHopDongDTO) {
        LOG.debug("Request to save ThongTinChungHopDong : {}", thongTinChungHopDongDTO);
        ThongTinChungHopDong thongTinChungHopDong = thongTinChungHopDongMapper.toEntity(thongTinChungHopDongDTO);
        thongTinChungHopDong = thongTinChungHopDongRepository.save(thongTinChungHopDong);
        return thongTinChungHopDongMapper.toDto(thongTinChungHopDong);
    }

    @Override
    public ThongTinChungHopDongDTO update(ThongTinChungHopDongDTO thongTinChungHopDongDTO) {
        LOG.debug("Request to update ThongTinChungHopDong : {}", thongTinChungHopDongDTO);
        ThongTinChungHopDong thongTinChungHopDong = thongTinChungHopDongMapper.toEntity(thongTinChungHopDongDTO);
        thongTinChungHopDong = thongTinChungHopDongRepository.save(thongTinChungHopDong);
        return thongTinChungHopDongMapper.toDto(thongTinChungHopDong);
    }

    @Override
    public Optional<ThongTinChungHopDongDTO> partialUpdate(ThongTinChungHopDongDTO thongTinChungHopDongDTO) {
        LOG.debug("Request to partially update ThongTinChungHopDong : {}", thongTinChungHopDongDTO);

        return thongTinChungHopDongRepository
            .findById(thongTinChungHopDongDTO.getId())
            .map(existingThongTinChungHopDong -> {
                thongTinChungHopDongMapper.partialUpdate(existingThongTinChungHopDong, thongTinChungHopDongDTO);

                return existingThongTinChungHopDong;
            })
            .map(thongTinChungHopDongRepository::save)
            .map(thongTinChungHopDongMapper::toDto);
    }

    @Override
    public Page<ThongTinChungHopDongDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all ThongTinChungHopDongs");
        return thongTinChungHopDongRepository.findAll(pageable).map(thongTinChungHopDongMapper::toDto);
    }

    @Override
    public Optional<ThongTinChungHopDongDTO> findOne(String id) {
        LOG.debug("Request to get ThongTinChungHopDong : {}", id);
        return thongTinChungHopDongRepository.findById(id).map(thongTinChungHopDongMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete ThongTinChungHopDong : {}", id);
        thongTinChungHopDongRepository.deleteById(id);
    }
}
