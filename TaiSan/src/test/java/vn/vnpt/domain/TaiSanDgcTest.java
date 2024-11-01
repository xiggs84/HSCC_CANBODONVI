package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhMucLoaiTaiSanTestSamples.*;
import static vn.vnpt.domain.TaiSanDgcTestSamples.*;
import static vn.vnpt.domain.TaiSanTestSamples.*;
import static vn.vnpt.domain.TinhTrangTaiSanTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class TaiSanDgcTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaiSanDgc.class);
        TaiSanDgc taiSanDgc1 = getTaiSanDgcSample1();
        TaiSanDgc taiSanDgc2 = new TaiSanDgc();
        assertThat(taiSanDgc1).isNotEqualTo(taiSanDgc2);

        taiSanDgc2.setId(taiSanDgc1.getId());
        assertThat(taiSanDgc1).isEqualTo(taiSanDgc2);

        taiSanDgc2 = getTaiSanDgcSample2();
        assertThat(taiSanDgc1).isNotEqualTo(taiSanDgc2);
    }

    @Test
    void taiSanTest() {
        TaiSanDgc taiSanDgc = getTaiSanDgcRandomSampleGenerator();
        TaiSan taiSanBack = getTaiSanRandomSampleGenerator();

        taiSanDgc.setTaiSan(taiSanBack);
        assertThat(taiSanDgc.getTaiSan()).isEqualTo(taiSanBack);

        taiSanDgc.taiSan(null);
        assertThat(taiSanDgc.getTaiSan()).isNull();
    }

    @Test
    void danhMucLoaiTaiSanTest() {
        TaiSanDgc taiSanDgc = getTaiSanDgcRandomSampleGenerator();
        DanhMucLoaiTaiSan danhMucLoaiTaiSanBack = getDanhMucLoaiTaiSanRandomSampleGenerator();

        taiSanDgc.setDanhMucLoaiTaiSan(danhMucLoaiTaiSanBack);
        assertThat(taiSanDgc.getDanhMucLoaiTaiSan()).isEqualTo(danhMucLoaiTaiSanBack);

        taiSanDgc.danhMucLoaiTaiSan(null);
        assertThat(taiSanDgc.getDanhMucLoaiTaiSan()).isNull();
    }

    @Test
    void tinhTrangTaiSanTest() {
        TaiSanDgc taiSanDgc = getTaiSanDgcRandomSampleGenerator();
        TinhTrangTaiSan tinhTrangTaiSanBack = getTinhTrangTaiSanRandomSampleGenerator();

        taiSanDgc.setTinhTrangTaiSan(tinhTrangTaiSanBack);
        assertThat(taiSanDgc.getTinhTrangTaiSan()).isEqualTo(tinhTrangTaiSanBack);

        taiSanDgc.tinhTrangTaiSan(null);
        assertThat(taiSanDgc.getTinhTrangTaiSan()).isNull();
    }
}
