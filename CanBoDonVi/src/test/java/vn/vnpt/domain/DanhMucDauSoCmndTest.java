package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhMucDauSoCmndTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucDauSoCmndTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucDauSoCmnd.class);
        DanhMucDauSoCmnd danhMucDauSoCmnd1 = getDanhMucDauSoCmndSample1();
        DanhMucDauSoCmnd danhMucDauSoCmnd2 = new DanhMucDauSoCmnd();
        assertThat(danhMucDauSoCmnd1).isNotEqualTo(danhMucDauSoCmnd2);

        danhMucDauSoCmnd2.setIdDauSo(danhMucDauSoCmnd1.getIdDauSo());
        assertThat(danhMucDauSoCmnd1).isEqualTo(danhMucDauSoCmnd2);

        danhMucDauSoCmnd2 = getDanhMucDauSoCmndSample2();
        assertThat(danhMucDauSoCmnd1).isNotEqualTo(danhMucDauSoCmnd2);
    }
}
