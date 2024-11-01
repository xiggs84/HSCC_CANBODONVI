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
import vn.vnpt.domain.DanhMucDonVi;
import vn.vnpt.repository.DanhMucDonViRepository;
import vn.vnpt.service.criteria.DanhMucDonViCriteria;
import vn.vnpt.service.dto.DanhMucDonViDTO;
import vn.vnpt.service.mapper.DanhMucDonViMapper;

/**
 * Service for executing complex queries for {@link DanhMucDonVi} entities in the database.
 * The main input is a {@link DanhMucDonViCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DanhMucDonViDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DanhMucDonViQueryService extends QueryService<DanhMucDonVi> {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucDonViQueryService.class);

    private final DanhMucDonViRepository danhMucDonViRepository;

    private final DanhMucDonViMapper danhMucDonViMapper;

    public DanhMucDonViQueryService(DanhMucDonViRepository danhMucDonViRepository, DanhMucDonViMapper danhMucDonViMapper) {
        this.danhMucDonViRepository = danhMucDonViRepository;
        this.danhMucDonViMapper = danhMucDonViMapper;
    }

    /**
     * Return a {@link Page} of {@link DanhMucDonViDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhMucDonViDTO> findByCriteria(DanhMucDonViCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DanhMucDonVi> specification = createSpecification(criteria);
        return danhMucDonViRepository.findAll(specification, page).map(danhMucDonViMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DanhMucDonViCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DanhMucDonVi> specification = createSpecification(criteria);
        return danhMucDonViRepository.count(specification);
    }

    /**
     * Function to convert {@link DanhMucDonViCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DanhMucDonVi> createSpecification(DanhMucDonViCriteria criteria) {
        Specification<DanhMucDonVi> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }

            Specification<DanhMucDonVi> orSpecification = Specification.where(null);

            if (criteria.getIdDonVi() != null) {
                orSpecification = orSpecification.or(buildRangeSpecification(criteria.getIdDonVi(), DanhMucDonVi_.idDonVi));
            }
            if (criteria.getTenDonVi() != null) {
                orSpecification = orSpecification.or(buildStringSpecification(criteria.getTenDonVi(), DanhMucDonVi_.tenDonVi));
            }
            if (criteria.getDiaChi() != null) {
                orSpecification = orSpecification.or(buildStringSpecification(criteria.getDiaChi(), DanhMucDonVi_.diaChi));
            }
            if (criteria.getNguoiDaiDien() != null) {
                orSpecification = orSpecification.or(buildStringSpecification(criteria.getNguoiDaiDien(), DanhMucDonVi_.nguoiDaiDien));
            }
            if (criteria.getSoDienThoai() != null) {
                orSpecification = orSpecification.or(buildStringSpecification(criteria.getSoDienThoai(), DanhMucDonVi_.soDienThoai));
            }
            if (criteria.getIdDonViQl() != null) {
                orSpecification = orSpecification.or(buildRangeSpecification(criteria.getIdDonViQl(), DanhMucDonVi_.idDonViQl));
            }
            if (criteria.getNgayKhaiBao() != null) {
                orSpecification = orSpecification.or(buildRangeSpecification(criteria.getNgayKhaiBao(), DanhMucDonVi_.ngayKhaiBao));
            }
            if (criteria.getTrangThai() != null) {
                orSpecification = orSpecification.or(buildRangeSpecification(criteria.getTrangThai(), DanhMucDonVi_.trangThai));
            }
            if (criteria.getSoNha() != null) {
                orSpecification = orSpecification.or(buildStringSpecification(criteria.getSoNha(), DanhMucDonVi_.soNha));
            }
            if (criteria.getMaSoThue() != null) {
                orSpecification = orSpecification.or(buildStringSpecification(criteria.getMaSoThue(), DanhMucDonVi_.maSoThue));
            }
            if (criteria.getHoaDonDt() != null) {
                orSpecification = orSpecification.or(buildRangeSpecification(criteria.getHoaDonDt(), DanhMucDonVi_.hoaDonDt));
            }
            if (criteria.getMaDonViIgate() != null) {
                orSpecification = orSpecification.or(buildStringSpecification(criteria.getMaDonViIgate(), DanhMucDonVi_.maDonViIgate));
            }
            if (criteria.getMaCoQuanIgate() != null) {
                orSpecification = orSpecification.or(buildStringSpecification(criteria.getMaCoQuanIgate(), DanhMucDonVi_.maCoQuanIgate));
            }
            if (criteria.getKySo() != null) {
                orSpecification = orSpecification.or(buildRangeSpecification(criteria.getKySo(), DanhMucDonVi_.kySo));
            }
            if (criteria.getQrScan() != null) {
                orSpecification = orSpecification.or(buildRangeSpecification(criteria.getQrScan(), DanhMucDonVi_.qrScan));
            }
            if (criteria.getVerifyIdCard() != null) {
                orSpecification = orSpecification.or(buildRangeSpecification(criteria.getVerifyIdCard(), DanhMucDonVi_.verifyIdCard));
            }
            if (criteria.getIsVerifyFace() != null) {
                orSpecification = orSpecification.or(buildRangeSpecification(criteria.getIsVerifyFace(), DanhMucDonVi_.isVerifyFace));
            }
            if (criteria.getIsElastic() != null) {
                orSpecification = orSpecification.or(buildRangeSpecification(criteria.getIsElastic(), DanhMucDonVi_.isElastic));
            }
            if (criteria.getApikeyCccd() != null) {
                orSpecification = orSpecification.or(buildStringSpecification(criteria.getApikeyCccd(), DanhMucDonVi_.apikeyCccd));
            }
            if (criteria.getApikeyFace() != null) {
                orSpecification = orSpecification.or(buildStringSpecification(criteria.getApikeyFace(), DanhMucDonVi_.apikeyFace));
            }
            if (criteria.getVerifyCodeCccd() != null) {
                orSpecification = orSpecification.or(buildStringSpecification(criteria.getVerifyCodeCccd(), DanhMucDonVi_.verifyCodeCccd));
            }
            if (criteria.getUsernameElastic() != null) {
                orSpecification = orSpecification.or(buildStringSpecification(criteria.getUsernameElastic(), DanhMucDonVi_.usernameElastic));
            }
            if (criteria.getPasswordElastic() != null) {
                orSpecification = orSpecification.or(buildStringSpecification(criteria.getPasswordElastic(), DanhMucDonVi_.passwordElastic));
            }
            if (criteria.getIdNhiemVu() != null) {
                orSpecification = orSpecification.or(buildStringSpecification(criteria.getIdNhiemVu(), DanhMucDonVi_.idNhiemVu));
            }
            if (criteria.getIdLoaiDv() != null) {
                orSpecification = orSpecification.or(buildStringSpecification(criteria.getIdLoaiDv(), DanhMucDonVi_.idLoaiDv));
            }
            if (criteria.getIdCapQl() != null) {
                orSpecification = orSpecification.or(buildStringSpecification(criteria.getIdCapQl(), DanhMucDonVi_.idCapQl));
            }
            if (criteria.getCanBoChungThucId() != null) {
                orSpecification = orSpecification.or(
                    buildSpecification(criteria.getCanBoChungThucId(), root ->
                        root.join(DanhMucDonVi_.canBoChungThucs, JoinType.LEFT).get(CanBoChungThuc_.idCanBoChungThuc)
                    )
                );
            }
            if (criteria.getCanBoQuyenId() != null) {
                orSpecification = orSpecification.or(
                    buildSpecification(criteria.getCanBoQuyenId(), root ->
                        root.join(DanhMucDonVi_.canBoQuyens, JoinType.LEFT).get(CanBoQuyen_.id)
                    )
                );
            }
            if (criteria.getCapQuanLyId() != null) {
                orSpecification = orSpecification.or(
                    buildSpecification(criteria.getCapQuanLyId(), root ->
                        root.join(DanhMucDonVi_.capQuanLy, JoinType.LEFT).get(CapQuanLy_.idCapQl)
                    )
                );
            }
            if (criteria.getLoaiDonViId() != null) {
                orSpecification = orSpecification.or(
                    buildSpecification(criteria.getLoaiDonViId(), root ->
                        root.join(DanhMucDonVi_.loaiDonVi, JoinType.LEFT).get(LoaiDonVi_.idLoaiDv)
                    )
                );
            }
            if (criteria.getNhiemVuId() != null) {
                orSpecification = orSpecification.or(
                    buildSpecification(criteria.getNhiemVuId(), root ->
                        root.join(DanhMucDonVi_.nhiemVu, JoinType.LEFT).get(NhiemVu_.idNhiemVu)
                    )
                );
            }
            if (criteria.getLanhDaoId() != null) {
                orSpecification = orSpecification.or(
                    buildSpecification(criteria.getLanhDaoId(), root ->
                        root.join(DanhMucDonVi_.lanhDaos, JoinType.LEFT).get(LanhDao_.idLanhDao)
                    )
                );
            }

            specification = specification.and(orSpecification);
        }
        return specification;
    }
}
