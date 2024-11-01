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
import vn.vnpt.domain.DanhMucQuocGia;
import vn.vnpt.repository.DanhMucQuocGiaRepository;
import vn.vnpt.service.criteria.DanhMucQuocGiaCriteria;
import vn.vnpt.service.dto.DanhMucQuocGiaDTO;
import vn.vnpt.service.mapper.DanhMucQuocGiaMapper;

/**
 * Service for executing complex queries for {@link DanhMucQuocGia} entities in the database.
 * The main input is a {@link DanhMucQuocGiaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DanhMucQuocGiaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DanhMucQuocGiaQueryService extends QueryService<DanhMucQuocGia> {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucQuocGiaQueryService.class);

    private final DanhMucQuocGiaRepository danhMucQuocGiaRepository;

    private final DanhMucQuocGiaMapper danhMucQuocGiaMapper;

    public DanhMucQuocGiaQueryService(DanhMucQuocGiaRepository danhMucQuocGiaRepository, DanhMucQuocGiaMapper danhMucQuocGiaMapper) {
        this.danhMucQuocGiaRepository = danhMucQuocGiaRepository;
        this.danhMucQuocGiaMapper = danhMucQuocGiaMapper;
    }

    /**
     * Return a {@link Page} of {@link DanhMucQuocGiaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhMucQuocGiaDTO> findByCriteria(DanhMucQuocGiaCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DanhMucQuocGia> specification = createSpecification(criteria);
        return danhMucQuocGiaRepository.findAll(specification, page).map(danhMucQuocGiaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DanhMucQuocGiaCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DanhMucQuocGia> specification = createSpecification(criteria);
        return danhMucQuocGiaRepository.count(specification);
    }

    /**
     * Function to convert {@link DanhMucQuocGiaCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DanhMucQuocGia> createSpecification(DanhMucQuocGiaCriteria criteria) {
        Specification<DanhMucQuocGia> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdQuocGia() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdQuocGia(), DanhMucQuocGia_.idQuocGia));
            }
            if (criteria.getTenQuocGia() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenQuocGia(), DanhMucQuocGia_.tenQuocGia));
            }
            if (criteria.getTenTiengAnh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenTiengAnh(), DanhMucQuocGia_.tenTiengAnh));
            }
        }
        return specification;
    }
}
