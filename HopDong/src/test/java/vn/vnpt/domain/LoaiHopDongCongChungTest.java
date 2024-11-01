package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.LoaiHopDongCongChungTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class LoaiHopDongCongChungTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoaiHopDongCongChung.class);
        LoaiHopDongCongChung loaiHopDongCongChung1 = getLoaiHopDongCongChungSample1();
        LoaiHopDongCongChung loaiHopDongCongChung2 = new LoaiHopDongCongChung();
        assertThat(loaiHopDongCongChung1).isNotEqualTo(loaiHopDongCongChung2);

        loaiHopDongCongChung2.setId(loaiHopDongCongChung1.getId());
        assertThat(loaiHopDongCongChung1).isEqualTo(loaiHopDongCongChung2);

        loaiHopDongCongChung2 = getLoaiHopDongCongChungSample2();
        assertThat(loaiHopDongCongChung1).isNotEqualTo(loaiHopDongCongChung2);
    }
}
