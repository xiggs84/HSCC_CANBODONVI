package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DuongSuTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static DuongSu getDuongSuSample1() {
        return new DuongSu()
            .idDuongSu(1L)
            .tenDuongSu("tenDuongSu1")
            .diaChi("diaChi1")
            .soDienThoai("soDienThoai1")
            .email("email1")
            .fax("fax1")
            .website("website1")
            .trangThai(1)
            .nguoiThaoTac(1L)
            .idDsGoc(1L)
            .idMaster("idMaster1")
            .idDonVi(1L)
            .strSearch("strSearch1")
            .soGiayTo("soGiayTo1")
            .ghiChu("ghiChu1")
            .idLoaiNganChan(1L)
            .syncStatus(1);
    }

    public static DuongSu getDuongSuSample2() {
        return new DuongSu()
            .idDuongSu(2L)
            .tenDuongSu("tenDuongSu2")
            .diaChi("diaChi2")
            .soDienThoai("soDienThoai2")
            .email("email2")
            .fax("fax2")
            .website("website2")
            .trangThai(2)
            .nguoiThaoTac(2L)
            .idDsGoc(2L)
            .idMaster("idMaster2")
            .idDonVi(2L)
            .strSearch("strSearch2")
            .soGiayTo("soGiayTo2")
            .ghiChu("ghiChu2")
            .idLoaiNganChan(2L)
            .syncStatus(2);
    }

    public static DuongSu getDuongSuRandomSampleGenerator() {
        return new DuongSu()
            .idDuongSu(longCount.incrementAndGet())
            .tenDuongSu(UUID.randomUUID().toString())
            .diaChi(UUID.randomUUID().toString())
            .soDienThoai(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString())
            .fax(UUID.randomUUID().toString())
            .website(UUID.randomUUID().toString())
            .trangThai(intCount.incrementAndGet())
            .nguoiThaoTac(longCount.incrementAndGet())
            .idDsGoc(longCount.incrementAndGet())
            .idMaster(UUID.randomUUID().toString())
            .idDonVi(longCount.incrementAndGet())
            .strSearch(UUID.randomUUID().toString())
            .soGiayTo(UUID.randomUUID().toString())
            .ghiChu(UUID.randomUUID().toString())
            .idLoaiNganChan(longCount.incrementAndGet())
            .syncStatus(intCount.incrementAndGet());
    }
}
