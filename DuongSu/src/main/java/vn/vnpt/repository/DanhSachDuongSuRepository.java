package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhSachDuongSu;

/**
 * Spring Data JPA repository for the DanhSachDuongSu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhSachDuongSuRepository extends JpaRepository<DanhSachDuongSu, Long>, JpaSpecificationExecutor<DanhSachDuongSu> {}
