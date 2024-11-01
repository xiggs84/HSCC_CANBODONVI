package vn.vnpt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.LoaiHopDongCongChung;

/**
 * Spring Data MongoDB repository for the LoaiHopDongCongChung entity.
 */
@Repository
public interface LoaiHopDongCongChungRepository extends MongoRepository<LoaiHopDongCongChung, String> {}
