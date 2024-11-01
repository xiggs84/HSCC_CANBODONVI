package vn.vnpt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucLoaiGiayToChungThuc;

import java.util.List;

/**
 * Spring Data MongoDB repository for the DanhMucLoaiGiayToChungThuc entity.
 */
@Repository
public interface DanhMucLoaiGiayToChungThucRepository extends MongoRepository<DanhMucLoaiGiayToChungThuc, String> {
    List<DanhMucLoaiGiayToChungThuc> findByIdLoaiSo(String idLoaiSo);
}
