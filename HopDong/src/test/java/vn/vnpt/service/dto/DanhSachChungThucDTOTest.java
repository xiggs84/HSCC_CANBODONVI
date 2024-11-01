package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhSachChungThucDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhSachChungThucDTO.class);
        DanhSachChungThucDTO danhSachChungThucDTO1 = new DanhSachChungThucDTO();
        danhSachChungThucDTO1.setId("id1");
        DanhSachChungThucDTO danhSachChungThucDTO2 = new DanhSachChungThucDTO();
        assertThat(danhSachChungThucDTO1).isNotEqualTo(danhSachChungThucDTO2);
        danhSachChungThucDTO2.setId(danhSachChungThucDTO1.getId());
        assertThat(danhSachChungThucDTO1).isEqualTo(danhSachChungThucDTO2);
        danhSachChungThucDTO2.setId("id2");
        assertThat(danhSachChungThucDTO1).isNotEqualTo(danhSachChungThucDTO2);
        danhSachChungThucDTO1.setId(null);
        assertThat(danhSachChungThucDTO1).isNotEqualTo(danhSachChungThucDTO2);
    }
}
