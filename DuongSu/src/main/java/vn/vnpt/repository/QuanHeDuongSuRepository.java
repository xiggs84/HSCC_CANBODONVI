package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.QuanHeDuongSu;

/**
 * Spring Data JPA repository for the QuanHeDuongSu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuanHeDuongSuRepository extends JpaRepository<QuanHeDuongSu, Long> {}
