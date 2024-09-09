package vn.vnpt.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class QuanHeMasterTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static QuanHeMaster getQuanHeMasterSample1() {
        return new QuanHeMaster().id(1L).idDuongSu(1L).idDuongSuQh(1L);
    }

    public static QuanHeMaster getQuanHeMasterSample2() {
        return new QuanHeMaster().id(2L).idDuongSu(2L).idDuongSuQh(2L);
    }

    public static QuanHeMaster getQuanHeMasterRandomSampleGenerator() {
        return new QuanHeMaster()
            .id(longCount.incrementAndGet())
            .idDuongSu(longCount.incrementAndGet())
            .idDuongSuQh(longCount.incrementAndGet());
    }
}
