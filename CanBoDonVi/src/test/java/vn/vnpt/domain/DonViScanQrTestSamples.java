package vn.vnpt.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class DonViScanQrTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DonViScanQr getDonViScanQrSample1() {
        return new DonViScanQr().idLuotQuet(1L).idCongDan(1L);
    }

    public static DonViScanQr getDonViScanQrSample2() {
        return new DonViScanQr().idLuotQuet(2L).idCongDan(2L);
    }

    public static DonViScanQr getDonViScanQrRandomSampleGenerator() {
        return new DonViScanQr().idLuotQuet(longCount.incrementAndGet()).idCongDan(longCount.incrementAndGet());
    }
}
