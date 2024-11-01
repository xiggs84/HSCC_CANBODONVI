package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DanhMucTuVietTatTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DanhMucTuVietTat getDanhMucTuVietTatSample1() {
        return new DanhMucTuVietTat().id("id1").tuVietTat("tuVietTat1").dienGiai("dienGiai1").idDonVi(1L).nguoiThaoTac(1L).trangThai(1L);
    }

    public static DanhMucTuVietTat getDanhMucTuVietTatSample2() {
        return new DanhMucTuVietTat().id("id2").tuVietTat("tuVietTat2").dienGiai("dienGiai2").idDonVi(2L).nguoiThaoTac(2L).trangThai(2L);
    }

    public static DanhMucTuVietTat getDanhMucTuVietTatRandomSampleGenerator() {
        return new DanhMucTuVietTat()
            .id(UUID.randomUUID().toString())
            .tuVietTat(UUID.randomUUID().toString())
            .dienGiai(UUID.randomUUID().toString())
            .idDonVi(longCount.incrementAndGet())
            .nguoiThaoTac(longCount.incrementAndGet())
            .trangThai(longCount.incrementAndGet());
    }
}
