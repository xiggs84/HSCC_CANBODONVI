package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.LoaiChungThucTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class LoaiChungThucTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoaiChungThuc.class);
        LoaiChungThuc loaiChungThuc1 = getLoaiChungThucSample1();
        LoaiChungThuc loaiChungThuc2 = new LoaiChungThuc();
        assertThat(loaiChungThuc1).isNotEqualTo(loaiChungThuc2);

        loaiChungThuc2.setId(loaiChungThuc1.getId());
        assertThat(loaiChungThuc1).isEqualTo(loaiChungThuc2);

        loaiChungThuc2 = getLoaiChungThucSample2();
        assertThat(loaiChungThuc1).isNotEqualTo(loaiChungThuc2);
    }
}
