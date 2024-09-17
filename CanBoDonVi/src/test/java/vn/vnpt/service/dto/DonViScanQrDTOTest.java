package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DonViScanQrDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DonViScanQrDTO.class);
        DonViScanQrDTO donViScanQrDTO1 = new DonViScanQrDTO();
        donViScanQrDTO1.setIdLuotQuet(1L);
        DonViScanQrDTO donViScanQrDTO2 = new DonViScanQrDTO();
        assertThat(donViScanQrDTO1).isNotEqualTo(donViScanQrDTO2);
        donViScanQrDTO2.setIdLuotQuet(donViScanQrDTO1.getIdLuotQuet());
        assertThat(donViScanQrDTO1).isEqualTo(donViScanQrDTO2);
        donViScanQrDTO2.setIdLuotQuet(2L);
        assertThat(donViScanQrDTO1).isNotEqualTo(donViScanQrDTO2);
        donViScanQrDTO1.setIdLuotQuet(null);
        assertThat(donViScanQrDTO1).isNotEqualTo(donViScanQrDTO2);
    }
}
