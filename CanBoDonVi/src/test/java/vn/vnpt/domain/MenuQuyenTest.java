package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.MenuQuyenTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class MenuQuyenTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MenuQuyen.class);
        MenuQuyen menuQuyen1 = getMenuQuyenSample1();
        MenuQuyen menuQuyen2 = new MenuQuyen();
        assertThat(menuQuyen1).isNotEqualTo(menuQuyen2);

        menuQuyen2.setId(menuQuyen1.getId());
        assertThat(menuQuyen1).isEqualTo(menuQuyen2);

        menuQuyen2 = getMenuQuyenSample2();
        assertThat(menuQuyen1).isNotEqualTo(menuQuyen2);
    }
}
