package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class QuanHeNhanThanTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static QuanHeNhanThan getQuanHeNhanThanSample1() {
        return new QuanHeNhanThan().idQuanHe(1L).dienGiai("dienGiai1").idQuanHeDoiUng(1L);
    }

    public static QuanHeNhanThan getQuanHeNhanThanSample2() {
        return new QuanHeNhanThan().idQuanHe(2L).dienGiai("dienGiai2").idQuanHeDoiUng(2L);
    }

    public static QuanHeNhanThan getQuanHeNhanThanRandomSampleGenerator() {
        return new QuanHeNhanThan()
            .idQuanHe(longCount.incrementAndGet())
            .dienGiai(UUID.randomUUID().toString())
            .idQuanHeDoiUng(longCount.incrementAndGet());
    }
}
