package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.CapQuanLyTestSamples.*;
import static vn.vnpt.domain.DanhMucDonViTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class CapQuanLyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CapQuanLy.class);
        CapQuanLy capQuanLy1 = getCapQuanLySample1();
        CapQuanLy capQuanLy2 = new CapQuanLy();
        assertThat(capQuanLy1).isNotEqualTo(capQuanLy2);

        capQuanLy2.setIdCapQl(capQuanLy1.getIdCapQl());
        assertThat(capQuanLy1).isEqualTo(capQuanLy2);

        capQuanLy2 = getCapQuanLySample2();
        assertThat(capQuanLy1).isNotEqualTo(capQuanLy2);
    }

    @Test
    void idCapQlTest() {
        CapQuanLy capQuanLy = getCapQuanLyRandomSampleGenerator();
        DanhMucDonVi danhMucDonViBack = getDanhMucDonViRandomSampleGenerator();

        capQuanLy.addIdCapQl(danhMucDonViBack);
        assertThat(capQuanLy.getIdCapQls()).containsOnly(danhMucDonViBack);
        assertThat(danhMucDonViBack.getCapQuanLy()).isEqualTo(capQuanLy);

        capQuanLy.removeIdCapQl(danhMucDonViBack);
        assertThat(capQuanLy.getIdCapQls()).doesNotContain(danhMucDonViBack);
        assertThat(danhMucDonViBack.getCapQuanLy()).isNull();

        capQuanLy.idCapQls(new HashSet<>(Set.of(danhMucDonViBack)));
        assertThat(capQuanLy.getIdCapQls()).containsOnly(danhMucDonViBack);
        assertThat(danhMucDonViBack.getCapQuanLy()).isEqualTo(capQuanLy);

        capQuanLy.setIdCapQls(new HashSet<>());
        assertThat(capQuanLy.getIdCapQls()).doesNotContain(danhMucDonViBack);
        assertThat(danhMucDonViBack.getCapQuanLy()).isNull();
    }
}
