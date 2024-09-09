package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.ChiTietNganChanTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class ChiTietNganChanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChiTietNganChan.class);
        ChiTietNganChan chiTietNganChan1 = getChiTietNganChanSample1();
        ChiTietNganChan chiTietNganChan2 = new ChiTietNganChan();
        assertThat(chiTietNganChan1).isNotEqualTo(chiTietNganChan2);

        chiTietNganChan2.setId(chiTietNganChan1.getId());
        assertThat(chiTietNganChan1).isEqualTo(chiTietNganChan2);

        chiTietNganChan2 = getChiTietNganChanSample2();
        assertThat(chiTietNganChan1).isNotEqualTo(chiTietNganChan2);
    }
}
