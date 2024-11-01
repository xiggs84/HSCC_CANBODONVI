package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhSachHopDongDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhSachHopDongDTO.class);
        DanhSachHopDongDTO danhSachHopDongDTO1 = new DanhSachHopDongDTO();
        danhSachHopDongDTO1.setId("id1");
        DanhSachHopDongDTO danhSachHopDongDTO2 = new DanhSachHopDongDTO();
        assertThat(danhSachHopDongDTO1).isNotEqualTo(danhSachHopDongDTO2);
        danhSachHopDongDTO2.setId(danhSachHopDongDTO1.getId());
        assertThat(danhSachHopDongDTO1).isEqualTo(danhSachHopDongDTO2);
        danhSachHopDongDTO2.setId("id2");
        assertThat(danhSachHopDongDTO1).isNotEqualTo(danhSachHopDongDTO2);
        danhSachHopDongDTO1.setId(null);
        assertThat(danhSachHopDongDTO1).isNotEqualTo(danhSachHopDongDTO2);
    }
}
