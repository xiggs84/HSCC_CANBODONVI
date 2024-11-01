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
import vn.vnpt.domain.Quyen;
import vn.vnpt.repository.QuyenRepository;
import vn.vnpt.service.criteria.QuyenCriteria;
import vn.vnpt.service.dto.QuyenDTO;
import vn.vnpt.service.mapper.QuyenMapper;

/**
 * Service for executing complex queries for {@link Quyen} entities in the database.
 * The main input is a {@link QuyenCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link QuyenDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QuyenQueryService extends QueryService<Quyen> {

    private static final Logger LOG = LoggerFactory.getLogger(QuyenQueryService.class);

    private final QuyenRepository quyenRepository;

    private final QuyenMapper quyenMapper;

    public QuyenQueryService(QuyenRepository quyenRepository, QuyenMapper quyenMapper) {
        this.quyenRepository = quyenRepository;
        this.quyenMapper = quyenMapper;
    }

    /**
     * Return a {@link Page} of {@link QuyenDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QuyenDTO> findByCriteria(QuyenCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Quyen> specification = createSpecification(criteria);
        return quyenRepository.findAll(specification, page).map(quyenMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(QuyenCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<Quyen> specification = createSpecification(criteria);
        return quyenRepository.count(specification);
    }

    /**
     * Function to convert {@link QuyenCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Quyen> createSpecification(QuyenCriteria criteria) {
        Specification<Quyen> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdQuyen() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdQuyen(), Quyen_.idQuyen));
            }
            if (criteria.getTenQuyen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenQuyen(), Quyen_.tenQuyen));
            }
        }
        return specification;
    }
}
