package vn.vnpt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.ChungThuc;

/**
 * Spring Data MongoDB repository for the ChungThuc entity.
 */
@Repository
public interface ChungThucRepository extends MongoRepository<ChungThuc, String> {}
