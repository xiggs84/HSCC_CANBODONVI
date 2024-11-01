package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ThongTinCapNhatDuongSuTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ThongTinCapNhatDuongSu getThongTinCapNhatDuongSuSample1() {
        return new ThongTinCapNhatDuongSu().idCapNhat(1L).tenDuongSu("tenDuongSu1").soGiayTo("soGiayTo1");
    }

    public static ThongTinCapNhatDuongSu getThongTinCapNhatDuongSuSample2() {
        return new ThongTinCapNhatDuongSu().idCapNhat(2L).tenDuongSu("tenDuongSu2").soGiayTo("soGiayTo2");
    }

    public static ThongTinCapNhatDuongSu getThongTinCapNhatDuongSuRandomSampleGenerator() {
        return new ThongTinCapNhatDuongSu()
            .idCapNhat(longCount.incrementAndGet())
            .tenDuongSu(UUID.randomUUID().toString())
            .soGiayTo(UUID.randomUUID().toString());
    }
}
