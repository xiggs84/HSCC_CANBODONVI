package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DuongSuTrungCmndBakDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DuongSuTrungCmndBakDTO.class);
        DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO1 = new DuongSuTrungCmndBakDTO();
        duongSuTrungCmndBakDTO1.setId(1L);
        DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO2 = new DuongSuTrungCmndBakDTO();
        assertThat(duongSuTrungCmndBakDTO1).isNotEqualTo(duongSuTrungCmndBakDTO2);
        duongSuTrungCmndBakDTO2.setId(duongSuTrungCmndBakDTO1.getId());
        assertThat(duongSuTrungCmndBakDTO1).isEqualTo(duongSuTrungCmndBakDTO2);
        duongSuTrungCmndBakDTO2.setId(2L);
        assertThat(duongSuTrungCmndBakDTO1).isNotEqualTo(duongSuTrungCmndBakDTO2);
        duongSuTrungCmndBakDTO1.setId(null);
        assertThat(duongSuTrungCmndBakDTO1).isNotEqualTo(duongSuTrungCmndBakDTO2);
    }
}
