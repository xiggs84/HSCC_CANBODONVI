package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucLoaiSoCongChungDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucLoaiSoCongChungDTO.class);
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO1 = new DanhMucLoaiSoCongChungDTO();
        danhMucLoaiSoCongChungDTO1.setId("id1");
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO2 = new DanhMucLoaiSoCongChungDTO();
        assertThat(danhMucLoaiSoCongChungDTO1).isNotEqualTo(danhMucLoaiSoCongChungDTO2);
        danhMucLoaiSoCongChungDTO2.setId(danhMucLoaiSoCongChungDTO1.getId());
        assertThat(danhMucLoaiSoCongChungDTO1).isEqualTo(danhMucLoaiSoCongChungDTO2);
        danhMucLoaiSoCongChungDTO2.setId("id2");
        assertThat(danhMucLoaiSoCongChungDTO1).isNotEqualTo(danhMucLoaiSoCongChungDTO2);
        danhMucLoaiSoCongChungDTO1.setId(null);
        assertThat(danhMucLoaiSoCongChungDTO1).isNotEqualTo(danhMucLoaiSoCongChungDTO2);
    }
}
