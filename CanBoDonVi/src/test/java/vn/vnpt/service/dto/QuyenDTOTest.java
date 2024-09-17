package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class QuyenDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuyenDTO.class);
        QuyenDTO quyenDTO1 = new QuyenDTO();
        quyenDTO1.setIdQuyen(1L);
        QuyenDTO quyenDTO2 = new QuyenDTO();
        assertThat(quyenDTO1).isNotEqualTo(quyenDTO2);
        quyenDTO2.setIdQuyen(quyenDTO1.getIdQuyen());
        assertThat(quyenDTO1).isEqualTo(quyenDTO2);
        quyenDTO2.setIdQuyen(2L);
        assertThat(quyenDTO1).isNotEqualTo(quyenDTO2);
        quyenDTO1.setIdQuyen(null);
        assertThat(quyenDTO1).isNotEqualTo(quyenDTO2);
    }
}
