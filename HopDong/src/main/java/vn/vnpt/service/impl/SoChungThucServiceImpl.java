package vn.vnpt.service.impl;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.vnpt.domain.SoChungThuc;
import vn.vnpt.repository.SoChungThucRepository;
import vn.vnpt.service.SoChungThucService;
import vn.vnpt.service.dto.SoChungThucDTO;
import vn.vnpt.service.mapper.SoChungThucMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.SoChungThuc}.
 */
@Service
public class SoChungThucServiceImpl implements SoChungThucService {

    private static final Logger LOG = LoggerFactory.getLogger(SoChungThucServiceImpl.class);

    private final SoChungThucRepository soChungThucRepository;

    private final SoChungThucMapper soChungThucMapper;

    public SoChungThucServiceImpl(SoChungThucRepository soChungThucRepository, SoChungThucMapper soChungThucMapper) {
        this.soChungThucRepository = soChungThucRepository;
        this.soChungThucMapper = soChungThucMapper;
    }

    @Override
    public SoChungThucDTO save(SoChungThucDTO soChungThucDTO) {
        LOG.debug("Request to save SoChungThuc : {}", soChungThucDTO);
        SoChungThuc soChungThuc = soChungThucMapper.toEntity(soChungThucDTO);
        soChungThuc = soChungThucRepository.save(soChungThuc);
        return soChungThucMapper.toDto(soChungThuc);
    }

    @Override
    public SoChungThucDTO update(SoChungThucDTO soChungThucDTO) {
        LOG.debug("Request to update SoChungThuc : {}", soChungThucDTO);
        SoChungThuc soChungThuc = soChungThucMapper.toEntity(soChungThucDTO);
        soChungThuc = soChungThucRepository.save(soChungThuc);
        return soChungThucMapper.toDto(soChungThuc);
    }

    @Override
    public Optional<SoChungThucDTO> partialUpdate(SoChungThucDTO soChungThucDTO) {
        LOG.debug("Request to partially update SoChungThuc : {}", soChungThucDTO);

        return soChungThucRepository
            .findById(soChungThucDTO.getId())
            .map(existingSoChungThuc -> {
                soChungThucMapper.partialUpdate(existingSoChungThuc, soChungThucDTO);

                return existingSoChungThuc;
            })
            .map(soChungThucRepository::save)
            .map(soChungThucMapper::toDto);
    }

    @Override
    public Page<SoChungThucDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all SoChungThucs");
        return soChungThucRepository.findAll(pageable).map(soChungThucMapper::toDto);
    }

    @Override
    public Optional<SoChungThucDTO> findOne(String id) {
        LOG.debug("Request to get SoChungThuc : {}", id);
        return soChungThucRepository.findById(id).map(soChungThucMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete SoChungThuc : {}", id);
        soChungThucRepository.deleteById(id);
    }

    @Override
    public List<SoChungThucDTO> findByDonVi(Long idDonVi) {
        LOG.debug("Request to find SoChungThucs by idDonVi : {}", idDonVi);
        List<SoChungThuc> soChungThucs = soChungThucRepository.findByIdDonVi(idDonVi);
        return soChungThucMapper.toDto(soChungThucs);
    }
}
