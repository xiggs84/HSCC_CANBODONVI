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
import vn.vnpt.domain.QuanHeMaster;
import vn.vnpt.repository.QuanHeMasterRepository;
import vn.vnpt.service.criteria.QuanHeMasterCriteria;
import vn.vnpt.service.dto.QuanHeMasterDTO;
import vn.vnpt.service.mapper.QuanHeMasterMapper;

/**
 * Service for executing complex queries for {@link QuanHeMaster} entities in the database.
 * The main input is a {@link QuanHeMasterCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link QuanHeMasterDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QuanHeMasterQueryService extends QueryService<QuanHeMaster> {

    private static final Logger LOG = LoggerFactory.getLogger(QuanHeMasterQueryService.class);

    private final QuanHeMasterRepository quanHeMasterRepository;

    private final QuanHeMasterMapper quanHeMasterMapper;

    public QuanHeMasterQueryService(QuanHeMasterRepository quanHeMasterRepository, QuanHeMasterMapper quanHeMasterMapper) {
        this.quanHeMasterRepository = quanHeMasterRepository;
        this.quanHeMasterMapper = quanHeMasterMapper;
    }

    /**
     * Return a {@link Page} of {@link QuanHeMasterDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QuanHeMasterDTO> findByCriteria(QuanHeMasterCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<QuanHeMaster> specification = createSpecification(criteria);
        return quanHeMasterRepository.findAll(specification, page).map(quanHeMasterMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(QuanHeMasterCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<QuanHeMaster> specification = createSpecification(criteria);
        return quanHeMasterRepository.count(specification);
    }

    /**
     * Function to convert {@link QuanHeMasterCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<QuanHeMaster> createSpecification(QuanHeMasterCriteria criteria) {
        Specification<QuanHeMaster> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), QuanHeMaster_.id));
            }
            if (criteria.getIdDuongSu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDuongSu(), QuanHeMaster_.idDuongSu));
            }
            if (criteria.getIdDuongSuQh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDuongSuQh(), QuanHeMaster_.idDuongSuQh));
            }
        }
        return specification;
    }
}
