package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.QuyenTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class QuyenTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Quyen.class);
        Quyen quyen1 = getQuyenSample1();
        Quyen quyen2 = new Quyen();
        assertThat(quyen1).isNotEqualTo(quyen2);

        quyen2.setIdQuyen(quyen1.getIdQuyen());
        assertThat(quyen1).isEqualTo(quyen2);

        quyen2 = getQuyenSample2();
        assertThat(quyen1).isNotEqualTo(quyen2);
    }
}
