package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucLoaiHopDongDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucLoaiHopDongDTO.class);
        DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO1 = new DanhMucLoaiHopDongDTO();
        danhMucLoaiHopDongDTO1.setId("id1");
        DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO2 = new DanhMucLoaiHopDongDTO();
        assertThat(danhMucLoaiHopDongDTO1).isNotEqualTo(danhMucLoaiHopDongDTO2);
        danhMucLoaiHopDongDTO2.setId(danhMucLoaiHopDongDTO1.getId());
        assertThat(danhMucLoaiHopDongDTO1).isEqualTo(danhMucLoaiHopDongDTO2);
        danhMucLoaiHopDongDTO2.setId("id2");
        assertThat(danhMucLoaiHopDongDTO1).isNotEqualTo(danhMucLoaiHopDongDTO2);
        danhMucLoaiHopDongDTO1.setId(null);
        assertThat(danhMucLoaiHopDongDTO1).isNotEqualTo(danhMucLoaiHopDongDTO2);
    }
}
