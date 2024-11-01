package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhMucLoaiVanBanTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucLoaiVanBanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucLoaiVanBan.class);
        DanhMucLoaiVanBan danhMucLoaiVanBan1 = getDanhMucLoaiVanBanSample1();
        DanhMucLoaiVanBan danhMucLoaiVanBan2 = new DanhMucLoaiVanBan();
        assertThat(danhMucLoaiVanBan1).isNotEqualTo(danhMucLoaiVanBan2);

        danhMucLoaiVanBan2.setId(danhMucLoaiVanBan1.getId());
        assertThat(danhMucLoaiVanBan1).isEqualTo(danhMucLoaiVanBan2);

        danhMucLoaiVanBan2 = getDanhMucLoaiVanBanSample2();
        assertThat(danhMucLoaiVanBan1).isNotEqualTo(danhMucLoaiVanBan2);
    }
}
