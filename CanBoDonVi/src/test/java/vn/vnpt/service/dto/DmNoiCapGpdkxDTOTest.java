package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DmNoiCapGpdkxDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmNoiCapGpdkxDTO.class);
        DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO1 = new DmNoiCapGpdkxDTO();
        dmNoiCapGpdkxDTO1.setIdNoiCap(1L);
        DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO2 = new DmNoiCapGpdkxDTO();
        assertThat(dmNoiCapGpdkxDTO1).isNotEqualTo(dmNoiCapGpdkxDTO2);
        dmNoiCapGpdkxDTO2.setIdNoiCap(dmNoiCapGpdkxDTO1.getIdNoiCap());
        assertThat(dmNoiCapGpdkxDTO1).isEqualTo(dmNoiCapGpdkxDTO2);
        dmNoiCapGpdkxDTO2.setIdNoiCap(2L);
        assertThat(dmNoiCapGpdkxDTO1).isNotEqualTo(dmNoiCapGpdkxDTO2);
        dmNoiCapGpdkxDTO1.setIdNoiCap(null);
        assertThat(dmNoiCapGpdkxDTO1).isNotEqualTo(dmNoiCapGpdkxDTO2);
    }
}
