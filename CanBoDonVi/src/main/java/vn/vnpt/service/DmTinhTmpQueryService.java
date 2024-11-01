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
import vn.vnpt.domain.DmTinhTmp;
import vn.vnpt.repository.DmTinhTmpRepository;
import vn.vnpt.service.criteria.DmTinhTmpCriteria;
import vn.vnpt.service.dto.DmTinhTmpDTO;
import vn.vnpt.service.mapper.DmTinhTmpMapper;

/**
 * Service for executing complex queries for {@link DmTinhTmp} entities in the database.
 * The main input is a {@link DmTinhTmpCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DmTinhTmpDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DmTinhTmpQueryService extends QueryService<DmTinhTmp> {

    private static final Logger LOG = LoggerFactory.getLogger(DmTinhTmpQueryService.class);

    private final DmTinhTmpRepository dmTinhTmpRepository;

    private final DmTinhTmpMapper dmTinhTmpMapper;

    public DmTinhTmpQueryService(DmTinhTmpRepository dmTinhTmpRepository, DmTinhTmpMapper dmTinhTmpMapper) {
        this.dmTinhTmpRepository = dmTinhTmpRepository;
        this.dmTinhTmpMapper = dmTinhTmpMapper;
    }

    /**
     * Return a {@link Page} of {@link DmTinhTmpDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DmTinhTmpDTO> findByCriteria(DmTinhTmpCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DmTinhTmp> specification = createSpecification(criteria);
        return dmTinhTmpRepository.findAll(specification, page).map(dmTinhTmpMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DmTinhTmpCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DmTinhTmp> specification = createSpecification(criteria);
        return dmTinhTmpRepository.count(specification);
    }

    /**
     * Function to convert {@link DmTinhTmpCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DmTinhTmp> createSpecification(DmTinhTmpCriteria criteria) {
        Specification<DmTinhTmp> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getMaTinh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaTinh(), DmTinhTmp_.maTinh));
            }
            if (criteria.getTenTinh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenTinh(), DmTinhTmp_.tenTinh));
            }
        }
        return specification;
    }
}
