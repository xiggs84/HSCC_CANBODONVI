package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucQuocGia;
import vn.vnpt.repository.DanhMucQuocGiaRepository;
import vn.vnpt.service.DanhMucQuocGiaService;
import vn.vnpt.service.dto.DanhMucQuocGiaDTO;
import vn.vnpt.service.mapper.DanhMucQuocGiaMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucQuocGia}.
 */
@Service
@Transactional
public class DanhMucQuocGiaServiceImpl implements DanhMucQuocGiaService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucQuocGiaServiceImpl.class);

    private final DanhMucQuocGiaRepository danhMucQuocGiaRepository;

    private final DanhMucQuocGiaMapper danhMucQuocGiaMapper;

    public DanhMucQuocGiaServiceImpl(DanhMucQuocGiaRepository danhMucQuocGiaRepository, DanhMucQuocGiaMapper danhMucQuocGiaMapper) {
        this.danhMucQuocGiaRepository = danhMucQuocGiaRepository;
        this.danhMucQuocGiaMapper = danhMucQuocGiaMapper;
    }

    @Override
    public DanhMucQuocGiaDTO save(DanhMucQuocGiaDTO danhMucQuocGiaDTO) {
        LOG.debug("Request to save DanhMucQuocGia : {}", danhMucQuocGiaDTO);
        DanhMucQuocGia danhMucQuocGia = danhMucQuocGiaMapper.toEntity(danhMucQuocGiaDTO);
        danhMucQuocGia = danhMucQuocGiaRepository.save(danhMucQuocGia);
        return danhMucQuocGiaMapper.toDto(danhMucQuocGia);
    }

    @Override
    public DanhMucQuocGiaDTO update(DanhMucQuocGiaDTO danhMucQuocGiaDTO) {
        LOG.debug("Request to update DanhMucQuocGia : {}", danhMucQuocGiaDTO);
        DanhMucQuocGia danhMucQuocGia = danhMucQuocGiaMapper.toEntity(danhMucQuocGiaDTO);
        danhMucQuocGia = danhMucQuocGiaRepository.save(danhMucQuocGia);
        return danhMucQuocGiaMapper.toDto(danhMucQuocGia);
    }

    @Override
    public Optional<DanhMucQuocGiaDTO> partialUpdate(DanhMucQuocGiaDTO danhMucQuocGiaDTO) {
        LOG.debug("Request to partially update DanhMucQuocGia : {}", danhMucQuocGiaDTO);

        return danhMucQuocGiaRepository
            .findById(danhMucQuocGiaDTO.getIdQuocGia())
            .map(existingDanhMucQuocGia -> {
                danhMucQuocGiaMapper.partialUpdate(existingDanhMucQuocGia, danhMucQuocGiaDTO);

                return existingDanhMucQuocGia;
            })
            .map(danhMucQuocGiaRepository::save)
            .map(danhMucQuocGiaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DanhMucQuocGiaDTO> findOne(Long id) {
        LOG.debug("Request to get DanhMucQuocGia : {}", id);
        return danhMucQuocGiaRepository.findById(id).map(danhMucQuocGiaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete DanhMucQuocGia : {}", id);
        danhMucQuocGiaRepository.deleteById(id);
    }
}
