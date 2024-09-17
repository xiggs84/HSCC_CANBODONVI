package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.CapQuanLyTestSamples.*;
import static vn.vnpt.domain.DanhMucDonViTestSamples.*;
import static vn.vnpt.domain.LoaiDonViTestSamples.*;
import static vn.vnpt.domain.NhiemVuTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucDonViTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucDonVi.class);
        DanhMucDonVi danhMucDonVi1 = getDanhMucDonViSample1();
        DanhMucDonVi danhMucDonVi2 = new DanhMucDonVi();
        assertThat(danhMucDonVi1).isNotEqualTo(danhMucDonVi2);

        danhMucDonVi2.setIdDonVi(danhMucDonVi1.getIdDonVi());
        assertThat(danhMucDonVi1).isEqualTo(danhMucDonVi2);

        danhMucDonVi2 = getDanhMucDonViSample2();
        assertThat(danhMucDonVi1).isNotEqualTo(danhMucDonVi2);
    }

    @Test
    void capQuanLyTest() {
        DanhMucDonVi danhMucDonVi = getDanhMucDonViRandomSampleGenerator();
        CapQuanLy capQuanLyBack = getCapQuanLyRandomSampleGenerator();

        danhMucDonVi.setCapQuanLy(capQuanLyBack);
        assertThat(danhMucDonVi.getCapQuanLy()).isEqualTo(capQuanLyBack);

        danhMucDonVi.capQuanLy(null);
        assertThat(danhMucDonVi.getCapQuanLy()).isNull();
    }

    @Test
    void loaiDonViTest() {
        DanhMucDonVi danhMucDonVi = getDanhMucDonViRandomSampleGenerator();
        LoaiDonVi loaiDonViBack = getLoaiDonViRandomSampleGenerator();

        danhMucDonVi.setLoaiDonVi(loaiDonViBack);
        assertThat(danhMucDonVi.getLoaiDonVi()).isEqualTo(loaiDonViBack);

        danhMucDonVi.loaiDonVi(null);
        assertThat(danhMucDonVi.getLoaiDonVi()).isNull();
    }

    @Test
    void nhiemVuTest() {
        DanhMucDonVi danhMucDonVi = getDanhMucDonViRandomSampleGenerator();
        NhiemVu nhiemVuBack = getNhiemVuRandomSampleGenerator();

        danhMucDonVi.setNhiemVu(nhiemVuBack);
        assertThat(danhMucDonVi.getNhiemVu()).isEqualTo(nhiemVuBack);

        danhMucDonVi.nhiemVu(null);
        assertThat(danhMucDonVi.getNhiemVu()).isNull();
    }
}
