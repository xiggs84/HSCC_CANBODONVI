package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DuongSuTrungCmndBak;

/**
 * Spring Data JPA repository for the DuongSuTrungCmndBak entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DuongSuTrungCmndBakRepository
    extends JpaRepository<DuongSuTrungCmndBak, Long>, JpaSpecificationExecutor<DuongSuTrungCmndBak> {}
