package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class CapQuanLyDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CapQuanLyDTO.class);
        CapQuanLyDTO capQuanLyDTO1 = new CapQuanLyDTO();
        capQuanLyDTO1.setIdCapQl("id1");
        CapQuanLyDTO capQuanLyDTO2 = new CapQuanLyDTO();
        assertThat(capQuanLyDTO1).isNotEqualTo(capQuanLyDTO2);
        capQuanLyDTO2.setIdCapQl(capQuanLyDTO1.getIdCapQl());
        assertThat(capQuanLyDTO1).isEqualTo(capQuanLyDTO2);
        capQuanLyDTO2.setIdCapQl("id2");
        assertThat(capQuanLyDTO1).isNotEqualTo(capQuanLyDTO2);
        capQuanLyDTO1.setIdCapQl(null);
        assertThat(capQuanLyDTO1).isNotEqualTo(capQuanLyDTO2);
    }
}
