package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DmNoiCapGpdkxTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DmNoiCapGpdkxTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmNoiCapGpdkx.class);
        DmNoiCapGpdkx dmNoiCapGpdkx1 = getDmNoiCapGpdkxSample1();
        DmNoiCapGpdkx dmNoiCapGpdkx2 = new DmNoiCapGpdkx();
        assertThat(dmNoiCapGpdkx1).isNotEqualTo(dmNoiCapGpdkx2);

        dmNoiCapGpdkx2.setIdNoiCap(dmNoiCapGpdkx1.getIdNoiCap());
        assertThat(dmNoiCapGpdkx1).isEqualTo(dmNoiCapGpdkx2);

        dmNoiCapGpdkx2 = getDmNoiCapGpdkxSample2();
        assertThat(dmNoiCapGpdkx1).isNotEqualTo(dmNoiCapGpdkx2);
    }
}
