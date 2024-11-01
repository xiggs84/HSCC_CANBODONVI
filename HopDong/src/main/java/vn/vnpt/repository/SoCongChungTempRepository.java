package vn.vnpt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.SoCongChungTemp;

/**
 * Spring Data MongoDB repository for the SoCongChungTemp entity.
 */
@Repository
public interface SoCongChungTempRepository extends MongoRepository<SoCongChungTemp, String> {}
