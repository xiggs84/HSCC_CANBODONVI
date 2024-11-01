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
import vn.vnpt.domain.DanhMucCanBo;
import vn.vnpt.repository.DanhMucCanBoRepository;
import vn.vnpt.service.criteria.DanhMucCanBoCriteria;
import vn.vnpt.service.dto.DanhMucCanBoDTO;
import vn.vnpt.service.mapper.DanhMucCanBoMapper;

/**
 * Service for executing complex queries for {@link DanhMucCanBo} entities in the database.
 * The main input is a {@link DanhMucCanBoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DanhMucCanBoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DanhMucCanBoQueryService extends QueryService<DanhMucCanBo> {

    private static final Logger LOG = LoggerFactory.getLogger(DanhMucCanBoQueryService.class);

    private final DanhMucCanBoRepository danhMucCanBoRepository;

    private final DanhMucCanBoMapper danhMucCanBoMapper;

    public DanhMucCanBoQueryService(DanhMucCanBoRepository danhMucCanBoRepository, DanhMucCanBoMapper danhMucCanBoMapper) {
        this.danhMucCanBoRepository = danhMucCanBoRepository;
        this.danhMucCanBoMapper = danhMucCanBoMapper;
    }

    /**
     * Return a {@link Page} of {@link DanhMucCanBoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhMucCanBoDTO> findByCriteria(DanhMucCanBoCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DanhMucCanBo> specification = createSpecification(criteria);
        return danhMucCanBoRepository.findAll(specification, page).map(danhMucCanBoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DanhMucCanBoCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DanhMucCanBo> specification = createSpecification(criteria);
        return danhMucCanBoRepository.count(specification);
    }

    /**
     * Function to convert {@link DanhMucCanBoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DanhMucCanBo> createSpecification(DanhMucCanBoCriteria criteria) {
        Specification<DanhMucCanBo> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdCanBo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdCanBo(), DanhMucCanBo_.idCanBo));
            }
            if (criteria.getTenCanBo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenCanBo(), DanhMucCanBo_.tenCanBo));
            }
            if (criteria.getDiaChi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDiaChi(), DanhMucCanBo_.diaChi));
            }
            if (criteria.getNamSinh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNamSinh(), DanhMucCanBo_.namSinh));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), DanhMucCanBo_.email));
            }
            if (criteria.getSoDienThoai() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoDienThoai(), DanhMucCanBo_.soDienThoai));
            }
            if (criteria.getSoGiayToTuyThan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoGiayToTuyThan(), DanhMucCanBo_.soGiayToTuyThan));
            }
            if (criteria.getTenDangNhap() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenDangNhap(), DanhMucCanBo_.tenDangNhap));
            }
            if (criteria.getMatKhau() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMatKhau(), DanhMucCanBo_.matKhau));
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), DanhMucCanBo_.trangThai));
            }
            if (criteria.getClientId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClientId(), DanhMucCanBo_.clientId));
            }
            if (criteria.getClientSecret() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClientSecret(), DanhMucCanBo_.clientSecret));
            }
            if (criteria.getUsernameKyso() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUsernameKyso(), DanhMucCanBo_.usernameKyso));
            }
            if (criteria.getPasswordKyso() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPasswordKyso(), DanhMucCanBo_.passwordKyso));
            }
            if (criteria.getUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserLogin(), DanhMucCanBo_.userLogin));
            }
        }
        return specification;
    }
}
