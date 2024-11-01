package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.ChiTietNganChanTaiSan;

/**
 * Spring Data JPA repository for the ChiTietNganChanTaiSan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChiTietNganChanTaiSanRepository
    extends JpaRepository<ChiTietNganChanTaiSan, Long>, JpaSpecificationExecutor<ChiTietNganChanTaiSan> {}
