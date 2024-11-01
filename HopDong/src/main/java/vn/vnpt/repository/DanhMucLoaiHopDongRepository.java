package vn.vnpt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucLoaiHopDong;

import java.util.List;

/**
 * Spring Data MongoDB repository for the DanhMucLoaiHopDong entity.
 */
@Repository
public interface DanhMucLoaiHopDongRepository extends MongoRepository<DanhMucLoaiHopDong, String> {
    List<DanhMucLoaiHopDong> findByIdDonVi(Long idDonVi);

    List<DanhMucLoaiHopDong> findByDienGiaiContainingOrIdNhomHopDongOrIdVaiTro1OrIdVaiTro2OrIdDonVi(
        String dienGiai, String idNhomHopDong, String idVaiTro1, String idVaiTro2, Long idDonVi);

    List<DanhMucLoaiHopDong> findByNhomTen(String nhomTen);
}
