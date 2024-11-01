package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.vnpt.domain.DanhSachChungThuc;
import vn.vnpt.repository.DanhSachChungThucRepository;
import vn.vnpt.service.DanhSachChungThucService;
import vn.vnpt.service.dto.DanhSachChungThucDTO;
import vn.vnpt.service.mapper.DanhSachChungThucMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhSachChungThuc}.
 */
@Service
public class DanhSachChungThucServiceImpl implements DanhSachChungThucService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhSachChungThucServiceImpl.class);

    private final DanhSachChungThucRepository danhSachChungThucRepository;

    private final DanhSachChungThucMapper danhSachChungThucMapper;

    public DanhSachChungThucServiceImpl(
        DanhSachChungThucRepository danhSachChungThucRepository,
        DanhSachChungThucMapper danhSachChungThucMapper
    ) {
        this.danhSachChungThucRepository = danhSachChungThucRepository;
        this.danhSachChungThucMapper = danhSachChungThucMapper;
    }

    @Override
    public DanhSachChungThucDTO save(DanhSachChungThucDTO danhSachChungThucDTO) {
        LOG.debug("Request to save DanhSachChungThuc : {}", danhSachChungThucDTO);
        DanhSachChungThuc danhSachChungThuc = danhSachChungThucMapper.toEntity(danhSachChungThucDTO);
        danhSachChungThuc = danhSachChungThucRepository.save(danhSachChungThuc);
        return danhSachChungThucMapper.toDto(danhSachChungThuc);
    }

    @Override
    public DanhSachChungThucDTO update(DanhSachChungThucDTO danhSachChungThucDTO) {
        LOG.debug("Request to update DanhSachChungThuc : {}", danhSachChungThucDTO);
        DanhSachChungThuc danhSachChungThuc = danhSachChungThucMapper.toEntity(danhSachChungThucDTO);
        danhSachChungThuc = danhSachChungThucRepository.save(danhSachChungThuc);
        return danhSachChungThucMapper.toDto(danhSachChungThuc);
    }

    @Override
    public Optional<DanhSachChungThucDTO> partialUpdate(DanhSachChungThucDTO danhSachChungThucDTO) {
        LOG.debug("Request to partially update DanhSachChungThuc : {}", danhSachChungThucDTO);

        return danhSachChungThucRepository
            .findById(danhSachChungThucDTO.getId())
            .map(existingDanhSachChungThuc -> {
                danhSachChungThucMapper.partialUpdate(existingDanhSachChungThuc, danhSachChungThucDTO);

                return existingDanhSachChungThuc;
            })
            .map(danhSachChungThucRepository::save)
            .map(danhSachChungThucMapper::toDto);
    }

    @Override
    public Page<DanhSachChungThucDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all DanhSachChungThucs");
        return danhSachChungThucRepository.findAll(pageable).map(danhSachChungThucMapper::toDto);
    }

    @Override
    public Optional<DanhSachChungThucDTO> findOne(String id) {
        LOG.debug("Request to get DanhSachChungThuc : {}", id);
        return danhSachChungThucRepository.findById(id).map(danhSachChungThucMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete DanhSachChungThuc : {}", id);
        danhSachChungThucRepository.deleteById(id);
    }
}
