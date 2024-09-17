package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DmXaTmpTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DmXaTmp getDmXaTmpSample1() {
        return new DmXaTmp().maXa(1L).tenXa("tenXa1");
    }

    public static DmXaTmp getDmXaTmpSample2() {
        return new DmXaTmp().maXa(2L).tenXa("tenXa2");
    }

    public static DmXaTmp getDmXaTmpRandomSampleGenerator() {
        return new DmXaTmp().maXa(longCount.incrementAndGet()).tenXa(UUID.randomUUID().toString());
    }
}
