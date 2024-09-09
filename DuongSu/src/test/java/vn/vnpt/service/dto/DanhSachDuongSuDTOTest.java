package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhSachDuongSuDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhSachDuongSuDTO.class);
        DanhSachDuongSuDTO danhSachDuongSuDTO1 = new DanhSachDuongSuDTO();
        danhSachDuongSuDTO1.setId(1L);
        DanhSachDuongSuDTO danhSachDuongSuDTO2 = new DanhSachDuongSuDTO();
        assertThat(danhSachDuongSuDTO1).isNotEqualTo(danhSachDuongSuDTO2);
        danhSachDuongSuDTO2.setId(danhSachDuongSuDTO1.getId());
        assertThat(danhSachDuongSuDTO1).isEqualTo(danhSachDuongSuDTO2);
        danhSachDuongSuDTO2.setId(2L);
        assertThat(danhSachDuongSuDTO1).isNotEqualTo(danhSachDuongSuDTO2);
        danhSachDuongSuDTO1.setId(null);
        assertThat(danhSachDuongSuDTO1).isNotEqualTo(danhSachDuongSuDTO2);
    }
}
