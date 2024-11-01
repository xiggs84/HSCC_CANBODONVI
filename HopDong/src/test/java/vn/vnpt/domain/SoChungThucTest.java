package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.SoChungThucTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class SoChungThucTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoChungThuc.class);
        SoChungThuc soChungThuc1 = getSoChungThucSample1();
        SoChungThuc soChungThuc2 = new SoChungThuc();
        assertThat(soChungThuc1).isNotEqualTo(soChungThuc2);

        soChungThuc2.setId(soChungThuc1.getId());
        assertThat(soChungThuc1).isEqualTo(soChungThuc2);

        soChungThuc2 = getSoChungThucSample2();
        assertThat(soChungThuc1).isNotEqualTo(soChungThuc2);
    }
}
