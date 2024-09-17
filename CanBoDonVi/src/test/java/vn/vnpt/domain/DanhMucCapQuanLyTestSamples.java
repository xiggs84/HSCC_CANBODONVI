package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DanhMucCapQuanLyTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DanhMucCapQuanLy getDanhMucCapQuanLySample1() {
        return new DanhMucCapQuanLy().idCapQl(1L).dienGiai("dienGiai1");
    }

    public static DanhMucCapQuanLy getDanhMucCapQuanLySample2() {
        return new DanhMucCapQuanLy().idCapQl(2L).dienGiai("dienGiai2");
    }

    public static DanhMucCapQuanLy getDanhMucCapQuanLyRandomSampleGenerator() {
        return new DanhMucCapQuanLy().idCapQl(longCount.incrementAndGet()).dienGiai(UUID.randomUUID().toString());
    }
}
