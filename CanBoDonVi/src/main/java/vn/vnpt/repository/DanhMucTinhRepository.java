package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucTinh;

/**
 * Spring Data JPA repository for the DanhMucTinh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhMucTinhRepository extends JpaRepository<DanhMucTinh, String> {}
