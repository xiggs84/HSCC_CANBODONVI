package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.NoiCapGttt;

/**
 * Spring Data JPA repository for the NoiCapGttt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NoiCapGtttRepository extends JpaRepository<NoiCapGttt, Long>, JpaSpecificationExecutor<NoiCapGttt> {}
