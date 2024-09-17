package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DmTinhTmpDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmTinhTmpDTO.class);
        DmTinhTmpDTO dmTinhTmpDTO1 = new DmTinhTmpDTO();
        dmTinhTmpDTO1.setMaTinh(1L);
        DmTinhTmpDTO dmTinhTmpDTO2 = new DmTinhTmpDTO();
        assertThat(dmTinhTmpDTO1).isNotEqualTo(dmTinhTmpDTO2);
        dmTinhTmpDTO2.setMaTinh(dmTinhTmpDTO1.getMaTinh());
        assertThat(dmTinhTmpDTO1).isEqualTo(dmTinhTmpDTO2);
        dmTinhTmpDTO2.setMaTinh(2L);
        assertThat(dmTinhTmpDTO1).isNotEqualTo(dmTinhTmpDTO2);
        dmTinhTmpDTO1.setMaTinh(null);
        assertThat(dmTinhTmpDTO1).isNotEqualTo(dmTinhTmpDTO2);
    }
}
