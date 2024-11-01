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
import vn.vnpt.domain.DmXaTmp;
import vn.vnpt.repository.DmXaTmpRepository;
import vn.vnpt.service.criteria.DmXaTmpCriteria;
import vn.vnpt.service.dto.DmXaTmpDTO;
import vn.vnpt.service.mapper.DmXaTmpMapper;

/**
 * Service for executing complex queries for {@link DmXaTmp} entities in the database.
 * The main input is a {@link DmXaTmpCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DmXaTmpDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DmXaTmpQueryService extends QueryService<DmXaTmp> {

    private static final Logger LOG = LoggerFactory.getLogger(DmXaTmpQueryService.class);

    private final DmXaTmpRepository dmXaTmpRepository;

    private final DmXaTmpMapper dmXaTmpMapper;

    public DmXaTmpQueryService(DmXaTmpRepository dmXaTmpRepository, DmXaTmpMapper dmXaTmpMapper) {
        this.dmXaTmpRepository = dmXaTmpRepository;
        this.dmXaTmpMapper = dmXaTmpMapper;
    }

    /**
     * Return a {@link Page} of {@link DmXaTmpDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DmXaTmpDTO> findByCriteria(DmXaTmpCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DmXaTmp> specification = createSpecification(criteria);
        return dmXaTmpRepository.findAll(specification, page).map(dmXaTmpMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DmXaTmpCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DmXaTmp> specification = createSpecification(criteria);
        return dmXaTmpRepository.count(specification);
    }

    /**
     * Function to convert {@link DmXaTmpCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DmXaTmp> createSpecification(DmXaTmpCriteria criteria) {
        Specification<DmXaTmp> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getMaXa() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaXa(), DmXaTmp_.maXa));
            }
            if (criteria.getTenXa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenXa(), DmXaTmp_.tenXa));
            }
        }
        return specification;
    }
}
