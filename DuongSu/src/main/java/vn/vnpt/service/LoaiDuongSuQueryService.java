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
import vn.vnpt.domain.LoaiDuongSu;
import vn.vnpt.repository.LoaiDuongSuRepository;
import vn.vnpt.service.criteria.LoaiDuongSuCriteria;
import vn.vnpt.service.dto.LoaiDuongSuDTO;
import vn.vnpt.service.mapper.LoaiDuongSuMapper;

/**
 * Service for executing complex queries for {@link LoaiDuongSu} entities in the database.
 * The main input is a {@link LoaiDuongSuCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link LoaiDuongSuDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LoaiDuongSuQueryService extends QueryService<LoaiDuongSu> {

    private static final Logger LOG = LoggerFactory.getLogger(LoaiDuongSuQueryService.class);

    private final LoaiDuongSuRepository loaiDuongSuRepository;

    private final LoaiDuongSuMapper loaiDuongSuMapper;

    public LoaiDuongSuQueryService(LoaiDuongSuRepository loaiDuongSuRepository, LoaiDuongSuMapper loaiDuongSuMapper) {
        this.loaiDuongSuRepository = loaiDuongSuRepository;
        this.loaiDuongSuMapper = loaiDuongSuMapper;
    }

    /**
     * Return a {@link Page} of {@link LoaiDuongSuDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LoaiDuongSuDTO> findByCriteria(LoaiDuongSuCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<LoaiDuongSu> specification = createSpecification(criteria);
        return loaiDuongSuRepository.findAll(specification, page).map(loaiDuongSuMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LoaiDuongSuCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<LoaiDuongSu> specification = createSpecification(criteria);
        return loaiDuongSuRepository.count(specification);
    }

    /**
     * Function to convert {@link LoaiDuongSuCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<LoaiDuongSu> createSpecification(LoaiDuongSuCriteria criteria) {
        Specification<LoaiDuongSu> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdLoaiDuongSu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdLoaiDuongSu(), LoaiDuongSu_.idLoaiDuongSu));
            }
            if (criteria.getTenLoaiDuongSu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenLoaiDuongSu(), LoaiDuongSu_.tenLoaiDuongSu));
            }
            if (criteria.getIdLoaiDuongSuId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getIdLoaiDuongSuId(), root ->
                        root.join(LoaiDuongSu_.idLoaiDuongSus, JoinType.LEFT).get(DuongSu_.idDuongSu)
                    )
                );
            }
            if (criteria.getThongTinCapNhatDuongSuId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getThongTinCapNhatDuongSuId(), root ->
                        root.join(LoaiDuongSu_.thongTinCapNhatDuongSus, JoinType.LEFT).get(ThongTinCapNhatDuongSu_.idCapNhat)
                    )
                );
            }
        }
        return specification;
    }
}
