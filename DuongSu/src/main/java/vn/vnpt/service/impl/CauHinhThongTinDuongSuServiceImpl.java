package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.CauHinhThongTinDuongSu;
import vn.vnpt.repository.CauHinhThongTinDuongSuRepository;
import vn.vnpt.service.CauHinhThongTinDuongSuService;
import vn.vnpt.service.dto.CauHinhThongTinDuongSuDTO;
import vn.vnpt.service.mapper.CauHinhThongTinDuongSuMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.CauHinhThongTinDuongSu}.
 */
@Service
@Transactional
public class CauHinhThongTinDuongSuServiceImpl implements CauHinhThongTinDuongSuService {

    private static final Logger LOG = LoggerFactory.getLogger(CauHinhThongTinDuongSuServiceImpl.class);

    private final CauHinhThongTinDuongSuRepository cauHinhThongTinDuongSuRepository;

    private final CauHinhThongTinDuongSuMapper cauHinhThongTinDuongSuMapper;

    public CauHinhThongTinDuongSuServiceImpl(
        CauHinhThongTinDuongSuRepository cauHinhThongTinDuongSuRepository,
        CauHinhThongTinDuongSuMapper cauHinhThongTinDuongSuMapper
    ) {
        this.cauHinhThongTinDuongSuRepository = cauHinhThongTinDuongSuRepository;
        this.cauHinhThongTinDuongSuMapper = cauHinhThongTinDuongSuMapper;
    }

    @Override
    public CauHinhThongTinDuongSuDTO save(CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO) {
        LOG.debug("Request to save CauHinhThongTinDuongSu : {}", cauHinhThongTinDuongSuDTO);
        CauHinhThongTinDuongSu cauHinhThongTinDuongSu = cauHinhThongTinDuongSuMapper.toEntity(cauHinhThongTinDuongSuDTO);
        cauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.save(cauHinhThongTinDuongSu);
        return cauHinhThongTinDuongSuMapper.toDto(cauHinhThongTinDuongSu);
    }

    @Override
    public CauHinhThongTinDuongSuDTO update(CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO) {
        LOG.debug("Request to update CauHinhThongTinDuongSu : {}", cauHinhThongTinDuongSuDTO);
        CauHinhThongTinDuongSu cauHinhThongTinDuongSu = cauHinhThongTinDuongSuMapper.toEntity(cauHinhThongTinDuongSuDTO);
        cauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.save(cauHinhThongTinDuongSu);
        return cauHinhThongTinDuongSuMapper.toDto(cauHinhThongTinDuongSu);
    }

    @Override
    public Optional<CauHinhThongTinDuongSuDTO> partialUpdate(CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO) {
        LOG.debug("Request to partially update CauHinhThongTinDuongSu : {}", cauHinhThongTinDuongSuDTO);

        return cauHinhThongTinDuongSuRepository
            .findById(cauHinhThongTinDuongSuDTO.getIdCauHinh())
            .map(existingCauHinhThongTinDuongSu -> {
                cauHinhThongTinDuongSuMapper.partialUpdate(existingCauHinhThongTinDuongSu, cauHinhThongTinDuongSuDTO);

                return existingCauHinhThongTinDuongSu;
            })
            .map(cauHinhThongTinDuongSuRepository::save)
            .map(cauHinhThongTinDuongSuMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CauHinhThongTinDuongSuDTO> findOne(Long id) {
        LOG.debug("Request to get CauHinhThongTinDuongSu : {}", id);
        return cauHinhThongTinDuongSuRepository.findById(id).map(cauHinhThongTinDuongSuMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete CauHinhThongTinDuongSu : {}", id);
        cauHinhThongTinDuongSuRepository.deleteById(id);
    }
}
