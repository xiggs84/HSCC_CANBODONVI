package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class QuanHeNhanThanDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuanHeNhanThanDTO.class);
        QuanHeNhanThanDTO quanHeNhanThanDTO1 = new QuanHeNhanThanDTO();
        quanHeNhanThanDTO1.setIdQuanHe(1L);
        QuanHeNhanThanDTO quanHeNhanThanDTO2 = new QuanHeNhanThanDTO();
        assertThat(quanHeNhanThanDTO1).isNotEqualTo(quanHeNhanThanDTO2);
        quanHeNhanThanDTO2.setIdQuanHe(quanHeNhanThanDTO1.getIdQuanHe());
        assertThat(quanHeNhanThanDTO1).isEqualTo(quanHeNhanThanDTO2);
        quanHeNhanThanDTO2.setIdQuanHe(2L);
        assertThat(quanHeNhanThanDTO1).isNotEqualTo(quanHeNhanThanDTO2);
        quanHeNhanThanDTO1.setIdQuanHe(null);
        assertThat(quanHeNhanThanDTO1).isNotEqualTo(quanHeNhanThanDTO2);
    }
}
