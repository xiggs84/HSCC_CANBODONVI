package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DmTinhTmp;

/**
 * Spring Data JPA repository for the DmTinhTmp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DmTinhTmpRepository extends JpaRepository<DmTinhTmp, Long>, JpaSpecificationExecutor<DmTinhTmp> {}
