package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucHuyen;

/**
 * Spring Data JPA repository for the DanhMucHuyen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhMucHuyenRepository extends JpaRepository<DanhMucHuyen, String>, JpaSpecificationExecutor<DanhMucHuyen> {}
