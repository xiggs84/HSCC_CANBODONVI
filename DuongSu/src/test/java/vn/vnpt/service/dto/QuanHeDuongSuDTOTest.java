package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class QuanHeDuongSuDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuanHeDuongSuDTO.class);
        QuanHeDuongSuDTO quanHeDuongSuDTO1 = new QuanHeDuongSuDTO();
        quanHeDuongSuDTO1.setIdQuanHe(1L);
        QuanHeDuongSuDTO quanHeDuongSuDTO2 = new QuanHeDuongSuDTO();
        assertThat(quanHeDuongSuDTO1).isNotEqualTo(quanHeDuongSuDTO2);
        quanHeDuongSuDTO2.setIdQuanHe(quanHeDuongSuDTO1.getIdQuanHe());
        assertThat(quanHeDuongSuDTO1).isEqualTo(quanHeDuongSuDTO2);
        quanHeDuongSuDTO2.setIdQuanHe(2L);
        assertThat(quanHeDuongSuDTO1).isNotEqualTo(quanHeDuongSuDTO2);
        quanHeDuongSuDTO1.setIdQuanHe(null);
        assertThat(quanHeDuongSuDTO1).isNotEqualTo(quanHeDuongSuDTO2);
    }
}
