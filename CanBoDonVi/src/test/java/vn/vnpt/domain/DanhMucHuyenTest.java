package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhMucHuyenTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucHuyenTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucHuyen.class);
        DanhMucHuyen danhMucHuyen1 = getDanhMucHuyenSample1();
        DanhMucHuyen danhMucHuyen2 = new DanhMucHuyen();
        assertThat(danhMucHuyen1).isNotEqualTo(danhMucHuyen2);

        danhMucHuyen2.setMaHuyen(danhMucHuyen1.getMaHuyen());
        assertThat(danhMucHuyen1).isEqualTo(danhMucHuyen2);

        danhMucHuyen2 = getDanhMucHuyenSample2();
        assertThat(danhMucHuyen1).isNotEqualTo(danhMucHuyen2);
    }
}
