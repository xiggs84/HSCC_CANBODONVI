package vn.vnpt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.LoaiChungThuc;

/**
 * Spring Data MongoDB repository for the LoaiChungThuc entity.
 */
@Repository
public interface LoaiChungThucRepository extends MongoRepository<LoaiChungThuc, String> {}
