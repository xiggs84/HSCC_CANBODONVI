package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class NoiCapGtttTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static NoiCapGttt getNoiCapGtttSample1() {
        return new NoiCapGttt().idNoiCap(1L).dienGiai("dienGiai1").trangThai(1L);
    }

    public static NoiCapGttt getNoiCapGtttSample2() {
        return new NoiCapGttt().idNoiCap(2L).dienGiai("dienGiai2").trangThai(2L);
    }

    public static NoiCapGttt getNoiCapGtttRandomSampleGenerator() {
        return new NoiCapGttt()
            .idNoiCap(longCount.incrementAndGet())
            .dienGiai(UUID.randomUUID().toString())
            .trangThai(longCount.incrementAndGet());
    }
}
