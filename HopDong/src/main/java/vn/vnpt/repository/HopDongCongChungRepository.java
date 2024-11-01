package vn.vnpt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.HopDongCongChung;

/**
 * Spring Data MongoDB repository for the HopDongCongChung entity.
 */
@Repository
public interface HopDongCongChungRepository extends MongoRepository<HopDongCongChung, String> {}
