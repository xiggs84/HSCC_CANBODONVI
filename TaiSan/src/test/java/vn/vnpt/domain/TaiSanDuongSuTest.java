package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.TaiSanDuongSuTestSamples.*;
import static vn.vnpt.domain.TaiSanTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class TaiSanDuongSuTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaiSanDuongSu.class);
        TaiSanDuongSu taiSanDuongSu1 = getTaiSanDuongSuSample1();
        TaiSanDuongSu taiSanDuongSu2 = new TaiSanDuongSu();
        assertThat(taiSanDuongSu1).isNotEqualTo(taiSanDuongSu2);

        taiSanDuongSu2.setId(taiSanDuongSu1.getId());
        assertThat(taiSanDuongSu1).isEqualTo(taiSanDuongSu2);

        taiSanDuongSu2 = getTaiSanDuongSuSample2();
        assertThat(taiSanDuongSu1).isNotEqualTo(taiSanDuongSu2);
    }

    @Test
    void taiSanTest() {
        TaiSanDuongSu taiSanDuongSu = getTaiSanDuongSuRandomSampleGenerator();
        TaiSan taiSanBack = getTaiSanRandomSampleGenerator();

        taiSanDuongSu.setTaiSan(taiSanBack);
        assertThat(taiSanDuongSu.getTaiSan()).isEqualTo(taiSanBack);

        taiSanDuongSu.taiSan(null);
        assertThat(taiSanDuongSu.getTaiSan()).isNull();
    }
}
