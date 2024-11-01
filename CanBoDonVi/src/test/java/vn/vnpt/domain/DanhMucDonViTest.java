package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.CanBoChungThucTestSamples.*;
import static vn.vnpt.domain.CanBoQuyenTestSamples.*;
import static vn.vnpt.domain.CapQuanLyTestSamples.*;
import static vn.vnpt.domain.DanhMucDonViTestSamples.*;
import static vn.vnpt.domain.LanhDaoTestSamples.*;
import static vn.vnpt.domain.LoaiDonViTestSamples.*;
import static vn.vnpt.domain.NhiemVuTestSamples.*;

import java.util.HashSet;
import java.util.Set;
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
    void canBoChungThucTest() {
        DanhMucDonVi danhMucDonVi = getDanhMucDonViRandomSampleGenerator();
        CanBoChungThuc canBoChungThucBack = getCanBoChungThucRandomSampleGenerator();

        danhMucDonVi.addCanBoChungThuc(canBoChungThucBack);
        assertThat(danhMucDonVi.getCanBoChungThucs()).containsOnly(canBoChungThucBack);
        assertThat(canBoChungThucBack.getDanhMucDonVi()).isEqualTo(danhMucDonVi);

        danhMucDonVi.removeCanBoChungThuc(canBoChungThucBack);
        assertThat(danhMucDonVi.getCanBoChungThucs()).doesNotContain(canBoChungThucBack);
        assertThat(canBoChungThucBack.getDanhMucDonVi()).isNull();

        danhMucDonVi.canBoChungThucs(new HashSet<>(Set.of(canBoChungThucBack)));
        assertThat(danhMucDonVi.getCanBoChungThucs()).containsOnly(canBoChungThucBack);
        assertThat(canBoChungThucBack.getDanhMucDonVi()).isEqualTo(danhMucDonVi);

        danhMucDonVi.setCanBoChungThucs(new HashSet<>());
        assertThat(danhMucDonVi.getCanBoChungThucs()).doesNotContain(canBoChungThucBack);
        assertThat(canBoChungThucBack.getDanhMucDonVi()).isNull();
    }

    @Test
    void canBoQuyenTest() {
        DanhMucDonVi danhMucDonVi = getDanhMucDonViRandomSampleGenerator();
        CanBoQuyen canBoQuyenBack = getCanBoQuyenRandomSampleGenerator();

        danhMucDonVi.addCanBoQuyen(canBoQuyenBack);
        assertThat(danhMucDonVi.getCanBoQuyens()).containsOnly(canBoQuyenBack);
        assertThat(canBoQuyenBack.getDanhMucDonVi()).isEqualTo(danhMucDonVi);

        danhMucDonVi.removeCanBoQuyen(canBoQuyenBack);
        assertThat(danhMucDonVi.getCanBoQuyens()).doesNotContain(canBoQuyenBack);
        assertThat(canBoQuyenBack.getDanhMucDonVi()).isNull();

        danhMucDonVi.canBoQuyens(new HashSet<>(Set.of(canBoQuyenBack)));
        assertThat(danhMucDonVi.getCanBoQuyens()).containsOnly(canBoQuyenBack);
        assertThat(canBoQuyenBack.getDanhMucDonVi()).isEqualTo(danhMucDonVi);

        danhMucDonVi.setCanBoQuyens(new HashSet<>());
        assertThat(danhMucDonVi.getCanBoQuyens()).doesNotContain(canBoQuyenBack);
        assertThat(canBoQuyenBack.getDanhMucDonVi()).isNull();
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

    @Test
    void lanhDaoTest() {
        DanhMucDonVi danhMucDonVi = getDanhMucDonViRandomSampleGenerator();
        LanhDao lanhDaoBack = getLanhDaoRandomSampleGenerator();

        danhMucDonVi.addLanhDao(lanhDaoBack);
        assertThat(danhMucDonVi.getLanhDaos()).containsOnly(lanhDaoBack);
        assertThat(lanhDaoBack.getDanhMucDonVi()).isEqualTo(danhMucDonVi);

        danhMucDonVi.removeLanhDao(lanhDaoBack);
        assertThat(danhMucDonVi.getLanhDaos()).doesNotContain(lanhDaoBack);
        assertThat(lanhDaoBack.getDanhMucDonVi()).isNull();

        danhMucDonVi.lanhDaos(new HashSet<>(Set.of(lanhDaoBack)));
        assertThat(danhMucDonVi.getLanhDaos()).containsOnly(lanhDaoBack);
        assertThat(lanhDaoBack.getDanhMucDonVi()).isEqualTo(danhMucDonVi);

        danhMucDonVi.setLanhDaos(new HashSet<>());
        assertThat(danhMucDonVi.getLanhDaos()).doesNotContain(lanhDaoBack);
        assertThat(lanhDaoBack.getDanhMucDonVi()).isNull();
    }
}
