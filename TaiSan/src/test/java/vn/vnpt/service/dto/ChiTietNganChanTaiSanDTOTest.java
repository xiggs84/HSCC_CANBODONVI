package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class ChiTietNganChanTaiSanDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChiTietNganChanTaiSanDTO.class);
        ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO1 = new ChiTietNganChanTaiSanDTO();
        chiTietNganChanTaiSanDTO1.setIdNganChan(1L);
        ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO2 = new ChiTietNganChanTaiSanDTO();
        assertThat(chiTietNganChanTaiSanDTO1).isNotEqualTo(chiTietNganChanTaiSanDTO2);
        chiTietNganChanTaiSanDTO2.setIdNganChan(chiTietNganChanTaiSanDTO1.getIdNganChan());
        assertThat(chiTietNganChanTaiSanDTO1).isEqualTo(chiTietNganChanTaiSanDTO2);
        chiTietNganChanTaiSanDTO2.setIdNganChan(2L);
        assertThat(chiTietNganChanTaiSanDTO1).isNotEqualTo(chiTietNganChanTaiSanDTO2);
        chiTietNganChanTaiSanDTO1.setIdNganChan(null);
        assertThat(chiTietNganChanTaiSanDTO1).isNotEqualTo(chiTietNganChanTaiSanDTO2);
    }
}
