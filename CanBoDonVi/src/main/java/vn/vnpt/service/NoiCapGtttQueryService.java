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
import vn.vnpt.domain.NoiCapGttt;
import vn.vnpt.repository.NoiCapGtttRepository;
import vn.vnpt.service.criteria.NoiCapGtttCriteria;
import vn.vnpt.service.dto.NoiCapGtttDTO;
import vn.vnpt.service.mapper.NoiCapGtttMapper;

/**
 * Service for executing complex queries for {@link NoiCapGttt} entities in the database.
 * The main input is a {@link NoiCapGtttCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link NoiCapGtttDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NoiCapGtttQueryService extends QueryService<NoiCapGttt> {

    private static final Logger LOG = LoggerFactory.getLogger(NoiCapGtttQueryService.class);

    private final NoiCapGtttRepository noiCapGtttRepository;

    private final NoiCapGtttMapper noiCapGtttMapper;

    public NoiCapGtttQueryService(NoiCapGtttRepository noiCapGtttRepository, NoiCapGtttMapper noiCapGtttMapper) {
        this.noiCapGtttRepository = noiCapGtttRepository;
        this.noiCapGtttMapper = noiCapGtttMapper;
    }

    /**
     * Return a {@link Page} of {@link NoiCapGtttDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NoiCapGtttDTO> findByCriteria(NoiCapGtttCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NoiCapGttt> specification = createSpecification(criteria);
        return noiCapGtttRepository.findAll(specification, page).map(noiCapGtttMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NoiCapGtttCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<NoiCapGttt> specification = createSpecification(criteria);
        return noiCapGtttRepository.count(specification);
    }

    /**
     * Function to convert {@link NoiCapGtttCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<NoiCapGttt> createSpecification(NoiCapGtttCriteria criteria) {
        Specification<NoiCapGttt> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdNoiCap() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdNoiCap(), NoiCapGttt_.idNoiCap));
            }
            if (criteria.getDienGiai() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDienGiai(), NoiCapGttt_.dienGiai));
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), NoiCapGttt_.trangThai));
            }
        }
        return specification;
    }
}
