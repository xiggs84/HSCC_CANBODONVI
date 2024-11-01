package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DanhMucLoaiSoCongChungTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DanhMucLoaiSoCongChung getDanhMucLoaiSoCongChungSample1() {
        return new DanhMucLoaiSoCongChung().id("id1").tenLoai("tenLoai1").trangThai(1L);
    }

    public static DanhMucLoaiSoCongChung getDanhMucLoaiSoCongChungSample2() {
        return new DanhMucLoaiSoCongChung().id("id2").tenLoai("tenLoai2").trangThai(2L);
    }

    public static DanhMucLoaiSoCongChung getDanhMucLoaiSoCongChungRandomSampleGenerator() {
        return new DanhMucLoaiSoCongChung()
            .id(UUID.randomUUID().toString())
            .tenLoai(UUID.randomUUID().toString())
            .trangThai(longCount.incrementAndGet());
    }
}
