package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhMucTinhTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucTinhTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucTinh.class);
        DanhMucTinh danhMucTinh1 = getDanhMucTinhSample1();
        DanhMucTinh danhMucTinh2 = new DanhMucTinh();
        assertThat(danhMucTinh1).isNotEqualTo(danhMucTinh2);

        danhMucTinh2.setMaTinh(danhMucTinh1.getMaTinh());
        assertThat(danhMucTinh1).isEqualTo(danhMucTinh2);

        danhMucTinh2 = getDanhMucTinhSample2();
        assertThat(danhMucTinh1).isNotEqualTo(danhMucTinh2);
    }
}
