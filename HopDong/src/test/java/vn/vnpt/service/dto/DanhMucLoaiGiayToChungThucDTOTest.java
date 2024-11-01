package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucLoaiGiayToChungThucDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucLoaiGiayToChungThucDTO.class);
        DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO1 = new DanhMucLoaiGiayToChungThucDTO();
        danhMucLoaiGiayToChungThucDTO1.setId("id1");
        DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO2 = new DanhMucLoaiGiayToChungThucDTO();
        assertThat(danhMucLoaiGiayToChungThucDTO1).isNotEqualTo(danhMucLoaiGiayToChungThucDTO2);
        danhMucLoaiGiayToChungThucDTO2.setId(danhMucLoaiGiayToChungThucDTO1.getId());
        assertThat(danhMucLoaiGiayToChungThucDTO1).isEqualTo(danhMucLoaiGiayToChungThucDTO2);
        danhMucLoaiGiayToChungThucDTO2.setId("id2");
        assertThat(danhMucLoaiGiayToChungThucDTO1).isNotEqualTo(danhMucLoaiGiayToChungThucDTO2);
        danhMucLoaiGiayToChungThucDTO1.setId(null);
        assertThat(danhMucLoaiGiayToChungThucDTO1).isNotEqualTo(danhMucLoaiGiayToChungThucDTO2);
    }
}
