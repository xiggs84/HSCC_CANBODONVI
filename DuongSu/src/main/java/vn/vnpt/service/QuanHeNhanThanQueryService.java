package vn.vnpt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import vn.vnpt.domain.*; // for static metamodels
import vn.vnpt.domain.QuanHeNhanThan;
import vn.vnpt.repository.QuanHeNhanThanRepository;
import vn.vnpt.service.criteria.QuanHeNhanThanCriteria;
import vn.vnpt.service.dto.QuanHeNhanThanDTO;
import vn.vnpt.service.mapper.QuanHeNhanThanMapper;

/**
 * Service for executing complex queries for {@link QuanHeNhanThan} entities in the database.
 * The main input is a {@link QuanHeNhanThanCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link QuanHeNhanThanDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QuanHeNhanThanQueryService extends QueryService<QuanHeNhanThan> {

    private static final Logger LOG = LoggerFactory.getLogger(QuanHeNhanThanQueryService.class);

    private final QuanHeNhanThanRepository quanHeNhanThanRepository;

    private final QuanHeNhanThanMapper quanHeNhanThanMapper;

    public QuanHeNhanThanQueryService(QuanHeNhanThanRepository quanHeNhanThanRepository, QuanHeNhanThanMapper quanHeNhanThanMapper) {
        this.quanHeNhanThanRepository = quanHeNhanThanRepository;
        this.quanHeNhanThanMapper = quanHeNhanThanMapper;
    }

    /**
     * Return a {@link Page} of {@link QuanHeNhanThanDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QuanHeNhanThanDTO> findByCriteria(QuanHeNhanThanCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<QuanHeNhanThan> specification = createSpecification(criteria);
        return quanHeNhanThanRepository.findAll(specification, page).map(quanHeNhanThanMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(QuanHeNhanThanCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<QuanHeNhanThan> specification = createSpecification(criteria);
        return quanHeNhanThanRepository.count(specification);
    }

    /**
     * Function to convert {@link QuanHeNhanThanCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<QuanHeNhanThan> createSpecification(QuanHeNhanThanCriteria criteria) {
        Specification<QuanHeNhanThan> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdQuanHe() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdQuanHe(), QuanHeNhanThan_.idQuanHe));
            }
            if (criteria.getDienGiai() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDienGiai(), QuanHeNhanThan_.dienGiai));
            }
            if (criteria.getIdQuanHeDoiUng() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdQuanHeDoiUng(), QuanHeNhanThan_.idQuanHeDoiUng));
            }
        }
        return specification;
    }
}
