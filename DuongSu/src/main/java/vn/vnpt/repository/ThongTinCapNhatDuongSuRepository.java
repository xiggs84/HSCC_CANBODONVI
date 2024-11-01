package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.ThongTinCapNhatDuongSu;

/**
 * Spring Data JPA repository for the ThongTinCapNhatDuongSu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ThongTinCapNhatDuongSuRepository
    extends JpaRepository<ThongTinCapNhatDuongSu, Long>, JpaSpecificationExecutor<ThongTinCapNhatDuongSu> {}
