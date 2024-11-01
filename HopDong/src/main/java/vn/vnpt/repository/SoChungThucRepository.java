package vn.vnpt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.SoChungThuc;

import java.util.List;

/**
 * Spring Data MongoDB repository for the SoChungThuc entity.
 */
@Repository
public interface SoChungThucRepository extends MongoRepository<SoChungThuc, String> {
    List<SoChungThuc> findByIdDonVi(Long idDonVi);
}
