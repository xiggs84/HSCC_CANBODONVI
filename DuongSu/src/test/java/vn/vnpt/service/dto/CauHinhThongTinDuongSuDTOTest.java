package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class CauHinhThongTinDuongSuDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CauHinhThongTinDuongSuDTO.class);
        CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO1 = new CauHinhThongTinDuongSuDTO();
        cauHinhThongTinDuongSuDTO1.setIdCauHinh(1L);
        CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO2 = new CauHinhThongTinDuongSuDTO();
        assertThat(cauHinhThongTinDuongSuDTO1).isNotEqualTo(cauHinhThongTinDuongSuDTO2);
        cauHinhThongTinDuongSuDTO2.setIdCauHinh(cauHinhThongTinDuongSuDTO1.getIdCauHinh());
        assertThat(cauHinhThongTinDuongSuDTO1).isEqualTo(cauHinhThongTinDuongSuDTO2);
        cauHinhThongTinDuongSuDTO2.setIdCauHinh(2L);
        assertThat(cauHinhThongTinDuongSuDTO1).isNotEqualTo(cauHinhThongTinDuongSuDTO2);
        cauHinhThongTinDuongSuDTO1.setIdCauHinh(null);
        assertThat(cauHinhThongTinDuongSuDTO1).isNotEqualTo(cauHinhThongTinDuongSuDTO2);
    }
}
