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
import vn.vnpt.domain.NhiemVu;
import vn.vnpt.repository.NhiemVuRepository;
import vn.vnpt.service.criteria.NhiemVuCriteria;
import vn.vnpt.service.dto.NhiemVuDTO;
import vn.vnpt.service.mapper.NhiemVuMapper;

/**
 * Service for executing complex queries for {@link NhiemVu} entities in the database.
 * The main input is a {@link NhiemVuCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link NhiemVuDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NhiemVuQueryService extends QueryService<NhiemVu> {

    private static final Logger LOG = LoggerFactory.getLogger(NhiemVuQueryService.class);

    private final NhiemVuRepository nhiemVuRepository;

    private final NhiemVuMapper nhiemVuMapper;

    public NhiemVuQueryService(NhiemVuRepository nhiemVuRepository, NhiemVuMapper nhiemVuMapper) {
        this.nhiemVuRepository = nhiemVuRepository;
        this.nhiemVuMapper = nhiemVuMapper;
    }

    /**
     * Return a {@link Page} of {@link NhiemVuDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NhiemVuDTO> findByCriteria(NhiemVuCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NhiemVu> specification = createSpecification(criteria);
        return nhiemVuRepository.findAll(specification, page).map(nhiemVuMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NhiemVuCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<NhiemVu> specification = createSpecification(criteria);
        return nhiemVuRepository.count(specification);
    }

    /**
     * Function to convert {@link NhiemVuCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<NhiemVu> createSpecification(NhiemVuCriteria criteria) {
        Specification<NhiemVu> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdNhiemVu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdNhiemVu(), NhiemVu_.idNhiemVu));
            }
            if (criteria.getTenNhiemVu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenNhiemVu(), NhiemVu_.tenNhiemVu));
            }
            if (criteria.getIdNhiemVuId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getIdNhiemVuId(), root ->
                        root.join(NhiemVu_.idNhiemVus, JoinType.LEFT).get(DanhMucDonVi_.idDonVi)
                    )
                );
            }
        }
        return specification;
    }
}
