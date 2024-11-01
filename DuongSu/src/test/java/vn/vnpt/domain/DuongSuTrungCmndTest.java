package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DuongSuTestSamples.*;
import static vn.vnpt.domain.DuongSuTrungCmndTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DuongSuTrungCmndTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DuongSuTrungCmnd.class);
        DuongSuTrungCmnd duongSuTrungCmnd1 = getDuongSuTrungCmndSample1();
        DuongSuTrungCmnd duongSuTrungCmnd2 = new DuongSuTrungCmnd();
        assertThat(duongSuTrungCmnd1).isNotEqualTo(duongSuTrungCmnd2);

        duongSuTrungCmnd2.setId(duongSuTrungCmnd1.getId());
        assertThat(duongSuTrungCmnd1).isEqualTo(duongSuTrungCmnd2);

        duongSuTrungCmnd2 = getDuongSuTrungCmndSample2();
        assertThat(duongSuTrungCmnd1).isNotEqualTo(duongSuTrungCmnd2);
    }

    @Test
    void duongSuTest() {
        DuongSuTrungCmnd duongSuTrungCmnd = getDuongSuTrungCmndRandomSampleGenerator();
        DuongSu duongSuBack = getDuongSuRandomSampleGenerator();

        duongSuTrungCmnd.setDuongSu(duongSuBack);
        assertThat(duongSuTrungCmnd.getDuongSu()).isEqualTo(duongSuBack);

        duongSuTrungCmnd.duongSu(null);
        assertThat(duongSuTrungCmnd.getDuongSu()).isNull();
    }
}
