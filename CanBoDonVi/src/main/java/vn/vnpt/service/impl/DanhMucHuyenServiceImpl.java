package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucHuyen;
import vn.vnpt.repository.DanhMucHuyenRepository;
import vn.vnpt.service.DanhMucHuyenService;
import vn.vnpt.service.dto.DanhMucHuyenDTO;
import vn.vnpt.service.mapper.DanhMucHuyenMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucHuyen}.
 */
@Service
@Transactional
public class DanhMucHuyenServiceImpl implements DanhMucHuyenService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucHuyenServiceImpl.class);

    private final DanhMucHuyenRepository danhMucHuyenRepository;

    private final DanhMucHuyenMapper danhMucHuyenMapper;

    public DanhMucHuyenServiceImpl(DanhMucHuyenRepository danhMucHuyenRepository, DanhMucHuyenMapper danhMucHuyenMapper) {
        this.danhMucHuyenRepository = danhMucHuyenRepository;
        this.danhMucHuyenMapper = danhMucHuyenMapper;
    }

    @Override
    public DanhMucHuyenDTO save(DanhMucHuyenDTO danhMucHuyenDTO) {
        LOG.debug("Request to save DanhMucHuyen : {}", danhMucHuyenDTO);
        DanhMucHuyen danhMucHuyen = danhMucHuyenMapper.toEntity(danhMucHuyenDTO);
        danhMucHuyen = danhMucHuyenRepository.save(danhMucHuyen);
        return danhMucHuyenMapper.toDto(danhMucHuyen);
    }

    @Override
    public DanhMucHuyenDTO update(DanhMucHuyenDTO danhMucHuyenDTO) {
        LOG.debug("Request to update DanhMucHuyen : {}", danhMucHuyenDTO);
        DanhMucHuyen danhMucHuyen = danhMucHuyenMapper.toEntity(danhMucHuyenDTO);
        danhMucHuyen.setIsPersisted();
        danhMucHuyen = danhMucHuyenRepository.save(danhMucHuyen);
        return danhMucHuyenMapper.toDto(danhMucHuyen);
    }

    @Override
    public Optional<DanhMucHuyenDTO> partialUpdate(DanhMucHuyenDTO danhMucHuyenDTO) {
        LOG.debug("Request to partially update DanhMucHuyen : {}", danhMucHuyenDTO);

        return danhMucHuyenRepository
            .findById(danhMucHuyenDTO.getMaHuyen())
            .map(existingDanhMucHuyen -> {
                danhMucHuyenMapper.partialUpdate(existingDanhMucHuyen, danhMucHuyenDTO);

                return existingDanhMucHuyen;
            })
            .map(danhMucHuyenRepository::save)
            .map(danhMucHuyenMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DanhMucHuyenDTO> findOne(String id) {
        LOG.debug("Request to get DanhMucHuyen : {}", id);
        return danhMucHuyenRepository.findById(id).map(danhMucHuyenMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete DanhMucHuyen : {}", id);
        danhMucHuyenRepository.deleteById(id);
    }
}
