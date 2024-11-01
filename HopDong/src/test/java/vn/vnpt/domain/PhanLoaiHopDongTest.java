package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.PhanLoaiHopDongTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class PhanLoaiHopDongTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PhanLoaiHopDong.class);
        PhanLoaiHopDong phanLoaiHopDong1 = getPhanLoaiHopDongSample1();
        PhanLoaiHopDong phanLoaiHopDong2 = new PhanLoaiHopDong();
        assertThat(phanLoaiHopDong1).isNotEqualTo(phanLoaiHopDong2);

        phanLoaiHopDong2.setId(phanLoaiHopDong1.getId());
        assertThat(phanLoaiHopDong1).isEqualTo(phanLoaiHopDong2);

        phanLoaiHopDong2 = getPhanLoaiHopDongSample2();
        assertThat(phanLoaiHopDong1).isNotEqualTo(phanLoaiHopDong2);
    }
}
