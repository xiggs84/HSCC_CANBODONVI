package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DmTaiSanTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DmTaiSanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmTaiSan.class);
        DmTaiSan dmTaiSan1 = getDmTaiSanSample1();
        DmTaiSan dmTaiSan2 = new DmTaiSan();
        assertThat(dmTaiSan1).isNotEqualTo(dmTaiSan2);

        dmTaiSan2.setIdTaiSan(dmTaiSan1.getIdTaiSan());
        assertThat(dmTaiSan1).isEqualTo(dmTaiSan2);

        dmTaiSan2 = getDmTaiSanSample2();
        assertThat(dmTaiSan1).isNotEqualTo(dmTaiSan2);
    }
}
