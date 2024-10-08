package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.CapQuanLy;

/**
 * Spring Data JPA repository for the CapQuanLy entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CapQuanLyRepository extends JpaRepository<CapQuanLy, String>, JpaSpecificationExecutor<CapQuanLy> {}
