package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucQuocGiaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucQuocGiaDTO.class);
        DanhMucQuocGiaDTO danhMucQuocGiaDTO1 = new DanhMucQuocGiaDTO();
        danhMucQuocGiaDTO1.setIdQuocGia(1L);
        DanhMucQuocGiaDTO danhMucQuocGiaDTO2 = new DanhMucQuocGiaDTO();
        assertThat(danhMucQuocGiaDTO1).isNotEqualTo(danhMucQuocGiaDTO2);
        danhMucQuocGiaDTO2.setIdQuocGia(danhMucQuocGiaDTO1.getIdQuocGia());
        assertThat(danhMucQuocGiaDTO1).isEqualTo(danhMucQuocGiaDTO2);
        danhMucQuocGiaDTO2.setIdQuocGia(2L);
        assertThat(danhMucQuocGiaDTO1).isNotEqualTo(danhMucQuocGiaDTO2);
        danhMucQuocGiaDTO1.setIdQuocGia(null);
        assertThat(danhMucQuocGiaDTO1).isNotEqualTo(danhMucQuocGiaDTO2);
    }
}
