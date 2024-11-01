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
import vn.vnpt.domain.CauHinhThongTinDuongSu;
import vn.vnpt.repository.CauHinhThongTinDuongSuRepository;
import vn.vnpt.service.criteria.CauHinhThongTinDuongSuCriteria;
import vn.vnpt.service.dto.CauHinhThongTinDuongSuDTO;
import vn.vnpt.service.mapper.CauHinhThongTinDuongSuMapper;

/**
 * Service for executing complex queries for {@link CauHinhThongTinDuongSu} entities in the database.
 * The main input is a {@link CauHinhThongTinDuongSuCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link CauHinhThongTinDuongSuDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CauHinhThongTinDuongSuQueryService extends QueryService<CauHinhThongTinDuongSu> {

    private static final Logger LOG = LoggerFactory.getLogger(CauHinhThongTinDuongSuQueryService.class);

    private final CauHinhThongTinDuongSuRepository cauHinhThongTinDuongSuRepository;

    private final CauHinhThongTinDuongSuMapper cauHinhThongTinDuongSuMapper;

    public CauHinhThongTinDuongSuQueryService(
        CauHinhThongTinDuongSuRepository cauHinhThongTinDuongSuRepository,
        CauHinhThongTinDuongSuMapper cauHinhThongTinDuongSuMapper
    ) {
        this.cauHinhThongTinDuongSuRepository = cauHinhThongTinDuongSuRepository;
        this.cauHinhThongTinDuongSuMapper = cauHinhThongTinDuongSuMapper;
    }

    /**
     * Return a {@link Page} of {@link CauHinhThongTinDuongSuDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CauHinhThongTinDuongSuDTO> findByCriteria(CauHinhThongTinDuongSuCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CauHinhThongTinDuongSu> specification = createSpecification(criteria);
        return cauHinhThongTinDuongSuRepository.findAll(specification, page).map(cauHinhThongTinDuongSuMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CauHinhThongTinDuongSuCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<CauHinhThongTinDuongSu> specification = createSpecification(criteria);
        return cauHinhThongTinDuongSuRepository.count(specification);
    }

    /**
     * Function to convert {@link CauHinhThongTinDuongSuCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CauHinhThongTinDuongSu> createSpecification(CauHinhThongTinDuongSuCriteria criteria) {
        Specification<CauHinhThongTinDuongSu> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdCauHinh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdCauHinh(), CauHinhThongTinDuongSu_.idCauHinh));
            }
            if (criteria.getNoiDung() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNoiDung(), CauHinhThongTinDuongSu_.noiDung));
            }
            if (criteria.getJavascript() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJavascript(), CauHinhThongTinDuongSu_.javascript));
            }
            if (criteria.getCss() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCss(), CauHinhThongTinDuongSu_.css));
            }
            if (criteria.getIdLoaiDs() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdLoaiDs(), CauHinhThongTinDuongSu_.idLoaiDs));
            }
            if (criteria.getIdDonVi() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDonVi(), CauHinhThongTinDuongSu_.idDonVi));
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), CauHinhThongTinDuongSu_.trangThai));
            }
        }
        return specification;
    }
}
