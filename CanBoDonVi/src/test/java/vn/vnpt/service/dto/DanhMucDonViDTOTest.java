package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucDonViDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucDonViDTO.class);
        DanhMucDonViDTO danhMucDonViDTO1 = new DanhMucDonViDTO();
        danhMucDonViDTO1.setIdDonVi(1L);
        DanhMucDonViDTO danhMucDonViDTO2 = new DanhMucDonViDTO();
        assertThat(danhMucDonViDTO1).isNotEqualTo(danhMucDonViDTO2);
        danhMucDonViDTO2.setIdDonVi(danhMucDonViDTO1.getIdDonVi());
        assertThat(danhMucDonViDTO1).isEqualTo(danhMucDonViDTO2);
        danhMucDonViDTO2.setIdDonVi(2L);
        assertThat(danhMucDonViDTO1).isNotEqualTo(danhMucDonViDTO2);
        danhMucDonViDTO1.setIdDonVi(null);
        assertThat(danhMucDonViDTO1).isNotEqualTo(danhMucDonViDTO2);
    }
}
