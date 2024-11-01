package vn.vnpt.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.TinhTrangTaiSan;
import vn.vnpt.repository.TinhTrangTaiSanRepository;
import vn.vnpt.service.TinhTrangTaiSanService;
import vn.vnpt.service.dto.TinhTrangTaiSanDTO;
import vn.vnpt.service.mapper.TinhTrangTaiSanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.TinhTrangTaiSan}.
 */
@Service
@Transactional
public class TinhTrangTaiSanServiceImpl implements TinhTrangTaiSanService {

    private static final Logger LOG = LoggerFactory.getLogger(TinhTrangTaiSanServiceImpl.class);

    private final TinhTrangTaiSanRepository tinhTrangTaiSanRepository;

    private final TinhTrangTaiSanMapper tinhTrangTaiSanMapper;

    public TinhTrangTaiSanServiceImpl(TinhTrangTaiSanRepository tinhTrangTaiSanRepository, TinhTrangTaiSanMapper tinhTrangTaiSanMapper) {
        this.tinhTrangTaiSanRepository = tinhTrangTaiSanRepository;
        this.tinhTrangTaiSanMapper = tinhTrangTaiSanMapper;
    }

    @Override
    public TinhTrangTaiSanDTO save(TinhTrangTaiSanDTO tinhTrangTaiSanDTO) {
        LOG.debug("Request to save TinhTrangTaiSan : {}", tinhTrangTaiSanDTO);
        TinhTrangTaiSan tinhTrangTaiSan = tinhTrangTaiSanMapper.toEntity(tinhTrangTaiSanDTO);
        tinhTrangTaiSan = tinhTrangTaiSanRepository.save(tinhTrangTaiSan);
        return tinhTrangTaiSanMapper.toDto(tinhTrangTaiSan);
    }

    @Override
    public TinhTrangTaiSanDTO update(TinhTrangTaiSanDTO tinhTrangTaiSanDTO) {
        LOG.debug("Request to update TinhTrangTaiSan : {}", tinhTrangTaiSanDTO);
        TinhTrangTaiSan tinhTrangTaiSan = tinhTrangTaiSanMapper.toEntity(tinhTrangTaiSanDTO);
        tinhTrangTaiSan = tinhTrangTaiSanRepository.save(tinhTrangTaiSan);
        return tinhTrangTaiSanMapper.toDto(tinhTrangTaiSan);
    }

    @Override
    public Optional<TinhTrangTaiSanDTO> partialUpdate(TinhTrangTaiSanDTO tinhTrangTaiSanDTO) {
        LOG.debug("Request to partially update TinhTrangTaiSan : {}", tinhTrangTaiSanDTO);

        return tinhTrangTaiSanRepository
            .findById(tinhTrangTaiSanDTO.getIdTinhTrang())
            .map(existingTinhTrangTaiSan -> {
                tinhTrangTaiSanMapper.partialUpdate(existingTinhTrangTaiSan, tinhTrangTaiSanDTO);

                return existingTinhTrangTaiSan;
            })
            .map(tinhTrangTaiSanRepository::save)
            .map(tinhTrangTaiSanMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TinhTrangTaiSanDTO> findAll() {
        LOG.debug("Request to get all TinhTrangTaiSans");
        return tinhTrangTaiSanRepository
            .findAll()
            .stream()
            .map(tinhTrangTaiSanMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TinhTrangTaiSanDTO> findOne(Long id) {
        LOG.debug("Request to get TinhTrangTaiSan : {}", id);
        return tinhTrangTaiSanRepository.findById(id).map(tinhTrangTaiSanMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete TinhTrangTaiSan : {}", id);
        tinhTrangTaiSanRepository.deleteById(id);
    }
}
