package vn.vnpt.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.vnpt.domain.DanhMucLoaiVanBan;
import vn.vnpt.repository.DanhMucLoaiVanBanRepository;
import vn.vnpt.service.DanhMucLoaiVanBanService;
import vn.vnpt.service.dto.DanhMucLoaiVanBanDTO;
import vn.vnpt.service.mapper.DanhMucLoaiVanBanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucLoaiVanBan}.
 */
@Service
public class DanhMucLoaiVanBanServiceImpl implements DanhMucLoaiVanBanService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucLoaiVanBanServiceImpl.class);

    private final DanhMucLoaiVanBanRepository danhMucLoaiVanBanRepository;

    private final DanhMucLoaiVanBanMapper danhMucLoaiVanBanMapper;

    public DanhMucLoaiVanBanServiceImpl(
        DanhMucLoaiVanBanRepository danhMucLoaiVanBanRepository,
        DanhMucLoaiVanBanMapper danhMucLoaiVanBanMapper
    ) {
        this.danhMucLoaiVanBanRepository = danhMucLoaiVanBanRepository;
        this.danhMucLoaiVanBanMapper = danhMucLoaiVanBanMapper;
    }

    @Override
    public DanhMucLoaiVanBanDTO save(DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO) {
        LOG.debug("Request to save DanhMucLoaiVanBan : {}", danhMucLoaiVanBanDTO);
        DanhMucLoaiVanBan danhMucLoaiVanBan = danhMucLoaiVanBanMapper.toEntity(danhMucLoaiVanBanDTO);
        danhMucLoaiVanBan = danhMucLoaiVanBanRepository.save(danhMucLoaiVanBan);
        return danhMucLoaiVanBanMapper.toDto(danhMucLoaiVanBan);
    }

    @Override
    public DanhMucLoaiVanBanDTO update(DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO) {
        LOG.debug("Request to update DanhMucLoaiVanBan : {}", danhMucLoaiVanBanDTO);
        DanhMucLoaiVanBan danhMucLoaiVanBan = danhMucLoaiVanBanMapper.toEntity(danhMucLoaiVanBanDTO);
        danhMucLoaiVanBan = danhMucLoaiVanBanRepository.save(danhMucLoaiVanBan);
        return danhMucLoaiVanBanMapper.toDto(danhMucLoaiVanBan);
    }

    @Override
    public Optional<DanhMucLoaiVanBanDTO> partialUpdate(DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO) {
        LOG.debug("Request to partially update DanhMucLoaiVanBan : {}", danhMucLoaiVanBanDTO);

        return danhMucLoaiVanBanRepository
            .findById(danhMucLoaiVanBanDTO.getId())
            .map(existingDanhMucLoaiVanBan -> {
                danhMucLoaiVanBanMapper.partialUpdate(existingDanhMucLoaiVanBan, danhMucLoaiVanBanDTO);

                return existingDanhMucLoaiVanBan;
            })
            .map(danhMucLoaiVanBanRepository::save)
            .map(danhMucLoaiVanBanMapper::toDto);
    }

    @Override
    public Page<DanhMucLoaiVanBanDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all DanhMucLoaiVanBans");
        return danhMucLoaiVanBanRepository.findAll(pageable).map(danhMucLoaiVanBanMapper::toDto);
    }

    @Override
    public Optional<DanhMucLoaiVanBanDTO> findOne(String id) {
        LOG.debug("Request to get DanhMucLoaiVanBan : {}", id);
        return danhMucLoaiVanBanRepository.findById(id).map(danhMucLoaiVanBanMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete DanhMucLoaiVanBan : {}", id);
        danhMucLoaiVanBanRepository.deleteById(id);
    }

    @Override
    public List<DanhMucLoaiVanBanDTO> findByIdLoaiHopDong(String idLoaiHopDong) {
        LOG.debug("Request to find DanhMucLoaiVanBan by idLoaiHopDong : {}", idLoaiHopDong);
        return danhMucLoaiVanBanRepository.findByIdLoaiHopDong(idLoaiHopDong)
            .stream()
            .map(danhMucLoaiVanBanMapper::toDto)
            .collect(Collectors.toList());
    }
}
