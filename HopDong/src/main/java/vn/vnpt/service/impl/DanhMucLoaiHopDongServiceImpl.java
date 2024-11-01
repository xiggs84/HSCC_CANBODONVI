package vn.vnpt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.vnpt.domain.DanhMucLoaiHopDong;
import vn.vnpt.repository.DanhMucLoaiHopDongRepository;
import vn.vnpt.service.DanhMucLoaiHopDongService;
import vn.vnpt.service.dto.DanhMucLoaiHopDongDTO;
import vn.vnpt.service.mapper.DanhMucLoaiHopDongMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucLoaiHopDong}.
 */
@Service
public class DanhMucLoaiHopDongServiceImpl implements DanhMucLoaiHopDongService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucLoaiHopDongServiceImpl.class);

    private final DanhMucLoaiHopDongRepository danhMucLoaiHopDongRepository;

    private final DanhMucLoaiHopDongMapper danhMucLoaiHopDongMapper;

    public DanhMucLoaiHopDongServiceImpl(
        DanhMucLoaiHopDongRepository danhMucLoaiHopDongRepository,
        DanhMucLoaiHopDongMapper danhMucLoaiHopDongMapper
    ) {
        this.danhMucLoaiHopDongRepository = danhMucLoaiHopDongRepository;
        this.danhMucLoaiHopDongMapper = danhMucLoaiHopDongMapper;
    }

    @Override
    public DanhMucLoaiHopDongDTO save(DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO) {
        LOG.debug("Request to save DanhMucLoaiHopDong : {}", danhMucLoaiHopDongDTO);
        DanhMucLoaiHopDong danhMucLoaiHopDong = danhMucLoaiHopDongMapper.toEntity(danhMucLoaiHopDongDTO);
        danhMucLoaiHopDong = danhMucLoaiHopDongRepository.save(danhMucLoaiHopDong);
        return danhMucLoaiHopDongMapper.toDto(danhMucLoaiHopDong);
    }

    @Override
    public DanhMucLoaiHopDongDTO update(DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO) {
        LOG.debug("Request to update DanhMucLoaiHopDong : {}", danhMucLoaiHopDongDTO);
        DanhMucLoaiHopDong danhMucLoaiHopDong = danhMucLoaiHopDongMapper.toEntity(danhMucLoaiHopDongDTO);
        danhMucLoaiHopDong = danhMucLoaiHopDongRepository.save(danhMucLoaiHopDong);
        return danhMucLoaiHopDongMapper.toDto(danhMucLoaiHopDong);
    }

    @Override
    public Optional<DanhMucLoaiHopDongDTO> partialUpdate(DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO) {
        LOG.debug("Request to partially update DanhMucLoaiHopDong : {}", danhMucLoaiHopDongDTO);

        return danhMucLoaiHopDongRepository
            .findById(danhMucLoaiHopDongDTO.getId())
            .map(existingDanhMucLoaiHopDong -> {
                danhMucLoaiHopDongMapper.partialUpdate(existingDanhMucLoaiHopDong, danhMucLoaiHopDongDTO);

                return existingDanhMucLoaiHopDong;
            })
            .map(danhMucLoaiHopDongRepository::save)
            .map(danhMucLoaiHopDongMapper::toDto);
    }

    @Override
    public Page<DanhMucLoaiHopDongDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all DanhMucLoaiHopDongs");
        return danhMucLoaiHopDongRepository.findAll(pageable).map(danhMucLoaiHopDongMapper::toDto);
    }

    @Override
    public Optional<DanhMucLoaiHopDongDTO> findOne(String id) {
        LOG.debug("Request to get DanhMucLoaiHopDong : {}", id);
        return danhMucLoaiHopDongRepository.findById(id).map(danhMucLoaiHopDongMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete DanhMucLoaiHopDong : {}", id);
        danhMucLoaiHopDongRepository.deleteById(id);
    }

    @Override
    public List<DanhMucLoaiHopDongDTO> findByIdDonVi(Long idDonVi) {
        List<DanhMucLoaiHopDong> entities = danhMucLoaiHopDongRepository.findByIdDonVi(idDonVi);
        return entities.stream().map(danhMucLoaiHopDongMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DanhMucLoaiHopDongDTO> search(String dienGiai, String idNhomHopDong, String idVaiTro1, String idVaiTro2, Long idDonVi) {
        LOG.debug("Request to search DanhMucLoaiHopDong");

        List<DanhMucLoaiHopDong> entities;

        if (dienGiai != null || idNhomHopDong != null || idVaiTro1 != null || idVaiTro2 != null || idDonVi != null) {
            entities = danhMucLoaiHopDongRepository.findByDienGiaiContainingOrIdNhomHopDongOrIdVaiTro1OrIdVaiTro2OrIdDonVi(
                dienGiai, idNhomHopDong, idVaiTro1, idVaiTro2, idDonVi);
        } else {
            // Nếu tất cả các tham số đều null, có thể trả về danh sách rỗng hoặc ném ngoại lệ tùy thuộc vào yêu cầu
            entities = new ArrayList<>();
        }

        return entities.stream().map(danhMucLoaiHopDongMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DanhMucLoaiHopDongDTO> findByGroup(String grp) {
        return danhMucLoaiHopDongRepository.findByNhomTen(grp).stream()
            .map(danhMucLoaiHopDongMapper::toDto)
            .collect(Collectors.toList());
    }
}
