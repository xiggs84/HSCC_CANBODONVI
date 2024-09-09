package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.QuanHeMasterTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class QuanHeMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuanHeMaster.class);
        QuanHeMaster quanHeMaster1 = getQuanHeMasterSample1();
        QuanHeMaster quanHeMaster2 = new QuanHeMaster();
        assertThat(quanHeMaster1).isNotEqualTo(quanHeMaster2);

        quanHeMaster2.setId(quanHeMaster1.getId());
        assertThat(quanHeMaster1).isEqualTo(quanHeMaster2);

        quanHeMaster2 = getQuanHeMasterSample2();
        assertThat(quanHeMaster1).isNotEqualTo(quanHeMaster2);
    }
}
