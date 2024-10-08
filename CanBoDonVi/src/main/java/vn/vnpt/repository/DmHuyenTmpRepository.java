package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DmHuyenTmp;

/**
 * Spring Data JPA repository for the DmHuyenTmp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DmHuyenTmpRepository extends JpaRepository<DmHuyenTmp, Long>, JpaSpecificationExecutor<DmHuyenTmp> {}
