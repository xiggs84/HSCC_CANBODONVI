package vn.vnpt.service;

import jakarta.persistence.criteria.JoinType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import vn.vnpt.domain.*; // for static metamodels
import vn.vnpt.domain.ChiTietNganChanTaiSan;
import vn.vnpt.repository.ChiTietNganChanTaiSanRepository;
import vn.vnpt.service.criteria.ChiTietNganChanTaiSanCriteria;
import vn.vnpt.service.dto.ChiTietNganChanTaiSanDTO;
import vn.vnpt.service.mapper.ChiTietNganChanTaiSanMapper;

/**
 * Service for executing complex queries for {@link ChiTietNganChanTaiSan} entities in the database.
 * The main input is a {@link ChiTietNganChanTaiSanCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ChiTietNganChanTaiSanDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ChiTietNganChanTaiSanQueryService extends QueryService<ChiTietNganChanTaiSan> {

    private static final Logger LOG = LoggerFactory.getLogger(ChiTietNganChanTaiSanQueryService.class);

    private final ChiTietNganChanTaiSanRepository chiTietNganChanTaiSanRepository;

    private final ChiTietNganChanTaiSanMapper chiTietNganChanTaiSanMapper;

    public ChiTietNganChanTaiSanQueryService(
        ChiTietNganChanTaiSanRepository chiTietNganChanTaiSanRepository,
        ChiTietNganChanTaiSanMapper chiTietNganChanTaiSanMapper
    ) {
        this.chiTietNganChanTaiSanRepository = chiTietNganChanTaiSanRepository;
        this.chiTietNganChanTaiSanMapper = chiTietNganChanTaiSanMapper;
    }

    /**
     * Return a {@link List} of {@link ChiTietNganChanTaiSanDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ChiTietNganChanTaiSanDTO> findByCriteria(ChiTietNganChanTaiSanCriteria criteria) {
        LOG.debug("find by criteria : {}", criteria);
        final Specification<ChiTietNganChanTaiSan> specification = createSpecification(criteria);
        return chiTietNganChanTaiSanMapper.toDto(chiTietNganChanTaiSanRepository.findAll(specification));
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ChiTietNganChanTaiSanCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<ChiTietNganChanTaiSan> specification = createSpecification(criteria);
        return chiTietNganChanTaiSanRepository.count(specification);
    }

    /**
     * Function to convert {@link ChiTietNganChanTaiSanCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ChiTietNganChanTaiSan> createSpecification(ChiTietNganChanTaiSanCriteria criteria) {
        Specification<ChiTietNganChanTaiSan> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdNganChan(), ChiTietNganChanTaiSan_.idNganChan));
            }
            if (criteria.getNgayThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayThaoTac(), ChiTietNganChanTaiSan_.ngayThaoTac));
            }
            if (criteria.getSoHsCv() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoHsCv(), ChiTietNganChanTaiSan_.soHsCv));
            }
            if (criteria.getSoCc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoCc(), ChiTietNganChanTaiSan_.soCc));
            }
            if (criteria.getSoVaoSo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoVaoSo(), ChiTietNganChanTaiSan_.soVaoSo));
            }
            if (criteria.getMoTa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMoTa(), ChiTietNganChanTaiSan_.moTa));
            }
            if (criteria.getNgayNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayNganChan(), ChiTietNganChanTaiSan_.ngayNganChan));
            }
            if (criteria.getNgayBdNganChan() != null) {
                specification = specification.and(
                    buildRangeSpecification(criteria.getNgayBdNganChan(), ChiTietNganChanTaiSan_.ngayBdNganChan)
                );
            }
            if (criteria.getNgayKtNganChan() != null) {
                specification = specification.and(
                    buildRangeSpecification(criteria.getNgayKtNganChan(), ChiTietNganChanTaiSan_.ngayKtNganChan)
                );
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), ChiTietNganChanTaiSan_.trangThai));
            }
            if (criteria.getNguoiThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNguoiThaoTac(), ChiTietNganChanTaiSan_.nguoiThaoTac));
            }
            if (criteria.getLoaiNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLoaiNganChan(), ChiTietNganChanTaiSan_.loaiNganChan));
            }
            if (criteria.getNgayCongVan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayCongVan(), ChiTietNganChanTaiSan_.ngayCongVan));
            }
            if (criteria.getTaiSanId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getTaiSanId(), root ->
                        root.join(ChiTietNganChanTaiSan_.taiSan, JoinType.LEFT).get(TaiSan_.idTaiSan)
                    )
                );
            }
        }
        return specification;
    }
}
