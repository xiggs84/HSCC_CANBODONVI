package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class HopDongCongChungDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HopDongCongChungDTO.class);
        HopDongCongChungDTO hopDongCongChungDTO1 = new HopDongCongChungDTO();
        hopDongCongChungDTO1.setId("id1");
        HopDongCongChungDTO hopDongCongChungDTO2 = new HopDongCongChungDTO();
        assertThat(hopDongCongChungDTO1).isNotEqualTo(hopDongCongChungDTO2);
        hopDongCongChungDTO2.setId(hopDongCongChungDTO1.getId());
        assertThat(hopDongCongChungDTO1).isEqualTo(hopDongCongChungDTO2);
        hopDongCongChungDTO2.setId("id2");
        assertThat(hopDongCongChungDTO1).isNotEqualTo(hopDongCongChungDTO2);
        hopDongCongChungDTO1.setId(null);
        assertThat(hopDongCongChungDTO1).isNotEqualTo(hopDongCongChungDTO2);
    }
}
