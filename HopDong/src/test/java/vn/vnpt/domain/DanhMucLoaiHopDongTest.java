package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhMucLoaiHopDongTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucLoaiHopDongTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucLoaiHopDong.class);
        DanhMucLoaiHopDong danhMucLoaiHopDong1 = getDanhMucLoaiHopDongSample1();
        DanhMucLoaiHopDong danhMucLoaiHopDong2 = new DanhMucLoaiHopDong();
        assertThat(danhMucLoaiHopDong1).isNotEqualTo(danhMucLoaiHopDong2);

        danhMucLoaiHopDong2.setId(danhMucLoaiHopDong1.getId());
        assertThat(danhMucLoaiHopDong1).isEqualTo(danhMucLoaiHopDong2);

        danhMucLoaiHopDong2 = getDanhMucLoaiHopDongSample2();
        assertThat(danhMucLoaiHopDong1).isNotEqualTo(danhMucLoaiHopDong2);
    }
}
