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
import vn.vnpt.domain.DuongSuTrungCmnd;
import vn.vnpt.repository.DuongSuTrungCmndRepository;
import vn.vnpt.service.criteria.DuongSuTrungCmndCriteria;
import vn.vnpt.service.dto.DuongSuTrungCmndDTO;
import vn.vnpt.service.mapper.DuongSuTrungCmndMapper;

/**
 * Service for executing complex queries for {@link DuongSuTrungCmnd} entities in the database.
 * The main input is a {@link DuongSuTrungCmndCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DuongSuTrungCmndDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DuongSuTrungCmndQueryService extends QueryService<DuongSuTrungCmnd> {

    private static final Logger LOG = LoggerFactory.getLogger(DuongSuTrungCmndQueryService.class);

    private final DuongSuTrungCmndRepository duongSuTrungCmndRepository;

    private final DuongSuTrungCmndMapper duongSuTrungCmndMapper;

    public DuongSuTrungCmndQueryService(
        DuongSuTrungCmndRepository duongSuTrungCmndRepository,
        DuongSuTrungCmndMapper duongSuTrungCmndMapper
    ) {
        this.duongSuTrungCmndRepository = duongSuTrungCmndRepository;
        this.duongSuTrungCmndMapper = duongSuTrungCmndMapper;
    }

    /**
     * Return a {@link Page} of {@link DuongSuTrungCmndDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DuongSuTrungCmndDTO> findByCriteria(DuongSuTrungCmndCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DuongSuTrungCmnd> specification = createSpecification(criteria);
        return duongSuTrungCmndRepository.findAll(specification, page).map(duongSuTrungCmndMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DuongSuTrungCmndCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DuongSuTrungCmnd> specification = createSpecification(criteria);
        return duongSuTrungCmndRepository.count(specification);
    }

    /**
     * Function to convert {@link DuongSuTrungCmndCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DuongSuTrungCmnd> createSpecification(DuongSuTrungCmndCriteria criteria) {
        Specification<DuongSuTrungCmnd> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DuongSuTrungCmnd_.id));
            }
            if (criteria.getTenDuongSu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenDuongSu(), DuongSuTrungCmnd_.tenDuongSu));
            }
            if (criteria.getDiaChi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDiaChi(), DuongSuTrungCmnd_.diaChi));
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), DuongSuTrungCmnd_.trangThai));
            }
            if (criteria.getThongTinDs() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongTinDs(), DuongSuTrungCmnd_.thongTinDs));
            }
            if (criteria.getNgayThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayThaoTac(), DuongSuTrungCmnd_.ngayThaoTac));
            }
            if (criteria.getNguoiThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNguoiThaoTac(), DuongSuTrungCmnd_.nguoiThaoTac));
            }
            if (criteria.getIdDsGoc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDsGoc(), DuongSuTrungCmnd_.idDsGoc));
            }
            if (criteria.getIdMaster() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdMaster(), DuongSuTrungCmnd_.idMaster));
            }
            if (criteria.getIdDonVi() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDonVi(), DuongSuTrungCmnd_.idDonVi));
            }
            if (criteria.getStrSearch() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStrSearch(), DuongSuTrungCmnd_.strSearch));
            }
            if (criteria.getSoGiayTo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoGiayTo(), DuongSuTrungCmnd_.soGiayTo));
            }
            if (criteria.getIdDuongSuMin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDuongSuMin(), DuongSuTrungCmnd_.idDuongSuMin));
            }
            if (criteria.getIdMasterMin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdMasterMin(), DuongSuTrungCmnd_.idMasterMin));
            }
            if (criteria.getIdDuongSuMax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDuongSuMax(), DuongSuTrungCmnd_.idDuongSuMax));
            }
            if (criteria.getIdMasterMax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdMasterMax(), DuongSuTrungCmnd_.idMasterMax));
            }
            if (criteria.getDuongSuId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getDuongSuId(), root ->
                        root.join(DuongSuTrungCmnd_.duongSu, JoinType.LEFT).get(DuongSu_.idDuongSu)
                    )
                );
            }
        }
        return specification;
    }
}
