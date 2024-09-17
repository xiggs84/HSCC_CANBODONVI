package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucXaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucXaDTO.class);
        DanhMucXaDTO danhMucXaDTO1 = new DanhMucXaDTO();
        danhMucXaDTO1.setMaXa("id1");
        DanhMucXaDTO danhMucXaDTO2 = new DanhMucXaDTO();
        assertThat(danhMucXaDTO1).isNotEqualTo(danhMucXaDTO2);
        danhMucXaDTO2.setMaXa(danhMucXaDTO1.getMaXa());
        assertThat(danhMucXaDTO1).isEqualTo(danhMucXaDTO2);
        danhMucXaDTO2.setMaXa("id2");
        assertThat(danhMucXaDTO1).isNotEqualTo(danhMucXaDTO2);
        danhMucXaDTO1.setMaXa(null);
        assertThat(danhMucXaDTO1).isNotEqualTo(danhMucXaDTO2);
    }
}
