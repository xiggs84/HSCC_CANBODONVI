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
import vn.vnpt.domain.DuongSu;
import vn.vnpt.repository.DuongSuRepository;
import vn.vnpt.service.criteria.DuongSuCriteria;
import vn.vnpt.service.dto.DuongSuDTO;
import vn.vnpt.service.mapper.DuongSuMapper;

/**
 * Service for executing complex queries for {@link DuongSu} entities in the database.
 * The main input is a {@link DuongSuCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DuongSuDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DuongSuQueryService extends QueryService<DuongSu> {

    private static final Logger LOG = LoggerFactory.getLogger(DuongSuQueryService.class);

    private final DuongSuRepository duongSuRepository;

    private final DuongSuMapper duongSuMapper;

    public DuongSuQueryService(DuongSuRepository duongSuRepository, DuongSuMapper duongSuMapper) {
        this.duongSuRepository = duongSuRepository;
        this.duongSuMapper = duongSuMapper;
    }

    /**
     * Return a {@link Page} of {@link DuongSuDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DuongSuDTO> findByCriteria(DuongSuCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DuongSu> specification = createSpecification(criteria);
        return duongSuRepository.findAll(specification, page).map(duongSuMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DuongSuCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DuongSu> specification = createSpecification(criteria);
        return duongSuRepository.count(specification);
    }

    /**
     * Function to convert {@link DuongSuCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DuongSu> createSpecification(DuongSuCriteria criteria) {
        Specification<DuongSu> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdDuongSu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDuongSu(), DuongSu_.idDuongSu));
            }
            if (criteria.getTenDuongSu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenDuongSu(), DuongSu_.tenDuongSu));
            }
            if (criteria.getDiaChi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDiaChi(), DuongSu_.diaChi));
            }
            if (criteria.getSoDienThoai() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoDienThoai(), DuongSu_.soDienThoai));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), DuongSu_.email));
            }
            if (criteria.getFax() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFax(), DuongSu_.fax));
            }
            if (criteria.getWebsite() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWebsite(), DuongSu_.website));
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), DuongSu_.trangThai));
            }
            if (criteria.getNgayThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayThaoTac(), DuongSu_.ngayThaoTac));
            }
            if (criteria.getNguoiThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNguoiThaoTac(), DuongSu_.nguoiThaoTac));
            }
            if (criteria.getIdDsGoc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDsGoc(), DuongSu_.idDsGoc));
            }
            if (criteria.getIdMaster() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdMaster(), DuongSu_.idMaster));
            }
            if (criteria.getIdDonVi() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDonVi(), DuongSu_.idDonVi));
            }
            if (criteria.getStrSearch() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStrSearch(), DuongSu_.strSearch));
            }
            if (criteria.getSoGiayTo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoGiayTo(), DuongSu_.soGiayTo));
            }
            if (criteria.getGhiChu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGhiChu(), DuongSu_.ghiChu));
            }
            if (criteria.getIdLoaiNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdLoaiNganChan(), DuongSu_.idLoaiNganChan));
            }
            if (criteria.getSyncStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSyncStatus(), DuongSu_.syncStatus));
            }
            if (criteria.getThongTinCapNhatId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getThongTinCapNhatId(), root ->
                        root.join(DuongSu_.thongTinCapNhats, JoinType.LEFT).get(ThongTinCapNhatDuongSu_.idCapNhat)
                    )
                );
            }
            if (criteria.getTaiSanDuongSuId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getTaiSanDuongSuId(), root ->
                        root.join(DuongSu_.taiSanDuongSus, JoinType.LEFT).get(TaiSanDuongSu_.id)
                    )
                );
            }
            if (criteria.getQuanHeDuongSuId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getQuanHeDuongSuId(), root ->
                        root.join(DuongSu_.quanHeDuongSus, JoinType.LEFT).get(QuanHeDuongSu_.idQuanHe)
                    )
                );
            }
            if (criteria.getDanhSachDuongSuId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getDanhSachDuongSuId(), root ->
                        root.join(DuongSu_.danhSachDuongSus, JoinType.LEFT).get(DanhSachDuongSu_.id)
                    )
                );
            }
            if (criteria.getDuongSuTrungCmndId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getDuongSuTrungCmndId(), root ->
                        root.join(DuongSu_.duongSuTrungCmnds, JoinType.LEFT).get(DuongSuTrungCmnd_.id)
                    )
                );
            }
            if (criteria.getDuongSuTrungCmndBakId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getDuongSuTrungCmndBakId(), root ->
                        root.join(DuongSu_.duongSuTrungCmndBaks, JoinType.LEFT).get(DuongSuTrungCmndBak_.id)
                    )
                );
            }
            if (criteria.getLoaiDuongSuId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getLoaiDuongSuId(), root ->
                        root.join(DuongSu_.loaiDuongSu, JoinType.LEFT).get(LoaiDuongSu_.idLoaiDuongSu)
                    )
                );
            }
            if (criteria.getLoaiGiayToId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getLoaiGiayToId(), root ->
                        root.join(DuongSu_.loaiGiayTo, JoinType.LEFT).get(LoaiGiayTo_.idLoaiGiayTo)
                    )
                );
            }
        }
        return specification;
    }
}
