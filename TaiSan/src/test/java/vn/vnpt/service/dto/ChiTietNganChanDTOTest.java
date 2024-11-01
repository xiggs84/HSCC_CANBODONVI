package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class ChiTietNganChanDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChiTietNganChanDTO.class);
        ChiTietNganChanDTO chiTietNganChanDTO1 = new ChiTietNganChanDTO();
        chiTietNganChanDTO1.setId(1L);
        ChiTietNganChanDTO chiTietNganChanDTO2 = new ChiTietNganChanDTO();
        assertThat(chiTietNganChanDTO1).isNotEqualTo(chiTietNganChanDTO2);
        chiTietNganChanDTO2.setId(chiTietNganChanDTO1.getId());
        assertThat(chiTietNganChanDTO1).isEqualTo(chiTietNganChanDTO2);
        chiTietNganChanDTO2.setId(2L);
        assertThat(chiTietNganChanDTO1).isNotEqualTo(chiTietNganChanDTO2);
        chiTietNganChanDTO1.setId(null);
        assertThat(chiTietNganChanDTO1).isNotEqualTo(chiTietNganChanDTO2);
    }
}
