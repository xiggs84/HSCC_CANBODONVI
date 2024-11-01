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
import vn.vnpt.domain.MenuQuyen;
import vn.vnpt.repository.MenuQuyenRepository;
import vn.vnpt.service.criteria.MenuQuyenCriteria;
import vn.vnpt.service.dto.MenuQuyenDTO;
import vn.vnpt.service.mapper.MenuQuyenMapper;

/**
 * Service for executing complex queries for {@link MenuQuyen} entities in the database.
 * The main input is a {@link MenuQuyenCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link MenuQuyenDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MenuQuyenQueryService extends QueryService<MenuQuyen> {

    private static final Logger LOG = LoggerFactory.getLogger(MenuQuyenQueryService.class);

    private final MenuQuyenRepository menuQuyenRepository;

    private final MenuQuyenMapper menuQuyenMapper;

    public MenuQuyenQueryService(MenuQuyenRepository menuQuyenRepository, MenuQuyenMapper menuQuyenMapper) {
        this.menuQuyenRepository = menuQuyenRepository;
        this.menuQuyenMapper = menuQuyenMapper;
    }

    /**
     * Return a {@link Page} of {@link MenuQuyenDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MenuQuyenDTO> findByCriteria(MenuQuyenCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MenuQuyen> specification = createSpecification(criteria);
        return menuQuyenRepository.findAll(specification, page).map(menuQuyenMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MenuQuyenCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<MenuQuyen> specification = createSpecification(criteria);
        return menuQuyenRepository.count(specification);
    }

    /**
     * Function to convert {@link MenuQuyenCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<MenuQuyen> createSpecification(MenuQuyenCriteria criteria) {
        Specification<MenuQuyen> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), MenuQuyen_.id));
            }
            if (criteria.getListMenu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getListMenu(), MenuQuyen_.listMenu));
            }
        }
        return specification;
    }
}
