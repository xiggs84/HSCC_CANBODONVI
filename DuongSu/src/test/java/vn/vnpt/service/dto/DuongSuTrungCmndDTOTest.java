package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DuongSuTrungCmndDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DuongSuTrungCmndDTO.class);
        DuongSuTrungCmndDTO duongSuTrungCmndDTO1 = new DuongSuTrungCmndDTO();
        duongSuTrungCmndDTO1.setId(1L);
        DuongSuTrungCmndDTO duongSuTrungCmndDTO2 = new DuongSuTrungCmndDTO();
        assertThat(duongSuTrungCmndDTO1).isNotEqualTo(duongSuTrungCmndDTO2);
        duongSuTrungCmndDTO2.setId(duongSuTrungCmndDTO1.getId());
        assertThat(duongSuTrungCmndDTO1).isEqualTo(duongSuTrungCmndDTO2);
        duongSuTrungCmndDTO2.setId(2L);
        assertThat(duongSuTrungCmndDTO1).isNotEqualTo(duongSuTrungCmndDTO2);
        duongSuTrungCmndDTO1.setId(null);
        assertThat(duongSuTrungCmndDTO1).isNotEqualTo(duongSuTrungCmndDTO2);
    }
}
