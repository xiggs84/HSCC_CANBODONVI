package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DmXaTmpTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DmXaTmpTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmXaTmp.class);
        DmXaTmp dmXaTmp1 = getDmXaTmpSample1();
        DmXaTmp dmXaTmp2 = new DmXaTmp();
        assertThat(dmXaTmp1).isNotEqualTo(dmXaTmp2);

        dmXaTmp2.setMaXa(dmXaTmp1.getMaXa());
        assertThat(dmXaTmp1).isEqualTo(dmXaTmp2);

        dmXaTmp2 = getDmXaTmpSample2();
        assertThat(dmXaTmp1).isNotEqualTo(dmXaTmp2);
    }
}
