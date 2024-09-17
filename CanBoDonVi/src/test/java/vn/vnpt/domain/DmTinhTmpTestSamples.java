package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DmTinhTmpTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DmTinhTmp getDmTinhTmpSample1() {
        return new DmTinhTmp().maTinh(1L).tenTinh("tenTinh1");
    }

    public static DmTinhTmp getDmTinhTmpSample2() {
        return new DmTinhTmp().maTinh(2L).tenTinh("tenTinh2");
    }

    public static DmTinhTmp getDmTinhTmpRandomSampleGenerator() {
        return new DmTinhTmp().maTinh(longCount.incrementAndGet()).tenTinh(UUID.randomUUID().toString());
    }
}
