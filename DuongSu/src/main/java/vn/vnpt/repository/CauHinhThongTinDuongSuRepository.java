package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.CauHinhThongTinDuongSu;

/**
 * Spring Data JPA repository for the CauHinhThongTinDuongSu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CauHinhThongTinDuongSuRepository
    extends JpaRepository<CauHinhThongTinDuongSu, Long>, JpaSpecificationExecutor<CauHinhThongTinDuongSu> {}
