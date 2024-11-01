package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DuongSuTestSamples.*;
import static vn.vnpt.domain.TaiSanDuongSuTestSamples.*;

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
    void duongSuTest() {
        TaiSanDuongSu taiSanDuongSu = getTaiSanDuongSuRandomSampleGenerator();
        DuongSu duongSuBack = getDuongSuRandomSampleGenerator();

        taiSanDuongSu.setDuongSu(duongSuBack);
        assertThat(taiSanDuongSu.getDuongSu()).isEqualTo(duongSuBack);

        taiSanDuongSu.duongSu(null);
        assertThat(taiSanDuongSu.getDuongSu()).isNull();
    }
}
