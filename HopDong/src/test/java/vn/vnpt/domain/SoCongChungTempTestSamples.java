package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class SoCongChungTempTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static SoCongChungTemp getSoCongChungTempSample1() {
        return new SoCongChungTemp().id("id1").idHopDong("idHopDong1").idMaster(1L).soCc("soCc1");
    }

    public static SoCongChungTemp getSoCongChungTempSample2() {
        return new SoCongChungTemp().id("id2").idHopDong("idHopDong2").idMaster(2L).soCc("soCc2");
    }

    public static SoCongChungTemp getSoCongChungTempRandomSampleGenerator() {
        return new SoCongChungTemp()
            .id(UUID.randomUUID().toString())
            .idHopDong(UUID.randomUUID().toString())
            .idMaster(longCount.incrementAndGet())
            .soCc(UUID.randomUUID().toString());
    }
}
