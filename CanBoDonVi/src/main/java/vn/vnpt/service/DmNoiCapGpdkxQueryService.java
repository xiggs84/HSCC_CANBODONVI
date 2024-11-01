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
import vn.vnpt.domain.DmNoiCapGpdkx;
import vn.vnpt.repository.DmNoiCapGpdkxRepository;
import vn.vnpt.service.criteria.DmNoiCapGpdkxCriteria;
import vn.vnpt.service.dto.DmNoiCapGpdkxDTO;
import vn.vnpt.service.mapper.DmNoiCapGpdkxMapper;

/**
 * Service for executing complex queries for {@link DmNoiCapGpdkx} entities in the database.
 * The main input is a {@link DmNoiCapGpdkxCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DmNoiCapGpdkxDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DmNoiCapGpdkxQueryService extends QueryService<DmNoiCapGpdkx> {

    private static final Logger LOG = LoggerFactory.getLogger(DmNoiCapGpdkxQueryService.class);

    private final DmNoiCapGpdkxRepository dmNoiCapGpdkxRepository;

    private final DmNoiCapGpdkxMapper dmNoiCapGpdkxMapper;

    public DmNoiCapGpdkxQueryService(DmNoiCapGpdkxRepository dmNoiCapGpdkxRepository, DmNoiCapGpdkxMapper dmNoiCapGpdkxMapper) {
        this.dmNoiCapGpdkxRepository = dmNoiCapGpdkxRepository;
        this.dmNoiCapGpdkxMapper = dmNoiCapGpdkxMapper;
    }

    /**
     * Return a {@link Page} of {@link DmNoiCapGpdkxDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DmNoiCapGpdkxDTO> findByCriteria(DmNoiCapGpdkxCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DmNoiCapGpdkx> specification = createSpecification(criteria);
        return dmNoiCapGpdkxRepository.findAll(specification, page).map(dmNoiCapGpdkxMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DmNoiCapGpdkxCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DmNoiCapGpdkx> specification = createSpecification(criteria);
        return dmNoiCapGpdkxRepository.count(specification);
    }

    /**
     * Function to convert {@link DmNoiCapGpdkxCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DmNoiCapGpdkx> createSpecification(DmNoiCapGpdkxCriteria criteria) {
        Specification<DmNoiCapGpdkx> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdNoiCap() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdNoiCap(), DmNoiCapGpdkx_.idNoiCap));
            }
            if (criteria.getDienGiai() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDienGiai(), DmNoiCapGpdkx_.dienGiai));
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), DmNoiCapGpdkx_.trangThai));
            }
        }
        return specification;
    }
}
