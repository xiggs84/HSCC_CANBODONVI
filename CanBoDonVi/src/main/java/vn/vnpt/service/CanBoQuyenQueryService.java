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
import vn.vnpt.domain.CanBoQuyen;
import vn.vnpt.repository.CanBoQuyenRepository;
import vn.vnpt.service.criteria.CanBoQuyenCriteria;
import vn.vnpt.service.dto.CanBoQuyenDTO;
import vn.vnpt.service.mapper.CanBoQuyenMapper;

/**
 * Service for executing complex queries for {@link CanBoQuyen} entities in the database.
 * The main input is a {@link CanBoQuyenCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link CanBoQuyenDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CanBoQuyenQueryService extends QueryService<CanBoQuyen> {

    private static final Logger LOG = LoggerFactory.getLogger(CanBoQuyenQueryService.class);

    private final CanBoQuyenRepository canBoQuyenRepository;

    private final CanBoQuyenMapper canBoQuyenMapper;

    public CanBoQuyenQueryService(CanBoQuyenRepository canBoQuyenRepository, CanBoQuyenMapper canBoQuyenMapper) {
        this.canBoQuyenRepository = canBoQuyenRepository;
        this.canBoQuyenMapper = canBoQuyenMapper;
    }

    /**
     * Return a {@link Page} of {@link CanBoQuyenDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CanBoQuyenDTO> findByCriteria(CanBoQuyenCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CanBoQuyen> specification = createSpecification(criteria);
        return canBoQuyenRepository.findAll(specification, page).map(canBoQuyenMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CanBoQuyenCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<CanBoQuyen> specification = createSpecification(criteria);
        return canBoQuyenRepository.count(specification);
    }

    /**
     * Function to convert {@link CanBoQuyenCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CanBoQuyen> createSpecification(CanBoQuyenCriteria criteria) {
        Specification<CanBoQuyen> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CanBoQuyen_.id));
            }
            if (criteria.getDanhMucDonViId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getDanhMucDonViId(), root ->
                        root.join(CanBoQuyen_.danhMucDonVi, JoinType.LEFT).get(DanhMucDonVi_.idDonVi)
                    )
                );
            }
        }
        return specification;
    }
}
