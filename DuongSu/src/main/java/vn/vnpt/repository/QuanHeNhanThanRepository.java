package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.QuanHeNhanThan;

/**
 * Spring Data JPA repository for the QuanHeNhanThan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuanHeNhanThanRepository extends JpaRepository<QuanHeNhanThan, Long> {}
