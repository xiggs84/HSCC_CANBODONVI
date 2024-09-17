package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DmNoiCapGpdkxTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DmNoiCapGpdkx getDmNoiCapGpdkxSample1() {
        return new DmNoiCapGpdkx().idNoiCap(1L).dienGiai("dienGiai1").trangThai(1L);
    }

    public static DmNoiCapGpdkx getDmNoiCapGpdkxSample2() {
        return new DmNoiCapGpdkx().idNoiCap(2L).dienGiai("dienGiai2").trangThai(2L);
    }

    public static DmNoiCapGpdkx getDmNoiCapGpdkxRandomSampleGenerator() {
        return new DmNoiCapGpdkx()
            .idNoiCap(longCount.incrementAndGet())
            .dienGiai(UUID.randomUUID().toString())
            .trangThai(longCount.incrementAndGet());
    }
}
