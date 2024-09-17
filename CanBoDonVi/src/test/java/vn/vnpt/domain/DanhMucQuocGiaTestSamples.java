package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DanhMucQuocGiaTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DanhMucQuocGia getDanhMucQuocGiaSample1() {
        return new DanhMucQuocGia().idQuocGia(1L).tenQuocGia("tenQuocGia1").tenTiengAnh("tenTiengAnh1");
    }

    public static DanhMucQuocGia getDanhMucQuocGiaSample2() {
        return new DanhMucQuocGia().idQuocGia(2L).tenQuocGia("tenQuocGia2").tenTiengAnh("tenTiengAnh2");
    }

    public static DanhMucQuocGia getDanhMucQuocGiaRandomSampleGenerator() {
        return new DanhMucQuocGia()
            .idQuocGia(longCount.incrementAndGet())
            .tenQuocGia(UUID.randomUUID().toString())
            .tenTiengAnh(UUID.randomUUID().toString());
    }
}
