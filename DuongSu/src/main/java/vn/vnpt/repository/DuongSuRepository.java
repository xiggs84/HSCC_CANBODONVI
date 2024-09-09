package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DuongSu;

import java.util.Optional;

/**
 * Spring Data JPA repository for the DuongSu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DuongSuRepository extends JpaRepository<DuongSu, Long> {
    Optional<DuongSu> findBySoGiayTo(String soGiayTo);
}
