package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.Quyen;

/**
 * Spring Data JPA repository for the Quyen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuyenRepository extends JpaRepository<Quyen, Long> {}
