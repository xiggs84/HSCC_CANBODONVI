package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.ThongTinCapNhatDuongSu;
import vn.vnpt.repository.ThongTinCapNhatDuongSuRepository;
import vn.vnpt.service.ThongTinCapNhatDuongSuService;
import vn.vnpt.service.dto.ThongTinCapNhatDuongSuDTO;
import vn.vnpt.service.mapper.ThongTinCapNhatDuongSuMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.ThongTinCapNhatDuongSu}.
 */
@Service
@Transactional
public class ThongTinCapNhatDuongSuServiceImpl implements ThongTinCapNhatDuongSuService {

    private static final Logger LOG = LoggerFactory.getLogger(ThongTinCapNhatDuongSuServiceImpl.class);

    private final ThongTinCapNhatDuongSuRepository thongTinCapNhatDuongSuRepository;

    private final ThongTinCapNhatDuongSuMapper thongTinCapNhatDuongSuMapper;

    public ThongTinCapNhatDuongSuServiceImpl(
        ThongTinCapNhatDuongSuRepository thongTinCapNhatDuongSuRepository,
        ThongTinCapNhatDuongSuMapper thongTinCapNhatDuongSuMapper
    ) {
        this.thongTinCapNhatDuongSuRepository = thongTinCapNhatDuongSuRepository;
        this.thongTinCapNhatDuongSuMapper = thongTinCapNhatDuongSuMapper;
    }

    @Override
    public ThongTinCapNhatDuongSuDTO save(ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO) {
        LOG.debug("Request to save ThongTinCapNhatDuongSu : {}", thongTinCapNhatDuongSuDTO);
        ThongTinCapNhatDuongSu thongTinCapNhatDuongSu = thongTinCapNhatDuongSuMapper.toEntity(thongTinCapNhatDuongSuDTO);
        thongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.save(thongTinCapNhatDuongSu);
        return thongTinCapNhatDuongSuMapper.toDto(thongTinCapNhatDuongSu);
    }

    @Override
    public ThongTinCapNhatDuongSuDTO update(ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO) {
        LOG.debug("Request to update ThongTinCapNhatDuongSu : {}", thongTinCapNhatDuongSuDTO);
        ThongTinCapNhatDuongSu thongTinCapNhatDuongSu = thongTinCapNhatDuongSuMapper.toEntity(thongTinCapNhatDuongSuDTO);
        thongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.save(thongTinCapNhatDuongSu);
        return thongTinCapNhatDuongSuMapper.toDto(thongTinCapNhatDuongSu);
    }

    @Override
    public Optional<ThongTinCapNhatDuongSuDTO> partialUpdate(ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO) {
        LOG.debug("Request to partially update ThongTinCapNhatDuongSu : {}", thongTinCapNhatDuongSuDTO);

        return thongTinCapNhatDuongSuRepository
            .findById(thongTinCapNhatDuongSuDTO.getIdCapNhat())
            .map(existingThongTinCapNhatDuongSu -> {
                thongTinCapNhatDuongSuMapper.partialUpdate(existingThongTinCapNhatDuongSu, thongTinCapNhatDuongSuDTO);

                return existingThongTinCapNhatDuongSu;
            })
            .map(thongTinCapNhatDuongSuRepository::save)
            .map(thongTinCapNhatDuongSuMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ThongTinCapNhatDuongSuDTO> findOne(Long id) {
        LOG.debug("Request to get ThongTinCapNhatDuongSu : {}", id);
        return thongTinCapNhatDuongSuRepository.findById(id).map(thongTinCapNhatDuongSuMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete ThongTinCapNhatDuongSu : {}", id);
        thongTinCapNhatDuongSuRepository.deleteById(id);
    }
}
