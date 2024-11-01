package vn.vnpt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhSachHopDong;

/**
 * Spring Data MongoDB repository for the DanhSachHopDong entity.
 */
@Repository
public interface DanhSachHopDongRepository extends MongoRepository<DanhSachHopDong, String> {}
