package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CauHinhThongTinDuongSuTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static CauHinhThongTinDuongSu getCauHinhThongTinDuongSuSample1() {
        return new CauHinhThongTinDuongSu()
            .idCauHinh(1L)
            .noiDung("noiDung1")
            .javascript("javascript1")
            .css("css1")
            .idLoaiDs(1L)
            .idDonVi(1L)
            .trangThai(1);
    }

    public static CauHinhThongTinDuongSu getCauHinhThongTinDuongSuSample2() {
        return new CauHinhThongTinDuongSu()
            .idCauHinh(2L)
            .noiDung("noiDung2")
            .javascript("javascript2")
            .css("css2")
            .idLoaiDs(2L)
            .idDonVi(2L)
            .trangThai(2);
    }

    public static CauHinhThongTinDuongSu getCauHinhThongTinDuongSuRandomSampleGenerator() {
        return new CauHinhThongTinDuongSu()
            .idCauHinh(longCount.incrementAndGet())
            .noiDung(UUID.randomUUID().toString())
            .javascript(UUID.randomUUID().toString())
            .css(UUID.randomUUID().toString())
            .idLoaiDs(longCount.incrementAndGet())
            .idDonVi(longCount.incrementAndGet())
            .trangThai(intCount.incrementAndGet());
    }
}
