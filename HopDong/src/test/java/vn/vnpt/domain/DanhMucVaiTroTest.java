package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhMucVaiTroTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucVaiTroTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucVaiTro.class);
        DanhMucVaiTro danhMucVaiTro1 = getDanhMucVaiTroSample1();
        DanhMucVaiTro danhMucVaiTro2 = new DanhMucVaiTro();
        assertThat(danhMucVaiTro1).isNotEqualTo(danhMucVaiTro2);

        danhMucVaiTro2.setId(danhMucVaiTro1.getId());
        assertThat(danhMucVaiTro1).isEqualTo(danhMucVaiTro2);

        danhMucVaiTro2 = getDanhMucVaiTroSample2();
        assertThat(danhMucVaiTro1).isNotEqualTo(danhMucVaiTro2);
    }
}
