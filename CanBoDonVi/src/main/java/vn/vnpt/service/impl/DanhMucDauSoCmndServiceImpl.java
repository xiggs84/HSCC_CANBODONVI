package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucDauSoCmnd;
import vn.vnpt.repository.DanhMucDauSoCmndRepository;
import vn.vnpt.service.DanhMucDauSoCmndService;
import vn.vnpt.service.dto.DanhMucDauSoCmndDTO;
import vn.vnpt.service.mapper.DanhMucDauSoCmndMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucDauSoCmnd}.
 */
@Service
@Transactional
public class DanhMucDauSoCmndServiceImpl implements DanhMucDauSoCmndService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucDauSoCmndServiceImpl.class);

    private final DanhMucDauSoCmndRepository danhMucDauSoCmndRepository;

    private final DanhMucDauSoCmndMapper danhMucDauSoCmndMapper;

    public DanhMucDauSoCmndServiceImpl(
        DanhMucDauSoCmndRepository danhMucDauSoCmndRepository,
        DanhMucDauSoCmndMapper danhMucDauSoCmndMapper
    ) {
        this.danhMucDauSoCmndRepository = danhMucDauSoCmndRepository;
        this.danhMucDauSoCmndMapper = danhMucDauSoCmndMapper;
    }

    @Override
    public DanhMucDauSoCmndDTO save(DanhMucDauSoCmndDTO danhMucDauSoCmndDTO) {
        LOG.debug("Request to save DanhMucDauSoCmnd : {}", danhMucDauSoCmndDTO);
        DanhMucDauSoCmnd danhMucDauSoCmnd = danhMucDauSoCmndMapper.toEntity(danhMucDauSoCmndDTO);
        danhMucDauSoCmnd = danhMucDauSoCmndRepository.save(danhMucDauSoCmnd);
        return danhMucDauSoCmndMapper.toDto(danhMucDauSoCmnd);
    }

    @Override
    public DanhMucDauSoCmndDTO update(DanhMucDauSoCmndDTO danhMucDauSoCmndDTO) {
        LOG.debug("Request to update DanhMucDauSoCmnd : {}", danhMucDauSoCmndDTO);
        DanhMucDauSoCmnd danhMucDauSoCmnd = danhMucDauSoCmndMapper.toEntity(danhMucDauSoCmndDTO);
        danhMucDauSoCmnd = danhMucDauSoCmndRepository.save(danhMucDauSoCmnd);
        return danhMucDauSoCmndMapper.toDto(danhMucDauSoCmnd);
    }

    @Override
    public Optional<DanhMucDauSoCmndDTO> partialUpdate(DanhMucDauSoCmndDTO danhMucDauSoCmndDTO) {
        LOG.debug("Request to partially update DanhMucDauSoCmnd : {}", danhMucDauSoCmndDTO);

        return danhMucDauSoCmndRepository
            .findById(danhMucDauSoCmndDTO.getIdDauSo())
            .map(existingDanhMucDauSoCmnd -> {
                danhMucDauSoCmndMapper.partialUpdate(existingDanhMucDauSoCmnd, danhMucDauSoCmndDTO);

                return existingDanhMucDauSoCmnd;
            })
            .map(danhMucDauSoCmndRepository::save)
            .map(danhMucDauSoCmndMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DanhMucDauSoCmndDTO> findOne(Long id) {
        LOG.debug("Request to get DanhMucDauSoCmnd : {}", id);
        return danhMucDauSoCmndRepository.findById(id).map(danhMucDauSoCmndMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete DanhMucDauSoCmnd : {}", id);
        danhMucDauSoCmndRepository.deleteById(id);
    }
}
