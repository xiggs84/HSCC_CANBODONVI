package vn.vnpt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucLoaiVanBan;

import java.util.List;

/**
 * Spring Data MongoDB repository for the DanhMucLoaiVanBan entity.
 */
@Repository
public interface DanhMucLoaiVanBanRepository extends MongoRepository<DanhMucLoaiVanBan, String> {
    List<DanhMucLoaiVanBan> findByIdLoaiHopDong(String idLoaiHopDong);
}
