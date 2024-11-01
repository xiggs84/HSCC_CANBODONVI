package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.TaiSanTestSamples.*;
import static vn.vnpt.domain.ThuaTachTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class ThuaTachTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThuaTach.class);
        ThuaTach thuaTach1 = getThuaTachSample1();
        ThuaTach thuaTach2 = new ThuaTach();
        assertThat(thuaTach1).isNotEqualTo(thuaTach2);

        thuaTach2.setIdThuaTach(thuaTach1.getIdThuaTach());
        assertThat(thuaTach1).isEqualTo(thuaTach2);

        thuaTach2 = getThuaTachSample2();
        assertThat(thuaTach1).isNotEqualTo(thuaTach2);
    }

    @Test
    void taiSanTest() {
        ThuaTach thuaTach = getThuaTachRandomSampleGenerator();
        TaiSan taiSanBack = getTaiSanRandomSampleGenerator();

        thuaTach.setTaiSan(taiSanBack);
        assertThat(thuaTach.getTaiSan()).isEqualTo(taiSanBack);

        thuaTach.taiSan(null);
        assertThat(thuaTach.getTaiSan()).isNull();
    }
}
