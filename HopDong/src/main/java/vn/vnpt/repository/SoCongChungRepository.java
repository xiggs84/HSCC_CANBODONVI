package vn.vnpt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.SoCongChung;

import java.util.List;

/**
 * Spring Data MongoDB repository for the SoCongChung entity.
 */
@Repository
public interface SoCongChungRepository extends MongoRepository<SoCongChung, String> {
    List<SoCongChung> findByIdDonVi(Long idDonVi);
}
