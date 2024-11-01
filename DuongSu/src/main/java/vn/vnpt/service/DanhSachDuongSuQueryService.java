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
import vn.vnpt.domain.DanhSachDuongSu;
import vn.vnpt.repository.DanhSachDuongSuRepository;
import vn.vnpt.service.criteria.DanhSachDuongSuCriteria;
import vn.vnpt.service.dto.DanhSachDuongSuDTO;
import vn.vnpt.service.mapper.DanhSachDuongSuMapper;

/**
 * Service for executing complex queries for {@link DanhSachDuongSu} entities in the database.
 * The main input is a {@link DanhSachDuongSuCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DanhSachDuongSuDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DanhSachDuongSuQueryService extends QueryService<DanhSachDuongSu> {

    private static final Logger LOG = LoggerFactory.getLogger(DanhSachDuongSuQueryService.class);

    private final DanhSachDuongSuRepository danhSachDuongSuRepository;

    private final DanhSachDuongSuMapper danhSachDuongSuMapper;

    public DanhSachDuongSuQueryService(DanhSachDuongSuRepository danhSachDuongSuRepository, DanhSachDuongSuMapper danhSachDuongSuMapper) {
        this.danhSachDuongSuRepository = danhSachDuongSuRepository;
        this.danhSachDuongSuMapper = danhSachDuongSuMapper;
    }

    /**
     * Return a {@link Page} of {@link DanhSachDuongSuDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhSachDuongSuDTO> findByCriteria(DanhSachDuongSuCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DanhSachDuongSu> specification = createSpecification(criteria);
        return danhSachDuongSuRepository.findAll(specification, page).map(danhSachDuongSuMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DanhSachDuongSuCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DanhSachDuongSu> specification = createSpecification(criteria);
        return danhSachDuongSuRepository.count(specification);
    }

    /**
     * Function to convert {@link DanhSachDuongSuCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DanhSachDuongSu> createSpecification(DanhSachDuongSuCriteria criteria) {
        Specification<DanhSachDuongSu> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DanhSachDuongSu_.id));
            }
            if (criteria.getTenDuongSu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenDuongSu(), DanhSachDuongSu_.tenDuongSu));
            }
            if (criteria.getDiaChi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDiaChi(), DanhSachDuongSu_.diaChi));
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), DanhSachDuongSu_.trangThai));
            }
            if (criteria.getNgayThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayThaoTac(), DanhSachDuongSu_.ngayThaoTac));
            }
            if (criteria.getNguoiThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNguoiThaoTac(), DanhSachDuongSu_.nguoiThaoTac));
            }
            if (criteria.getIdDsGoc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDsGoc(), DanhSachDuongSu_.idDsGoc));
            }
            if (criteria.getIdMaster() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdMaster(), DanhSachDuongSu_.idMaster));
            }
            if (criteria.getIdDonVi() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDonVi(), DanhSachDuongSu_.idDonVi));
            }
            if (criteria.getStrSearch() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStrSearch(), DanhSachDuongSu_.strSearch));
            }
            if (criteria.getSoGiayTo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoGiayTo(), DanhSachDuongSu_.soGiayTo));
            }
            if (criteria.getIdLoaiNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdLoaiNganChan(), DanhSachDuongSu_.idLoaiNganChan));
            }
            if (criteria.getDuongSuId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getDuongSuId(), root ->
                        root.join(DanhSachDuongSu_.duongSu, JoinType.LEFT).get(DuongSu_.idDuongSu)
                    )
                );
            }
        }
        return specification;
    }
}
