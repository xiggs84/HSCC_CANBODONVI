package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.SoCongChungTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class SoCongChungTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoCongChung.class);
        SoCongChung soCongChung1 = getSoCongChungSample1();
        SoCongChung soCongChung2 = new SoCongChung();
        assertThat(soCongChung1).isNotEqualTo(soCongChung2);

        soCongChung2.setId(soCongChung1.getId());
        assertThat(soCongChung1).isEqualTo(soCongChung2);

        soCongChung2 = getSoCongChungSample2();
        assertThat(soCongChung1).isNotEqualTo(soCongChung2);
    }
}
