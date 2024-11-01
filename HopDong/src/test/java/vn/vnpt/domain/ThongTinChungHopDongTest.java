package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.ThongTinChungHopDongTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class ThongTinChungHopDongTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThongTinChungHopDong.class);
        ThongTinChungHopDong thongTinChungHopDong1 = getThongTinChungHopDongSample1();
        ThongTinChungHopDong thongTinChungHopDong2 = new ThongTinChungHopDong();
        assertThat(thongTinChungHopDong1).isNotEqualTo(thongTinChungHopDong2);

        thongTinChungHopDong2.setId(thongTinChungHopDong1.getId());
        assertThat(thongTinChungHopDong1).isEqualTo(thongTinChungHopDong2);

        thongTinChungHopDong2 = getThongTinChungHopDongSample2();
        assertThat(thongTinChungHopDong1).isNotEqualTo(thongTinChungHopDong2);
    }
}
