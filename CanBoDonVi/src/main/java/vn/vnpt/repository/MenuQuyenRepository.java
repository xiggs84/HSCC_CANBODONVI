package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.MenuQuyen;

/**
 * Spring Data JPA repository for the MenuQuyen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MenuQuyenRepository extends JpaRepository<MenuQuyen, Long>, JpaSpecificationExecutor<MenuQuyen> {}
