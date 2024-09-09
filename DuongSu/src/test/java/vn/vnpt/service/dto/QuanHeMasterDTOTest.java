package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class QuanHeMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuanHeMasterDTO.class);
        QuanHeMasterDTO quanHeMasterDTO1 = new QuanHeMasterDTO();
        quanHeMasterDTO1.setId(1L);
        QuanHeMasterDTO quanHeMasterDTO2 = new QuanHeMasterDTO();
        assertThat(quanHeMasterDTO1).isNotEqualTo(quanHeMasterDTO2);
        quanHeMasterDTO2.setId(quanHeMasterDTO1.getId());
        assertThat(quanHeMasterDTO1).isEqualTo(quanHeMasterDTO2);
        quanHeMasterDTO2.setId(2L);
        assertThat(quanHeMasterDTO1).isNotEqualTo(quanHeMasterDTO2);
        quanHeMasterDTO1.setId(null);
        assertThat(quanHeMasterDTO1).isNotEqualTo(quanHeMasterDTO2);
    }
}
