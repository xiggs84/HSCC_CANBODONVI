package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.ThongTinCapNhatTaiSan;
import vn.vnpt.repository.ThongTinCapNhatTaiSanRepository;
import vn.vnpt.service.ThongTinCapNhatTaiSanService;
import vn.vnpt.service.dto.ThongTinCapNhatTaiSanDTO;
import vn.vnpt.service.mapper.ThongTinCapNhatTaiSanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.ThongTinCapNhatTaiSan}.
 */
@Service
@Transactional
public class ThongTinCapNhatTaiSanServiceImpl implements ThongTinCapNhatTaiSanService {

    private static final Logger LOG = LoggerFactory.getLogger(ThongTinCapNhatTaiSanServiceImpl.class);

    private final ThongTinCapNhatTaiSanRepository thongTinCapNhatTaiSanRepository;

    private final ThongTinCapNhatTaiSanMapper thongTinCapNhatTaiSanMapper;

    public ThongTinCapNhatTaiSanServiceImpl(
        ThongTinCapNhatTaiSanRepository thongTinCapNhatTaiSanRepository,
        ThongTinCapNhatTaiSanMapper thongTinCapNhatTaiSanMapper
    ) {
        this.thongTinCapNhatTaiSanRepository = thongTinCapNhatTaiSanRepository;
        this.thongTinCapNhatTaiSanMapper = thongTinCapNhatTaiSanMapper;
    }

    @Override
    public ThongTinCapNhatTaiSanDTO save(ThongTinCapNhatTaiSanDTO thongTinCapNhatTaiSanDTO) {
        LOG.debug("Request to save ThongTinCapNhatTaiSan : {}", thongTinCapNhatTaiSanDTO);
        ThongTinCapNhatTaiSan thongTinCapNhatTaiSan = thongTinCapNhatTaiSanMapper.toEntity(thongTinCapNhatTaiSanDTO);
        thongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.save(thongTinCapNhatTaiSan);
        return thongTinCapNhatTaiSanMapper.toDto(thongTinCapNhatTaiSan);
    }

    @Override
    public ThongTinCapNhatTaiSanDTO update(ThongTinCapNhatTaiSanDTO thongTinCapNhatTaiSanDTO) {
        LOG.debug("Request to update ThongTinCapNhatTaiSan : {}", thongTinCapNhatTaiSanDTO);
        ThongTinCapNhatTaiSan thongTinCapNhatTaiSan = thongTinCapNhatTaiSanMapper.toEntity(thongTinCapNhatTaiSanDTO);
        thongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.save(thongTinCapNhatTaiSan);
        return thongTinCapNhatTaiSanMapper.toDto(thongTinCapNhatTaiSan);
    }

    @Override
    public Optional<ThongTinCapNhatTaiSanDTO> partialUpdate(ThongTinCapNhatTaiSanDTO thongTinCapNhatTaiSanDTO) {
        LOG.debug("Request to partially update ThongTinCapNhatTaiSan : {}", thongTinCapNhatTaiSanDTO);

        return thongTinCapNhatTaiSanRepository
            .findById(thongTinCapNhatTaiSanDTO.getIdCapNhat())
            .map(existingThongTinCapNhatTaiSan -> {
                thongTinCapNhatTaiSanMapper.partialUpdate(existingThongTinCapNhatTaiSan, thongTinCapNhatTaiSanDTO);

                return existingThongTinCapNhatTaiSan;
            })
            .map(thongTinCapNhatTaiSanRepository::save)
            .map(thongTinCapNhatTaiSanMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ThongTinCapNhatTaiSanDTO> findOne(Long id) {
        LOG.debug("Request to get ThongTinCapNhatTaiSan : {}", id);
        return thongTinCapNhatTaiSanRepository.findById(id).map(thongTinCapNhatTaiSanMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete ThongTinCapNhatTaiSan : {}", id);
        thongTinCapNhatTaiSanRepository.deleteById(id);
    }
}
