package vn.vnpt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhSachChungThuc;

/**
 * Spring Data MongoDB repository for the DanhSachChungThuc entity.
 */
@Repository
public interface DanhSachChungThucRepository extends MongoRepository<DanhSachChungThuc, String> {}
