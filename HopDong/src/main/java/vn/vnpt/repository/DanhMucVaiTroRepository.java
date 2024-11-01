package vn.vnpt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucVaiTro;

import java.util.Optional;

/**
 * Spring Data MongoDB repository for the DanhMucVaiTro entity.
 */
@Repository
public interface DanhMucVaiTroRepository extends MongoRepository<DanhMucVaiTro, String> {
    Optional<String> findDienGiaiById(String id);
}
