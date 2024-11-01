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
import vn.vnpt.domain.DanhMucTinh;
import vn.vnpt.repository.DanhMucTinhRepository;
import vn.vnpt.service.criteria.DanhMucTinhCriteria;
import vn.vnpt.service.dto.DanhMucTinhDTO;
import vn.vnpt.service.mapper.DanhMucTinhMapper;

/**
 * Service for executing complex queries for {@link DanhMucTinh} entities in the database.
 * The main input is a {@link DanhMucTinhCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DanhMucTinhDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DanhMucTinhQueryService extends QueryService<DanhMucTinh> {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucTinhQueryService.class);

    private final DanhMucTinhRepository danhMucTinhRepository;

    private final DanhMucTinhMapper danhMucTinhMapper;

    public DanhMucTinhQueryService(DanhMucTinhRepository danhMucTinhRepository, DanhMucTinhMapper danhMucTinhMapper) {
        this.danhMucTinhRepository = danhMucTinhRepository;
        this.danhMucTinhMapper = danhMucTinhMapper;
    }

    /**
     * Return a {@link Page} of {@link DanhMucTinhDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhMucTinhDTO> findByCriteria(DanhMucTinhCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DanhMucTinh> specification = createSpecification(criteria);
        return danhMucTinhRepository.findAll(specification, page).map(danhMucTinhMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DanhMucTinhCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DanhMucTinh> specification = createSpecification(criteria);
        return danhMucTinhRepository.count(specification);
    }

    /**
     * Function to convert {@link DanhMucTinhCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DanhMucTinh> createSpecification(DanhMucTinhCriteria criteria) {
        Specification<DanhMucTinh> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getMaTinh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaTinh(), DanhMucTinh_.maTinh));
            }
            if (criteria.getTenTinh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenTinh(), DanhMucTinh_.tenTinh));
            }
        }
        return specification;
    }
}
