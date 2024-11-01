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
import vn.vnpt.domain.DanhMucXa;
import vn.vnpt.repository.DanhMucXaRepository;
import vn.vnpt.service.criteria.DanhMucXaCriteria;
import vn.vnpt.service.dto.DanhMucXaDTO;
import vn.vnpt.service.mapper.DanhMucXaMapper;

/**
 * Service for executing complex queries for {@link DanhMucXa} entities in the database.
 * The main input is a {@link DanhMucXaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DanhMucXaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DanhMucXaQueryService extends QueryService<DanhMucXa> {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucXaQueryService.class);

    private final DanhMucXaRepository danhMucXaRepository;

    private final DanhMucXaMapper danhMucXaMapper;

    public DanhMucXaQueryService(DanhMucXaRepository danhMucXaRepository, DanhMucXaMapper danhMucXaMapper) {
        this.danhMucXaRepository = danhMucXaRepository;
        this.danhMucXaMapper = danhMucXaMapper;
    }

    /**
     * Return a {@link Page} of {@link DanhMucXaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhMucXaDTO> findByCriteria(DanhMucXaCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DanhMucXa> specification = createSpecification(criteria);
        return danhMucXaRepository.findAll(specification, page).map(danhMucXaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DanhMucXaCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DanhMucXa> specification = createSpecification(criteria);
        return danhMucXaRepository.count(specification);
    }

    /**
     * Function to convert {@link DanhMucXaCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DanhMucXa> createSpecification(DanhMucXaCriteria criteria) {
        Specification<DanhMucXa> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getMaXa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaXa(), DanhMucXa_.maXa));
            }
            if (criteria.getTenXa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenXa(), DanhMucXa_.tenXa));
            }
            if (criteria.getMaHuyen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaHuyen(), DanhMucXa_.maHuyen));
            }
        }
        return specification;
    }
}
