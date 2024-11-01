package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class SoChungThucDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoChungThucDTO.class);
        SoChungThucDTO soChungThucDTO1 = new SoChungThucDTO();
        soChungThucDTO1.setId("id1");
        SoChungThucDTO soChungThucDTO2 = new SoChungThucDTO();
        assertThat(soChungThucDTO1).isNotEqualTo(soChungThucDTO2);
        soChungThucDTO2.setId(soChungThucDTO1.getId());
        assertThat(soChungThucDTO1).isEqualTo(soChungThucDTO2);
        soChungThucDTO2.setId("id2");
        assertThat(soChungThucDTO1).isNotEqualTo(soChungThucDTO2);
        soChungThucDTO1.setId(null);
        assertThat(soChungThucDTO1).isNotEqualTo(soChungThucDTO2);
    }
}
