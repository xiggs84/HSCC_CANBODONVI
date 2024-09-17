package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class MenuQuyenDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MenuQuyenDTO.class);
        MenuQuyenDTO menuQuyenDTO1 = new MenuQuyenDTO();
        menuQuyenDTO1.setId(1L);
        MenuQuyenDTO menuQuyenDTO2 = new MenuQuyenDTO();
        assertThat(menuQuyenDTO1).isNotEqualTo(menuQuyenDTO2);
        menuQuyenDTO2.setId(menuQuyenDTO1.getId());
        assertThat(menuQuyenDTO1).isEqualTo(menuQuyenDTO2);
        menuQuyenDTO2.setId(2L);
        assertThat(menuQuyenDTO1).isNotEqualTo(menuQuyenDTO2);
        menuQuyenDTO1.setId(null);
        assertThat(menuQuyenDTO1).isNotEqualTo(menuQuyenDTO2);
    }
}
