package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucDauSoCmndDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucDauSoCmndDTO.class);
        DanhMucDauSoCmndDTO danhMucDauSoCmndDTO1 = new DanhMucDauSoCmndDTO();
        danhMucDauSoCmndDTO1.setIdDauSo(1L);
        DanhMucDauSoCmndDTO danhMucDauSoCmndDTO2 = new DanhMucDauSoCmndDTO();
        assertThat(danhMucDauSoCmndDTO1).isNotEqualTo(danhMucDauSoCmndDTO2);
        danhMucDauSoCmndDTO2.setIdDauSo(danhMucDauSoCmndDTO1.getIdDauSo());
        assertThat(danhMucDauSoCmndDTO1).isEqualTo(danhMucDauSoCmndDTO2);
        danhMucDauSoCmndDTO2.setIdDauSo(2L);
        assertThat(danhMucDauSoCmndDTO1).isNotEqualTo(danhMucDauSoCmndDTO2);
        danhMucDauSoCmndDTO1.setIdDauSo(null);
        assertThat(danhMucDauSoCmndDTO1).isNotEqualTo(danhMucDauSoCmndDTO2);
    }
}
