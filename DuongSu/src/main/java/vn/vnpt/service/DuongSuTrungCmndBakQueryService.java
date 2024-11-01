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
import vn.vnpt.domain.DuongSuTrungCmndBak;
import vn.vnpt.repository.DuongSuTrungCmndBakRepository;
import vn.vnpt.service.criteria.DuongSuTrungCmndBakCriteria;
import vn.vnpt.service.dto.DuongSuTrungCmndBakDTO;
import vn.vnpt.service.mapper.DuongSuTrungCmndBakMapper;

/**
 * Service for executing complex queries for {@link DuongSuTrungCmndBak} entities in the database.
 * The main input is a {@link DuongSuTrungCmndBakCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DuongSuTrungCmndBakDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DuongSuTrungCmndBakQueryService extends QueryService<DuongSuTrungCmndBak> {

    private static final Logger LOG = LoggerFactory.getLogger(DuongSuTrungCmndBakQueryService.class);

    private final DuongSuTrungCmndBakRepository duongSuTrungCmndBakRepository;

    private final DuongSuTrungCmndBakMapper duongSuTrungCmndBakMapper;

    public DuongSuTrungCmndBakQueryService(
        DuongSuTrungCmndBakRepository duongSuTrungCmndBakRepository,
        DuongSuTrungCmndBakMapper duongSuTrungCmndBakMapper
    ) {
        this.duongSuTrungCmndBakRepository = duongSuTrungCmndBakRepository;
        this.duongSuTrungCmndBakMapper = duongSuTrungCmndBakMapper;
    }

    /**
     * Return a {@link Page} of {@link DuongSuTrungCmndBakDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DuongSuTrungCmndBakDTO> findByCriteria(DuongSuTrungCmndBakCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DuongSuTrungCmndBak> specification = createSpecification(criteria);
        return duongSuTrungCmndBakRepository.findAll(specification, page).map(duongSuTrungCmndBakMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DuongSuTrungCmndBakCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DuongSuTrungCmndBak> specification = createSpecification(criteria);
        return duongSuTrungCmndBakRepository.count(specification);
    }

    /**
     * Function to convert {@link DuongSuTrungCmndBakCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DuongSuTrungCmndBak> createSpecification(DuongSuTrungCmndBakCriteria criteria) {
        Specification<DuongSuTrungCmndBak> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DuongSuTrungCmndBak_.id));
            }
            if (criteria.getTenDuongSu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenDuongSu(), DuongSuTrungCmndBak_.tenDuongSu));
            }
            if (criteria.getDiaChi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDiaChi(), DuongSuTrungCmndBak_.diaChi));
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), DuongSuTrungCmndBak_.trangThai));
            }
            if (criteria.getThongTinDs() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongTinDs(), DuongSuTrungCmndBak_.thongTinDs));
            }
            if (criteria.getNgayThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayThaoTac(), DuongSuTrungCmndBak_.ngayThaoTac));
            }
            if (criteria.getNguoiThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNguoiThaoTac(), DuongSuTrungCmndBak_.nguoiThaoTac));
            }
            if (criteria.getIdDsGoc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDsGoc(), DuongSuTrungCmndBak_.idDsGoc));
            }
            if (criteria.getIdMaster() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdMaster(), DuongSuTrungCmndBak_.idMaster));
            }
            if (criteria.getIdDonVi() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDonVi(), DuongSuTrungCmndBak_.idDonVi));
            }
            if (criteria.getStrSearch() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStrSearch(), DuongSuTrungCmndBak_.strSearch));
            }
            if (criteria.getSoGiayTo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoGiayTo(), DuongSuTrungCmndBak_.soGiayTo));
            }
            if (criteria.getDuongSuId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getDuongSuId(), root ->
                        root.join(DuongSuTrungCmndBak_.duongSu, JoinType.LEFT).get(DuongSu_.idDuongSu)
                    )
                );
            }
        }
        return specification;
    }
}
