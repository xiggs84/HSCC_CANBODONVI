package vn.vnpt.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.vnpt.domain.DanhMucLoaiGiayToChungThuc;
import vn.vnpt.repository.DanhMucLoaiGiayToChungThucRepository;
import vn.vnpt.service.DanhMucLoaiGiayToChungThucService;
import vn.vnpt.service.dto.DanhMucLoaiGiayToChungThucDTO;
import vn.vnpt.service.mapper.DanhMucLoaiGiayToChungThucMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucLoaiGiayToChungThuc}.
 */
@Service
public class DanhMucLoaiGiayToChungThucServiceImpl implements DanhMucLoaiGiayToChungThucService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucLoaiGiayToChungThucServiceImpl.class);

    private final DanhMucLoaiGiayToChungThucRepository danhMucLoaiGiayToChungThucRepository;

    private final DanhMucLoaiGiayToChungThucMapper danhMucLoaiGiayToChungThucMapper;

    public DanhMucLoaiGiayToChungThucServiceImpl(
        DanhMucLoaiGiayToChungThucRepository danhMucLoaiGiayToChungThucRepository,
        DanhMucLoaiGiayToChungThucMapper danhMucLoaiGiayToChungThucMapper
    ) {
        this.danhMucLoaiGiayToChungThucRepository = danhMucLoaiGiayToChungThucRepository;
        this.danhMucLoaiGiayToChungThucMapper = danhMucLoaiGiayToChungThucMapper;
    }

    @Override
    public DanhMucLoaiGiayToChungThucDTO save(DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO) {
        LOG.debug("Request to save DanhMucLoaiGiayToChungThuc : {}", danhMucLoaiGiayToChungThucDTO);
        DanhMucLoaiGiayToChungThuc danhMucLoaiGiayToChungThuc = danhMucLoaiGiayToChungThucMapper.toEntity(danhMucLoaiGiayToChungThucDTO);
        danhMucLoaiGiayToChungThuc = danhMucLoaiGiayToChungThucRepository.save(danhMucLoaiGiayToChungThuc);
        return danhMucLoaiGiayToChungThucMapper.toDto(danhMucLoaiGiayToChungThuc);
    }

    @Override
    public DanhMucLoaiGiayToChungThucDTO update(DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO) {
        LOG.debug("Request to update DanhMucLoaiGiayToChungThuc : {}", danhMucLoaiGiayToChungThucDTO);
        DanhMucLoaiGiayToChungThuc danhMucLoaiGiayToChungThuc = danhMucLoaiGiayToChungThucMapper.toEntity(danhMucLoaiGiayToChungThucDTO);
        danhMucLoaiGiayToChungThuc = danhMucLoaiGiayToChungThucRepository.save(danhMucLoaiGiayToChungThuc);
        return danhMucLoaiGiayToChungThucMapper.toDto(danhMucLoaiGiayToChungThuc);
    }

    @Override
    public Optional<DanhMucLoaiGiayToChungThucDTO> partialUpdate(DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO) {
        LOG.debug("Request to partially update DanhMucLoaiGiayToChungThuc : {}", danhMucLoaiGiayToChungThucDTO);

        return danhMucLoaiGiayToChungThucRepository
            .findById(danhMucLoaiGiayToChungThucDTO.getId())
            .map(existingDanhMucLoaiGiayToChungThuc -> {
                danhMucLoaiGiayToChungThucMapper.partialUpdate(existingDanhMucLoaiGiayToChungThuc, danhMucLoaiGiayToChungThucDTO);

                return existingDanhMucLoaiGiayToChungThuc;
            })
            .map(danhMucLoaiGiayToChungThucRepository::save)
            .map(danhMucLoaiGiayToChungThucMapper::toDto);
    }

    @Override
    public Page<DanhMucLoaiGiayToChungThucDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all DanhMucLoaiGiayToChungThucs");
        return danhMucLoaiGiayToChungThucRepository.findAll(pageable).map(danhMucLoaiGiayToChungThucMapper::toDto);
    }

    @Override
    public Optional<DanhMucLoaiGiayToChungThucDTO> findOne(String id) {
        LOG.debug("Request to get DanhMucLoaiGiayToChungThuc : {}", id);
        return danhMucLoaiGiayToChungThucRepository.findById(id).map(danhMucLoaiGiayToChungThucMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete DanhMucLoaiGiayToChungThuc : {}", id);
        danhMucLoaiGiayToChungThucRepository.deleteById(id);
    }

    @Override
    public List<DanhMucLoaiGiayToChungThucDTO> findByIdLoaiSo(String idLoaiSo) {
        List<DanhMucLoaiGiayToChungThuc> entities = danhMucLoaiGiayToChungThucRepository.findByIdLoaiSo(idLoaiSo);
        return entities.stream().map(danhMucLoaiGiayToChungThucMapper::toDto).collect(Collectors.toList());
    }
}
