package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.LoaiDonVi;

/**
 * Spring Data JPA repository for the LoaiDonVi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoaiDonViRepository extends JpaRepository<LoaiDonVi, String> {}
