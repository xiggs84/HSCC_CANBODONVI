package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhMucLoaiSoCongChungTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucLoaiSoCongChungTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucLoaiSoCongChung.class);
        DanhMucLoaiSoCongChung danhMucLoaiSoCongChung1 = getDanhMucLoaiSoCongChungSample1();
        DanhMucLoaiSoCongChung danhMucLoaiSoCongChung2 = new DanhMucLoaiSoCongChung();
        assertThat(danhMucLoaiSoCongChung1).isNotEqualTo(danhMucLoaiSoCongChung2);

        danhMucLoaiSoCongChung2.setId(danhMucLoaiSoCongChung1.getId());
        assertThat(danhMucLoaiSoCongChung1).isEqualTo(danhMucLoaiSoCongChung2);

        danhMucLoaiSoCongChung2 = getDanhMucLoaiSoCongChungSample2();
        assertThat(danhMucLoaiSoCongChung1).isNotEqualTo(danhMucLoaiSoCongChung2);
    }
}
