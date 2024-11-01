package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class SoCongChungTempDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoCongChungTempDTO.class);
        SoCongChungTempDTO soCongChungTempDTO1 = new SoCongChungTempDTO();
        soCongChungTempDTO1.setId("id1");
        SoCongChungTempDTO soCongChungTempDTO2 = new SoCongChungTempDTO();
        assertThat(soCongChungTempDTO1).isNotEqualTo(soCongChungTempDTO2);
        soCongChungTempDTO2.setId(soCongChungTempDTO1.getId());
        assertThat(soCongChungTempDTO1).isEqualTo(soCongChungTempDTO2);
        soCongChungTempDTO2.setId("id2");
        assertThat(soCongChungTempDTO1).isNotEqualTo(soCongChungTempDTO2);
        soCongChungTempDTO1.setId(null);
        assertThat(soCongChungTempDTO1).isNotEqualTo(soCongChungTempDTO2);
    }
}
