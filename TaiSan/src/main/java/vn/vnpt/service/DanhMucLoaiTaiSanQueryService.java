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
import vn.vnpt.domain.DanhMucLoaiTaiSan;
import vn.vnpt.repository.DanhMucLoaiTaiSanRepository;
import vn.vnpt.service.criteria.DanhMucLoaiTaiSanCriteria;
import vn.vnpt.service.dto.DanhMucLoaiTaiSanDTO;
import vn.vnpt.service.mapper.DanhMucLoaiTaiSanMapper;

/**
 * Service for executing complex queries for {@link DanhMucLoaiTaiSan} entities in the database.
 * The main input is a {@link DanhMucLoaiTaiSanCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DanhMucLoaiTaiSanDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DanhMucLoaiTaiSanQueryService extends QueryService<DanhMucLoaiTaiSan> {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucLoaiTaiSanQueryService.class);

    private final DanhMucLoaiTaiSanRepository danhMucLoaiTaiSanRepository;

    private final DanhMucLoaiTaiSanMapper danhMucLoaiTaiSanMapper;

    public DanhMucLoaiTaiSanQueryService(
        DanhMucLoaiTaiSanRepository danhMucLoaiTaiSanRepository,
        DanhMucLoaiTaiSanMapper danhMucLoaiTaiSanMapper
    ) {
        this.danhMucLoaiTaiSanRepository = danhMucLoaiTaiSanRepository;
        this.danhMucLoaiTaiSanMapper = danhMucLoaiTaiSanMapper;
    }

    /**
     * Return a {@link Page} of {@link DanhMucLoaiTaiSanDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhMucLoaiTaiSanDTO> findByCriteria(DanhMucLoaiTaiSanCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DanhMucLoaiTaiSan> specification = createSpecification(criteria);
        return danhMucLoaiTaiSanRepository.findAll(specification, page).map(danhMucLoaiTaiSanMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DanhMucLoaiTaiSanCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DanhMucLoaiTaiSan> specification = createSpecification(criteria);
        return danhMucLoaiTaiSanRepository.count(specification);
    }

    /**
     * Function to convert {@link DanhMucLoaiTaiSanCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DanhMucLoaiTaiSan> createSpecification(DanhMucLoaiTaiSanCriteria criteria) {
        Specification<DanhMucLoaiTaiSan> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdLoaiTs() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdLoaiTs(), DanhMucLoaiTaiSan_.idLoaiTs));
            }
            if (criteria.getDienGiai() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDienGiai(), DanhMucLoaiTaiSan_.dienGiai));
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), DanhMucLoaiTaiSan_.trangThai));
            }
            if (criteria.getLoaiTaiSanId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getLoaiTaiSanId(), root ->
                        root.join(DanhMucLoaiTaiSan_.loaiTaiSans, JoinType.LEFT).get(TaiSan_.idTaiSan)
                    )
                );
            }
            if (criteria.getDanhSachTaiSanId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getDanhSachTaiSanId(), root ->
                        root.join(DanhMucLoaiTaiSan_.danhSachTaiSans, JoinType.LEFT).get(DanhSachTaiSan_.id)
                    )
                );
            }
            if (criteria.getTaiSanDgcId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getTaiSanDgcId(), root ->
                        root.join(DanhMucLoaiTaiSan_.taiSanDgcs, JoinType.LEFT).get(TaiSanDgc_.id)
                    )
                );
            }
            if (criteria.getTaiSanDatNhaId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getTaiSanDatNhaId(), root ->
                        root.join(DanhMucLoaiTaiSan_.taiSanDatNhas, JoinType.LEFT).get(TaiSanDatNha_.id)
                    )
                );
            }
            if (criteria.getThongTinCapNhatTaiSanId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getThongTinCapNhatTaiSanId(), root ->
                        root.join(DanhMucLoaiTaiSan_.thongTinCapNhatTaiSans, JoinType.LEFT).get(ThongTinCapNhatTaiSan_.idCapNhat)
                    )
                );
            }
        }
        return specification;
    }
}
