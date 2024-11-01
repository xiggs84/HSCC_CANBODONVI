package vn.vnpt.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class QuanHeDuongSuTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static QuanHeDuongSu getQuanHeDuongSuSample1() {
        return new QuanHeDuongSu().idQuanHe(1L).idDuongSuQh(1L).trangThai(1);
    }

    public static QuanHeDuongSu getQuanHeDuongSuSample2() {
        return new QuanHeDuongSu().idQuanHe(2L).idDuongSuQh(2L).trangThai(2);
    }

    public static QuanHeDuongSu getQuanHeDuongSuRandomSampleGenerator() {
        return new QuanHeDuongSu()
            .idQuanHe(longCount.incrementAndGet())
            .idDuongSuQh(longCount.incrementAndGet())
            .trangThai(intCount.incrementAndGet());
    }
}
