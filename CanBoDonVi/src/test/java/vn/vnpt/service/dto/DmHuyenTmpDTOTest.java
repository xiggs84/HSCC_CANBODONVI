package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DmHuyenTmpDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmHuyenTmpDTO.class);
        DmHuyenTmpDTO dmHuyenTmpDTO1 = new DmHuyenTmpDTO();
        dmHuyenTmpDTO1.setMaHuyen(1L);
        DmHuyenTmpDTO dmHuyenTmpDTO2 = new DmHuyenTmpDTO();
        assertThat(dmHuyenTmpDTO1).isNotEqualTo(dmHuyenTmpDTO2);
        dmHuyenTmpDTO2.setMaHuyen(dmHuyenTmpDTO1.getMaHuyen());
        assertThat(dmHuyenTmpDTO1).isEqualTo(dmHuyenTmpDTO2);
        dmHuyenTmpDTO2.setMaHuyen(2L);
        assertThat(dmHuyenTmpDTO1).isNotEqualTo(dmHuyenTmpDTO2);
        dmHuyenTmpDTO1.setMaHuyen(null);
        assertThat(dmHuyenTmpDTO1).isNotEqualTo(dmHuyenTmpDTO2);
    }
}
