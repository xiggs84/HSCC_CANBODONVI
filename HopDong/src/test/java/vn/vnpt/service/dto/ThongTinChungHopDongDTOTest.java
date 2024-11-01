package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class ThongTinChungHopDongDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThongTinChungHopDongDTO.class);
        ThongTinChungHopDongDTO thongTinChungHopDongDTO1 = new ThongTinChungHopDongDTO();
        thongTinChungHopDongDTO1.setId("id1");
        ThongTinChungHopDongDTO thongTinChungHopDongDTO2 = new ThongTinChungHopDongDTO();
        assertThat(thongTinChungHopDongDTO1).isNotEqualTo(thongTinChungHopDongDTO2);
        thongTinChungHopDongDTO2.setId(thongTinChungHopDongDTO1.getId());
        assertThat(thongTinChungHopDongDTO1).isEqualTo(thongTinChungHopDongDTO2);
        thongTinChungHopDongDTO2.setId("id2");
        assertThat(thongTinChungHopDongDTO1).isNotEqualTo(thongTinChungHopDongDTO2);
        thongTinChungHopDongDTO1.setId(null);
        assertThat(thongTinChungHopDongDTO1).isNotEqualTo(thongTinChungHopDongDTO2);
    }
}
