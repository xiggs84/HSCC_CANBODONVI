package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucHuyen;

import java.util.List;

/**
 * Spring Data JPA repository for the DanhMucHuyen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhMucHuyenRepository extends JpaRepository<DanhMucHuyen, String> {
    List<DanhMucHuyen> findByMaTinh(String maTinh);
}
