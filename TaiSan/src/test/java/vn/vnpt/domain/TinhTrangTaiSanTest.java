package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.TaiSanDatNhaTestSamples.*;
import static vn.vnpt.domain.TaiSanDgcTestSamples.*;
import static vn.vnpt.domain.TaiSanTestSamples.*;
import static vn.vnpt.domain.TinhTrangTaiSanTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class TinhTrangTaiSanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TinhTrangTaiSan.class);
        TinhTrangTaiSan tinhTrangTaiSan1 = getTinhTrangTaiSanSample1();
        TinhTrangTaiSan tinhTrangTaiSan2 = new TinhTrangTaiSan();
        assertThat(tinhTrangTaiSan1).isNotEqualTo(tinhTrangTaiSan2);

        tinhTrangTaiSan2.setIdTinhTrang(tinhTrangTaiSan1.getIdTinhTrang());
        assertThat(tinhTrangTaiSan1).isEqualTo(tinhTrangTaiSan2);

        tinhTrangTaiSan2 = getTinhTrangTaiSanSample2();
        assertThat(tinhTrangTaiSan1).isNotEqualTo(tinhTrangTaiSan2);
    }

    @Test
    void tinhTrangTest() {
        TinhTrangTaiSan tinhTrangTaiSan = getTinhTrangTaiSanRandomSampleGenerator();
        TaiSan taiSanBack = getTaiSanRandomSampleGenerator();

        tinhTrangTaiSan.addTinhTrang(taiSanBack);
        assertThat(tinhTrangTaiSan.getTinhTrangs()).containsOnly(taiSanBack);
        assertThat(taiSanBack.getTinhTrangTaiSan()).isEqualTo(tinhTrangTaiSan);

        tinhTrangTaiSan.removeTinhTrang(taiSanBack);
        assertThat(tinhTrangTaiSan.getTinhTrangs()).doesNotContain(taiSanBack);
        assertThat(taiSanBack.getTinhTrangTaiSan()).isNull();

        tinhTrangTaiSan.tinhTrangs(new HashSet<>(Set.of(taiSanBack)));
        assertThat(tinhTrangTaiSan.getTinhTrangs()).containsOnly(taiSanBack);
        assertThat(taiSanBack.getTinhTrangTaiSan()).isEqualTo(tinhTrangTaiSan);

        tinhTrangTaiSan.setTinhTrangs(new HashSet<>());
        assertThat(tinhTrangTaiSan.getTinhTrangs()).doesNotContain(taiSanBack);
        assertThat(taiSanBack.getTinhTrangTaiSan()).isNull();
    }

    @Test
    void taiSanDgcTest() {
        TinhTrangTaiSan tinhTrangTaiSan = getTinhTrangTaiSanRandomSampleGenerator();
        TaiSanDgc taiSanDgcBack = getTaiSanDgcRandomSampleGenerator();

        tinhTrangTaiSan.addTaiSanDgc(taiSanDgcBack);
        assertThat(tinhTrangTaiSan.getTaiSanDgcs()).containsOnly(taiSanDgcBack);
        assertThat(taiSanDgcBack.getTinhTrangTaiSan()).isEqualTo(tinhTrangTaiSan);

        tinhTrangTaiSan.removeTaiSanDgc(taiSanDgcBack);
        assertThat(tinhTrangTaiSan.getTaiSanDgcs()).doesNotContain(taiSanDgcBack);
        assertThat(taiSanDgcBack.getTinhTrangTaiSan()).isNull();

        tinhTrangTaiSan.taiSanDgcs(new HashSet<>(Set.of(taiSanDgcBack)));
        assertThat(tinhTrangTaiSan.getTaiSanDgcs()).containsOnly(taiSanDgcBack);
        assertThat(taiSanDgcBack.getTinhTrangTaiSan()).isEqualTo(tinhTrangTaiSan);

        tinhTrangTaiSan.setTaiSanDgcs(new HashSet<>());
        assertThat(tinhTrangTaiSan.getTaiSanDgcs()).doesNotContain(taiSanDgcBack);
        assertThat(taiSanDgcBack.getTinhTrangTaiSan()).isNull();
    }

    @Test
    void taiSanDatNhaTest() {
        TinhTrangTaiSan tinhTrangTaiSan = getTinhTrangTaiSanRandomSampleGenerator();
        TaiSanDatNha taiSanDatNhaBack = getTaiSanDatNhaRandomSampleGenerator();

        tinhTrangTaiSan.addTaiSanDatNha(taiSanDatNhaBack);
        assertThat(tinhTrangTaiSan.getTaiSanDatNhas()).containsOnly(taiSanDatNhaBack);
        assertThat(taiSanDatNhaBack.getTinhTrangTaiSan()).isEqualTo(tinhTrangTaiSan);

        tinhTrangTaiSan.removeTaiSanDatNha(taiSanDatNhaBack);
        assertThat(tinhTrangTaiSan.getTaiSanDatNhas()).doesNotContain(taiSanDatNhaBack);
        assertThat(taiSanDatNhaBack.getTinhTrangTaiSan()).isNull();

        tinhTrangTaiSan.taiSanDatNhas(new HashSet<>(Set.of(taiSanDatNhaBack)));
        assertThat(tinhTrangTaiSan.getTaiSanDatNhas()).containsOnly(taiSanDatNhaBack);
        assertThat(taiSanDatNhaBack.getTinhTrangTaiSan()).isEqualTo(tinhTrangTaiSan);

        tinhTrangTaiSan.setTaiSanDatNhas(new HashSet<>());
        assertThat(tinhTrangTaiSan.getTaiSanDatNhas()).doesNotContain(taiSanDatNhaBack);
        assertThat(taiSanDatNhaBack.getTinhTrangTaiSan()).isNull();
    }
}
