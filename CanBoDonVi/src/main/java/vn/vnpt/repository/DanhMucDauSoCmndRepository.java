package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucDauSoCmnd;

/**
 * Spring Data JPA repository for the DanhMucDauSoCmnd entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhMucDauSoCmndRepository extends JpaRepository<DanhMucDauSoCmnd, Long>, JpaSpecificationExecutor<DanhMucDauSoCmnd> {}
