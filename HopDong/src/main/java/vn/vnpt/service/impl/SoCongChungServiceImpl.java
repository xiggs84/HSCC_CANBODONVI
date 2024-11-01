package vn.vnpt.service.impl;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.vnpt.domain.SoCongChung;
import vn.vnpt.repository.SoCongChungRepository;
import vn.vnpt.service.SoCongChungService;
import vn.vnpt.service.dto.SoCongChungDTO;
import vn.vnpt.service.mapper.SoCongChungMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.SoCongChung}.
 */
@Service
public class SoCongChungServiceImpl implements SoCongChungService {

    private static final Logger LOG = LoggerFactory.getLogger(SoCongChungServiceImpl.class);

    private final SoCongChungRepository soCongChungRepository;

    private final SoCongChungMapper soCongChungMapper;

    public SoCongChungServiceImpl(SoCongChungRepository soCongChungRepository, SoCongChungMapper soCongChungMapper) {
        this.soCongChungRepository = soCongChungRepository;
        this.soCongChungMapper = soCongChungMapper;
    }

    @Override
    public SoCongChungDTO save(SoCongChungDTO soCongChungDTO) {
        LOG.debug("Request to save SoCongChung : {}", soCongChungDTO);
        SoCongChung soCongChung = soCongChungMapper.toEntity(soCongChungDTO);
        soCongChung = soCongChungRepository.save(soCongChung);
        return soCongChungMapper.toDto(soCongChung);
    }

    @Override
    public SoCongChungDTO update(SoCongChungDTO soCongChungDTO) {
        LOG.debug("Request to update SoCongChung : {}", soCongChungDTO);
        SoCongChung soCongChung = soCongChungMapper.toEntity(soCongChungDTO);
        soCongChung = soCongChungRepository.save(soCongChung);
        return soCongChungMapper.toDto(soCongChung);
    }

    @Override
    public Optional<SoCongChungDTO> partialUpdate(SoCongChungDTO soCongChungDTO) {
        LOG.debug("Request to partially update SoCongChung : {}", soCongChungDTO);

        return soCongChungRepository
            .findById(soCongChungDTO.getId())
            .map(existingSoCongChung -> {
                soCongChungMapper.partialUpdate(existingSoCongChung, soCongChungDTO);

                return existingSoCongChung;
            })
            .map(soCongChungRepository::save)
            .map(soCongChungMapper::toDto);
    }

    @Override
    public Page<SoCongChungDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all SoCongChungs");
        return soCongChungRepository.findAll(pageable).map(soCongChungMapper::toDto);
    }

    @Override
    public Optional<SoCongChungDTO> findOne(String id) {
        LOG.debug("Request to get SoCongChung : {}", id);
        return soCongChungRepository.findById(id).map(soCongChungMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete SoCongChung : {}", id);
        soCongChungRepository.deleteById(id);
    }

    @Override
    public List<SoCongChungDTO> findByDonVi(Long idDonVi) {
        LOG.debug("Request to find SoCongChungs by idDonVi : {}", idDonVi);
        List<SoCongChung> soCongChungs = soCongChungRepository.findByIdDonVi(idDonVi);
        return soCongChungMapper.toDto(soCongChungs);
    }
}
