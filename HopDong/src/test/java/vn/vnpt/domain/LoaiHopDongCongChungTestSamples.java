package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class LoaiHopDongCongChungTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static LoaiHopDongCongChung getLoaiHopDongCongChungSample1() {
        return new LoaiHopDongCongChung().id("id1").dienGiai("dienGiai1").giaTri(1L).trangThai(1L);
    }

    public static LoaiHopDongCongChung getLoaiHopDongCongChungSample2() {
        return new LoaiHopDongCongChung().id("id2").dienGiai("dienGiai2").giaTri(2L).trangThai(2L);
    }

    public static LoaiHopDongCongChung getLoaiHopDongCongChungRandomSampleGenerator() {
        return new LoaiHopDongCongChung()
            .id(UUID.randomUUID().toString())
            .dienGiai(UUID.randomUUID().toString())
            .giaTri(longCount.incrementAndGet())
            .trangThai(longCount.incrementAndGet());
    }
}
