package vn.vnpt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.PhanLoaiHopDong;

import java.util.List;

/**
 * Spring Data MongoDB repository for the PhanLoaiHopDong entity.
 */
@Repository
public interface PhanLoaiHopDongRepository extends MongoRepository<PhanLoaiHopDong, String> {
    List<PhanLoaiHopDong> findByIdNhomHopDong(String idNhomHopDong);
}
