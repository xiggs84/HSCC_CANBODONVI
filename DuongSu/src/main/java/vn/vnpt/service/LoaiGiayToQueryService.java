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
import vn.vnpt.domain.LoaiGiayTo;
import vn.vnpt.repository.LoaiGiayToRepository;
import vn.vnpt.service.criteria.LoaiGiayToCriteria;
import vn.vnpt.service.dto.LoaiGiayToDTO;
import vn.vnpt.service.mapper.LoaiGiayToMapper;

/**
 * Service for executing complex queries for {@link LoaiGiayTo} entities in the database.
 * The main input is a {@link LoaiGiayToCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link LoaiGiayToDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LoaiGiayToQueryService extends QueryService<LoaiGiayTo> {

    private static final Logger LOG = LoggerFactory.getLogger(LoaiGiayToQueryService.class);

    private final LoaiGiayToRepository loaiGiayToRepository;

    private final LoaiGiayToMapper loaiGiayToMapper;

    public LoaiGiayToQueryService(LoaiGiayToRepository loaiGiayToRepository, LoaiGiayToMapper loaiGiayToMapper) {
        this.loaiGiayToRepository = loaiGiayToRepository;
        this.loaiGiayToMapper = loaiGiayToMapper;
    }

    /**
     * Return a {@link Page} of {@link LoaiGiayToDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LoaiGiayToDTO> findByCriteria(LoaiGiayToCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<LoaiGiayTo> specification = createSpecification(criteria);
        return loaiGiayToRepository.findAll(specification, page).map(loaiGiayToMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LoaiGiayToCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<LoaiGiayTo> specification = createSpecification(criteria);
        return loaiGiayToRepository.count(specification);
    }

    /**
     * Function to convert {@link LoaiGiayToCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<LoaiGiayTo> createSpecification(LoaiGiayToCriteria criteria) {
        Specification<LoaiGiayTo> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdLoaiGiayTo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdLoaiGiayTo(), LoaiGiayTo_.idLoaiGiayTo));
            }
            if (criteria.getTenLoaiGiayTo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenLoaiGiayTo(), LoaiGiayTo_.tenLoaiGiayTo));
            }
            if (criteria.getIdLoaiGiayToId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getIdLoaiGiayToId(), root ->
                        root.join(LoaiGiayTo_.idLoaiGiayTos, JoinType.LEFT).get(DuongSu_.idDuongSu)
                    )
                );
            }
            if (criteria.getThongTinCapNhatDuongSuId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getThongTinCapNhatDuongSuId(), root ->
                        root.join(LoaiGiayTo_.thongTinCapNhatDuongSus, JoinType.LEFT).get(ThongTinCapNhatDuongSu_.idCapNhat)
                    )
                );
            }
        }
        return specification;
    }
}
