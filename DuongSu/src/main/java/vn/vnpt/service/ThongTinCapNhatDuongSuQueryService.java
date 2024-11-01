package vn.vnpt.service;

import jakarta.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import vn.vnpt.domain.*; // for static metamodels
import vn.vnpt.domain.ThongTinCapNhatDuongSu;
import vn.vnpt.repository.ThongTinCapNhatDuongSuRepository;
import vn.vnpt.service.criteria.ThongTinCapNhatDuongSuCriteria;
import vn.vnpt.service.dto.ThongTinCapNhatDuongSuDTO;
import vn.vnpt.service.mapper.ThongTinCapNhatDuongSuMapper;

/**
 * Service for executing complex queries for {@link ThongTinCapNhatDuongSu} entities in the database.
 * The main input is a {@link ThongTinCapNhatDuongSuCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link ThongTinCapNhatDuongSuDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ThongTinCapNhatDuongSuQueryService extends QueryService<ThongTinCapNhatDuongSu> {

    private static final Logger LOG = LoggerFactory.getLogger(ThongTinCapNhatDuongSuQueryService.class);

    private final ThongTinCapNhatDuongSuRepository thongTinCapNhatDuongSuRepository;

    private final ThongTinCapNhatDuongSuMapper thongTinCapNhatDuongSuMapper;

    public ThongTinCapNhatDuongSuQueryService(
        ThongTinCapNhatDuongSuRepository thongTinCapNhatDuongSuRepository,
        ThongTinCapNhatDuongSuMapper thongTinCapNhatDuongSuMapper
    ) {
        this.thongTinCapNhatDuongSuRepository = thongTinCapNhatDuongSuRepository;
        this.thongTinCapNhatDuongSuMapper = thongTinCapNhatDuongSuMapper;
    }

    /**
     * Return a {@link Page} of {@link ThongTinCapNhatDuongSuDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ThongTinCapNhatDuongSuDTO> findByCriteria(ThongTinCapNhatDuongSuCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ThongTinCapNhatDuongSu> specification = createSpecification(criteria);
        return thongTinCapNhatDuongSuRepository.findAll(specification, page).map(thongTinCapNhatDuongSuMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ThongTinCapNhatDuongSuCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<ThongTinCapNhatDuongSu> specification = createSpecification(criteria);
        return thongTinCapNhatDuongSuRepository.count(specification);
    }

    /**
     * Function to convert {@link ThongTinCapNhatDuongSuCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ThongTinCapNhatDuongSu> createSpecification(ThongTinCapNhatDuongSuCriteria criteria) {
        Specification<ThongTinCapNhatDuongSu> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdCapNhat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdCapNhat(), ThongTinCapNhatDuongSu_.idCapNhat));
            }
            if (criteria.getTenDuongSu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenDuongSu(), ThongTinCapNhatDuongSu_.tenDuongSu));
            }
            if (criteria.getSoGiayTo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoGiayTo(), ThongTinCapNhatDuongSu_.soGiayTo));
            }
            if (criteria.getNgayCapNhat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayCapNhat(), ThongTinCapNhatDuongSu_.ngayCapNhat));
            }
            if (criteria.getLoaiDuongSuId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getLoaiDuongSuId(), root ->
                        root.join(ThongTinCapNhatDuongSu_.loaiDuongSu, JoinType.LEFT).get(LoaiDuongSu_.idLoaiDuongSu)
                    )
                );
            }
            if (criteria.getLoaiGiayToId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getLoaiGiayToId(), root ->
                        root.join(ThongTinCapNhatDuongSu_.loaiGiayTo, JoinType.LEFT).get(LoaiGiayTo_.idLoaiGiayTo)
                    )
                );
            }
            if (criteria.getDuongSuId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getDuongSuId(), root ->
                        root.join(ThongTinCapNhatDuongSu_.duongSu, JoinType.LEFT).get(DuongSu_.idDuongSu)
                    )
                );
            }
        }
        return specification;
    }
}
