package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucXa;

import java.util.List;

/**
 * Spring Data JPA repository for the DanhMucXa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhMucXaRepository extends JpaRepository<DanhMucXa, String> {
    List<DanhMucXa> findByMaHuyen(String maHuyen);
}
