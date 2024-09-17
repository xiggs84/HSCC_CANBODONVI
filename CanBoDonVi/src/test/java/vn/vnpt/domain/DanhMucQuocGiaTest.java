package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhMucQuocGiaTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucQuocGiaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucQuocGia.class);
        DanhMucQuocGia danhMucQuocGia1 = getDanhMucQuocGiaSample1();
        DanhMucQuocGia danhMucQuocGia2 = new DanhMucQuocGia();
        assertThat(danhMucQuocGia1).isNotEqualTo(danhMucQuocGia2);

        danhMucQuocGia2.setIdQuocGia(danhMucQuocGia1.getIdQuocGia());
        assertThat(danhMucQuocGia1).isEqualTo(danhMucQuocGia2);

        danhMucQuocGia2 = getDanhMucQuocGiaSample2();
        assertThat(danhMucQuocGia1).isNotEqualTo(danhMucQuocGia2);
    }
}
