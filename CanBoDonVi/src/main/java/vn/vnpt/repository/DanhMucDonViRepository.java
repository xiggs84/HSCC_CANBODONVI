package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucDonVi;

/**
 * Spring Data JPA repository for the DanhMucDonVi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhMucDonViRepository extends JpaRepository<DanhMucDonVi, Long>, JpaSpecificationExecutor<DanhMucDonVi> {}
