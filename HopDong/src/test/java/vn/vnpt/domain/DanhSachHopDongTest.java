package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhSachHopDongTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhSachHopDongTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhSachHopDong.class);
        DanhSachHopDong danhSachHopDong1 = getDanhSachHopDongSample1();
        DanhSachHopDong danhSachHopDong2 = new DanhSachHopDong();
        assertThat(danhSachHopDong1).isNotEqualTo(danhSachHopDong2);

        danhSachHopDong2.setId(danhSachHopDong1.getId());
        assertThat(danhSachHopDong1).isEqualTo(danhSachHopDong2);

        danhSachHopDong2 = getDanhSachHopDongSample2();
        assertThat(danhSachHopDong1).isNotEqualTo(danhSachHopDong2);
    }
}
