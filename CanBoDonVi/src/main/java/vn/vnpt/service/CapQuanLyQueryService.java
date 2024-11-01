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
import vn.vnpt.domain.CapQuanLy;
import vn.vnpt.repository.CapQuanLyRepository;
import vn.vnpt.service.criteria.CapQuanLyCriteria;
import vn.vnpt.service.dto.CapQuanLyDTO;
import vn.vnpt.service.mapper.CapQuanLyMapper;

/**
 * Service for executing complex queries for {@link CapQuanLy} entities in the database.
 * The main input is a {@link CapQuanLyCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link CapQuanLyDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CapQuanLyQueryService extends QueryService<CapQuanLy> {

    private static final Logger LOG = LoggerFactory.getLogger(CapQuanLyQueryService.class);

    private final CapQuanLyRepository capQuanLyRepository;

    private final CapQuanLyMapper capQuanLyMapper;

    public CapQuanLyQueryService(CapQuanLyRepository capQuanLyRepository, CapQuanLyMapper capQuanLyMapper) {
        this.capQuanLyRepository = capQuanLyRepository;
        this.capQuanLyMapper = capQuanLyMapper;
    }

    /**
     * Return a {@link Page} of {@link CapQuanLyDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CapQuanLyDTO> findByCriteria(CapQuanLyCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CapQuanLy> specification = createSpecification(criteria);
        return capQuanLyRepository.findAll(specification, page).map(capQuanLyMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CapQuanLyCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<CapQuanLy> specification = createSpecification(criteria);
        return capQuanLyRepository.count(specification);
    }

    /**
     * Function to convert {@link CapQuanLyCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CapQuanLy> createSpecification(CapQuanLyCriteria criteria) {
        Specification<CapQuanLy> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdCapQl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdCapQl(), CapQuanLy_.idCapQl));
            }
            if (criteria.getTenCapQl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenCapQl(), CapQuanLy_.tenCapQl));
            }
            if (criteria.getIdCapQlId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getIdCapQlId(), root ->
                        root.join(CapQuanLy_.idCapQls, JoinType.LEFT).get(DanhMucDonVi_.idDonVi)
                    )
                );
            }
        }
        return specification;
    }
}
