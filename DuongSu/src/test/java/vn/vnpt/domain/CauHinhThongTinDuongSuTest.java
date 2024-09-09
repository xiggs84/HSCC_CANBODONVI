package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.CauHinhThongTinDuongSuTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class CauHinhThongTinDuongSuTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CauHinhThongTinDuongSu.class);
        CauHinhThongTinDuongSu cauHinhThongTinDuongSu1 = getCauHinhThongTinDuongSuSample1();
        CauHinhThongTinDuongSu cauHinhThongTinDuongSu2 = new CauHinhThongTinDuongSu();
        assertThat(cauHinhThongTinDuongSu1).isNotEqualTo(cauHinhThongTinDuongSu2);

        cauHinhThongTinDuongSu2.setIdCauHinh(cauHinhThongTinDuongSu1.getIdCauHinh());
        assertThat(cauHinhThongTinDuongSu1).isEqualTo(cauHinhThongTinDuongSu2);

        cauHinhThongTinDuongSu2 = getCauHinhThongTinDuongSuSample2();
        assertThat(cauHinhThongTinDuongSu1).isNotEqualTo(cauHinhThongTinDuongSu2);
    }
}
