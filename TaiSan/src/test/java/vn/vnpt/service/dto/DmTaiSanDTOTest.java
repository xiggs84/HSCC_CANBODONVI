package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DmTaiSanDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmTaiSanDTO.class);
        DmTaiSanDTO dmTaiSanDTO1 = new DmTaiSanDTO();
        dmTaiSanDTO1.setIdTaiSan(1L);
        DmTaiSanDTO dmTaiSanDTO2 = new DmTaiSanDTO();
        assertThat(dmTaiSanDTO1).isNotEqualTo(dmTaiSanDTO2);
        dmTaiSanDTO2.setIdTaiSan(dmTaiSanDTO1.getIdTaiSan());
        assertThat(dmTaiSanDTO1).isEqualTo(dmTaiSanDTO2);
        dmTaiSanDTO2.setIdTaiSan(2L);
        assertThat(dmTaiSanDTO1).isNotEqualTo(dmTaiSanDTO2);
        dmTaiSanDTO1.setIdTaiSan(null);
        assertThat(dmTaiSanDTO1).isNotEqualTo(dmTaiSanDTO2);
    }
}
