package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.CanBoQuyenTestSamples.*;
import static vn.vnpt.domain.DanhMucDonViTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class CanBoQuyenTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CanBoQuyen.class);
        CanBoQuyen canBoQuyen1 = getCanBoQuyenSample1();
        CanBoQuyen canBoQuyen2 = new CanBoQuyen();
        assertThat(canBoQuyen1).isNotEqualTo(canBoQuyen2);

        canBoQuyen2.setId(canBoQuyen1.getId());
        assertThat(canBoQuyen1).isEqualTo(canBoQuyen2);

        canBoQuyen2 = getCanBoQuyenSample2();
        assertThat(canBoQuyen1).isNotEqualTo(canBoQuyen2);
    }

    @Test
    void danhMucDonViTest() {
        CanBoQuyen canBoQuyen = getCanBoQuyenRandomSampleGenerator();
        DanhMucDonVi danhMucDonViBack = getDanhMucDonViRandomSampleGenerator();

        canBoQuyen.setDanhMucDonVi(danhMucDonViBack);
        assertThat(canBoQuyen.getDanhMucDonVi()).isEqualTo(danhMucDonViBack);

        canBoQuyen.danhMucDonVi(null);
        assertThat(canBoQuyen.getDanhMucDonVi()).isNull();
    }
}
