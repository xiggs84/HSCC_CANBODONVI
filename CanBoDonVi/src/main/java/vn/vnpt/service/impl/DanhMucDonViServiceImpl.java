package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucDonVi;
import vn.vnpt.repository.DanhMucDonViRepository;
import vn.vnpt.service.DanhMucDonViService;
import vn.vnpt.service.dto.DanhMucDonViDTO;
import vn.vnpt.service.mapper.DanhMucDonViMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucDonVi}.
 */
@Service
@Transactional
public class DanhMucDonViServiceImpl implements DanhMucDonViService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucDonViServiceImpl.class);

    private final DanhMucDonViRepository danhMucDonViRepository;

    private final DanhMucDonViMapper danhMucDonViMapper;

    public DanhMucDonViServiceImpl(DanhMucDonViRepository danhMucDonViRepository, DanhMucDonViMapper danhMucDonViMapper) {
        this.danhMucDonViRepository = danhMucDonViRepository;
        this.danhMucDonViMapper = danhMucDonViMapper;
    }

    @Override
    public DanhMucDonViDTO save(DanhMucDonViDTO danhMucDonViDTO) {
        LOG.debug("Request to save DanhMucDonVi : {}", danhMucDonViDTO);
        DanhMucDonVi danhMucDonVi = danhMucDonViMapper.toEntity(danhMucDonViDTO);
        danhMucDonVi = danhMucDonViRepository.save(danhMucDonVi);
        return danhMucDonViMapper.toDto(danhMucDonVi);
    }

    @Override
    public DanhMucDonViDTO update(DanhMucDonViDTO danhMucDonViDTO) {
        LOG.debug("Request to update DanhMucDonVi : {}", danhMucDonViDTO);
        DanhMucDonVi danhMucDonVi = danhMucDonViMapper.toEntity(danhMucDonViDTO);
        danhMucDonVi = danhMucDonViRepository.save(danhMucDonVi);
        return danhMucDonViMapper.toDto(danhMucDonVi);
    }

    @Override
    public Optional<DanhMucDonViDTO> partialUpdate(DanhMucDonViDTO danhMucDonViDTO) {
        LOG.debug("Request to partially update DanhMucDonVi : {}", danhMucDonViDTO);

        return danhMucDonViRepository
            .findById(danhMucDonViDTO.getIdDonVi())
            .map(existingDanhMucDonVi -> {
                danhMucDonViMapper.partialUpdate(existingDanhMucDonVi, danhMucDonViDTO);

                return existingDanhMucDonVi;
            })
            .map(danhMucDonViRepository::save)
            .map(danhMucDonViMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DanhMucDonViDTO> findOne(Long id) {
        LOG.debug("Request to get DanhMucDonVi : {}", id);
        return danhMucDonViRepository.findById(id).map(danhMucDonViMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete DanhMucDonVi : {}", id);
        danhMucDonViRepository.deleteById(id);
    }
}
