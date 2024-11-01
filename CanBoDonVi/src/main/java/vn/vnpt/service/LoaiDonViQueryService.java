package vn.vnpt.service;

import jakarta.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import vn.vnpt.domain.*; // for static metamodels
import vn.vnpt.domain.LoaiDonVi;
import vn.vnpt.repository.LoaiDonViRepository;
import vn.vnpt.service.criteria.LoaiDonViCriteria;
import vn.vnpt.service.dto.LoaiDonViDTO;
import vn.vnpt.service.mapper.LoaiDonViMapper;

/**
 * Service for executing complex queries for {@link LoaiDonVi} entities in the database.
 * The main input is a {@link LoaiDonViCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link LoaiDonViDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LoaiDonViQueryService extends QueryService<LoaiDonVi> {

    private static final Logger LOG = LoggerFactory.getLogger(LoaiDonViQueryService.class);

    private final LoaiDonViRepository loaiDonViRepository;

    private final LoaiDonViMapper loaiDonViMapper;

    public LoaiDonViQueryService(LoaiDonViRepository loaiDonViRepository, LoaiDonViMapper loaiDonViMapper) {
        this.loaiDonViRepository = loaiDonViRepository;
        this.loaiDonViMapper = loaiDonViMapper;
    }

    /**
     * Return a {@link Page} of {@link LoaiDonViDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LoaiDonViDTO> findByCriteria(LoaiDonViCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<LoaiDonVi> specification = createSpecification(criteria);
        return loaiDonViRepository.findAll(specification, page).map(loaiDonViMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LoaiDonViCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<LoaiDonVi> specification = createSpecification(criteria);
        return loaiDonViRepository.count(specification);
    }

    /**
     * Function to convert {@link LoaiDonViCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<LoaiDonVi> createSpecification(LoaiDonViCriteria criteria) {
        Specification<LoaiDonVi> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdLoaiDv() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdLoaiDv(), LoaiDonVi_.idLoaiDv));
            }
            if (criteria.getTenLoaiDv() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenLoaiDv(), LoaiDonVi_.tenLoaiDv));
            }
            if (criteria.getIdLoaiDvId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getIdLoaiDvId(), root ->
                        root.join(LoaiDonVi_.idLoaiDvs, JoinType.LEFT).get(DanhMucDonVi_.idDonVi)
                    )
                );
            }
        }
        return specification;
    }
}
