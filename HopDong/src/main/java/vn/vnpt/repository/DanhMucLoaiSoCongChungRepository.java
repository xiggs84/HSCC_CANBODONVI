package vn.vnpt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucLoaiSoCongChung;

/**
 * Spring Data MongoDB repository for the DanhMucLoaiSoCongChung entity.
 */
@Repository
public interface DanhMucLoaiSoCongChungRepository extends MongoRepository<DanhMucLoaiSoCongChung, String> {}
