package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class LoaiDonViDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoaiDonViDTO.class);
        LoaiDonViDTO loaiDonViDTO1 = new LoaiDonViDTO();
        loaiDonViDTO1.setIdLoaiDv("id1");
        LoaiDonViDTO loaiDonViDTO2 = new LoaiDonViDTO();
        assertThat(loaiDonViDTO1).isNotEqualTo(loaiDonViDTO2);
        loaiDonViDTO2.setIdLoaiDv(loaiDonViDTO1.getIdLoaiDv());
        assertThat(loaiDonViDTO1).isEqualTo(loaiDonViDTO2);
        loaiDonViDTO2.setIdLoaiDv("id2");
        assertThat(loaiDonViDTO1).isNotEqualTo(loaiDonViDTO2);
        loaiDonViDTO1.setIdLoaiDv(null);
        assertThat(loaiDonViDTO1).isNotEqualTo(loaiDonViDTO2);
    }
}
