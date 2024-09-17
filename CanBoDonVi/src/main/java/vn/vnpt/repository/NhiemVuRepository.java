package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.NhiemVu;

/**
 * Spring Data JPA repository for the NhiemVu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NhiemVuRepository extends JpaRepository<NhiemVu, String> {}
