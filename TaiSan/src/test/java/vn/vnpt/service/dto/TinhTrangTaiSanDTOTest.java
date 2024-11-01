package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class TinhTrangTaiSanDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TinhTrangTaiSanDTO.class);
        TinhTrangTaiSanDTO tinhTrangTaiSanDTO1 = new TinhTrangTaiSanDTO();
        tinhTrangTaiSanDTO1.setIdTinhTrang(1L);
        TinhTrangTaiSanDTO tinhTrangTaiSanDTO2 = new TinhTrangTaiSanDTO();
        assertThat(tinhTrangTaiSanDTO1).isNotEqualTo(tinhTrangTaiSanDTO2);
        tinhTrangTaiSanDTO2.setIdTinhTrang(tinhTrangTaiSanDTO1.getIdTinhTrang());
        assertThat(tinhTrangTaiSanDTO1).isEqualTo(tinhTrangTaiSanDTO2);
        tinhTrangTaiSanDTO2.setIdTinhTrang(2L);
        assertThat(tinhTrangTaiSanDTO1).isNotEqualTo(tinhTrangTaiSanDTO2);
        tinhTrangTaiSanDTO1.setIdTinhTrang(null);
        assertThat(tinhTrangTaiSanDTO1).isNotEqualTo(tinhTrangTaiSanDTO2);
    }
}
