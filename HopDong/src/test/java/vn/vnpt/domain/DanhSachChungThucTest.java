package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhMucLoaiGiayToChungThucTestSamples.*;
import static vn.vnpt.domain.DanhSachChungThucTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhSachChungThucTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhSachChungThuc.class);
        DanhSachChungThuc danhSachChungThuc1 = getDanhSachChungThucSample1();
        DanhSachChungThuc danhSachChungThuc2 = new DanhSachChungThuc();
        assertThat(danhSachChungThuc1).isNotEqualTo(danhSachChungThuc2);

        danhSachChungThuc2.setId(danhSachChungThuc1.getId());
        assertThat(danhSachChungThuc1).isEqualTo(danhSachChungThuc2);

        danhSachChungThuc2 = getDanhSachChungThucSample2();
        assertThat(danhSachChungThuc1).isNotEqualTo(danhSachChungThuc2);
    }

    @Test
    void danhMucLoaiGiayToChungThucTest() {
        DanhSachChungThuc danhSachChungThuc = getDanhSachChungThucRandomSampleGenerator();
        DanhMucLoaiGiayToChungThuc danhMucLoaiGiayToChungThucBack = getDanhMucLoaiGiayToChungThucRandomSampleGenerator();

        danhSachChungThuc.setDanhMucLoaiGiayToChungThuc(danhMucLoaiGiayToChungThucBack);
        assertThat(danhSachChungThuc.getDanhMucLoaiGiayToChungThuc()).isEqualTo(danhMucLoaiGiayToChungThucBack);

        danhSachChungThuc.danhMucLoaiGiayToChungThuc(null);
        assertThat(danhSachChungThuc.getDanhMucLoaiGiayToChungThuc()).isNull();
    }
}
