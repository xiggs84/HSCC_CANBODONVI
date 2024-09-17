package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DmXaTmp;

/**
 * Spring Data JPA repository for the DmXaTmp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DmXaTmpRepository extends JpaRepository<DmXaTmp, Long> {}
