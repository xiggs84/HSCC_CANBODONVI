package vn.vnpt.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TaiSanDuongSuTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static TaiSanDuongSu getTaiSanDuongSuSample1() {
        return new TaiSanDuongSu().id(1L).idTaiSan(1L).trangThai(1).idHopDong(1L).idLoaiHopDong(1L).idChungThuc(1L);
    }

    public static TaiSanDuongSu getTaiSanDuongSuSample2() {
        return new TaiSanDuongSu().id(2L).idTaiSan(2L).trangThai(2).idHopDong(2L).idLoaiHopDong(2L).idChungThuc(2L);
    }

    public static TaiSanDuongSu getTaiSanDuongSuRandomSampleGenerator() {
        return new TaiSanDuongSu()
            .id(longCount.incrementAndGet())
            .idTaiSan(longCount.incrementAndGet())
            .trangThai(intCount.incrementAndGet())
            .idHopDong(longCount.incrementAndGet())
            .idLoaiHopDong(longCount.incrementAndGet())
            .idChungThuc(longCount.incrementAndGet());
    }
}
