package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhMucLoaiTaiSanTestSamples.*;
import static vn.vnpt.domain.DanhSachTaiSanTestSamples.*;
import static vn.vnpt.domain.TaiSanDatNhaTestSamples.*;
import static vn.vnpt.domain.TaiSanDgcTestSamples.*;
import static vn.vnpt.domain.TaiSanTestSamples.*;
import static vn.vnpt.domain.ThongTinCapNhatTaiSanTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucLoaiTaiSanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucLoaiTaiSan.class);
        DanhMucLoaiTaiSan danhMucLoaiTaiSan1 = getDanhMucLoaiTaiSanSample1();
        DanhMucLoaiTaiSan danhMucLoaiTaiSan2 = new DanhMucLoaiTaiSan();
        assertThat(danhMucLoaiTaiSan1).isNotEqualTo(danhMucLoaiTaiSan2);

        danhMucLoaiTaiSan2.setIdLoaiTs(danhMucLoaiTaiSan1.getIdLoaiTs());
        assertThat(danhMucLoaiTaiSan1).isEqualTo(danhMucLoaiTaiSan2);

        danhMucLoaiTaiSan2 = getDanhMucLoaiTaiSanSample2();
        assertThat(danhMucLoaiTaiSan1).isNotEqualTo(danhMucLoaiTaiSan2);
    }

    @Test
    void loaiTaiSanTest() {
        DanhMucLoaiTaiSan danhMucLoaiTaiSan = getDanhMucLoaiTaiSanRandomSampleGenerator();
        TaiSan taiSanBack = getTaiSanRandomSampleGenerator();

        danhMucLoaiTaiSan.addLoaiTaiSan(taiSanBack);
        assertThat(danhMucLoaiTaiSan.getLoaiTaiSans()).containsOnly(taiSanBack);
        assertThat(taiSanBack.getDanhMucLoaiTaiSan()).isEqualTo(danhMucLoaiTaiSan);

        danhMucLoaiTaiSan.removeLoaiTaiSan(taiSanBack);
        assertThat(danhMucLoaiTaiSan.getLoaiTaiSans()).doesNotContain(taiSanBack);
        assertThat(taiSanBack.getDanhMucLoaiTaiSan()).isNull();

        danhMucLoaiTaiSan.loaiTaiSans(new HashSet<>(Set.of(taiSanBack)));
        assertThat(danhMucLoaiTaiSan.getLoaiTaiSans()).containsOnly(taiSanBack);
        assertThat(taiSanBack.getDanhMucLoaiTaiSan()).isEqualTo(danhMucLoaiTaiSan);

        danhMucLoaiTaiSan.setLoaiTaiSans(new HashSet<>());
        assertThat(danhMucLoaiTaiSan.getLoaiTaiSans()).doesNotContain(taiSanBack);
        assertThat(taiSanBack.getDanhMucLoaiTaiSan()).isNull();
    }

    @Test
    void danhSachTaiSanTest() {
        DanhMucLoaiTaiSan danhMucLoaiTaiSan = getDanhMucLoaiTaiSanRandomSampleGenerator();
        DanhSachTaiSan danhSachTaiSanBack = getDanhSachTaiSanRandomSampleGenerator();

        danhMucLoaiTaiSan.addDanhSachTaiSan(danhSachTaiSanBack);
        assertThat(danhMucLoaiTaiSan.getDanhSachTaiSans()).containsOnly(danhSachTaiSanBack);
        assertThat(danhSachTaiSanBack.getDanhMucLoaiTaiSan()).isEqualTo(danhMucLoaiTaiSan);

        danhMucLoaiTaiSan.removeDanhSachTaiSan(danhSachTaiSanBack);
        assertThat(danhMucLoaiTaiSan.getDanhSachTaiSans()).doesNotContain(danhSachTaiSanBack);
        assertThat(danhSachTaiSanBack.getDanhMucLoaiTaiSan()).isNull();

        danhMucLoaiTaiSan.danhSachTaiSans(new HashSet<>(Set.of(danhSachTaiSanBack)));
        assertThat(danhMucLoaiTaiSan.getDanhSachTaiSans()).containsOnly(danhSachTaiSanBack);
        assertThat(danhSachTaiSanBack.getDanhMucLoaiTaiSan()).isEqualTo(danhMucLoaiTaiSan);

        danhMucLoaiTaiSan.setDanhSachTaiSans(new HashSet<>());
        assertThat(danhMucLoaiTaiSan.getDanhSachTaiSans()).doesNotContain(danhSachTaiSanBack);
        assertThat(danhSachTaiSanBack.getDanhMucLoaiTaiSan()).isNull();
    }

    @Test
    void taiSanDgcTest() {
        DanhMucLoaiTaiSan danhMucLoaiTaiSan = getDanhMucLoaiTaiSanRandomSampleGenerator();
        TaiSanDgc taiSanDgcBack = getTaiSanDgcRandomSampleGenerator();

        danhMucLoaiTaiSan.addTaiSanDgc(taiSanDgcBack);
        assertThat(danhMucLoaiTaiSan.getTaiSanDgcs()).containsOnly(taiSanDgcBack);
        assertThat(taiSanDgcBack.getDanhMucLoaiTaiSan()).isEqualTo(danhMucLoaiTaiSan);

        danhMucLoaiTaiSan.removeTaiSanDgc(taiSanDgcBack);
        assertThat(danhMucLoaiTaiSan.getTaiSanDgcs()).doesNotContain(taiSanDgcBack);
        assertThat(taiSanDgcBack.getDanhMucLoaiTaiSan()).isNull();

        danhMucLoaiTaiSan.taiSanDgcs(new HashSet<>(Set.of(taiSanDgcBack)));
        assertThat(danhMucLoaiTaiSan.getTaiSanDgcs()).containsOnly(taiSanDgcBack);
        assertThat(taiSanDgcBack.getDanhMucLoaiTaiSan()).isEqualTo(danhMucLoaiTaiSan);

        danhMucLoaiTaiSan.setTaiSanDgcs(new HashSet<>());
        assertThat(danhMucLoaiTaiSan.getTaiSanDgcs()).doesNotContain(taiSanDgcBack);
        assertThat(taiSanDgcBack.getDanhMucLoaiTaiSan()).isNull();
    }

    @Test
    void taiSanDatNhaTest() {
        DanhMucLoaiTaiSan danhMucLoaiTaiSan = getDanhMucLoaiTaiSanRandomSampleGenerator();
        TaiSanDatNha taiSanDatNhaBack = getTaiSanDatNhaRandomSampleGenerator();

        danhMucLoaiTaiSan.addTaiSanDatNha(taiSanDatNhaBack);
        assertThat(danhMucLoaiTaiSan.getTaiSanDatNhas()).containsOnly(taiSanDatNhaBack);
        assertThat(taiSanDatNhaBack.getDanhMucLoaiTaiSan()).isEqualTo(danhMucLoaiTaiSan);

        danhMucLoaiTaiSan.removeTaiSanDatNha(taiSanDatNhaBack);
        assertThat(danhMucLoaiTaiSan.getTaiSanDatNhas()).doesNotContain(taiSanDatNhaBack);
        assertThat(taiSanDatNhaBack.getDanhMucLoaiTaiSan()).isNull();

        danhMucLoaiTaiSan.taiSanDatNhas(new HashSet<>(Set.of(taiSanDatNhaBack)));
        assertThat(danhMucLoaiTaiSan.getTaiSanDatNhas()).containsOnly(taiSanDatNhaBack);
        assertThat(taiSanDatNhaBack.getDanhMucLoaiTaiSan()).isEqualTo(danhMucLoaiTaiSan);

        danhMucLoaiTaiSan.setTaiSanDatNhas(new HashSet<>());
        assertThat(danhMucLoaiTaiSan.getTaiSanDatNhas()).doesNotContain(taiSanDatNhaBack);
        assertThat(taiSanDatNhaBack.getDanhMucLoaiTaiSan()).isNull();
    }

    @Test
    void thongTinCapNhatTaiSanTest() {
        DanhMucLoaiTaiSan danhMucLoaiTaiSan = getDanhMucLoaiTaiSanRandomSampleGenerator();
        ThongTinCapNhatTaiSan thongTinCapNhatTaiSanBack = getThongTinCapNhatTaiSanRandomSampleGenerator();

        danhMucLoaiTaiSan.addThongTinCapNhatTaiSan(thongTinCapNhatTaiSanBack);
        assertThat(danhMucLoaiTaiSan.getThongTinCapNhatTaiSans()).containsOnly(thongTinCapNhatTaiSanBack);
        assertThat(thongTinCapNhatTaiSanBack.getDanhMucLoaiTaiSan()).isEqualTo(danhMucLoaiTaiSan);

        danhMucLoaiTaiSan.removeThongTinCapNhatTaiSan(thongTinCapNhatTaiSanBack);
        assertThat(danhMucLoaiTaiSan.getThongTinCapNhatTaiSans()).doesNotContain(thongTinCapNhatTaiSanBack);
        assertThat(thongTinCapNhatTaiSanBack.getDanhMucLoaiTaiSan()).isNull();

        danhMucLoaiTaiSan.thongTinCapNhatTaiSans(new HashSet<>(Set.of(thongTinCapNhatTaiSanBack)));
        assertThat(danhMucLoaiTaiSan.getThongTinCapNhatTaiSans()).containsOnly(thongTinCapNhatTaiSanBack);
        assertThat(thongTinCapNhatTaiSanBack.getDanhMucLoaiTaiSan()).isEqualTo(danhMucLoaiTaiSan);

        danhMucLoaiTaiSan.setThongTinCapNhatTaiSans(new HashSet<>());
        assertThat(danhMucLoaiTaiSan.getThongTinCapNhatTaiSans()).doesNotContain(thongTinCapNhatTaiSanBack);
        assertThat(thongTinCapNhatTaiSanBack.getDanhMucLoaiTaiSan()).isNull();
    }
}
