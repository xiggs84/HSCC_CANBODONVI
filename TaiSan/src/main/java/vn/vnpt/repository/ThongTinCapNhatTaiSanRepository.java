package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.ThongTinCapNhatTaiSan;

/**
 * Spring Data JPA repository for the ThongTinCapNhatTaiSan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ThongTinCapNhatTaiSanRepository
    extends JpaRepository<ThongTinCapNhatTaiSan, Long>, JpaSpecificationExecutor<ThongTinCapNhatTaiSan> {}
