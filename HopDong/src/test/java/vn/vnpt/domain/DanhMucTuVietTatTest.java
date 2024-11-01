package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhMucTuVietTatTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucTuVietTatTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucTuVietTat.class);
        DanhMucTuVietTat danhMucTuVietTat1 = getDanhMucTuVietTatSample1();
        DanhMucTuVietTat danhMucTuVietTat2 = new DanhMucTuVietTat();
        assertThat(danhMucTuVietTat1).isNotEqualTo(danhMucTuVietTat2);

        danhMucTuVietTat2.setId(danhMucTuVietTat1.getId());
        assertThat(danhMucTuVietTat1).isEqualTo(danhMucTuVietTat2);

        danhMucTuVietTat2 = getDanhMucTuVietTatSample2();
        assertThat(danhMucTuVietTat1).isNotEqualTo(danhMucTuVietTat2);
    }
}
