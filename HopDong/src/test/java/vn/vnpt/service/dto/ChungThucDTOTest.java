package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class ChungThucDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChungThucDTO.class);
        ChungThucDTO chungThucDTO1 = new ChungThucDTO();
        chungThucDTO1.setId("id1");
        ChungThucDTO chungThucDTO2 = new ChungThucDTO();
        assertThat(chungThucDTO1).isNotEqualTo(chungThucDTO2);
        chungThucDTO2.setId(chungThucDTO1.getId());
        assertThat(chungThucDTO1).isEqualTo(chungThucDTO2);
        chungThucDTO2.setId("id2");
        assertThat(chungThucDTO1).isNotEqualTo(chungThucDTO2);
        chungThucDTO1.setId(null);
        assertThat(chungThucDTO1).isNotEqualTo(chungThucDTO2);
    }
}
