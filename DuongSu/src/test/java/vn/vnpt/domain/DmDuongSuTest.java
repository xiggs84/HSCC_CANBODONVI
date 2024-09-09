package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DmDuongSuTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DmDuongSuTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmDuongSu.class);
        DmDuongSu dmDuongSu1 = getDmDuongSuSample1();
        DmDuongSu dmDuongSu2 = new DmDuongSu();
        assertThat(dmDuongSu1).isNotEqualTo(dmDuongSu2);

        dmDuongSu2.setIdDuongSu(dmDuongSu1.getIdDuongSu());
        assertThat(dmDuongSu1).isEqualTo(dmDuongSu2);

        dmDuongSu2 = getDmDuongSuSample2();
        assertThat(dmDuongSu1).isNotEqualTo(dmDuongSu2);
    }
}
