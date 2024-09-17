package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class NhiemVuDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhiemVuDTO.class);
        NhiemVuDTO nhiemVuDTO1 = new NhiemVuDTO();
        nhiemVuDTO1.setIdNhiemVu("id1");
        NhiemVuDTO nhiemVuDTO2 = new NhiemVuDTO();
        assertThat(nhiemVuDTO1).isNotEqualTo(nhiemVuDTO2);
        nhiemVuDTO2.setIdNhiemVu(nhiemVuDTO1.getIdNhiemVu());
        assertThat(nhiemVuDTO1).isEqualTo(nhiemVuDTO2);
        nhiemVuDTO2.setIdNhiemVu("id2");
        assertThat(nhiemVuDTO1).isNotEqualTo(nhiemVuDTO2);
        nhiemVuDTO1.setIdNhiemVu(null);
        assertThat(nhiemVuDTO1).isNotEqualTo(nhiemVuDTO2);
    }
}
