package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.ChiTietNganChanTaiSanTestSamples.*;
import static vn.vnpt.domain.TaiSanTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class ChiTietNganChanTaiSanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChiTietNganChanTaiSan.class);
        ChiTietNganChanTaiSan chiTietNganChanTaiSan1 = getChiTietNganChanTaiSanSample1();
        ChiTietNganChanTaiSan chiTietNganChanTaiSan2 = new ChiTietNganChanTaiSan();
        assertThat(chiTietNganChanTaiSan1).isNotEqualTo(chiTietNganChanTaiSan2);

        chiTietNganChanTaiSan2.setIdNganChan(chiTietNganChanTaiSan1.getIdNganChan());
        assertThat(chiTietNganChanTaiSan1).isEqualTo(chiTietNganChanTaiSan2);

        chiTietNganChanTaiSan2 = getChiTietNganChanTaiSanSample2();
        assertThat(chiTietNganChanTaiSan1).isNotEqualTo(chiTietNganChanTaiSan2);
    }

    @Test
    void taiSanTest() {
        ChiTietNganChanTaiSan chiTietNganChanTaiSan = getChiTietNganChanTaiSanRandomSampleGenerator();
        TaiSan taiSanBack = getTaiSanRandomSampleGenerator();

        chiTietNganChanTaiSan.setTaiSan(taiSanBack);
        assertThat(chiTietNganChanTaiSan.getTaiSan()).isEqualTo(taiSanBack);

        chiTietNganChanTaiSan.taiSan(null);
        assertThat(chiTietNganChanTaiSan.getTaiSan()).isNull();
    }
}
