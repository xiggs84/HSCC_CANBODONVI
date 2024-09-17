package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucCapQuanLy;

/**
 * Spring Data JPA repository for the DanhMucCapQuanLy entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhMucCapQuanLyRepository extends JpaRepository<DanhMucCapQuanLy, Long> {}
