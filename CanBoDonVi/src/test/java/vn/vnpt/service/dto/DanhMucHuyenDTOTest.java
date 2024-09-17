package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucHuyenDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucHuyenDTO.class);
        DanhMucHuyenDTO danhMucHuyenDTO1 = new DanhMucHuyenDTO();
        danhMucHuyenDTO1.setMaHuyen("id1");
        DanhMucHuyenDTO danhMucHuyenDTO2 = new DanhMucHuyenDTO();
        assertThat(danhMucHuyenDTO1).isNotEqualTo(danhMucHuyenDTO2);
        danhMucHuyenDTO2.setMaHuyen(danhMucHuyenDTO1.getMaHuyen());
        assertThat(danhMucHuyenDTO1).isEqualTo(danhMucHuyenDTO2);
        danhMucHuyenDTO2.setMaHuyen("id2");
        assertThat(danhMucHuyenDTO1).isNotEqualTo(danhMucHuyenDTO2);
        danhMucHuyenDTO1.setMaHuyen(null);
        assertThat(danhMucHuyenDTO1).isNotEqualTo(danhMucHuyenDTO2);
    }
}
