package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DmXaTmpDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmXaTmpDTO.class);
        DmXaTmpDTO dmXaTmpDTO1 = new DmXaTmpDTO();
        dmXaTmpDTO1.setMaXa(1L);
        DmXaTmpDTO dmXaTmpDTO2 = new DmXaTmpDTO();
        assertThat(dmXaTmpDTO1).isNotEqualTo(dmXaTmpDTO2);
        dmXaTmpDTO2.setMaXa(dmXaTmpDTO1.getMaXa());
        assertThat(dmXaTmpDTO1).isEqualTo(dmXaTmpDTO2);
        dmXaTmpDTO2.setMaXa(2L);
        assertThat(dmXaTmpDTO1).isNotEqualTo(dmXaTmpDTO2);
        dmXaTmpDTO1.setMaXa(null);
        assertThat(dmXaTmpDTO1).isNotEqualTo(dmXaTmpDTO2);
    }
}
