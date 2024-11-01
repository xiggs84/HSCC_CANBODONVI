package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DuongSuTestSamples.*;
import static vn.vnpt.domain.LoaiDuongSuTestSamples.*;
import static vn.vnpt.domain.LoaiGiayToTestSamples.*;
import static vn.vnpt.domain.ThongTinCapNhatDuongSuTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class ThongTinCapNhatDuongSuTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThongTinCapNhatDuongSu.class);
        ThongTinCapNhatDuongSu thongTinCapNhatDuongSu1 = getThongTinCapNhatDuongSuSample1();
        ThongTinCapNhatDuongSu thongTinCapNhatDuongSu2 = new ThongTinCapNhatDuongSu();
        assertThat(thongTinCapNhatDuongSu1).isNotEqualTo(thongTinCapNhatDuongSu2);

        thongTinCapNhatDuongSu2.setIdCapNhat(thongTinCapNhatDuongSu1.getIdCapNhat());
        assertThat(thongTinCapNhatDuongSu1).isEqualTo(thongTinCapNhatDuongSu2);

        thongTinCapNhatDuongSu2 = getThongTinCapNhatDuongSuSample2();
        assertThat(thongTinCapNhatDuongSu1).isNotEqualTo(thongTinCapNhatDuongSu2);
    }

    @Test
    void loaiDuongSuTest() {
        ThongTinCapNhatDuongSu thongTinCapNhatDuongSu = getThongTinCapNhatDuongSuRandomSampleGenerator();
        LoaiDuongSu loaiDuongSuBack = getLoaiDuongSuRandomSampleGenerator();

        thongTinCapNhatDuongSu.setLoaiDuongSu(loaiDuongSuBack);
        assertThat(thongTinCapNhatDuongSu.getLoaiDuongSu()).isEqualTo(loaiDuongSuBack);

        thongTinCapNhatDuongSu.loaiDuongSu(null);
        assertThat(thongTinCapNhatDuongSu.getLoaiDuongSu()).isNull();
    }

    @Test
    void loaiGiayToTest() {
        ThongTinCapNhatDuongSu thongTinCapNhatDuongSu = getThongTinCapNhatDuongSuRandomSampleGenerator();
        LoaiGiayTo loaiGiayToBack = getLoaiGiayToRandomSampleGenerator();

        thongTinCapNhatDuongSu.setLoaiGiayTo(loaiGiayToBack);
        assertThat(thongTinCapNhatDuongSu.getLoaiGiayTo()).isEqualTo(loaiGiayToBack);

        thongTinCapNhatDuongSu.loaiGiayTo(null);
        assertThat(thongTinCapNhatDuongSu.getLoaiGiayTo()).isNull();
    }

    @Test
    void duongSuTest() {
        ThongTinCapNhatDuongSu thongTinCapNhatDuongSu = getThongTinCapNhatDuongSuRandomSampleGenerator();
        DuongSu duongSuBack = getDuongSuRandomSampleGenerator();

        thongTinCapNhatDuongSu.setDuongSu(duongSuBack);
        assertThat(thongTinCapNhatDuongSu.getDuongSu()).isEqualTo(duongSuBack);

        thongTinCapNhatDuongSu.duongSu(null);
        assertThat(thongTinCapNhatDuongSu.getDuongSu()).isNull();
    }
}
