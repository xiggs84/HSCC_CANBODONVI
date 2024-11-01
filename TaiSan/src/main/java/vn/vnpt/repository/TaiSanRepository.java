package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.TaiSan;

/**
 * Spring Data JPA repository for the TaiSan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaiSanRepository extends JpaRepository<TaiSan, Long>, JpaSpecificationExecutor<TaiSan> {}
