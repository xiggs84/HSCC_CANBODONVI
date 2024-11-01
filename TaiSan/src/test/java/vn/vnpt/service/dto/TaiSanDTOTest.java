package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class TaiSanDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaiSanDTO.class);
        TaiSanDTO taiSanDTO1 = new TaiSanDTO();
        taiSanDTO1.setIdTaiSan(1L);
        TaiSanDTO taiSanDTO2 = new TaiSanDTO();
        assertThat(taiSanDTO1).isNotEqualTo(taiSanDTO2);
        taiSanDTO2.setIdTaiSan(taiSanDTO1.getIdTaiSan());
        assertThat(taiSanDTO1).isEqualTo(taiSanDTO2);
        taiSanDTO2.setIdTaiSan(2L);
        assertThat(taiSanDTO1).isNotEqualTo(taiSanDTO2);
        taiSanDTO1.setIdTaiSan(null);
        assertThat(taiSanDTO1).isNotEqualTo(taiSanDTO2);
    }
}
