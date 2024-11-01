package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class LoaiHopDongCongChungDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoaiHopDongCongChungDTO.class);
        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO1 = new LoaiHopDongCongChungDTO();
        loaiHopDongCongChungDTO1.setId("id1");
        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO2 = new LoaiHopDongCongChungDTO();
        assertThat(loaiHopDongCongChungDTO1).isNotEqualTo(loaiHopDongCongChungDTO2);
        loaiHopDongCongChungDTO2.setId(loaiHopDongCongChungDTO1.getId());
        assertThat(loaiHopDongCongChungDTO1).isEqualTo(loaiHopDongCongChungDTO2);
        loaiHopDongCongChungDTO2.setId("id2");
        assertThat(loaiHopDongCongChungDTO1).isNotEqualTo(loaiHopDongCongChungDTO2);
        loaiHopDongCongChungDTO1.setId(null);
        assertThat(loaiHopDongCongChungDTO1).isNotEqualTo(loaiHopDongCongChungDTO2);
    }
}
