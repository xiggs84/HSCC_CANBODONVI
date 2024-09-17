package vn.vnpt.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class CanBoQuyenTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CanBoQuyen getCanBoQuyenSample1() {
        return new CanBoQuyen().id(1L);
    }

    public static CanBoQuyen getCanBoQuyenSample2() {
        return new CanBoQuyen().id(2L);
    }

    public static CanBoQuyen getCanBoQuyenRandomSampleGenerator() {
        return new CanBoQuyen().id(longCount.incrementAndGet());
    }
}
