package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class SoCongChungDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoCongChungDTO.class);
        SoCongChungDTO soCongChungDTO1 = new SoCongChungDTO();
        soCongChungDTO1.setId("id1");
        SoCongChungDTO soCongChungDTO2 = new SoCongChungDTO();
        assertThat(soCongChungDTO1).isNotEqualTo(soCongChungDTO2);
        soCongChungDTO2.setId(soCongChungDTO1.getId());
        assertThat(soCongChungDTO1).isEqualTo(soCongChungDTO2);
        soCongChungDTO2.setId("id2");
        assertThat(soCongChungDTO1).isNotEqualTo(soCongChungDTO2);
        soCongChungDTO1.setId(null);
        assertThat(soCongChungDTO1).isNotEqualTo(soCongChungDTO2);
    }
}
