package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DanhMucXaTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucXaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucXa.class);
        DanhMucXa danhMucXa1 = getDanhMucXaSample1();
        DanhMucXa danhMucXa2 = new DanhMucXa();
        assertThat(danhMucXa1).isNotEqualTo(danhMucXa2);

        danhMucXa2.setMaXa(danhMucXa1.getMaXa());
        assertThat(danhMucXa1).isEqualTo(danhMucXa2);

        danhMucXa2 = getDanhMucXaSample2();
        assertThat(danhMucXa1).isNotEqualTo(danhMucXa2);
    }
}
