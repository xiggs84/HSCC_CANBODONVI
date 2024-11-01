package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucTuVietTatDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucTuVietTatDTO.class);
        DanhMucTuVietTatDTO danhMucTuVietTatDTO1 = new DanhMucTuVietTatDTO();
        danhMucTuVietTatDTO1.setId("id1");
        DanhMucTuVietTatDTO danhMucTuVietTatDTO2 = new DanhMucTuVietTatDTO();
        assertThat(danhMucTuVietTatDTO1).isNotEqualTo(danhMucTuVietTatDTO2);
        danhMucTuVietTatDTO2.setId(danhMucTuVietTatDTO1.getId());
        assertThat(danhMucTuVietTatDTO1).isEqualTo(danhMucTuVietTatDTO2);
        danhMucTuVietTatDTO2.setId("id2");
        assertThat(danhMucTuVietTatDTO1).isNotEqualTo(danhMucTuVietTatDTO2);
        danhMucTuVietTatDTO1.setId(null);
        assertThat(danhMucTuVietTatDTO1).isNotEqualTo(danhMucTuVietTatDTO2);
    }
}
