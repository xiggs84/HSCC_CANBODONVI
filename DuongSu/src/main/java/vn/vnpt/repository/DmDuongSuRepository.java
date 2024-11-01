package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DmDuongSu;

/**
 * Spring Data JPA repository for the DmDuongSu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DmDuongSuRepository extends JpaRepository<DmDuongSu, Long>, JpaSpecificationExecutor<DmDuongSu> {}
