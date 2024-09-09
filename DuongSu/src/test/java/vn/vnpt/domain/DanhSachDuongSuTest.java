package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhSachDuongSuTestSamples.*;
import static vn.vnpt.domain.DuongSuTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhSachDuongSuTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhSachDuongSu.class);
        DanhSachDuongSu danhSachDuongSu1 = getDanhSachDuongSuSample1();
        DanhSachDuongSu danhSachDuongSu2 = new DanhSachDuongSu();
        assertThat(danhSachDuongSu1).isNotEqualTo(danhSachDuongSu2);

        danhSachDuongSu2.setId(danhSachDuongSu1.getId());
        assertThat(danhSachDuongSu1).isEqualTo(danhSachDuongSu2);

        danhSachDuongSu2 = getDanhSachDuongSuSample2();
        assertThat(danhSachDuongSu1).isNotEqualTo(danhSachDuongSu2);
    }

    @Test
    void idDuongSuTest() {
        DanhSachDuongSu danhSachDuongSu = getDanhSachDuongSuRandomSampleGenerator();
        DuongSu duongSuBack = getDuongSuRandomSampleGenerator();

        danhSachDuongSu.setIdDuongSu(duongSuBack);
        assertThat(danhSachDuongSu.getIdDuongSu()).isEqualTo(duongSuBack);

        danhSachDuongSu.idDuongSu(null);
        assertThat(danhSachDuongSu.getIdDuongSu()).isNull();
    }
}
