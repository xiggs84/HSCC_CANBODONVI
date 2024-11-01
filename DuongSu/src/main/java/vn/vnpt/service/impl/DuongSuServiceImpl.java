package vn.vnpt.service.impl;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DuongSu;
import vn.vnpt.repository.DuongSuRepository;
import vn.vnpt.repository.QuanHeDuongSuRepository;
import vn.vnpt.service.DuongSuService;
import vn.vnpt.service.dto.DuongSuDTO;
import vn.vnpt.service.mapper.DuongSuMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DuongSu}.
 */
@Service
@Transactional
public class DuongSuServiceImpl implements DuongSuService {

    private static final Logger LOG = LoggerFactory.getLogger(DuongSuServiceImpl.class);

    private final DuongSuRepository duongSuRepository;

    private final DuongSuMapper duongSuMapper;
    private final QuanHeDuongSuRepository quanHeDuongSuRepository;

    public DuongSuServiceImpl(DuongSuRepository duongSuRepository, DuongSuMapper duongSuMapper, QuanHeDuongSuRepository quanHeDuongSuRepository) {
        this.duongSuRepository = duongSuRepository;
        this.duongSuMapper = duongSuMapper;
        this.quanHeDuongSuRepository = quanHeDuongSuRepository;
    }

    @Override
    public DuongSuDTO save(DuongSuDTO duongSuDTO) {
        LOG.debug("Request to save DuongSu : {}", duongSuDTO);
        DuongSu duongSu = duongSuMapper.toEntity(duongSuDTO);
        duongSu = duongSuRepository.save(duongSu);
        return duongSuMapper.toDto(duongSu);
    }

    @Override
    public DuongSuDTO update(DuongSuDTO duongSuDTO) {
        LOG.debug("Request to update DuongSu : {}", duongSuDTO);
        DuongSu duongSu = duongSuMapper.toEntity(duongSuDTO);
        duongSu = duongSuRepository.save(duongSu);
        return duongSuMapper.toDto(duongSu);
    }

    @Override
    public Optional<DuongSuDTO> partialUpdate(DuongSuDTO duongSuDTO) {
        LOG.debug("Request to partially update DuongSu : {}", duongSuDTO);

        return duongSuRepository
            .findById(duongSuDTO.getIdDuongSu())
            .map(existingDuongSu -> {
                duongSuMapper.partialUpdate(existingDuongSu, duongSuDTO);

                return existingDuongSu;
            })
            .map(duongSuRepository::save)
            .map(duongSuMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DuongSuDTO> findOne(Long id) {
        LOG.debug("Request to get DuongSu : {}", id);
        return duongSuRepository.findById(id).map(duongSuMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete DuongSu : {}", id);
        duongSuRepository.deleteById(id);
    }

    public String stripHtmlTags(String input) {
        if (input == null) return null;
        return input.replaceAll("<[^>]*>", ""); // Loại bỏ tất cả các thẻ HTML
    }

    public String removeDiacritics(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
    }

    public List<DuongSuDTO> searchByTenAndOppositeGender(String tenDuongSu, String gender) {
        String oppositeGender = gender.equals("Nam") ? "Nu" : "Nam";

        List<DuongSu> allDuongSus = duongSuRepository.findAll();

        Set<Long> idsInQuanHeDuongSu = new HashSet<>(quanHeDuongSuRepository.findAll()
            .stream()
            .filter(qh -> qh.getDuongSu() != null) // Kiểm tra không null
            .map(qh -> qh.getDuongSu().getIdDuongSu())
            .collect(Collectors.toList()));

        LOG.info("Dang tim kiem voi ten: {} va gioi tinh doi lap: {}", tenDuongSu, oppositeGender);

        return allDuongSus.stream()
            .filter(duongSu -> {
                String thongTinDs = duongSu.getThongTinDs();
                // Loại bỏ thẻ HTML từ thongTinDs
                String thongTinDsStripped = stripHtmlTags(thongTinDs);
                boolean matchesName = duongSu.getTenDuongSu() != null && duongSu.getTenDuongSu().contains(tenDuongSu);
                boolean matchesOppositeGender = thongTinDsStripped != null && removeDiacritics(thongTinDsStripped).contains("Gioi tinh: " + removeDiacritics(oppositeGender));

                LOG.debug("Kiem tra gioi tinh doi lap: {}, gia tri thongTinDs: {}", matchesOppositeGender, thongTinDsStripped);
                boolean notInQuanHeDuongSu = !idsInQuanHeDuongSu.contains(duongSu.getIdDuongSu());

                // Log các điều kiện kiểm tra
                LOG.debug("Kiem tra duong su: {}, ten: {}, gioi tinh: {}, matchesName: {}, matchesOppositeGender: {}, notInQuanHeDuongSu: {}",
                    duongSu.getIdDuongSu(),
                    duongSu.getTenDuongSu(),
                    thongTinDsStripped.contains("Gioi tinh: " + gender),
                    matchesName,
                    matchesOppositeGender,
                    notInQuanHeDuongSu);

                return matchesName && matchesOppositeGender && notInQuanHeDuongSu;
            })
            .map(duongSuMapper::toDto)
            .collect(Collectors.toList());
    }
}
