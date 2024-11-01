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
import vn.vnpt.domain.DanhMucHuyen;
import vn.vnpt.repository.DanhMucHuyenRepository;
import vn.vnpt.service.criteria.DanhMucHuyenCriteria;
import vn.vnpt.service.dto.DanhMucHuyenDTO;
import vn.vnpt.service.mapper.DanhMucHuyenMapper;

/**
 * Service for executing complex queries for {@link DanhMucHuyen} entities in the database.
 * The main input is a {@link DanhMucHuyenCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DanhMucHuyenDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DanhMucHuyenQueryService extends QueryService<DanhMucHuyen> {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucHuyenQueryService.class);

    private final DanhMucHuyenRepository danhMucHuyenRepository;

    private final DanhMucHuyenMapper danhMucHuyenMapper;

    public DanhMucHuyenQueryService(DanhMucHuyenRepository danhMucHuyenRepository, DanhMucHuyenMapper danhMucHuyenMapper) {
        this.danhMucHuyenRepository = danhMucHuyenRepository;
        this.danhMucHuyenMapper = danhMucHuyenMapper;
    }

    /**
     * Return a {@link Page} of {@link DanhMucHuyenDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhMucHuyenDTO> findByCriteria(DanhMucHuyenCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DanhMucHuyen> specification = createSpecification(criteria);
        return danhMucHuyenRepository.findAll(specification, page).map(danhMucHuyenMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DanhMucHuyenCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DanhMucHuyen> specification = createSpecification(criteria);
        return danhMucHuyenRepository.count(specification);
    }

    /**
     * Function to convert {@link DanhMucHuyenCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DanhMucHuyen> createSpecification(DanhMucHuyenCriteria criteria) {
        Specification<DanhMucHuyen> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getMaHuyen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaHuyen(), DanhMucHuyen_.maHuyen));
            }
            if (criteria.getTenHuyen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenHuyen(), DanhMucHuyen_.tenHuyen));
            }
            if (criteria.getMaTinh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaTinh(), DanhMucHuyen_.maTinh));
            }
        }
        return specification;
    }
}
