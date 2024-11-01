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
import vn.vnpt.domain.DonViScanQr;
import vn.vnpt.repository.DonViScanQrRepository;
import vn.vnpt.service.criteria.DonViScanQrCriteria;
import vn.vnpt.service.dto.DonViScanQrDTO;
import vn.vnpt.service.mapper.DonViScanQrMapper;

/**
 * Service for executing complex queries for {@link DonViScanQr} entities in the database.
 * The main input is a {@link DonViScanQrCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DonViScanQrDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DonViScanQrQueryService extends QueryService<DonViScanQr> {

    private static final Logger LOG = LoggerFactory.getLogger(DonViScanQrQueryService.class);

    private final DonViScanQrRepository donViScanQrRepository;

    private final DonViScanQrMapper donViScanQrMapper;

    public DonViScanQrQueryService(DonViScanQrRepository donViScanQrRepository, DonViScanQrMapper donViScanQrMapper) {
        this.donViScanQrRepository = donViScanQrRepository;
        this.donViScanQrMapper = donViScanQrMapper;
    }

    /**
     * Return a {@link Page} of {@link DonViScanQrDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DonViScanQrDTO> findByCriteria(DonViScanQrCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DonViScanQr> specification = createSpecification(criteria);
        return donViScanQrRepository.findAll(specification, page).map(donViScanQrMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DonViScanQrCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<DonViScanQr> specification = createSpecification(criteria);
        return donViScanQrRepository.count(specification);
    }

    /**
     * Function to convert {@link DonViScanQrCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DonViScanQr> createSpecification(DonViScanQrCriteria criteria) {
        Specification<DonViScanQr> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getIdLuotQuet() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdLuotQuet(), DonViScanQr_.idLuotQuet));
            }
            if (criteria.getIdCongDan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdCongDan(), DonViScanQr_.idCongDan));
            }
            if (criteria.getNgayThaoTac() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayThaoTac(), DonViScanQr_.ngayThaoTac));
            }
        }
        return specification;
    }
}
