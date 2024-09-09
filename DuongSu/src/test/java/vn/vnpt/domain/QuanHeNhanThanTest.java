package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.QuanHeNhanThanTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class QuanHeNhanThanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuanHeNhanThan.class);
        QuanHeNhanThan quanHeNhanThan1 = getQuanHeNhanThanSample1();
        QuanHeNhanThan quanHeNhanThan2 = new QuanHeNhanThan();
        assertThat(quanHeNhanThan1).isNotEqualTo(quanHeNhanThan2);

        quanHeNhanThan2.setIdQuanHe(quanHeNhanThan1.getIdQuanHe());
        assertThat(quanHeNhanThan1).isEqualTo(quanHeNhanThan2);

        quanHeNhanThan2 = getQuanHeNhanThanSample2();
        assertThat(quanHeNhanThan1).isNotEqualTo(quanHeNhanThan2);
    }
}
