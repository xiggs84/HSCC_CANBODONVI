package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class ThuaTachDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThuaTachDTO.class);
        ThuaTachDTO thuaTachDTO1 = new ThuaTachDTO();
        thuaTachDTO1.setIdThuaTach(1L);
        ThuaTachDTO thuaTachDTO2 = new ThuaTachDTO();
        assertThat(thuaTachDTO1).isNotEqualTo(thuaTachDTO2);
        thuaTachDTO2.setIdThuaTach(thuaTachDTO1.getIdThuaTach());
        assertThat(thuaTachDTO1).isEqualTo(thuaTachDTO2);
        thuaTachDTO2.setIdThuaTach(2L);
        assertThat(thuaTachDTO1).isNotEqualTo(thuaTachDTO2);
        thuaTachDTO1.setIdThuaTach(null);
        assertThat(thuaTachDTO1).isNotEqualTo(thuaTachDTO2);
    }
}
