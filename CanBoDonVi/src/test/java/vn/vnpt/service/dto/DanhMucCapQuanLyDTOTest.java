package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucCapQuanLyDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucCapQuanLyDTO.class);
        DanhMucCapQuanLyDTO danhMucCapQuanLyDTO1 = new DanhMucCapQuanLyDTO();
        danhMucCapQuanLyDTO1.setIdCapQl(1L);
        DanhMucCapQuanLyDTO danhMucCapQuanLyDTO2 = new DanhMucCapQuanLyDTO();
        assertThat(danhMucCapQuanLyDTO1).isNotEqualTo(danhMucCapQuanLyDTO2);
        danhMucCapQuanLyDTO2.setIdCapQl(danhMucCapQuanLyDTO1.getIdCapQl());
        assertThat(danhMucCapQuanLyDTO1).isEqualTo(danhMucCapQuanLyDTO2);
        danhMucCapQuanLyDTO2.setIdCapQl(2L);
        assertThat(danhMucCapQuanLyDTO1).isNotEqualTo(danhMucCapQuanLyDTO2);
        danhMucCapQuanLyDTO1.setIdCapQl(null);
        assertThat(danhMucCapQuanLyDTO1).isNotEqualTo(danhMucCapQuanLyDTO2);
    }
}
