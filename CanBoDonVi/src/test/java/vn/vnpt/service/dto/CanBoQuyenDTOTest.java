package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class CanBoQuyenDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CanBoQuyenDTO.class);
        CanBoQuyenDTO canBoQuyenDTO1 = new CanBoQuyenDTO();
        canBoQuyenDTO1.setId(1L);
        CanBoQuyenDTO canBoQuyenDTO2 = new CanBoQuyenDTO();
        assertThat(canBoQuyenDTO1).isNotEqualTo(canBoQuyenDTO2);
        canBoQuyenDTO2.setId(canBoQuyenDTO1.getId());
        assertThat(canBoQuyenDTO1).isEqualTo(canBoQuyenDTO2);
        canBoQuyenDTO2.setId(2L);
        assertThat(canBoQuyenDTO1).isNotEqualTo(canBoQuyenDTO2);
        canBoQuyenDTO1.setId(null);
        assertThat(canBoQuyenDTO1).isNotEqualTo(canBoQuyenDTO2);
    }
}
