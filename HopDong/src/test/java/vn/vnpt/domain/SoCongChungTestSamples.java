package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class SoCongChungTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static SoCongChung getSoCongChungSample1() {
        return new SoCongChung().id("id1").idDonVi(1L).tenSo("tenSo1").giaTri(1L).nguoiThaoTac(1L).trangThai(1L).idLoaiSo("idLoaiSo1");
    }

    public static SoCongChung getSoCongChungSample2() {
        return new SoCongChung().id("id2").idDonVi(2L).tenSo("tenSo2").giaTri(2L).nguoiThaoTac(2L).trangThai(2L).idLoaiSo("idLoaiSo2");
    }

    public static SoCongChung getSoCongChungRandomSampleGenerator() {
        return new SoCongChung()
            .id(UUID.randomUUID().toString())
            .idDonVi(longCount.incrementAndGet())
            .tenSo(UUID.randomUUID().toString())
            .giaTri(longCount.incrementAndGet())
            .nguoiThaoTac(longCount.incrementAndGet())
            .trangThai(longCount.incrementAndGet())
            .idLoaiSo(UUID.randomUUID().toString());
    }
}
