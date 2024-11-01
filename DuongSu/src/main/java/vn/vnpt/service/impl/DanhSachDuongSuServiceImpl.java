package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhSachDuongSu;
import vn.vnpt.repository.DanhSachDuongSuRepository;
import vn.vnpt.service.DanhSachDuongSuService;
import vn.vnpt.service.dto.DanhSachDuongSuDTO;
import vn.vnpt.service.mapper.DanhSachDuongSuMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhSachDuongSu}.
 */
@Service
@Transactional
public class DanhSachDuongSuServiceImpl implements DanhSachDuongSuService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhSachDuongSuServiceImpl.class);

    private final DanhSachDuongSuRepository danhSachDuongSuRepository;

    private final DanhSachDuongSuMapper danhSachDuongSuMapper;

    public DanhSachDuongSuServiceImpl(DanhSachDuongSuRepository danhSachDuongSuRepository, DanhSachDuongSuMapper danhSachDuongSuMapper) {
        this.danhSachDuongSuRepository = danhSachDuongSuRepository;
        this.danhSachDuongSuMapper = danhSachDuongSuMapper;
    }

    @Override
    public DanhSachDuongSuDTO save(DanhSachDuongSuDTO danhSachDuongSuDTO) {
        LOG.debug("Request to save DanhSachDuongSu : {}", danhSachDuongSuDTO);
        DanhSachDuongSu danhSachDuongSu = danhSachDuongSuMapper.toEntity(danhSachDuongSuDTO);
        danhSachDuongSu = danhSachDuongSuRepository.save(danhSachDuongSu);
        return danhSachDuongSuMapper.toDto(danhSachDuongSu);
    }

    @Override
    public DanhSachDuongSuDTO update(DanhSachDuongSuDTO danhSachDuongSuDTO) {
        LOG.debug("Request to update DanhSachDuongSu : {}", danhSachDuongSuDTO);
        DanhSachDuongSu danhSachDuongSu = danhSachDuongSuMapper.toEntity(danhSachDuongSuDTO);
        danhSachDuongSu = danhSachDuongSuRepository.save(danhSachDuongSu);
        return danhSachDuongSuMapper.toDto(danhSachDuongSu);
    }

    @Override
    public Optional<DanhSachDuongSuDTO> partialUpdate(DanhSachDuongSuDTO danhSachDuongSuDTO) {
        LOG.debug("Request to partially update DanhSachDuongSu : {}", danhSachDuongSuDTO);

        return danhSachDuongSuRepository
            .findById(danhSachDuongSuDTO.getId())
            .map(existingDanhSachDuongSu -> {
                danhSachDuongSuMapper.partialUpdate(existingDanhSachDuongSu, danhSachDuongSuDTO);

                return existingDanhSachDuongSu;
            })
            .map(danhSachDuongSuRepository::save)
            .map(danhSachDuongSuMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DanhSachDuongSuDTO> findOne(Long id) {
        LOG.debug("Request to get DanhSachDuongSu : {}", id);
        return danhSachDuongSuRepository.findById(id).map(danhSachDuongSuMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete DanhSachDuongSu : {}", id);
        danhSachDuongSuRepository.deleteById(id);
    }
}
