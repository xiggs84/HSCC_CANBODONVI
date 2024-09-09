package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DuongSuTestSamples.*;
import static vn.vnpt.domain.QuanHeDuongSuTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class QuanHeDuongSuTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuanHeDuongSu.class);
        QuanHeDuongSu quanHeDuongSu1 = getQuanHeDuongSuSample1();
        QuanHeDuongSu quanHeDuongSu2 = new QuanHeDuongSu();
        assertThat(quanHeDuongSu1).isNotEqualTo(quanHeDuongSu2);

        quanHeDuongSu2.setIdQuanHe(quanHeDuongSu1.getIdQuanHe());
        assertThat(quanHeDuongSu1).isEqualTo(quanHeDuongSu2);

        quanHeDuongSu2 = getQuanHeDuongSuSample2();
        assertThat(quanHeDuongSu1).isNotEqualTo(quanHeDuongSu2);
    }

    @Test
    void idDuongSuTest() {
        QuanHeDuongSu quanHeDuongSu = getQuanHeDuongSuRandomSampleGenerator();
        DuongSu duongSuBack = getDuongSuRandomSampleGenerator();

        quanHeDuongSu.setIdDuongSu(duongSuBack);
        assertThat(quanHeDuongSu.getIdDuongSu()).isEqualTo(duongSuBack);

        quanHeDuongSu.idDuongSu(null);
        assertThat(quanHeDuongSu.getIdDuongSu()).isNull();
    }
}
