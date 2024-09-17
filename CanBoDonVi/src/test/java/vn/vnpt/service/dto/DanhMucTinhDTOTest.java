package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucTinhDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucTinhDTO.class);
        DanhMucTinhDTO danhMucTinhDTO1 = new DanhMucTinhDTO();
        danhMucTinhDTO1.setMaTinh("id1");
        DanhMucTinhDTO danhMucTinhDTO2 = new DanhMucTinhDTO();
        assertThat(danhMucTinhDTO1).isNotEqualTo(danhMucTinhDTO2);
        danhMucTinhDTO2.setMaTinh(danhMucTinhDTO1.getMaTinh());
        assertThat(danhMucTinhDTO1).isEqualTo(danhMucTinhDTO2);
        danhMucTinhDTO2.setMaTinh("id2");
        assertThat(danhMucTinhDTO1).isNotEqualTo(danhMucTinhDTO2);
        danhMucTinhDTO1.setMaTinh(null);
        assertThat(danhMucTinhDTO1).isNotEqualTo(danhMucTinhDTO2);
    }
}
