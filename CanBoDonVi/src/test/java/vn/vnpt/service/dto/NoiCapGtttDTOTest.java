package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class NoiCapGtttDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoiCapGtttDTO.class);
        NoiCapGtttDTO noiCapGtttDTO1 = new NoiCapGtttDTO();
        noiCapGtttDTO1.setIdNoiCap(1L);
        NoiCapGtttDTO noiCapGtttDTO2 = new NoiCapGtttDTO();
        assertThat(noiCapGtttDTO1).isNotEqualTo(noiCapGtttDTO2);
        noiCapGtttDTO2.setIdNoiCap(noiCapGtttDTO1.getIdNoiCap());
        assertThat(noiCapGtttDTO1).isEqualTo(noiCapGtttDTO2);
        noiCapGtttDTO2.setIdNoiCap(2L);
        assertThat(noiCapGtttDTO1).isNotEqualTo(noiCapGtttDTO2);
        noiCapGtttDTO1.setIdNoiCap(null);
        assertThat(noiCapGtttDTO1).isNotEqualTo(noiCapGtttDTO2);
    }
}
