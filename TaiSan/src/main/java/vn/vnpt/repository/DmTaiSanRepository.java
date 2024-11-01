package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DmTaiSan;

/**
 * Spring Data JPA repository for the DmTaiSan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DmTaiSanRepository extends JpaRepository<DmTaiSan, Long> {}
