package vn.vnpt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.ThongTinChungHopDong;

/**
 * Spring Data MongoDB repository for the ThongTinChungHopDong entity.
 */
@Repository
public interface ThongTinChungHopDongRepository extends MongoRepository<ThongTinChungHopDong, String> {}
