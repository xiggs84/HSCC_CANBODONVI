package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DuongSuTrungCmnd;

/**
 * Spring Data JPA repository for the DuongSuTrungCmnd entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DuongSuTrungCmndRepository extends JpaRepository<DuongSuTrungCmnd, Long> {}
