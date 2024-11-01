package vn.vnpt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucTuVietTat;

import java.util.List;

/**
 * Spring Data MongoDB repository for the DanhMucTuVietTat entity.
 */
@Repository
public interface DanhMucTuVietTatRepository extends MongoRepository<DanhMucTuVietTat, String> {
    List<DanhMucTuVietTat> findByIdDonVi(Long idDonVi);
}
