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
import vn.vnpt.domain.DmHuyenTmp;
import vn.vnpt.repository.DmHuyenTmpRepository;
import vn.vnpt.service.criteria.DmHuyenTmpCriteria;
import vn.vnpt.service.dto.DmHuyenTmpDTO;
import vn.vnpt.service.mapper.DmHuyenTmpMapper;

/**
 * Service for executing complex queries for {@link DmHuyenTmp} entities in the database.
 * The main input is a {@link DmHuyenTmpCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DmHuyenTmpDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DmHuyenTmpQueryService extends QueryService<DmHuyenTmp> {

    private static final Logger LOG = LoggerFactory.getLogger(DmHuyenTmpQueryService.class);

    private final DmHuyenTmpRepository dmHuyenTmpRepository;

    private final DmHuyenTmpMapper dmHuyenTmpMapper;

    public DmHuyenTmpQueryService(DmHuyenTmpRepository dmHuyenTmpRepository, DmHuyenTmpMapper dmHuyenTmpMapper) {
        this.dmHuyenTmpRepository = dmHuyenTmpRepository;
        this.dmHuyenTmpMapper = dmHuyenTmpMapper;
    }

    /**
     * Return a {@link Page} of {@link DmHuyenTmpDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DmHuyenTmpDTO> findByCriteria(DmHuyenTmpCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DmHuyenTmp> specification = createSpecification(criteria);
        return dmHuyenTmpRepository.findAll(specification, page).map(dmHuyenTmpMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DmHuyenTmpCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DmHuyenTmp> specification = createSpecification(criteria);
        return dmHuyenTmpRepository.count(specification);
    }

    /**
     * Function to convert {@link DmHuyenTmpCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DmHuyenTmp> createSpecification(DmHuyenTmpCriteria criteria) {
        Specification<DmHuyenTmp> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getMaHuyen() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaHuyen(), DmHuyenTmp_.maHuyen));
            }
            if (criteria.getTenHuyen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenHuyen(), DmHuyenTmp_.tenHuyen));
            }
        }
        return specification;
    }
}
