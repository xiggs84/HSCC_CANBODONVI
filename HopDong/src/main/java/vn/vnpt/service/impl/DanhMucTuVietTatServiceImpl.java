package vn.vnpt.service.impl;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.vnpt.domain.DanhMucTuVietTat;
import vn.vnpt.domain.SoCongChung;
import vn.vnpt.repository.DanhMucTuVietTatRepository;
import vn.vnpt.service.DanhMucTuVietTatService;
import vn.vnpt.service.dto.DanhMucTuVietTatDTO;
import vn.vnpt.service.mapper.DanhMucTuVietTatMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucTuVietTat}.
 */
@Service
public class DanhMucTuVietTatServiceImpl implements DanhMucTuVietTatService {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucTuVietTatServiceImpl.class);

    private final DanhMucTuVietTatRepository danhMucTuVietTatRepository;

    private final DanhMucTuVietTatMapper danhMucTuVietTatMapper;

    public DanhMucTuVietTatServiceImpl(
        DanhMucTuVietTatRepository danhMucTuVietTatRepository,
        DanhMucTuVietTatMapper danhMucTuVietTatMapper
    ) {
        this.danhMucTuVietTatRepository = danhMucTuVietTatRepository;
        this.danhMucTuVietTatMapper = danhMucTuVietTatMapper;
    }

    @Override
    public DanhMucTuVietTatDTO save(DanhMucTuVietTatDTO danhMucTuVietTatDTO) {
        LOG.debug("Request to save DanhMucTuVietTat : {}", danhMucTuVietTatDTO);
        DanhMucTuVietTat danhMucTuVietTat = danhMucTuVietTatMapper.toEntity(danhMucTuVietTatDTO);
        danhMucTuVietTat = danhMucTuVietTatRepository.save(danhMucTuVietTat);
        return danhMucTuVietTatMapper.toDto(danhMucTuVietTat);
    }

    @Override
    public DanhMucTuVietTatDTO update(DanhMucTuVietTatDTO danhMucTuVietTatDTO) {
        LOG.debug("Request to update DanhMucTuVietTat : {}", danhMucTuVietTatDTO);
        DanhMucTuVietTat danhMucTuVietTat = danhMucTuVietTatMapper.toEntity(danhMucTuVietTatDTO);
        danhMucTuVietTat = danhMucTuVietTatRepository.save(danhMucTuVietTat);
        return danhMucTuVietTatMapper.toDto(danhMucTuVietTat);
    }

    @Override
    public Optional<DanhMucTuVietTatDTO> partialUpdate(DanhMucTuVietTatDTO danhMucTuVietTatDTO) {
        LOG.debug("Request to partially update DanhMucTuVietTat : {}", danhMucTuVietTatDTO);

        return danhMucTuVietTatRepository
            .findById(danhMucTuVietTatDTO.getId())
            .map(existingDanhMucTuVietTat -> {
                danhMucTuVietTatMapper.partialUpdate(existingDanhMucTuVietTat, danhMucTuVietTatDTO);

                return existingDanhMucTuVietTat;
            })
            .map(danhMucTuVietTatRepository::save)
            .map(danhMucTuVietTatMapper::toDto);
    }

    @Override
    public Page<DanhMucTuVietTatDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all DanhMucTuVietTats");
        return danhMucTuVietTatRepository.findAll(pageable).map(danhMucTuVietTatMapper::toDto);
    }

    @Override
    public Optional<DanhMucTuVietTatDTO> findOne(String id) {
        LOG.debug("Request to get DanhMucTuVietTat : {}", id);
        return danhMucTuVietTatRepository.findById(id).map(danhMucTuVietTatMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete DanhMucTuVietTat : {}", id);
        danhMucTuVietTatRepository.deleteById(id);
    }

    @Override
    public List<DanhMucTuVietTatDTO> findByDonVi(Long idDonVi) {
        LOG.debug("Request to find TuVietTats by idDonVi : {}", idDonVi);
        List<DanhMucTuVietTat> tuVietTats = danhMucTuVietTatRepository.findByIdDonVi(idDonVi);
        return danhMucTuVietTatMapper.toDto(tuVietTats);
    }
}
