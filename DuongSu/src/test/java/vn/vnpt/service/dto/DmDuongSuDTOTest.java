package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DmDuongSuDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmDuongSuDTO.class);
        DmDuongSuDTO dmDuongSuDTO1 = new DmDuongSuDTO();
        dmDuongSuDTO1.setIdDuongSu(1L);
        DmDuongSuDTO dmDuongSuDTO2 = new DmDuongSuDTO();
        assertThat(dmDuongSuDTO1).isNotEqualTo(dmDuongSuDTO2);
        dmDuongSuDTO2.setIdDuongSu(dmDuongSuDTO1.getIdDuongSu());
        assertThat(dmDuongSuDTO1).isEqualTo(dmDuongSuDTO2);
        dmDuongSuDTO2.setIdDuongSu(2L);
        assertThat(dmDuongSuDTO1).isNotEqualTo(dmDuongSuDTO2);
        dmDuongSuDTO1.setIdDuongSu(null);
        assertThat(dmDuongSuDTO1).isNotEqualTo(dmDuongSuDTO2);
    }
}
