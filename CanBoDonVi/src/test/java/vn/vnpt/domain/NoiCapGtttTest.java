package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.NoiCapGtttTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class NoiCapGtttTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoiCapGttt.class);
        NoiCapGttt noiCapGttt1 = getNoiCapGtttSample1();
        NoiCapGttt noiCapGttt2 = new NoiCapGttt();
        assertThat(noiCapGttt1).isNotEqualTo(noiCapGttt2);

        noiCapGttt2.setIdNoiCap(noiCapGttt1.getIdNoiCap());
        assertThat(noiCapGttt1).isEqualTo(noiCapGttt2);

        noiCapGttt2 = getNoiCapGtttSample2();
        assertThat(noiCapGttt1).isNotEqualTo(noiCapGttt2);
    }
}
