package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DmHuyenTmpTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DmHuyenTmpTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmHuyenTmp.class);
        DmHuyenTmp dmHuyenTmp1 = getDmHuyenTmpSample1();
        DmHuyenTmp dmHuyenTmp2 = new DmHuyenTmp();
        assertThat(dmHuyenTmp1).isNotEqualTo(dmHuyenTmp2);

        dmHuyenTmp2.setMaHuyen(dmHuyenTmp1.getMaHuyen());
        assertThat(dmHuyenTmp1).isEqualTo(dmHuyenTmp2);

        dmHuyenTmp2 = getDmHuyenTmpSample2();
        assertThat(dmHuyenTmp1).isNotEqualTo(dmHuyenTmp2);
    }
}
