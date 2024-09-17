package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucQuocGia;

/**
 * Spring Data JPA repository for the DanhMucQuocGia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhMucQuocGiaRepository extends JpaRepository<DanhMucQuocGia, Long> {}
