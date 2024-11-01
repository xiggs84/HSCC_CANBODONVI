package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.ChungThucTestSamples.*;
import static vn.vnpt.domain.DanhMucLoaiGiayToChungThucTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class ChungThucTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChungThuc.class);
        ChungThuc chungThuc1 = getChungThucSample1();
        ChungThuc chungThuc2 = new ChungThuc();
        assertThat(chungThuc1).isNotEqualTo(chungThuc2);

        chungThuc2.setId(chungThuc1.getId());
        assertThat(chungThuc1).isEqualTo(chungThuc2);

        chungThuc2 = getChungThucSample2();
        assertThat(chungThuc1).isNotEqualTo(chungThuc2);
    }

    @Test
    void danhMucLoaiGiayToChungThucTest() {
        ChungThuc chungThuc = getChungThucRandomSampleGenerator();
        DanhMucLoaiGiayToChungThuc danhMucLoaiGiayToChungThucBack = getDanhMucLoaiGiayToChungThucRandomSampleGenerator();

        chungThuc.setDanhMucLoaiGiayToChungThuc(danhMucLoaiGiayToChungThucBack);
        assertThat(chungThuc.getDanhMucLoaiGiayToChungThuc()).isEqualTo(danhMucLoaiGiayToChungThucBack);

        chungThuc.danhMucLoaiGiayToChungThuc(null);
        assertThat(chungThuc.getDanhMucLoaiGiayToChungThuc()).isNull();
    }
}
