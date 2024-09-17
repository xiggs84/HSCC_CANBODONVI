package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhMucDonViTestSamples.*;
import static vn.vnpt.domain.LoaiDonViTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class LoaiDonViTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoaiDonVi.class);
        LoaiDonVi loaiDonVi1 = getLoaiDonViSample1();
        LoaiDonVi loaiDonVi2 = new LoaiDonVi();
        assertThat(loaiDonVi1).isNotEqualTo(loaiDonVi2);

        loaiDonVi2.setIdLoaiDv(loaiDonVi1.getIdLoaiDv());
        assertThat(loaiDonVi1).isEqualTo(loaiDonVi2);

        loaiDonVi2 = getLoaiDonViSample2();
        assertThat(loaiDonVi1).isNotEqualTo(loaiDonVi2);
    }

    @Test
    void idLoaiDvTest() {
        LoaiDonVi loaiDonVi = getLoaiDonViRandomSampleGenerator();
        DanhMucDonVi danhMucDonViBack = getDanhMucDonViRandomSampleGenerator();

        loaiDonVi.addIdLoaiDv(danhMucDonViBack);
        assertThat(loaiDonVi.getIdLoaiDvs()).containsOnly(danhMucDonViBack);
        assertThat(danhMucDonViBack.getLoaiDonVi()).isEqualTo(loaiDonVi);

        loaiDonVi.removeIdLoaiDv(danhMucDonViBack);
        assertThat(loaiDonVi.getIdLoaiDvs()).doesNotContain(danhMucDonViBack);
        assertThat(danhMucDonViBack.getLoaiDonVi()).isNull();

        loaiDonVi.idLoaiDvs(new HashSet<>(Set.of(danhMucDonViBack)));
        assertThat(loaiDonVi.getIdLoaiDvs()).containsOnly(danhMucDonViBack);
        assertThat(danhMucDonViBack.getLoaiDonVi()).isEqualTo(loaiDonVi);

        loaiDonVi.setIdLoaiDvs(new HashSet<>());
        assertThat(loaiDonVi.getIdLoaiDvs()).doesNotContain(danhMucDonViBack);
        assertThat(danhMucDonViBack.getLoaiDonVi()).isNull();
    }
}
