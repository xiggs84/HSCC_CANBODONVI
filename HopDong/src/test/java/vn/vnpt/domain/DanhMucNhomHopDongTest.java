package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhMucNhomHopDongTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucNhomHopDongTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucNhomHopDong.class);
        DanhMucNhomHopDong danhMucNhomHopDong1 = getDanhMucNhomHopDongSample1();
        DanhMucNhomHopDong danhMucNhomHopDong2 = new DanhMucNhomHopDong();
        assertThat(danhMucNhomHopDong1).isNotEqualTo(danhMucNhomHopDong2);

        danhMucNhomHopDong2.setId(danhMucNhomHopDong1.getId());
        assertThat(danhMucNhomHopDong1).isEqualTo(danhMucNhomHopDong2);

        danhMucNhomHopDong2 = getDanhMucNhomHopDongSample2();
        assertThat(danhMucNhomHopDong1).isNotEqualTo(danhMucNhomHopDong2);
    }
}
