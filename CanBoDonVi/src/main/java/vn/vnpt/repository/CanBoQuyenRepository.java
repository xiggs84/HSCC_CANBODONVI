package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.CanBoQuyen;

/**
 * Spring Data JPA repository for the CanBoQuyen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CanBoQuyenRepository extends JpaRepository<CanBoQuyen, Long>, JpaSpecificationExecutor<CanBoQuyen> {}
