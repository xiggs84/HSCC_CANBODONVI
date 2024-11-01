package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.ChungThucTestSamples.*;
import static vn.vnpt.domain.DanhMucLoaiGiayToChungThucTestSamples.*;
import static vn.vnpt.domain.DanhSachChungThucTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucLoaiGiayToChungThucTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucLoaiGiayToChungThuc.class);
        DanhMucLoaiGiayToChungThuc danhMucLoaiGiayToChungThuc1 = getDanhMucLoaiGiayToChungThucSample1();
        DanhMucLoaiGiayToChungThuc danhMucLoaiGiayToChungThuc2 = new DanhMucLoaiGiayToChungThuc();
        assertThat(danhMucLoaiGiayToChungThuc1).isNotEqualTo(danhMucLoaiGiayToChungThuc2);

        danhMucLoaiGiayToChungThuc2.setId(danhMucLoaiGiayToChungThuc1.getId());
        assertThat(danhMucLoaiGiayToChungThuc1).isEqualTo(danhMucLoaiGiayToChungThuc2);

        danhMucLoaiGiayToChungThuc2 = getDanhMucLoaiGiayToChungThucSample2();
        assertThat(danhMucLoaiGiayToChungThuc1).isNotEqualTo(danhMucLoaiGiayToChungThuc2);
    }

    @Test
    void chungThucTest() {
        DanhMucLoaiGiayToChungThuc danhMucLoaiGiayToChungThuc = getDanhMucLoaiGiayToChungThucRandomSampleGenerator();
        ChungThuc chungThucBack = getChungThucRandomSampleGenerator();

        danhMucLoaiGiayToChungThuc.addChungThuc(chungThucBack);
        assertThat(danhMucLoaiGiayToChungThuc.getChungThucs()).containsOnly(chungThucBack);
        assertThat(chungThucBack.getDanhMucLoaiGiayToChungThuc()).isEqualTo(danhMucLoaiGiayToChungThuc);

        danhMucLoaiGiayToChungThuc.removeChungThuc(chungThucBack);
        assertThat(danhMucLoaiGiayToChungThuc.getChungThucs()).doesNotContain(chungThucBack);
        assertThat(chungThucBack.getDanhMucLoaiGiayToChungThuc()).isNull();

        danhMucLoaiGiayToChungThuc.chungThucs(new HashSet<>(Set.of(chungThucBack)));
        assertThat(danhMucLoaiGiayToChungThuc.getChungThucs()).containsOnly(chungThucBack);
        assertThat(chungThucBack.getDanhMucLoaiGiayToChungThuc()).isEqualTo(danhMucLoaiGiayToChungThuc);

        danhMucLoaiGiayToChungThuc.setChungThucs(new HashSet<>());
        assertThat(danhMucLoaiGiayToChungThuc.getChungThucs()).doesNotContain(chungThucBack);
        assertThat(chungThucBack.getDanhMucLoaiGiayToChungThuc()).isNull();
    }

    @Test
    void danhSachChungThucTest() {
        DanhMucLoaiGiayToChungThuc danhMucLoaiGiayToChungThuc = getDanhMucLoaiGiayToChungThucRandomSampleGenerator();
        DanhSachChungThuc danhSachChungThucBack = getDanhSachChungThucRandomSampleGenerator();

        danhMucLoaiGiayToChungThuc.addDanhSachChungThuc(danhSachChungThucBack);
        assertThat(danhMucLoaiGiayToChungThuc.getDanhSachChungThucs()).containsOnly(danhSachChungThucBack);
        assertThat(danhSachChungThucBack.getDanhMucLoaiGiayToChungThuc()).isEqualTo(danhMucLoaiGiayToChungThuc);

        danhMucLoaiGiayToChungThuc.removeDanhSachChungThuc(danhSachChungThucBack);
        assertThat(danhMucLoaiGiayToChungThuc.getDanhSachChungThucs()).doesNotContain(danhSachChungThucBack);
        assertThat(danhSachChungThucBack.getDanhMucLoaiGiayToChungThuc()).isNull();

        danhMucLoaiGiayToChungThuc.danhSachChungThucs(new HashSet<>(Set.of(danhSachChungThucBack)));
        assertThat(danhMucLoaiGiayToChungThuc.getDanhSachChungThucs()).containsOnly(danhSachChungThucBack);
        assertThat(danhSachChungThucBack.getDanhMucLoaiGiayToChungThuc()).isEqualTo(danhMucLoaiGiayToChungThuc);

        danhMucLoaiGiayToChungThuc.setDanhSachChungThucs(new HashSet<>());
        assertThat(danhMucLoaiGiayToChungThuc.getDanhSachChungThucs()).doesNotContain(danhSachChungThucBack);
        assertThat(danhSachChungThucBack.getDanhMucLoaiGiayToChungThuc()).isNull();
    }
}
