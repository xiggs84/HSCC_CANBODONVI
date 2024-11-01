package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.vnpt.domain.DanhMucNhomHopDong;
import vn.vnpt.repository.DanhMucNhomHopDongRepository;
import vn.vnpt.service.DanhMucNhomHopDongService;
import vn.vnpt.service.dto.DanhMucNhomHopDongDTO;
import vn.vnpt.service.mapper.DanhMucNhomHopDongMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucNhomHopDong}.
 */
@Service
public class DanhMucNhomHopDongServiceImpl implements DanhMucNhomHopDongService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucNhomHopDongServiceImpl.class);

    private final DanhMucNhomHopDongRepository danhMucNhomHopDongRepository;

    private final DanhMucNhomHopDongMapper danhMucNhomHopDongMapper;

    public DanhMucNhomHopDongServiceImpl(
        DanhMucNhomHopDongRepository danhMucNhomHopDongRepository,
        DanhMucNhomHopDongMapper danhMucNhomHopDongMapper
    ) {
        this.danhMucNhomHopDongRepository = danhMucNhomHopDongRepository;
        this.danhMucNhomHopDongMapper = danhMucNhomHopDongMapper;
    }

    @Override
    public DanhMucNhomHopDongDTO save(DanhMucNhomHopDongDTO danhMucNhomHopDongDTO) {
        LOG.debug("Request to save DanhMucNhomHopDong : {}", danhMucNhomHopDongDTO);
        DanhMucNhomHopDong danhMucNhomHopDong = danhMucNhomHopDongMapper.toEntity(danhMucNhomHopDongDTO);
        danhMucNhomHopDong = danhMucNhomHopDongRepository.save(danhMucNhomHopDong);
        return danhMucNhomHopDongMapper.toDto(danhMucNhomHopDong);
    }

    @Override
    public DanhMucNhomHopDongDTO update(DanhMucNhomHopDongDTO danhMucNhomHopDongDTO) {
        LOG.debug("Request to update DanhMucNhomHopDong : {}", danhMucNhomHopDongDTO);
        DanhMucNhomHopDong danhMucNhomHopDong = danhMucNhomHopDongMapper.toEntity(danhMucNhomHopDongDTO);
        danhMucNhomHopDong = danhMucNhomHopDongRepository.save(danhMucNhomHopDong);
        return danhMucNhomHopDongMapper.toDto(danhMucNhomHopDong);
    }

    @Override
    public Optional<DanhMucNhomHopDongDTO> partialUpdate(DanhMucNhomHopDongDTO danhMucNhomHopDongDTO) {
        LOG.debug("Request to partially update DanhMucNhomHopDong : {}", danhMucNhomHopDongDTO);

        return danhMucNhomHopDongRepository
            .findById(danhMucNhomHopDongDTO.getId())
            .map(existingDanhMucNhomHopDong -> {
                danhMucNhomHopDongMapper.partialUpdate(existingDanhMucNhomHopDong, danhMucNhomHopDongDTO);

                return existingDanhMucNhomHopDong;
            })
            .map(danhMucNhomHopDongRepository::save)
            .map(danhMucNhomHopDongMapper::toDto);
    }

    @Override
    public Page<DanhMucNhomHopDongDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all DanhMucNhomHopDongs");
        return danhMucNhomHopDongRepository.findAll(pageable).map(danhMucNhomHopDongMapper::toDto);
    }

    @Override
    public Optional<DanhMucNhomHopDongDTO> findOne(String id) {
        LOG.debug("Request to get DanhMucNhomHopDong : {}", id);
        return danhMucNhomHopDongRepository.findById(id).map(danhMucNhomHopDongMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete DanhMucNhomHopDong : {}", id);
        danhMucNhomHopDongRepository.deleteById(id);
    }

    @Override
    public Optional<String> findDienGiaiById(String id) {
        LOG.debug("Request to get dienGiai of DanhMucNhomHopDong : {}", id);
        return danhMucNhomHopDongRepository.findById(id)
            .map(DanhMucNhomHopDong::getDienGiai); // Giả định rằng bạn có phương thức getDienGiai trong entity
    }
}
