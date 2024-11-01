package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.ChiTietNganChanTaiSanTestSamples.*;
import static vn.vnpt.domain.DanhMucLoaiTaiSanTestSamples.*;
import static vn.vnpt.domain.TaiSanDgcTestSamples.*;
import static vn.vnpt.domain.TaiSanDuongSuTestSamples.*;
import static vn.vnpt.domain.TaiSanTestSamples.*;
import static vn.vnpt.domain.ThongTinCapNhatTaiSanTestSamples.*;
import static vn.vnpt.domain.ThuaTachTestSamples.*;
import static vn.vnpt.domain.TinhTrangTaiSanTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class TaiSanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaiSan.class);
        TaiSan taiSan1 = getTaiSanSample1();
        TaiSan taiSan2 = new TaiSan();
        assertThat(taiSan1).isNotEqualTo(taiSan2);

        taiSan2.setIdTaiSan(taiSan1.getIdTaiSan());
        assertThat(taiSan1).isEqualTo(taiSan2);

        taiSan2 = getTaiSanSample2();
        assertThat(taiSan1).isNotEqualTo(taiSan2);
    }

    @Test
    void thuaTachTest() {
        TaiSan taiSan = getTaiSanRandomSampleGenerator();
        ThuaTach thuaTachBack = getThuaTachRandomSampleGenerator();

        taiSan.addThuaTach(thuaTachBack);
        assertThat(taiSan.getThuaTaches()).containsOnly(thuaTachBack);
        assertThat(thuaTachBack.getTaiSan()).isEqualTo(taiSan);

        taiSan.removeThuaTach(thuaTachBack);
        assertThat(taiSan.getThuaTaches()).doesNotContain(thuaTachBack);
        assertThat(thuaTachBack.getTaiSan()).isNull();

        taiSan.thuaTaches(new HashSet<>(Set.of(thuaTachBack)));
        assertThat(taiSan.getThuaTaches()).containsOnly(thuaTachBack);
        assertThat(thuaTachBack.getTaiSan()).isEqualTo(taiSan);

        taiSan.setThuaTaches(new HashSet<>());
        assertThat(taiSan.getThuaTaches()).doesNotContain(thuaTachBack);
        assertThat(thuaTachBack.getTaiSan()).isNull();
    }

    @Test
    void taiSanDuongSuTest() {
        TaiSan taiSan = getTaiSanRandomSampleGenerator();
        TaiSanDuongSu taiSanDuongSuBack = getTaiSanDuongSuRandomSampleGenerator();

        taiSan.addTaiSanDuongSu(taiSanDuongSuBack);
        assertThat(taiSan.getTaiSanDuongSus()).containsOnly(taiSanDuongSuBack);
        assertThat(taiSanDuongSuBack.getTaiSan()).isEqualTo(taiSan);

        taiSan.removeTaiSanDuongSu(taiSanDuongSuBack);
        assertThat(taiSan.getTaiSanDuongSus()).doesNotContain(taiSanDuongSuBack);
        assertThat(taiSanDuongSuBack.getTaiSan()).isNull();

        taiSan.taiSanDuongSus(new HashSet<>(Set.of(taiSanDuongSuBack)));
        assertThat(taiSan.getTaiSanDuongSus()).containsOnly(taiSanDuongSuBack);
        assertThat(taiSanDuongSuBack.getTaiSan()).isEqualTo(taiSan);

        taiSan.setTaiSanDuongSus(new HashSet<>());
        assertThat(taiSan.getTaiSanDuongSus()).doesNotContain(taiSanDuongSuBack);
        assertThat(taiSanDuongSuBack.getTaiSan()).isNull();
    }

    @Test
    void taiSanDgcTest() {
        TaiSan taiSan = getTaiSanRandomSampleGenerator();
        TaiSanDgc taiSanDgcBack = getTaiSanDgcRandomSampleGenerator();

        taiSan.addTaiSanDgc(taiSanDgcBack);
        assertThat(taiSan.getTaiSanDgcs()).containsOnly(taiSanDgcBack);
        assertThat(taiSanDgcBack.getTaiSan()).isEqualTo(taiSan);

        taiSan.removeTaiSanDgc(taiSanDgcBack);
        assertThat(taiSan.getTaiSanDgcs()).doesNotContain(taiSanDgcBack);
        assertThat(taiSanDgcBack.getTaiSan()).isNull();

        taiSan.taiSanDgcs(new HashSet<>(Set.of(taiSanDgcBack)));
        assertThat(taiSan.getTaiSanDgcs()).containsOnly(taiSanDgcBack);
        assertThat(taiSanDgcBack.getTaiSan()).isEqualTo(taiSan);

        taiSan.setTaiSanDgcs(new HashSet<>());
        assertThat(taiSan.getTaiSanDgcs()).doesNotContain(taiSanDgcBack);
        assertThat(taiSanDgcBack.getTaiSan()).isNull();
    }

    @Test
    void thongTinCapNhatTaiSanTest() {
        TaiSan taiSan = getTaiSanRandomSampleGenerator();
        ThongTinCapNhatTaiSan thongTinCapNhatTaiSanBack = getThongTinCapNhatTaiSanRandomSampleGenerator();

        taiSan.addThongTinCapNhatTaiSan(thongTinCapNhatTaiSanBack);
        assertThat(taiSan.getThongTinCapNhatTaiSans()).containsOnly(thongTinCapNhatTaiSanBack);
        assertThat(thongTinCapNhatTaiSanBack.getTaiSan()).isEqualTo(taiSan);

        taiSan.removeThongTinCapNhatTaiSan(thongTinCapNhatTaiSanBack);
        assertThat(taiSan.getThongTinCapNhatTaiSans()).doesNotContain(thongTinCapNhatTaiSanBack);
        assertThat(thongTinCapNhatTaiSanBack.getTaiSan()).isNull();

        taiSan.thongTinCapNhatTaiSans(new HashSet<>(Set.of(thongTinCapNhatTaiSanBack)));
        assertThat(taiSan.getThongTinCapNhatTaiSans()).containsOnly(thongTinCapNhatTaiSanBack);
        assertThat(thongTinCapNhatTaiSanBack.getTaiSan()).isEqualTo(taiSan);

        taiSan.setThongTinCapNhatTaiSans(new HashSet<>());
        assertThat(taiSan.getThongTinCapNhatTaiSans()).doesNotContain(thongTinCapNhatTaiSanBack);
        assertThat(thongTinCapNhatTaiSanBack.getTaiSan()).isNull();
    }

    @Test
    void danhMucLoaiTaiSanTest() {
        TaiSan taiSan = getTaiSanRandomSampleGenerator();
        DanhMucLoaiTaiSan danhMucLoaiTaiSanBack = getDanhMucLoaiTaiSanRandomSampleGenerator();

        taiSan.setDanhMucLoaiTaiSan(danhMucLoaiTaiSanBack);
        assertThat(taiSan.getDanhMucLoaiTaiSan()).isEqualTo(danhMucLoaiTaiSanBack);

        taiSan.danhMucLoaiTaiSan(null);
        assertThat(taiSan.getDanhMucLoaiTaiSan()).isNull();
    }

    @Test
    void tinhTrangTaiSanTest() {
        TaiSan taiSan = getTaiSanRandomSampleGenerator();
        TinhTrangTaiSan tinhTrangTaiSanBack = getTinhTrangTaiSanRandomSampleGenerator();

        taiSan.setTinhTrangTaiSan(tinhTrangTaiSanBack);
        assertThat(taiSan.getTinhTrangTaiSan()).isEqualTo(tinhTrangTaiSanBack);

        taiSan.tinhTrangTaiSan(null);
        assertThat(taiSan.getTinhTrangTaiSan()).isNull();
    }

    @Test
    void chiTietNganChanTaiSanTest() {
        TaiSan taiSan = getTaiSanRandomSampleGenerator();
        ChiTietNganChanTaiSan chiTietNganChanTaiSanBack = getChiTietNganChanTaiSanRandomSampleGenerator();

        taiSan.addChiTietNganChanTaiSan(chiTietNganChanTaiSanBack);
        assertThat(taiSan.getChiTietNganChanTaiSans()).containsOnly(chiTietNganChanTaiSanBack);
        assertThat(chiTietNganChanTaiSanBack.getTaiSan()).isEqualTo(taiSan);

        taiSan.removeChiTietNganChanTaiSan(chiTietNganChanTaiSanBack);
        assertThat(taiSan.getChiTietNganChanTaiSans()).doesNotContain(chiTietNganChanTaiSanBack);
        assertThat(chiTietNganChanTaiSanBack.getTaiSan()).isNull();

        taiSan.chiTietNganChanTaiSans(new HashSet<>(Set.of(chiTietNganChanTaiSanBack)));
        assertThat(taiSan.getChiTietNganChanTaiSans()).containsOnly(chiTietNganChanTaiSanBack);
        assertThat(chiTietNganChanTaiSanBack.getTaiSan()).isEqualTo(taiSan);

        taiSan.setChiTietNganChanTaiSans(new HashSet<>());
        assertThat(taiSan.getChiTietNganChanTaiSans()).doesNotContain(chiTietNganChanTaiSanBack);
        assertThat(chiTietNganChanTaiSanBack.getTaiSan()).isNull();
    }
}
