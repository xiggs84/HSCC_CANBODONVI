package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhSachDuongSuTestSamples.*;
import static vn.vnpt.domain.DuongSuTestSamples.*;
import static vn.vnpt.domain.DuongSuTrungCmndBakTestSamples.*;
import static vn.vnpt.domain.DuongSuTrungCmndTestSamples.*;
import static vn.vnpt.domain.QuanHeDuongSuTestSamples.*;
import static vn.vnpt.domain.TaiSanDuongSuTestSamples.*;

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
    void taiSanDuongSuTest() {
        DuongSu duongSu = getDuongSuRandomSampleGenerator();
        TaiSanDuongSu taiSanDuongSuBack = getTaiSanDuongSuRandomSampleGenerator();

        duongSu.addTaiSanDuongSu(taiSanDuongSuBack);
        assertThat(duongSu.getTaiSanDuongSus()).containsOnly(taiSanDuongSuBack);
        assertThat(taiSanDuongSuBack.getIdDuongSu()).isEqualTo(duongSu);

        duongSu.removeTaiSanDuongSu(taiSanDuongSuBack);
        assertThat(duongSu.getTaiSanDuongSus()).doesNotContain(taiSanDuongSuBack);
        assertThat(taiSanDuongSuBack.getIdDuongSu()).isNull();

        duongSu.taiSanDuongSus(new HashSet<>(Set.of(taiSanDuongSuBack)));
        assertThat(duongSu.getTaiSanDuongSus()).containsOnly(taiSanDuongSuBack);
        assertThat(taiSanDuongSuBack.getIdDuongSu()).isEqualTo(duongSu);

        duongSu.setTaiSanDuongSus(new HashSet<>());
        assertThat(duongSu.getTaiSanDuongSus()).doesNotContain(taiSanDuongSuBack);
        assertThat(taiSanDuongSuBack.getIdDuongSu()).isNull();
    }

    @Test
    void quanHeDuongSuTest() {
        DuongSu duongSu = getDuongSuRandomSampleGenerator();
        QuanHeDuongSu quanHeDuongSuBack = getQuanHeDuongSuRandomSampleGenerator();

        duongSu.addQuanHeDuongSu(quanHeDuongSuBack);
        assertThat(duongSu.getQuanHeDuongSus()).containsOnly(quanHeDuongSuBack);
        assertThat(quanHeDuongSuBack.getIdDuongSu()).isEqualTo(duongSu);

        duongSu.removeQuanHeDuongSu(quanHeDuongSuBack);
        assertThat(duongSu.getQuanHeDuongSus()).doesNotContain(quanHeDuongSuBack);
        assertThat(quanHeDuongSuBack.getIdDuongSu()).isNull();

        duongSu.quanHeDuongSus(new HashSet<>(Set.of(quanHeDuongSuBack)));
        assertThat(duongSu.getQuanHeDuongSus()).containsOnly(quanHeDuongSuBack);
        assertThat(quanHeDuongSuBack.getIdDuongSu()).isEqualTo(duongSu);

        duongSu.setQuanHeDuongSus(new HashSet<>());
        assertThat(duongSu.getQuanHeDuongSus()).doesNotContain(quanHeDuongSuBack);
        assertThat(quanHeDuongSuBack.getIdDuongSu()).isNull();
    }

    @Test
    void danhSachDuongSuTest() {
        DuongSu duongSu = getDuongSuRandomSampleGenerator();
        DanhSachDuongSu danhSachDuongSuBack = getDanhSachDuongSuRandomSampleGenerator();

        duongSu.addDanhSachDuongSu(danhSachDuongSuBack);
        assertThat(duongSu.getDanhSachDuongSus()).containsOnly(danhSachDuongSuBack);
        assertThat(danhSachDuongSuBack.getIdDuongSu()).isEqualTo(duongSu);

        duongSu.removeDanhSachDuongSu(danhSachDuongSuBack);
        assertThat(duongSu.getDanhSachDuongSus()).doesNotContain(danhSachDuongSuBack);
        assertThat(danhSachDuongSuBack.getIdDuongSu()).isNull();

        duongSu.danhSachDuongSus(new HashSet<>(Set.of(danhSachDuongSuBack)));
        assertThat(duongSu.getDanhSachDuongSus()).containsOnly(danhSachDuongSuBack);
        assertThat(danhSachDuongSuBack.getIdDuongSu()).isEqualTo(duongSu);

        duongSu.setDanhSachDuongSus(new HashSet<>());
        assertThat(duongSu.getDanhSachDuongSus()).doesNotContain(danhSachDuongSuBack);
        assertThat(danhSachDuongSuBack.getIdDuongSu()).isNull();
    }

    @Test
    void duongSuTrungCmndTest() {
        DuongSu duongSu = getDuongSuRandomSampleGenerator();
        DuongSuTrungCmnd duongSuTrungCmndBack = getDuongSuTrungCmndRandomSampleGenerator();

        duongSu.addDuongSuTrungCmnd(duongSuTrungCmndBack);
        assertThat(duongSu.getDuongSuTrungCmnds()).containsOnly(duongSuTrungCmndBack);
        assertThat(duongSuTrungCmndBack.getIdDuongSu()).isEqualTo(duongSu);

        duongSu.removeDuongSuTrungCmnd(duongSuTrungCmndBack);
        assertThat(duongSu.getDuongSuTrungCmnds()).doesNotContain(duongSuTrungCmndBack);
        assertThat(duongSuTrungCmndBack.getIdDuongSu()).isNull();

        duongSu.duongSuTrungCmnds(new HashSet<>(Set.of(duongSuTrungCmndBack)));
        assertThat(duongSu.getDuongSuTrungCmnds()).containsOnly(duongSuTrungCmndBack);
        assertThat(duongSuTrungCmndBack.getIdDuongSu()).isEqualTo(duongSu);

        duongSu.setDuongSuTrungCmnds(new HashSet<>());
        assertThat(duongSu.getDuongSuTrungCmnds()).doesNotContain(duongSuTrungCmndBack);
        assertThat(duongSuTrungCmndBack.getIdDuongSu()).isNull();
    }

    @Test
    void duongSuTrungCmndBakTest() {
        DuongSu duongSu = getDuongSuRandomSampleGenerator();
        DuongSuTrungCmndBak duongSuTrungCmndBakBack = getDuongSuTrungCmndBakRandomSampleGenerator();

        duongSu.addDuongSuTrungCmndBak(duongSuTrungCmndBakBack);
        assertThat(duongSu.getDuongSuTrungCmndBaks()).containsOnly(duongSuTrungCmndBakBack);
        assertThat(duongSuTrungCmndBakBack.getIdDuongSu()).isEqualTo(duongSu);

        duongSu.removeDuongSuTrungCmndBak(duongSuTrungCmndBakBack);
        assertThat(duongSu.getDuongSuTrungCmndBaks()).doesNotContain(duongSuTrungCmndBakBack);
        assertThat(duongSuTrungCmndBakBack.getIdDuongSu()).isNull();

        duongSu.duongSuTrungCmndBaks(new HashSet<>(Set.of(duongSuTrungCmndBakBack)));
        assertThat(duongSu.getDuongSuTrungCmndBaks()).containsOnly(duongSuTrungCmndBakBack);
        assertThat(duongSuTrungCmndBakBack.getIdDuongSu()).isEqualTo(duongSu);

        duongSu.setDuongSuTrungCmndBaks(new HashSet<>());
        assertThat(duongSu.getDuongSuTrungCmndBaks()).doesNotContain(duongSuTrungCmndBakBack);
        assertThat(duongSuTrungCmndBakBack.getIdDuongSu()).isNull();
    }
}
