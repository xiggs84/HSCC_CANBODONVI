package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.DonViScanQrTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DonViScanQrTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DonViScanQr.class);
        DonViScanQr donViScanQr1 = getDonViScanQrSample1();
        DonViScanQr donViScanQr2 = new DonViScanQr();
        assertThat(donViScanQr1).isNotEqualTo(donViScanQr2);

        donViScanQr2.setIdLuotQuet(donViScanQr1.getIdLuotQuet());
        assertThat(donViScanQr1).isEqualTo(donViScanQr2);

        donViScanQr2 = getDonViScanQrSample2();
        assertThat(donViScanQr1).isNotEqualTo(donViScanQr2);
    }
}
