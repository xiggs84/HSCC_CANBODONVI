package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DonViScanQr;
import vn.vnpt.repository.DonViScanQrRepository;
import vn.vnpt.service.DonViScanQrService;
import vn.vnpt.service.dto.DonViScanQrDTO;
import vn.vnpt.service.mapper.DonViScanQrMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DonViScanQr}.
 */
@Service
@Transactional
public class DonViScanQrServiceImpl implements DonViScanQrService {

    private static final Logger LOG = LoggerFactory.getLogger(DonViScanQrServiceImpl.class);

    private final DonViScanQrRepository donViScanQrRepository;

    private final DonViScanQrMapper donViScanQrMapper;

    public DonViScanQrServiceImpl(DonViScanQrRepository donViScanQrRepository, DonViScanQrMapper donViScanQrMapper) {
        this.donViScanQrRepository = donViScanQrRepository;
        this.donViScanQrMapper = donViScanQrMapper;
    }

    @Override
    public DonViScanQrDTO save(DonViScanQrDTO donViScanQrDTO) {
        LOG.debug("Request to save DonViScanQr : {}", donViScanQrDTO);
        DonViScanQr donViScanQr = donViScanQrMapper.toEntity(donViScanQrDTO);
        donViScanQr = donViScanQrRepository.save(donViScanQr);
        return donViScanQrMapper.toDto(donViScanQr);
    }

    @Override
    public DonViScanQrDTO update(DonViScanQrDTO donViScanQrDTO) {
        LOG.debug("Request to update DonViScanQr : {}", donViScanQrDTO);
        DonViScanQr donViScanQr = donViScanQrMapper.toEntity(donViScanQrDTO);
        donViScanQr = donViScanQrRepository.save(donViScanQr);
        return donViScanQrMapper.toDto(donViScanQr);
    }

    @Override
    public Optional<DonViScanQrDTO> partialUpdate(DonViScanQrDTO donViScanQrDTO) {
        LOG.debug("Request to partially update DonViScanQr : {}", donViScanQrDTO);

        return donViScanQrRepository
            .findById(donViScanQrDTO.getIdLuotQuet())
            .map(existingDonViScanQr -> {
                donViScanQrMapper.partialUpdate(existingDonViScanQr, donViScanQrDTO);

                return existingDonViScanQr;
            })
            .map(donViScanQrRepository::save)
            .map(donViScanQrMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DonViScanQrDTO> findOne(Long id) {
        LOG.debug("Request to get DonViScanQr : {}", id);
        return donViScanQrRepository.findById(id).map(donViScanQrMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete DonViScanQr : {}", id);
        donViScanQrRepository.deleteById(id);
    }
}
