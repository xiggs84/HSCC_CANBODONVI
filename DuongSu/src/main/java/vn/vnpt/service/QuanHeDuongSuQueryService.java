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
import vn.vnpt.domain.QuanHeDuongSu;
import vn.vnpt.repository.QuanHeDuongSuRepository;
import vn.vnpt.service.criteria.QuanHeDuongSuCriteria;
import vn.vnpt.service.dto.QuanHeDuongSuDTO;
import vn.vnpt.service.mapper.QuanHeDuongSuMapper;

/**
 * Service for executing complex queries for {@link QuanHeDuongSu} entities in the database.
 * The main input is a {@link QuanHeDuongSuCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link QuanHeDuongSuDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QuanHeDuongSuQueryService extends QueryService<QuanHeDuongSu> {

    private static final Logger LOG = LoggerFactory.getLogger(QuanHeDuongSuQueryService.class);

    private final QuanHeDuongSuRepository quanHeDuongSuRepository;

    private final QuanHeDuongSuMapper quanHeDuongSuMapper;

    public QuanHeDuongSuQueryService(QuanHeDuongSuRepository quanHeDuongSuRepository, QuanHeDuongSuMapper quanHeDuongSuMapper) {
        this.quanHeDuongSuRepository = quanHeDuongSuRepository;
        this.quanHeDuongSuMapper = quanHeDuongSuMapper;
    }

    /**
     * Return a {@link Page} of {@link QuanHeDuongSuDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QuanHeDuongSuDTO> findByCriteria(QuanHeDuongSuCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<QuanHeDuongSu> specification = createSpecification(criteria);
        return quanHeDuongSuRepository.findAll(specification, page).map(quanHeDuongSuMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(QuanHeDuongSuCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<QuanHeDuongSu> specification = createSpecification(criteria);
        return quanHeDuongSuRepository.count(specification);
    }

    /**
     * Function to convert {@link QuanHeDuongSuCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<QuanHeDuongSu> createSpecification(QuanHeDuongSuCriteria criteria) {
        Specification<QuanHeDuongSu> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdQuanHe() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdQuanHe(), QuanHeDuongSu_.idQuanHe));
            }
            if (criteria.getIdDuongSuQh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDuongSuQh(), QuanHeDuongSu_.idDuongSuQh));
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), QuanHeDuongSu_.trangThai));
            }
            if (criteria.getDuongSuId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getDuongSuId(), root ->
                        root.join(QuanHeDuongSu_.duongSu, JoinType.LEFT).get(DuongSu_.idDuongSu)
                    )
                );
            }
        }
        return specification;
    }
}
