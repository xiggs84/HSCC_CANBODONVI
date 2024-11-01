package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.vnpt.domain.DanhSachHopDong;
import vn.vnpt.repository.DanhSachHopDongRepository;
import vn.vnpt.service.DanhSachHopDongService;
import vn.vnpt.service.dto.DanhSachHopDongDTO;
import vn.vnpt.service.mapper.DanhSachHopDongMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhSachHopDong}.
 */
@Service
public class DanhSachHopDongServiceImpl implements DanhSachHopDongService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhSachHopDongServiceImpl.class);

    private final DanhSachHopDongRepository danhSachHopDongRepository;

    private final DanhSachHopDongMapper danhSachHopDongMapper;

    public DanhSachHopDongServiceImpl(DanhSachHopDongRepository danhSachHopDongRepository, DanhSachHopDongMapper danhSachHopDongMapper) {
        this.danhSachHopDongRepository = danhSachHopDongRepository;
        this.danhSachHopDongMapper = danhSachHopDongMapper;
    }

    @Override
    public DanhSachHopDongDTO save(DanhSachHopDongDTO danhSachHopDongDTO) {
        LOG.debug("Request to save DanhSachHopDong : {}", danhSachHopDongDTO);
        DanhSachHopDong danhSachHopDong = danhSachHopDongMapper.toEntity(danhSachHopDongDTO);
        danhSachHopDong = danhSachHopDongRepository.save(danhSachHopDong);
        return danhSachHopDongMapper.toDto(danhSachHopDong);
    }

    @Override
    public DanhSachHopDongDTO update(DanhSachHopDongDTO danhSachHopDongDTO) {
        LOG.debug("Request to update DanhSachHopDong : {}", danhSachHopDongDTO);
        DanhSachHopDong danhSachHopDong = danhSachHopDongMapper.toEntity(danhSachHopDongDTO);
        danhSachHopDong = danhSachHopDongRepository.save(danhSachHopDong);
        return danhSachHopDongMapper.toDto(danhSachHopDong);
    }

    @Override
    public Optional<DanhSachHopDongDTO> partialUpdate(DanhSachHopDongDTO danhSachHopDongDTO) {
        LOG.debug("Request to partially update DanhSachHopDong : {}", danhSachHopDongDTO);

        return danhSachHopDongRepository
            .findById(danhSachHopDongDTO.getId())
            .map(existingDanhSachHopDong -> {
                danhSachHopDongMapper.partialUpdate(existingDanhSachHopDong, danhSachHopDongDTO);

                return existingDanhSachHopDong;
            })
            .map(danhSachHopDongRepository::save)
            .map(danhSachHopDongMapper::toDto);
    }

    @Override
    public Page<DanhSachHopDongDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all DanhSachHopDongs");
        return danhSachHopDongRepository.findAll(pageable).map(danhSachHopDongMapper::toDto);
    }

    @Override
    public Optional<DanhSachHopDongDTO> findOne(String id) {
        LOG.debug("Request to get DanhSachHopDong : {}", id);
        return danhSachHopDongRepository.findById(id).map(danhSachHopDongMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete DanhSachHopDong : {}", id);
        danhSachHopDongRepository.deleteById(id);
    }
}
