package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DmDuongSuTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static DmDuongSu getDmDuongSuSample1() {
        return new DmDuongSu()
            .idDuongSu(1L)
            .tenDuongSu("tenDuongSu1")
            .diaChi("diaChi1")
            .trangThai(1)
            .thongTinDs("thongTinDs1")
            .nguoiThaoTac(1L)
            .idDsGoc(1L)
            .idMaster("idMaster1")
            .idDonVi(1L)
            .strSearch("strSearch1")
            .soGiayTo("soGiayTo1")
            .idLoaiNganChan(1L);
    }

    public static DmDuongSu getDmDuongSuSample2() {
        return new DmDuongSu()
            .idDuongSu(2L)
            .tenDuongSu("tenDuongSu2")
            .diaChi("diaChi2")
            .trangThai(2)
            .thongTinDs("thongTinDs2")
            .nguoiThaoTac(2L)
            .idDsGoc(2L)
            .idMaster("idMaster2")
            .idDonVi(2L)
            .strSearch("strSearch2")
            .soGiayTo("soGiayTo2")
            .idLoaiNganChan(2L);
    }

    public static DmDuongSu getDmDuongSuRandomSampleGenerator() {
        return new DmDuongSu()
            .idDuongSu(longCount.incrementAndGet())
            .tenDuongSu(UUID.randomUUID().toString())
            .diaChi(UUID.randomUUID().toString())
            .trangThai(intCount.incrementAndGet())
            .thongTinDs(UUID.randomUUID().toString())
            .nguoiThaoTac(longCount.incrementAndGet())
            .idDsGoc(longCount.incrementAndGet())
            .idMaster(UUID.randomUUID().toString())
            .idDonVi(longCount.incrementAndGet())
            .strSearch(UUID.randomUUID().toString())
            .soGiayTo(UUID.randomUUID().toString())
            .idLoaiNganChan(longCount.incrementAndGet());
    }
}
