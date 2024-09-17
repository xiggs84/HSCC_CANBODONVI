package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class QuyenTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Quyen getQuyenSample1() {
        return new Quyen().idQuyen(1L).tenQuyen("tenQuyen1");
    }

    public static Quyen getQuyenSample2() {
        return new Quyen().idQuyen(2L).tenQuyen("tenQuyen2");
    }

    public static Quyen getQuyenRandomSampleGenerator() {
        return new Quyen().idQuyen(longCount.incrementAndGet()).tenQuyen(UUID.randomUUID().toString());
    }
}
