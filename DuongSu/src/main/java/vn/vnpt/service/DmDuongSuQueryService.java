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
import vn.vnpt.domain.DmDuongSu;
import vn.vnpt.repository.DmDuongSuRepository;
import vn.vnpt.service.criteria.DmDuongSuCriteria;
import vn.vnpt.service.dto.DmDuongSuDTO;
import vn.vnpt.service.mapper.DmDuongSuMapper;

/**
 * Service for executing complex queries for {@link DmDuongSu} entities in the database.
 * The main input is a {@link DmDuongSuCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DmDuongSuDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DmDuongSuQueryService extends QueryService<DmDuongSu> {

    private static final Logger LOG = LoggerFactory.getLogger(DmDuongSuQueryService.class);

    private final DmDuongSuRepository dmDuongSuRepository;

    private final DmDuongSuMapper dmDuongSuMapper;

    public DmDuongSuQueryService(DmDuongSuRepository dmDuongSuRepository, DmDuongSuMapper dmDuongSuMapper) {
        this.dmDuongSuRepository = dmDuongSuRepository;
        this.dmDuongSuMapper = dmDuongSuMapper;
    }

    /**
     * Return a {@link Page} of {@link DmDuongSuDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DmDuongSuDTO> findByCriteria(DmDuongSuCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DmDuongSu> specification = createSpecification(criteria);
        return dmDuongSuRepository.findAll(specification, page).map(dmDuongSuMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DmDuongSuCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DmDuongSu> specification = createSpecification(criteria);
        return dmDuongSuRepository.count(specification);
    }

    /**
     * Function to convert {@link DmDuongSuCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DmDuongSu> createSpecification(DmDuongSuCriteria criteria) {
        Specification<DmDuongSu> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdDuongSu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDuongSu(), DmDuongSu_.idDuongSu));
            }
            if (criteria.getTenDuongSu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenDuongSu(), DmDuongSu_.tenDuongSu));
            }
            if (criteria.getDiaChi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDiaChi(), DmDuongSu_.diaChi));
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), DmDuongSu_.trangThai));
            }
            if (criteria.getThongTinDs() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongTinDs(), DmDuongSu_.thongTinDs));
            }
            if (criteria.getNgayThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayThaoTac(), DmDuongSu_.ngayThaoTac));
            }
            if (criteria.getNguoiThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNguoiThaoTac(), DmDuongSu_.nguoiThaoTac));
            }
            if (criteria.getIdDsGoc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDsGoc(), DmDuongSu_.idDsGoc));
            }
            if (criteria.getIdMaster() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdMaster(), DmDuongSu_.idMaster));
            }
            if (criteria.getIdDonVi() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDonVi(), DmDuongSu_.idDonVi));
            }
            if (criteria.getStrSearch() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStrSearch(), DmDuongSu_.strSearch));
            }
            if (criteria.getSoGiayTo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoGiayTo(), DmDuongSu_.soGiayTo));
            }
            if (criteria.getIdLoaiNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdLoaiNganChan(), DmDuongSu_.idLoaiNganChan));
            }
        }
        return specification;
    }
}
