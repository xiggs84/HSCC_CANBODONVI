package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.HopDongCongChungTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class HopDongCongChungTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HopDongCongChung.class);
        HopDongCongChung hopDongCongChung1 = getHopDongCongChungSample1();
        HopDongCongChung hopDongCongChung2 = new HopDongCongChung();
        assertThat(hopDongCongChung1).isNotEqualTo(hopDongCongChung2);

        hopDongCongChung2.setId(hopDongCongChung1.getId());
        assertThat(hopDongCongChung1).isEqualTo(hopDongCongChung2);

        hopDongCongChung2 = getHopDongCongChungSample2();
        assertThat(hopDongCongChung1).isNotEqualTo(hopDongCongChung2);
    }
}
