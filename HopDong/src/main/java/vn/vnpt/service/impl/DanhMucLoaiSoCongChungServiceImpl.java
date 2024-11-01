package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.vnpt.domain.DanhMucLoaiSoCongChung;
import vn.vnpt.repository.DanhMucLoaiSoCongChungRepository;
import vn.vnpt.service.DanhMucLoaiSoCongChungService;
import vn.vnpt.service.dto.DanhMucLoaiSoCongChungDTO;
import vn.vnpt.service.mapper.DanhMucLoaiSoCongChungMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucLoaiSoCongChung}.
 */
@Service
public class DanhMucLoaiSoCongChungServiceImpl implements DanhMucLoaiSoCongChungService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucLoaiSoCongChungServiceImpl.class);

    private final DanhMucLoaiSoCongChungRepository danhMucLoaiSoCongChungRepository;

    private final DanhMucLoaiSoCongChungMapper danhMucLoaiSoCongChungMapper;

    public DanhMucLoaiSoCongChungServiceImpl(
        DanhMucLoaiSoCongChungRepository danhMucLoaiSoCongChungRepository,
        DanhMucLoaiSoCongChungMapper danhMucLoaiSoCongChungMapper
    ) {
        this.danhMucLoaiSoCongChungRepository = danhMucLoaiSoCongChungRepository;
        this.danhMucLoaiSoCongChungMapper = danhMucLoaiSoCongChungMapper;
    }

    @Override
    public DanhMucLoaiSoCongChungDTO save(DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO) {
        LOG.debug("Request to save DanhMucLoaiSoCongChung : {}", danhMucLoaiSoCongChungDTO);
        DanhMucLoaiSoCongChung danhMucLoaiSoCongChung = danhMucLoaiSoCongChungMapper.toEntity(danhMucLoaiSoCongChungDTO);
        danhMucLoaiSoCongChung = danhMucLoaiSoCongChungRepository.save(danhMucLoaiSoCongChung);
        return danhMucLoaiSoCongChungMapper.toDto(danhMucLoaiSoCongChung);
    }

    @Override
    public DanhMucLoaiSoCongChungDTO update(DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO) {
        LOG.debug("Request to update DanhMucLoaiSoCongChung : {}", danhMucLoaiSoCongChungDTO);
        DanhMucLoaiSoCongChung danhMucLoaiSoCongChung = danhMucLoaiSoCongChungMapper.toEntity(danhMucLoaiSoCongChungDTO);
        danhMucLoaiSoCongChung = danhMucLoaiSoCongChungRepository.save(danhMucLoaiSoCongChung);
        return danhMucLoaiSoCongChungMapper.toDto(danhMucLoaiSoCongChung);
    }

    @Override
    public Optional<DanhMucLoaiSoCongChungDTO> partialUpdate(DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO) {
        LOG.debug("Request to partially update DanhMucLoaiSoCongChung : {}", danhMucLoaiSoCongChungDTO);

        return danhMucLoaiSoCongChungRepository
            .findById(danhMucLoaiSoCongChungDTO.getId())
            .map(existingDanhMucLoaiSoCongChung -> {
                danhMucLoaiSoCongChungMapper.partialUpdate(existingDanhMucLoaiSoCongChung, danhMucLoaiSoCongChungDTO);

                return existingDanhMucLoaiSoCongChung;
            })
            .map(danhMucLoaiSoCongChungRepository::save)
            .map(danhMucLoaiSoCongChungMapper::toDto);
    }

    @Override
    public Page<DanhMucLoaiSoCongChungDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all DanhMucLoaiSoCongChungs");
        return danhMucLoaiSoCongChungRepository.findAll(pageable).map(danhMucLoaiSoCongChungMapper::toDto);
    }

    @Override
    public Optional<DanhMucLoaiSoCongChungDTO> findOne(String id) {
        LOG.debug("Request to get DanhMucLoaiSoCongChung : {}", id);
        return danhMucLoaiSoCongChungRepository.findById(id).map(danhMucLoaiSoCongChungMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete DanhMucLoaiSoCongChung : {}", id);
        danhMucLoaiSoCongChungRepository.deleteById(id);
    }
}
