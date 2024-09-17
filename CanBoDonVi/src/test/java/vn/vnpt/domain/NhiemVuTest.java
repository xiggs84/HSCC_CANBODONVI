package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhMucDonViTestSamples.*;
import static vn.vnpt.domain.NhiemVuTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class NhiemVuTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhiemVu.class);
        NhiemVu nhiemVu1 = getNhiemVuSample1();
        NhiemVu nhiemVu2 = new NhiemVu();
        assertThat(nhiemVu1).isNotEqualTo(nhiemVu2);

        nhiemVu2.setIdNhiemVu(nhiemVu1.getIdNhiemVu());
        assertThat(nhiemVu1).isEqualTo(nhiemVu2);

        nhiemVu2 = getNhiemVuSample2();
        assertThat(nhiemVu1).isNotEqualTo(nhiemVu2);
    }

    @Test
    void idNhiemVuTest() {
        NhiemVu nhiemVu = getNhiemVuRandomSampleGenerator();
        DanhMucDonVi danhMucDonViBack = getDanhMucDonViRandomSampleGenerator();

        nhiemVu.addIdNhiemVu(danhMucDonViBack);
        assertThat(nhiemVu.getIdNhiemVus()).containsOnly(danhMucDonViBack);
        assertThat(danhMucDonViBack.getNhiemVu()).isEqualTo(nhiemVu);

        nhiemVu.removeIdNhiemVu(danhMucDonViBack);
        assertThat(nhiemVu.getIdNhiemVus()).doesNotContain(danhMucDonViBack);
        assertThat(danhMucDonViBack.getNhiemVu()).isNull();

        nhiemVu.idNhiemVus(new HashSet<>(Set.of(danhMucDonViBack)));
        assertThat(nhiemVu.getIdNhiemVus()).containsOnly(danhMucDonViBack);
        assertThat(danhMucDonViBack.getNhiemVu()).isEqualTo(nhiemVu);

        nhiemVu.setIdNhiemVus(new HashSet<>());
        assertThat(nhiemVu.getIdNhiemVus()).doesNotContain(danhMucDonViBack);
        assertThat(danhMucDonViBack.getNhiemVu()).isNull();
    }
}
