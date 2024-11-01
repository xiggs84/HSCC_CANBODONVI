package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class ThongTinCapNhatDuongSuDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThongTinCapNhatDuongSuDTO.class);
        ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO1 = new ThongTinCapNhatDuongSuDTO();
        thongTinCapNhatDuongSuDTO1.setIdCapNhat(1L);
        ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO2 = new ThongTinCapNhatDuongSuDTO();
        assertThat(thongTinCapNhatDuongSuDTO1).isNotEqualTo(thongTinCapNhatDuongSuDTO2);
        thongTinCapNhatDuongSuDTO2.setIdCapNhat(thongTinCapNhatDuongSuDTO1.getIdCapNhat());
        assertThat(thongTinCapNhatDuongSuDTO1).isEqualTo(thongTinCapNhatDuongSuDTO2);
        thongTinCapNhatDuongSuDTO2.setIdCapNhat(2L);
        assertThat(thongTinCapNhatDuongSuDTO1).isNotEqualTo(thongTinCapNhatDuongSuDTO2);
        thongTinCapNhatDuongSuDTO1.setIdCapNhat(null);
        assertThat(thongTinCapNhatDuongSuDTO1).isNotEqualTo(thongTinCapNhatDuongSuDTO2);
    }
}
