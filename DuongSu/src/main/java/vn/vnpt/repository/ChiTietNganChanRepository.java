package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.ChiTietNganChan;

/**
 * Spring Data JPA repository for the ChiTietNganChan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChiTietNganChanRepository extends JpaRepository<ChiTietNganChan, Long>, JpaSpecificationExecutor<ChiTietNganChan> {}
