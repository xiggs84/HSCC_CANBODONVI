package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhMucCapQuanLyTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucCapQuanLyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucCapQuanLy.class);
        DanhMucCapQuanLy danhMucCapQuanLy1 = getDanhMucCapQuanLySample1();
        DanhMucCapQuanLy danhMucCapQuanLy2 = new DanhMucCapQuanLy();
        assertThat(danhMucCapQuanLy1).isNotEqualTo(danhMucCapQuanLy2);

        danhMucCapQuanLy2.setIdCapQl(danhMucCapQuanLy1.getIdCapQl());
        assertThat(danhMucCapQuanLy1).isEqualTo(danhMucCapQuanLy2);

        danhMucCapQuanLy2 = getDanhMucCapQuanLySample2();
        assertThat(danhMucCapQuanLy1).isNotEqualTo(danhMucCapQuanLy2);
    }
}
