package vn.vnpt.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.vnpt.domain.PhanLoaiHopDong;
import vn.vnpt.repository.PhanLoaiHopDongRepository;
import vn.vnpt.service.PhanLoaiHopDongService;
import vn.vnpt.service.dto.PhanLoaiHopDongDTO;
import vn.vnpt.service.mapper.PhanLoaiHopDongMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.PhanLoaiHopDong}.
 */
@Service
public class PhanLoaiHopDongServiceImpl implements PhanLoaiHopDongService {

    private static final Logger LOG = LoggerFactory.getLogger(PhanLoaiHopDongServiceImpl.class);

    private final PhanLoaiHopDongRepository phanLoaiHopDongRepository;

    private final PhanLoaiHopDongMapper phanLoaiHopDongMapper;

    public PhanLoaiHopDongServiceImpl(PhanLoaiHopDongRepository phanLoaiHopDongRepository, PhanLoaiHopDongMapper phanLoaiHopDongMapper) {
        this.phanLoaiHopDongRepository = phanLoaiHopDongRepository;
        this.phanLoaiHopDongMapper = phanLoaiHopDongMapper;
    }

    @Override
    public PhanLoaiHopDongDTO save(PhanLoaiHopDongDTO phanLoaiHopDongDTO) {
        LOG.debug("Request to save PhanLoaiHopDong : {}", phanLoaiHopDongDTO);
        PhanLoaiHopDong phanLoaiHopDong = phanLoaiHopDongMapper.toEntity(phanLoaiHopDongDTO);
        phanLoaiHopDong = phanLoaiHopDongRepository.save(phanLoaiHopDong);
        return phanLoaiHopDongMapper.toDto(phanLoaiHopDong);
    }

    @Override
    public PhanLoaiHopDongDTO update(PhanLoaiHopDongDTO phanLoaiHopDongDTO) {
        LOG.debug("Request to update PhanLoaiHopDong : {}", phanLoaiHopDongDTO);
        PhanLoaiHopDong phanLoaiHopDong = phanLoaiHopDongMapper.toEntity(phanLoaiHopDongDTO);
        phanLoaiHopDong = phanLoaiHopDongRepository.save(phanLoaiHopDong);
        return phanLoaiHopDongMapper.toDto(phanLoaiHopDong);
    }

    @Override
    public Optional<PhanLoaiHopDongDTO> partialUpdate(PhanLoaiHopDongDTO phanLoaiHopDongDTO) {
        LOG.debug("Request to partially update PhanLoaiHopDong : {}", phanLoaiHopDongDTO);

        return phanLoaiHopDongRepository
            .findById(phanLoaiHopDongDTO.getId())
            .map(existingPhanLoaiHopDong -> {
                phanLoaiHopDongMapper.partialUpdate(existingPhanLoaiHopDong, phanLoaiHopDongDTO);

                return existingPhanLoaiHopDong;
            })
            .map(phanLoaiHopDongRepository::save)
            .map(phanLoaiHopDongMapper::toDto);
    }

    @Override
    public Page<PhanLoaiHopDongDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all PhanLoaiHopDongs");
        return phanLoaiHopDongRepository.findAll(pageable).map(phanLoaiHopDongMapper::toDto);
    }

    @Override
    public Optional<PhanLoaiHopDongDTO> findOne(String id) {
        LOG.debug("Request to get PhanLoaiHopDong : {}", id);
        return phanLoaiHopDongRepository.findById(id).map(phanLoaiHopDongMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete PhanLoaiHopDong : {}", id);
        phanLoaiHopDongRepository.deleteById(id);
    }

    @Override
    public List<PhanLoaiHopDongDTO> findByIdNhomHopDong(String idNhomHopDong) {
        LOG.debug("Request to find PhanLoaiHopDong by idNhomHopDong : {}", idNhomHopDong);
        return phanLoaiHopDongRepository.findByIdNhomHopDong(idNhomHopDong).stream()
            .map(phanLoaiHopDongMapper::toDto)
            .collect(Collectors.toList());
    }
}
