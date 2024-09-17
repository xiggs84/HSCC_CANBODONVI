package vn.vnpt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DonViScanQr;
import vn.vnpt.repository.DonViScanQrRepository;
import vn.vnpt.service.dto.DonViScanQrDTO;
import vn.vnpt.service.mapper.DonViScanQrMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DonViScanQr}.
 */
@Service
@Transactional
public class DonViScanQrService {

    private static final Logger LOG = LoggerFactory.getLogger(DonViScanQrService.class);

    private final DonViScanQrRepository donViScanQrRepository;

    private final DonViScanQrMapper donViScanQrMapper;

    public DonViScanQrService(DonViScanQrRepository donViScanQrRepository, DonViScanQrMapper donViScanQrMapper) {
        this.donViScanQrRepository = donViScanQrRepository;
        this.donViScanQrMapper = donViScanQrMapper;
    }

    /**
     * Save a donViScanQr.
     *
     * @param donViScanQrDTO the entity to save.
     * @return the persisted entity.
     */
    public DonViScanQrDTO save(DonViScanQrDTO donViScanQrDTO) {
        LOG.debug("Request to save DonViScanQr : {}", donViScanQrDTO);
        DonViScanQr donViScanQr = donViScanQrMapper.toEntity(donViScanQrDTO);
        donViScanQr = donViScanQrRepository.save(donViScanQr);
        return donViScanQrMapper.toDto(donViScanQr);
    }

    /**
     * Update a donViScanQr.
     *
     * @param donViScanQrDTO the entity to save.
     * @return the persisted entity.
     */
    public DonViScanQrDTO update(DonViScanQrDTO donViScanQrDTO) {
        LOG.debug("Request to update DonViScanQr : {}", donViScanQrDTO);
        DonViScanQr donViScanQr = donViScanQrMapper.toEntity(donViScanQrDTO);
        donViScanQr = donViScanQrRepository.save(donViScanQr);
        return donViScanQrMapper.toDto(donViScanQr);
    }

    /**
     * Partially update a donViScanQr.
     *
     * @param donViScanQrDTO the entity to update partially.
     * @return the persisted entity.
     */
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

    /**
     * Get all the donViScanQrs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DonViScanQrDTO> findAll() {
        LOG.debug("Request to get all DonViScanQrs");
        return donViScanQrRepository.findAll().stream().map(donViScanQrMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one donViScanQr by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DonViScanQrDTO> findOne(Long id) {
        LOG.debug("Request to get DonViScanQr : {}", id);
        return donViScanQrRepository.findById(id).map(donViScanQrMapper::toDto);
    }

    /**
     * Delete the donViScanQr by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DonViScanQr : {}", id);
        donViScanQrRepository.deleteById(id);
    }
}
