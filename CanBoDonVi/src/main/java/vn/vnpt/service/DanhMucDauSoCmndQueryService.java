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
import vn.vnpt.domain.DanhMucDauSoCmnd;
import vn.vnpt.repository.DanhMucDauSoCmndRepository;
import vn.vnpt.service.criteria.DanhMucDauSoCmndCriteria;
import vn.vnpt.service.dto.DanhMucDauSoCmndDTO;
import vn.vnpt.service.mapper.DanhMucDauSoCmndMapper;

/**
 * Service for executing complex queries for {@link DanhMucDauSoCmnd} entities in the database.
 * The main input is a {@link DanhMucDauSoCmndCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DanhMucDauSoCmndDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DanhMucDauSoCmndQueryService extends QueryService<DanhMucDauSoCmnd> {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucDauSoCmndQueryService.class);

    private final DanhMucDauSoCmndRepository danhMucDauSoCmndRepository;

    private final DanhMucDauSoCmndMapper danhMucDauSoCmndMapper;

    public DanhMucDauSoCmndQueryService(
        DanhMucDauSoCmndRepository danhMucDauSoCmndRepository,
        DanhMucDauSoCmndMapper danhMucDauSoCmndMapper
    ) {
        this.danhMucDauSoCmndRepository = danhMucDauSoCmndRepository;
        this.danhMucDauSoCmndMapper = danhMucDauSoCmndMapper;
    }

    /**
     * Return a {@link Page} of {@link DanhMucDauSoCmndDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhMucDauSoCmndDTO> findByCriteria(DanhMucDauSoCmndCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DanhMucDauSoCmnd> specification = createSpecification(criteria);
        return danhMucDauSoCmndRepository.findAll(specification, page).map(danhMucDauSoCmndMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DanhMucDauSoCmndCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DanhMucDauSoCmnd> specification = createSpecification(criteria);
        return danhMucDauSoCmndRepository.count(specification);
    }

    /**
     * Function to convert {@link DanhMucDauSoCmndCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DanhMucDauSoCmnd> createSpecification(DanhMucDauSoCmndCriteria criteria) {
        Specification<DanhMucDauSoCmnd> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdDauSo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDauSo(), DanhMucDauSoCmnd_.idDauSo));
            }
            if (criteria.getDauSo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDauSo(), DanhMucDauSoCmnd_.dauSo));
            }
            if (criteria.getTinhThanh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTinhThanh(), DanhMucDauSoCmnd_.tinhThanh));
            }
            if (criteria.getIdLoai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdLoai(), DanhMucDauSoCmnd_.idLoai));
            }
        }
        return specification;
    }
}
