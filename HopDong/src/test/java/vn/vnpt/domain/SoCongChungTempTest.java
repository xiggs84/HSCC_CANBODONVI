package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.SoCongChungTempTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class SoCongChungTempTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoCongChungTemp.class);
        SoCongChungTemp soCongChungTemp1 = getSoCongChungTempSample1();
        SoCongChungTemp soCongChungTemp2 = new SoCongChungTemp();
        assertThat(soCongChungTemp1).isNotEqualTo(soCongChungTemp2);

        soCongChungTemp2.setId(soCongChungTemp1.getId());
        assertThat(soCongChungTemp1).isEqualTo(soCongChungTemp2);

        soCongChungTemp2 = getSoCongChungTempSample2();
        assertThat(soCongChungTemp1).isNotEqualTo(soCongChungTemp2);
    }
}
