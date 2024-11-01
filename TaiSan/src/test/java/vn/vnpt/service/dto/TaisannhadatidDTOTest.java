package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class TaisannhadatidDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaisannhadatidDTO.class);
        TaisannhadatidDTO taisannhadatidDTO1 = new TaisannhadatidDTO();
        taisannhadatidDTO1.setIdTaiSan(1L);
        TaisannhadatidDTO taisannhadatidDTO2 = new TaisannhadatidDTO();
        assertThat(taisannhadatidDTO1).isNotEqualTo(taisannhadatidDTO2);
        taisannhadatidDTO2.setIdTaiSan(taisannhadatidDTO1.getIdTaiSan());
        assertThat(taisannhadatidDTO1).isEqualTo(taisannhadatidDTO2);
        taisannhadatidDTO2.setIdTaiSan(2L);
        assertThat(taisannhadatidDTO1).isNotEqualTo(taisannhadatidDTO2);
        taisannhadatidDTO1.setIdTaiSan(null);
        assertThat(taisannhadatidDTO1).isNotEqualTo(taisannhadatidDTO2);
    }
}
