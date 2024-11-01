package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucNhomHopDongDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucNhomHopDongDTO.class);
        DanhMucNhomHopDongDTO danhMucNhomHopDongDTO1 = new DanhMucNhomHopDongDTO();
        danhMucNhomHopDongDTO1.setId("id1");
        DanhMucNhomHopDongDTO danhMucNhomHopDongDTO2 = new DanhMucNhomHopDongDTO();
        assertThat(danhMucNhomHopDongDTO1).isNotEqualTo(danhMucNhomHopDongDTO2);
        danhMucNhomHopDongDTO2.setId(danhMucNhomHopDongDTO1.getId());
        assertThat(danhMucNhomHopDongDTO1).isEqualTo(danhMucNhomHopDongDTO2);
        danhMucNhomHopDongDTO2.setId("id2");
        assertThat(danhMucNhomHopDongDTO1).isNotEqualTo(danhMucNhomHopDongDTO2);
        danhMucNhomHopDongDTO1.setId(null);
        assertThat(danhMucNhomHopDongDTO1).isNotEqualTo(danhMucNhomHopDongDTO2);
    }
}
