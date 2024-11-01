package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucVaiTroDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucVaiTroDTO.class);
        DanhMucVaiTroDTO danhMucVaiTroDTO1 = new DanhMucVaiTroDTO();
        danhMucVaiTroDTO1.setId("id1");
        DanhMucVaiTroDTO danhMucVaiTroDTO2 = new DanhMucVaiTroDTO();
        assertThat(danhMucVaiTroDTO1).isNotEqualTo(danhMucVaiTroDTO2);
        danhMucVaiTroDTO2.setId(danhMucVaiTroDTO1.getId());
        assertThat(danhMucVaiTroDTO1).isEqualTo(danhMucVaiTroDTO2);
        danhMucVaiTroDTO2.setId("id2");
        assertThat(danhMucVaiTroDTO1).isNotEqualTo(danhMucVaiTroDTO2);
        danhMucVaiTroDTO1.setId(null);
        assertThat(danhMucVaiTroDTO1).isNotEqualTo(danhMucVaiTroDTO2);
    }
}
