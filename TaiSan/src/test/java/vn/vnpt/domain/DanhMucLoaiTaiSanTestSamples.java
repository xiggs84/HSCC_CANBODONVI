package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DanhMucLoaiTaiSanTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DanhMucLoaiTaiSan getDanhMucLoaiTaiSanSample1() {
        return new DanhMucLoaiTaiSan().idLoaiTs(1L).dienGiai("dienGiai1").trangThai(1L);
    }

    public static DanhMucLoaiTaiSan getDanhMucLoaiTaiSanSample2() {
        return new DanhMucLoaiTaiSan().idLoaiTs(2L).dienGiai("dienGiai2").trangThai(2L);
    }

    public static DanhMucLoaiTaiSan getDanhMucLoaiTaiSanRandomSampleGenerator() {
        return new DanhMucLoaiTaiSan()
            .idLoaiTs(longCount.incrementAndGet())
            .dienGiai(UUID.randomUUID().toString())
            .trangThai(longCount.incrementAndGet());
    }
}
