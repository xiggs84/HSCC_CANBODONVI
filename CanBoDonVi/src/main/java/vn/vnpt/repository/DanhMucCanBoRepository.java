package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucCanBo;

/**
 * Spring Data JPA repository for the DanhMucCanBo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhMucCanBoRepository extends JpaRepository<DanhMucCanBo, Long>, JpaSpecificationExecutor<DanhMucCanBo> {}
