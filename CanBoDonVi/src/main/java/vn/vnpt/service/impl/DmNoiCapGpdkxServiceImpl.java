package vn.vnpt.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DmNoiCapGpdkx;
import vn.vnpt.repository.DmNoiCapGpdkxRepository;
import vn.vnpt.service.DmNoiCapGpdkxService;
import vn.vnpt.service.dto.DmNoiCapGpdkxDTO;
import vn.vnpt.service.mapper.DmNoiCapGpdkxMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DmNoiCapGpdkx}.
 */
@Service
@Transactional
public class DmNoiCapGpdkxServiceImpl implements DmNoiCapGpdkxService {

    private static final Logger LOG = LoggerFactory.getLogger(DmNoiCapGpdkxServiceImpl.class);

    private final DmNoiCapGpdkxRepository dmNoiCapGpdkxRepository;

    private final DmNoiCapGpdkxMapper dmNoiCapGpdkxMapper;

    public DmNoiCapGpdkxServiceImpl(DmNoiCapGpdkxRepository dmNoiCapGpdkxRepository, DmNoiCapGpdkxMapper dmNoiCapGpdkxMapper) {
        this.dmNoiCapGpdkxRepository = dmNoiCapGpdkxRepository;
        this.dmNoiCapGpdkxMapper = dmNoiCapGpdkxMapper;
    }

    @Override
    public DmNoiCapGpdkxDTO save(DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO) {
        LOG.debug("Request to save DmNoiCapGpdkx : {}", dmNoiCapGpdkxDTO);
        DmNoiCapGpdkx dmNoiCapGpdkx = dmNoiCapGpdkxMapper.toEntity(dmNoiCapGpdkxDTO);
        dmNoiCapGpdkx = dmNoiCapGpdkxRepository.save(dmNoiCapGpdkx);
        return dmNoiCapGpdkxMapper.toDto(dmNoiCapGpdkx);
    }

    @Override
    public DmNoiCapGpdkxDTO update(DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO) {
        LOG.debug("Request to update DmNoiCapGpdkx : {}", dmNoiCapGpdkxDTO);
        DmNoiCapGpdkx dmNoiCapGpdkx = dmNoiCapGpdkxMapper.toEntity(dmNoiCapGpdkxDTO);
        dmNoiCapGpdkx = dmNoiCapGpdkxRepository.save(dmNoiCapGpdkx);
        return dmNoiCapGpdkxMapper.toDto(dmNoiCapGpdkx);
    }

    @Override
    public Optional<DmNoiCapGpdkxDTO> partialUpdate(DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO) {
        LOG.debug("Request to partially update DmNoiCapGpdkx : {}", dmNoiCapGpdkxDTO);

        return dmNoiCapGpdkxRepository
            .findById(dmNoiCapGpdkxDTO.getIdNoiCap())
            .map(existingDmNoiCapGpdkx -> {
                dmNoiCapGpdkxMapper.partialUpdate(existingDmNoiCapGpdkx, dmNoiCapGpdkxDTO);

                return existingDmNoiCapGpdkx;
            })
            .map(dmNoiCapGpdkxRepository::save)
            .map(dmNoiCapGpdkxMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DmNoiCapGpdkxDTO> findOne(Long id) {
        LOG.debug("Request to get DmNoiCapGpdkx : {}", id);
        return dmNoiCapGpdkxRepository.findById(id).map(dmNoiCapGpdkxMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete DmNoiCapGpdkx : {}", id);
        dmNoiCapGpdkxRepository.deleteById(id);
    }
}
