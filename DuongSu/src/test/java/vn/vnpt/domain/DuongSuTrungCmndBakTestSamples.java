package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DuongSuTrungCmndBakTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static DuongSuTrungCmndBak getDuongSuTrungCmndBakSample1() {
        return new DuongSuTrungCmndBak()
            .id(1L)
            .tenDuongSu("tenDuongSu1")
            .diaChi("diaChi1")
            .trangThai(1)
            .thongTinDs("thongTinDs1")
            .nguoiThaoTac(1L)
            .idDsGoc(1L)
            .idMaster("idMaster1")
            .idDonVi(1L)
            .strSearch("strSearch1")
            .soGiayTo("soGiayTo1");
    }

    public static DuongSuTrungCmndBak getDuongSuTrungCmndBakSample2() {
        return new DuongSuTrungCmndBak()
            .id(2L)
            .tenDuongSu("tenDuongSu2")
            .diaChi("diaChi2")
            .trangThai(2)
            .thongTinDs("thongTinDs2")
            .nguoiThaoTac(2L)
            .idDsGoc(2L)
            .idMaster("idMaster2")
            .idDonVi(2L)
            .strSearch("strSearch2")
            .soGiayTo("soGiayTo2");
    }

    public static DuongSuTrungCmndBak getDuongSuTrungCmndBakRandomSampleGenerator() {
        return new DuongSuTrungCmndBak()
            .id(longCount.incrementAndGet())
            .tenDuongSu(UUID.randomUUID().toString())
            .diaChi(UUID.randomUUID().toString())
            .trangThai(intCount.incrementAndGet())
            .thongTinDs(UUID.randomUUID().toString())
            .nguoiThaoTac(longCount.incrementAndGet())
            .idDsGoc(longCount.incrementAndGet())
            .idMaster(UUID.randomUUID().toString())
            .idDonVi(longCount.incrementAndGet())
            .strSearch(UUID.randomUUID().toString())
            .soGiayTo(UUID.randomUUID().toString());
    }
}
