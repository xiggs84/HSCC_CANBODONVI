package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DmTinhTmpTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DmTinhTmpTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmTinhTmp.class);
        DmTinhTmp dmTinhTmp1 = getDmTinhTmpSample1();
        DmTinhTmp dmTinhTmp2 = new DmTinhTmp();
        assertThat(dmTinhTmp1).isNotEqualTo(dmTinhTmp2);

        dmTinhTmp2.setMaTinh(dmTinhTmp1.getMaTinh());
        assertThat(dmTinhTmp1).isEqualTo(dmTinhTmp2);

        dmTinhTmp2 = getDmTinhTmpSample2();
        assertThat(dmTinhTmp1).isNotEqualTo(dmTinhTmp2);
    }
}
