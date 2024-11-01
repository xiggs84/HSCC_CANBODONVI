package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.QuanHeMaster;

/**
 * Spring Data JPA repository for the QuanHeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuanHeMasterRepository extends JpaRepository<QuanHeMaster, Long>, JpaSpecificationExecutor<QuanHeMaster> {}
