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
import vn.vnpt.domain.ThongTinCapNhatTaiSan;
import vn.vnpt.repository.ThongTinCapNhatTaiSanRepository;
import vn.vnpt.service.criteria.ThongTinCapNhatTaiSanCriteria;
import vn.vnpt.service.dto.ThongTinCapNhatTaiSanDTO;
import vn.vnpt.service.mapper.ThongTinCapNhatTaiSanMapper;

/**
 * Service for executing complex queries for {@link ThongTinCapNhatTaiSan} entities in the database.
 * The main input is a {@link ThongTinCapNhatTaiSanCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ThongTinCapNhatTaiSanDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ThongTinCapNhatTaiSanQueryService extends QueryService<ThongTinCapNhatTaiSan> {

    private static final Logger LOG = LoggerFactory.getLogger(ThongTinCapNhatTaiSanQueryService.class);

    private final ThongTinCapNhatTaiSanRepository thongTinCapNhatTaiSanRepository;

    private final ThongTinCapNhatTaiSanMapper thongTinCapNhatTaiSanMapper;

    public ThongTinCapNhatTaiSanQueryService(
        ThongTinCapNhatTaiSanRepository thongTinCapNhatTaiSanRepository,
        ThongTinCapNhatTaiSanMapper thongTinCapNhatTaiSanMapper
    ) {
        this.thongTinCapNhatTaiSanRepository = thongTinCapNhatTaiSanRepository;
        this.thongTinCapNhatTaiSanMapper = thongTinCapNhatTaiSanMapper;
    }

    /**
     * Return a {@link List} of {@link ThongTinCapNhatTaiSanDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ThongTinCapNhatTaiSanDTO> findByCriteria(ThongTinCapNhatTaiSanCriteria criteria) {
        LOG.debug("find by criteria : {}", criteria);
        final Specification<ThongTinCapNhatTaiSan> specification = createSpecification(criteria);
        return thongTinCapNhatTaiSanMapper.toDto(thongTinCapNhatTaiSanRepository.findAll(specification));
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ThongTinCapNhatTaiSanCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<ThongTinCapNhatTaiSan> specification = createSpecification(criteria);
        return thongTinCapNhatTaiSanRepository.count(specification);
    }

    /**
     * Function to convert {@link ThongTinCapNhatTaiSanCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ThongTinCapNhatTaiSan> createSpecification(ThongTinCapNhatTaiSanCriteria criteria) {
        Specification<ThongTinCapNhatTaiSan> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdCapNhat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdCapNhat(), ThongTinCapNhatTaiSan_.idCapNhat));
            }
            if (criteria.getTenTaiSan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenTaiSan(), ThongTinCapNhatTaiSan_.tenTaiSan));
            }
            if (criteria.getNgayCapNhat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayCapNhat(), ThongTinCapNhatTaiSan_.ngayCapNhat));
            }
            if (criteria.getTaiSanId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getTaiSanId(), root ->
                        root.join(ThongTinCapNhatTaiSan_.taiSan, JoinType.LEFT).get(TaiSan_.idTaiSan)
                    )
                );
            }
            if (criteria.getDanhMucLoaiTaiSanId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getDanhMucLoaiTaiSanId(), root ->
                        root.join(ThongTinCapNhatTaiSan_.danhMucLoaiTaiSan, JoinType.LEFT).get(DanhMucLoaiTaiSan_.idLoaiTs)
                    )
                );
            }
        }
        return specification;
    }
}
