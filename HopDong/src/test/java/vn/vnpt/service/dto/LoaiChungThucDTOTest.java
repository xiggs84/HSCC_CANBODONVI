package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class LoaiChungThucDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoaiChungThucDTO.class);
        LoaiChungThucDTO loaiChungThucDTO1 = new LoaiChungThucDTO();
        loaiChungThucDTO1.setId("id1");
        LoaiChungThucDTO loaiChungThucDTO2 = new LoaiChungThucDTO();
        assertThat(loaiChungThucDTO1).isNotEqualTo(loaiChungThucDTO2);
        loaiChungThucDTO2.setId(loaiChungThucDTO1.getId());
        assertThat(loaiChungThucDTO1).isEqualTo(loaiChungThucDTO2);
        loaiChungThucDTO2.setId("id2");
        assertThat(loaiChungThucDTO1).isNotEqualTo(loaiChungThucDTO2);
        loaiChungThucDTO1.setId(null);
        assertThat(loaiChungThucDTO1).isNotEqualTo(loaiChungThucDTO2);
    }
}
