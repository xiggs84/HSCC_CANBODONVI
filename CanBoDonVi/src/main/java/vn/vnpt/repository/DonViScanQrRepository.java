package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DonViScanQr;

/**
 * Spring Data JPA repository for the DonViScanQr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DonViScanQrRepository extends JpaRepository<DonViScanQr, Long>, JpaSpecificationExecutor<DonViScanQr> {}
