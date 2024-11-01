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
import vn.vnpt.domain.DanhMucCapQuanLy;
import vn.vnpt.repository.DanhMucCapQuanLyRepository;
import vn.vnpt.service.criteria.DanhMucCapQuanLyCriteria;
import vn.vnpt.service.dto.DanhMucCapQuanLyDTO;
import vn.vnpt.service.mapper.DanhMucCapQuanLyMapper;

/**
 * Service for executing complex queries for {@link DanhMucCapQuanLy} entities in the database.
 * The main input is a {@link DanhMucCapQuanLyCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DanhMucCapQuanLyDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DanhMucCapQuanLyQueryService extends QueryService<DanhMucCapQuanLy> {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucCapQuanLyQueryService.class);

    private final DanhMucCapQuanLyRepository danhMucCapQuanLyRepository;

    private final DanhMucCapQuanLyMapper danhMucCapQuanLyMapper;

    public DanhMucCapQuanLyQueryService(
        DanhMucCapQuanLyRepository danhMucCapQuanLyRepository,
        DanhMucCapQuanLyMapper danhMucCapQuanLyMapper
    ) {
        this.danhMucCapQuanLyRepository = danhMucCapQuanLyRepository;
        this.danhMucCapQuanLyMapper = danhMucCapQuanLyMapper;
    }

    /**
     * Return a {@link Page} of {@link DanhMucCapQuanLyDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhMucCapQuanLyDTO> findByCriteria(DanhMucCapQuanLyCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DanhMucCapQuanLy> specification = createSpecification(criteria);
        return danhMucCapQuanLyRepository.findAll(specification, page).map(danhMucCapQuanLyMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DanhMucCapQuanLyCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DanhMucCapQuanLy> specification = createSpecification(criteria);
        return danhMucCapQuanLyRepository.count(specification);
    }

    /**
     * Function to convert {@link DanhMucCapQuanLyCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DanhMucCapQuanLy> createSpecification(DanhMucCapQuanLyCriteria criteria) {
        Specification<DanhMucCapQuanLy> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdCapQl() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdCapQl(), DanhMucCapQuanLy_.idCapQl));
            }
            if (criteria.getDienGiai() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDienGiai(), DanhMucCapQuanLy_.dienGiai));
            }
        }
        return specification;
    }
}
