package vn.vnpt.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import vn.vnpt.domain.*; // for static metamodels
import vn.vnpt.domain.ChiTietNganChan;
import vn.vnpt.repository.ChiTietNganChanRepository;
import vn.vnpt.service.criteria.ChiTietNganChanCriteria;
import vn.vnpt.service.dto.ChiTietNganChanDTO;
import vn.vnpt.service.mapper.ChiTietNganChanMapper;

/**
 * Service for executing complex queries for {@link ChiTietNganChan} entities in the database.
 * The main input is a {@link ChiTietNganChanCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ChiTietNganChanDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ChiTietNganChanQueryService extends QueryService<ChiTietNganChan> {

    private static final Logger LOG = LoggerFactory.getLogger(ChiTietNganChanQueryService.class);

    private final ChiTietNganChanRepository chiTietNganChanRepository;

    private final ChiTietNganChanMapper chiTietNganChanMapper;

    public ChiTietNganChanQueryService(ChiTietNganChanRepository chiTietNganChanRepository, ChiTietNganChanMapper chiTietNganChanMapper) {
        this.chiTietNganChanRepository = chiTietNganChanRepository;
        this.chiTietNganChanMapper = chiTietNganChanMapper;
    }

    /**
     * Return a {@link List} of {@link ChiTietNganChanDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ChiTietNganChanDTO> findByCriteria(ChiTietNganChanCriteria criteria) {
        LOG.debug("find by criteria : {}", criteria);
        final Specification<ChiTietNganChan> specification = createSpecification(criteria);
        return chiTietNganChanMapper.toDto(chiTietNganChanRepository.findAll(specification));
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ChiTietNganChanCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<ChiTietNganChan> specification = createSpecification(criteria);
        return chiTietNganChanRepository.count(specification);
    }

    /**
     * Function to convert {@link ChiTietNganChanCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ChiTietNganChan> createSpecification(ChiTietNganChanCriteria criteria) {
        Specification<ChiTietNganChan> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ChiTietNganChan_.id));
            }
            if (criteria.getIdDoiTuong() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdDoiTuong(), ChiTietNganChan_.idDoiTuong));
            }
            if (criteria.getNgayThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayThaoTac(), ChiTietNganChan_.ngayThaoTac));
            }
            if (criteria.getLoaiDoiTuong() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLoaiDoiTuong(), ChiTietNganChan_.loaiDoiTuong));
            }
            if (criteria.getSoHsCv() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoHsCv(), ChiTietNganChan_.soHsCv));
            }
            if (criteria.getSoCc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoCc(), ChiTietNganChan_.soCc));
            }
            if (criteria.getSoVaoSo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoVaoSo(), ChiTietNganChan_.soVaoSo));
            }
            if (criteria.getMoTa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMoTa(), ChiTietNganChan_.moTa));
            }
            if (criteria.getNgayNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayNganChan(), ChiTietNganChan_.ngayNganChan));
            }
            if (criteria.getNgayBdNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayBdNganChan(), ChiTietNganChan_.ngayBdNganChan));
            }
            if (criteria.getNgayKtNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayKtNganChan(), ChiTietNganChan_.ngayKtNganChan));
            }
            if (criteria.getTrangThai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangThai(), ChiTietNganChan_.trangThai));
            }
            if (criteria.getNguoiThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNguoiThaoTac(), ChiTietNganChan_.nguoiThaoTac));
            }
            if (criteria.getLoaiNganChan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLoaiNganChan(), ChiTietNganChan_.loaiNganChan));
            }
            if (criteria.getNgayCongVan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayCongVan(), ChiTietNganChan_.ngayCongVan));
            }
        }
        return specification;
    }
}
