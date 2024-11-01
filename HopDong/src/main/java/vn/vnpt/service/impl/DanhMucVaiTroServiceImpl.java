package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.vnpt.domain.DanhMucVaiTro;
import vn.vnpt.repository.DanhMucVaiTroRepository;
import vn.vnpt.service.DanhMucVaiTroService;
import vn.vnpt.service.dto.DanhMucVaiTroDTO;
import vn.vnpt.service.mapper.DanhMucVaiTroMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucVaiTro}.
 */
@Service
public class DanhMucVaiTroServiceImpl implements DanhMucVaiTroService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucVaiTroServiceImpl.class);

    private final DanhMucVaiTroRepository danhMucVaiTroRepository;

    private final DanhMucVaiTroMapper danhMucVaiTroMapper;

    public DanhMucVaiTroServiceImpl(DanhMucVaiTroRepository danhMucVaiTroRepository, DanhMucVaiTroMapper danhMucVaiTroMapper) {
        this.danhMucVaiTroRepository = danhMucVaiTroRepository;
        this.danhMucVaiTroMapper = danhMucVaiTroMapper;
    }

    @Override
    public DanhMucVaiTroDTO save(DanhMucVaiTroDTO danhMucVaiTroDTO) {
        LOG.debug("Request to save DanhMucVaiTro : {}", danhMucVaiTroDTO);
        DanhMucVaiTro danhMucVaiTro = danhMucVaiTroMapper.toEntity(danhMucVaiTroDTO);
        danhMucVaiTro = danhMucVaiTroRepository.save(danhMucVaiTro);
        return danhMucVaiTroMapper.toDto(danhMucVaiTro);
    }

    @Override
    public DanhMucVaiTroDTO update(DanhMucVaiTroDTO danhMucVaiTroDTO) {
        LOG.debug("Request to update DanhMucVaiTro : {}", danhMucVaiTroDTO);
        DanhMucVaiTro danhMucVaiTro = danhMucVaiTroMapper.toEntity(danhMucVaiTroDTO);
        danhMucVaiTro = danhMucVaiTroRepository.save(danhMucVaiTro);
        return danhMucVaiTroMapper.toDto(danhMucVaiTro);
    }

    @Override
    public Optional<DanhMucVaiTroDTO> partialUpdate(DanhMucVaiTroDTO danhMucVaiTroDTO) {
        LOG.debug("Request to partially update DanhMucVaiTro : {}", danhMucVaiTroDTO);

        return danhMucVaiTroRepository
            .findById(danhMucVaiTroDTO.getId())
            .map(existingDanhMucVaiTro -> {
                danhMucVaiTroMapper.partialUpdate(existingDanhMucVaiTro, danhMucVaiTroDTO);

                return existingDanhMucVaiTro;
            })
            .map(danhMucVaiTroRepository::save)
            .map(danhMucVaiTroMapper::toDto);
    }

    @Override
    public Page<DanhMucVaiTroDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all DanhMucVaiTros");
        return danhMucVaiTroRepository.findAll(pageable).map(danhMucVaiTroMapper::toDto);
    }

    @Override
    public Optional<DanhMucVaiTroDTO> findOne(String id) {
        LOG.debug("Request to get DanhMucVaiTro : {}", id);
        return danhMucVaiTroRepository.findById(id).map(danhMucVaiTroMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete DanhMucVaiTro : {}", id);
        danhMucVaiTroRepository.deleteById(id);
    }

    @Override
    public Optional<String> findDienGiaiById(String id) {
        LOG.debug("Request to get dienGiai of DanhMucVaiTro : {}", id);
        return danhMucVaiTroRepository.findById(id)
            .map(DanhMucVaiTro::getDienGiai); // Giả định rằng bạn có phương thức getDienGiai trong entity
    }
}
