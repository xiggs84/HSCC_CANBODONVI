package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DuongSuTestSamples.*;
import static vn.vnpt.domain.DuongSuTrungCmndBakTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DuongSuTrungCmndBakTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DuongSuTrungCmndBak.class);
        DuongSuTrungCmndBak duongSuTrungCmndBak1 = getDuongSuTrungCmndBakSample1();
        DuongSuTrungCmndBak duongSuTrungCmndBak2 = new DuongSuTrungCmndBak();
        assertThat(duongSuTrungCmndBak1).isNotEqualTo(duongSuTrungCmndBak2);

        duongSuTrungCmndBak2.setId(duongSuTrungCmndBak1.getId());
        assertThat(duongSuTrungCmndBak1).isEqualTo(duongSuTrungCmndBak2);

        duongSuTrungCmndBak2 = getDuongSuTrungCmndBakSample2();
        assertThat(duongSuTrungCmndBak1).isNotEqualTo(duongSuTrungCmndBak2);
    }

    @Test
    void duongSuTest() {
        DuongSuTrungCmndBak duongSuTrungCmndBak = getDuongSuTrungCmndBakRandomSampleGenerator();
        DuongSu duongSuBack = getDuongSuRandomSampleGenerator();

        duongSuTrungCmndBak.setDuongSu(duongSuBack);
        assertThat(duongSuTrungCmndBak.getDuongSu()).isEqualTo(duongSuBack);

        duongSuTrungCmndBak.duongSu(null);
        assertThat(duongSuTrungCmndBak.getDuongSu()).isNull();
    }
}
