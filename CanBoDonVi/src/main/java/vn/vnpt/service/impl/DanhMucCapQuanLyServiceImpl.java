package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucCapQuanLy;
import vn.vnpt.repository.DanhMucCapQuanLyRepository;
import vn.vnpt.service.DanhMucCapQuanLyService;
import vn.vnpt.service.dto.DanhMucCapQuanLyDTO;
import vn.vnpt.service.mapper.DanhMucCapQuanLyMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucCapQuanLy}.
 */
@Service
@Transactional
public class DanhMucCapQuanLyServiceImpl implements DanhMucCapQuanLyService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucCapQuanLyServiceImpl.class);

    private final DanhMucCapQuanLyRepository danhMucCapQuanLyRepository;

    private final DanhMucCapQuanLyMapper danhMucCapQuanLyMapper;

    public DanhMucCapQuanLyServiceImpl(
        DanhMucCapQuanLyRepository danhMucCapQuanLyRepository,
        DanhMucCapQuanLyMapper danhMucCapQuanLyMapper
    ) {
        this.danhMucCapQuanLyRepository = danhMucCapQuanLyRepository;
        this.danhMucCapQuanLyMapper = danhMucCapQuanLyMapper;
    }

    @Override
    public DanhMucCapQuanLyDTO save(DanhMucCapQuanLyDTO danhMucCapQuanLyDTO) {
        LOG.debug("Request to save DanhMucCapQuanLy : {}", danhMucCapQuanLyDTO);
        DanhMucCapQuanLy danhMucCapQuanLy = danhMucCapQuanLyMapper.toEntity(danhMucCapQuanLyDTO);
        danhMucCapQuanLy = danhMucCapQuanLyRepository.save(danhMucCapQuanLy);
        return danhMucCapQuanLyMapper.toDto(danhMucCapQuanLy);
    }

    @Override
    public DanhMucCapQuanLyDTO update(DanhMucCapQuanLyDTO danhMucCapQuanLyDTO) {
        LOG.debug("Request to update DanhMucCapQuanLy : {}", danhMucCapQuanLyDTO);
        DanhMucCapQuanLy danhMucCapQuanLy = danhMucCapQuanLyMapper.toEntity(danhMucCapQuanLyDTO);
        danhMucCapQuanLy = danhMucCapQuanLyRepository.save(danhMucCapQuanLy);
        return danhMucCapQuanLyMapper.toDto(danhMucCapQuanLy);
    }

    @Override
    public Optional<DanhMucCapQuanLyDTO> partialUpdate(DanhMucCapQuanLyDTO danhMucCapQuanLyDTO) {
        LOG.debug("Request to partially update DanhMucCapQuanLy : {}", danhMucCapQuanLyDTO);

        return danhMucCapQuanLyRepository
            .findById(danhMucCapQuanLyDTO.getIdCapQl())
            .map(existingDanhMucCapQuanLy -> {
                danhMucCapQuanLyMapper.partialUpdate(existingDanhMucCapQuanLy, danhMucCapQuanLyDTO);

                return existingDanhMucCapQuanLy;
            })
            .map(danhMucCapQuanLyRepository::save)
            .map(danhMucCapQuanLyMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DanhMucCapQuanLyDTO> findOne(Long id) {
        LOG.debug("Request to get DanhMucCapQuanLy : {}", id);
        return danhMucCapQuanLyRepository.findById(id).map(danhMucCapQuanLyMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete DanhMucCapQuanLy : {}", id);
        danhMucCapQuanLyRepository.deleteById(id);
    }
}
