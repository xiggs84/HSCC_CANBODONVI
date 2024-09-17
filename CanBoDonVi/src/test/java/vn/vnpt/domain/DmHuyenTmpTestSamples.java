package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DmHuyenTmpTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DmHuyenTmp getDmHuyenTmpSample1() {
        return new DmHuyenTmp().maHuyen(1L).tenHuyen("tenHuyen1");
    }

    public static DmHuyenTmp getDmHuyenTmpSample2() {
        return new DmHuyenTmp().maHuyen(2L).tenHuyen("tenHuyen2");
    }

    public static DmHuyenTmp getDmHuyenTmpRandomSampleGenerator() {
        return new DmHuyenTmp().maHuyen(longCount.incrementAndGet()).tenHuyen(UUID.randomUUID().toString());
    }
}
