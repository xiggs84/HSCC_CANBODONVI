package vn.vnpt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucNhomHopDong;

import java.util.Optional;

/**
 * Spring Data MongoDB repository for the DanhMucNhomHopDong entity.
 */
@Repository
public interface DanhMucNhomHopDongRepository extends MongoRepository<DanhMucNhomHopDong, String> {
    Optional<String> findDienGiaiById(String id);
}
