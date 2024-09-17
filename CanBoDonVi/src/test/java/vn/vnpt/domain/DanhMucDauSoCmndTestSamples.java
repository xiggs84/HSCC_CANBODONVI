package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DanhMucDauSoCmndTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DanhMucDauSoCmnd getDanhMucDauSoCmndSample1() {
        return new DanhMucDauSoCmnd().idDauSo(1L).dauSo("dauSo1").tinhThanh("tinhThanh1").idLoai(1L);
    }

    public static DanhMucDauSoCmnd getDanhMucDauSoCmndSample2() {
        return new DanhMucDauSoCmnd().idDauSo(2L).dauSo("dauSo2").tinhThanh("tinhThanh2").idLoai(2L);
    }

    public static DanhMucDauSoCmnd getDanhMucDauSoCmndRandomSampleGenerator() {
        return new DanhMucDauSoCmnd()
            .idDauSo(longCount.incrementAndGet())
            .dauSo(UUID.randomUUID().toString())
            .tinhThanh(UUID.randomUUID().toString())
            .idLoai(longCount.incrementAndGet());
    }
}
