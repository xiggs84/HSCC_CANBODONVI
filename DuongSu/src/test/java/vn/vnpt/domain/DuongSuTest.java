package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhSachDuongSuTestSamples.*;
import static vn.vnpt.domain.DuongSuTestSamples.*;
import static vn.vnpt.domain.DuongSuTrungCmndBakTestSamples.*;
import static vn.vnpt.domain.DuongSuTrungCmndTestSamples.*;
import static vn.vnpt.domain.LoaiDuongSuTestSamples.*;
import static vn.vnpt.domain.LoaiGiayToTestSamples.*;
import static vn.vnpt.domain.QuanHeDuongSuTestSamples.*;
import static vn.vnpt.domain.TaiSanDuongSuTestSamples.*;
import static vn.vnpt.domain.ThongTinCapNhatDuongSuTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DuongSuTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DuongSu.class);
        DuongSu duongSu1 = getDuongSuSample1();
        DuongSu duongSu2 = new DuongSu();
        assertThat(duongSu1).isNotEqualTo(duongSu2);

        duongSu2.setIdDuongSu(duongSu1.getIdDuongSu());
        assertThat(duongSu1).isEqualTo(duongSu2);

        duongSu2 = getDuongSuSample2();
        assertThat(duongSu1).isNotEqualTo(duongSu2);
    }

    @Test
    void thongTinCapNhatTest() {
        DuongSu duongSu = getDuongSuRandomSampleGenerator();
        ThongTinCapNhatDuongSu thongTinCapNhatDuongSuBack = getThongTinCapNhatDuongSuRandomSampleGenerator();

        duongSu.addThongTinCapNhat(thongTinCapNhatDuongSuBack);
        assertThat(duongSu.getThongTinCapNhats()).containsOnly(thongTinCapNhatDuongSuBack);
        assertThat(thongTinCapNhatDuongSuBack.getDuongSu()).isEqualTo(duongSu);

        duongSu.removeThongTinCapNhat(thongTinCapNhatDuongSuBack);
        assertThat(duongSu.getThongTinCapNhats()).doesNotContain(thongTinCapNhatDuongSuBack);
        assertThat(thongTinCapNhatDuongSuBack.getDuongSu()).isNull();

        duongSu.thongTinCapNhats(new HashSet<>(Set.of(thongTinCapNhatDuongSuBack)));
        assertThat(duongSu.getThongTinCapNhats()).containsOnly(thongTinCapNhatDuongSuBack);
        assertThat(thongTinCapNhatDuongSuBack.getDuongSu()).isEqualTo(duongSu);

        duongSu.setThongTinCapNhats(new HashSet<>());
        assertThat(duongSu.getThongTinCapNhats()).doesNotContain(thongTinCapNhatDuongSuBack);
        assertThat(thongTinCapNhatDuongSuBack.getDuongSu()).isNull();
    }

    @Test
    void taiSanDuongSuTest() {
        DuongSu duongSu = getDuongSuRandomSampleGenerator();
        TaiSanDuongSu taiSanDuongSuBack = getTaiSanDuongSuRandomSampleGenerator();

        duongSu.addTaiSanDuongSu(taiSanDuongSuBack);
        assertThat(duongSu.getTaiSanDuongSus()).containsOnly(taiSanDuongSuBack);
        assertThat(taiSanDuongSuBack.getDuongSu()).isEqualTo(duongSu);

        duongSu.removeTaiSanDuongSu(taiSanDuongSuBack);
        assertThat(duongSu.getTaiSanDuongSus()).doesNotContain(taiSanDuongSuBack);
        assertThat(taiSanDuongSuBack.getDuongSu()).isNull();

        duongSu.taiSanDuongSus(new HashSet<>(Set.of(taiSanDuongSuBack)));
        assertThat(duongSu.getTaiSanDuongSus()).containsOnly(taiSanDuongSuBack);
        assertThat(taiSanDuongSuBack.getDuongSu()).isEqualTo(duongSu);

        duongSu.setTaiSanDuongSus(new HashSet<>());
        assertThat(duongSu.getTaiSanDuongSus()).doesNotContain(taiSanDuongSuBack);
        assertThat(taiSanDuongSuBack.getDuongSu()).isNull();
    }

    @Test
    void quanHeDuongSuTest() {
        DuongSu duongSu = getDuongSuRandomSampleGenerator();
        QuanHeDuongSu quanHeDuongSuBack = getQuanHeDuongSuRandomSampleGenerator();

        duongSu.addQuanHeDuongSu(quanHeDuongSuBack);
        assertThat(duongSu.getQuanHeDuongSus()).containsOnly(quanHeDuongSuBack);
        assertThat(quanHeDuongSuBack.getDuongSu()).isEqualTo(duongSu);

        duongSu.removeQuanHeDuongSu(quanHeDuongSuBack);
        assertThat(duongSu.getQuanHeDuongSus()).doesNotContain(quanHeDuongSuBack);
        assertThat(quanHeDuongSuBack.getDuongSu()).isNull();

        duongSu.quanHeDuongSus(new HashSet<>(Set.of(quanHeDuongSuBack)));
        assertThat(duongSu.getQuanHeDuongSus()).containsOnly(quanHeDuongSuBack);
        assertThat(quanHeDuongSuBack.getDuongSu()).isEqualTo(duongSu);

        duongSu.setQuanHeDuongSus(new HashSet<>());
        assertThat(duongSu.getQuanHeDuongSus()).doesNotContain(quanHeDuongSuBack);
        assertThat(quanHeDuongSuBack.getDuongSu()).isNull();
    }

    @Test
    void danhSachDuongSuTest() {
        DuongSu duongSu = getDuongSuRandomSampleGenerator();
        DanhSachDuongSu danhSachDuongSuBack = getDanhSachDuongSuRandomSampleGenerator();

        duongSu.addDanhSachDuongSu(danhSachDuongSuBack);
        assertThat(duongSu.getDanhSachDuongSus()).containsOnly(danhSachDuongSuBack);
        assertThat(danhSachDuongSuBack.getDuongSu()).isEqualTo(duongSu);

        duongSu.removeDanhSachDuongSu(danhSachDuongSuBack);
        assertThat(duongSu.getDanhSachDuongSus()).doesNotContain(danhSachDuongSuBack);
        assertThat(danhSachDuongSuBack.getDuongSu()).isNull();

        duongSu.danhSachDuongSus(new HashSet<>(Set.of(danhSachDuongSuBack)));
        assertThat(duongSu.getDanhSachDuongSus()).containsOnly(danhSachDuongSuBack);
        assertThat(danhSachDuongSuBack.getDuongSu()).isEqualTo(duongSu);

        duongSu.setDanhSachDuongSus(new HashSet<>());
        assertThat(duongSu.getDanhSachDuongSus()).doesNotContain(danhSachDuongSuBack);
        assertThat(danhSachDuongSuBack.getDuongSu()).isNull();
    }

    @Test
    void duongSuTrungCmndTest() {
        DuongSu duongSu = getDuongSuRandomSampleGenerator();
        DuongSuTrungCmnd duongSuTrungCmndBack = getDuongSuTrungCmndRandomSampleGenerator();

        duongSu.addDuongSuTrungCmnd(duongSuTrungCmndBack);
        assertThat(duongSu.getDuongSuTrungCmnds()).containsOnly(duongSuTrungCmndBack);
        assertThat(duongSuTrungCmndBack.getDuongSu()).isEqualTo(duongSu);

        duongSu.removeDuongSuTrungCmnd(duongSuTrungCmndBack);
        assertThat(duongSu.getDuongSuTrungCmnds()).doesNotContain(duongSuTrungCmndBack);
        assertThat(duongSuTrungCmndBack.getDuongSu()).isNull();

        duongSu.duongSuTrungCmnds(new HashSet<>(Set.of(duongSuTrungCmndBack)));
        assertThat(duongSu.getDuongSuTrungCmnds()).containsOnly(duongSuTrungCmndBack);
        assertThat(duongSuTrungCmndBack.getDuongSu()).isEqualTo(duongSu);

        duongSu.setDuongSuTrungCmnds(new HashSet<>());
        assertThat(duongSu.getDuongSuTrungCmnds()).doesNotContain(duongSuTrungCmndBack);
        assertThat(duongSuTrungCmndBack.getDuongSu()).isNull();
    }

    @Test
    void duongSuTrungCmndBakTest() {
        DuongSu duongSu = getDuongSuRandomSampleGenerator();
        DuongSuTrungCmndBak duongSuTrungCmndBakBack = getDuongSuTrungCmndBakRandomSampleGenerator();

        duongSu.addDuongSuTrungCmndBak(duongSuTrungCmndBakBack);
        assertThat(duongSu.getDuongSuTrungCmndBaks()).containsOnly(duongSuTrungCmndBakBack);
        assertThat(duongSuTrungCmndBakBack.getDuongSu()).isEqualTo(duongSu);

        duongSu.removeDuongSuTrungCmndBak(duongSuTrungCmndBakBack);
        assertThat(duongSu.getDuongSuTrungCmndBaks()).doesNotContain(duongSuTrungCmndBakBack);
        assertThat(duongSuTrungCmndBakBack.getDuongSu()).isNull();

        duongSu.duongSuTrungCmndBaks(new HashSet<>(Set.of(duongSuTrungCmndBakBack)));
        assertThat(duongSu.getDuongSuTrungCmndBaks()).containsOnly(duongSuTrungCmndBakBack);
        assertThat(duongSuTrungCmndBakBack.getDuongSu()).isEqualTo(duongSu);

        duongSu.setDuongSuTrungCmndBaks(new HashSet<>());
        assertThat(duongSu.getDuongSuTrungCmndBaks()).doesNotContain(duongSuTrungCmndBakBack);
        assertThat(duongSuTrungCmndBakBack.getDuongSu()).isNull();
    }

    @Test
    void loaiDuongSuTest() {
        DuongSu duongSu = getDuongSuRandomSampleGenerator();
        LoaiDuongSu loaiDuongSuBack = getLoaiDuongSuRandomSampleGenerator();

        duongSu.setLoaiDuongSu(loaiDuongSuBack);
        assertThat(duongSu.getLoaiDuongSu()).isEqualTo(loaiDuongSuBack);

        duongSu.loaiDuongSu(null);
        assertThat(duongSu.getLoaiDuongSu()).isNull();
    }

    @Test
    void loaiGiayToTest() {
        DuongSu duongSu = getDuongSuRandomSampleGenerator();
        LoaiGiayTo loaiGiayToBack = getLoaiGiayToRandomSampleGenerator();

        duongSu.setLoaiGiayTo(loaiGiayToBack);
        assertThat(duongSu.getLoaiGiayTo()).isEqualTo(loaiGiayToBack);

        duongSu.loaiGiayTo(null);
        assertThat(duongSu.getLoaiGiayTo()).isNull();
    }
}
