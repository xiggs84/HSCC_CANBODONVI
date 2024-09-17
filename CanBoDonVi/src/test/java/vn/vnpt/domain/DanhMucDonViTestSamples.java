package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DanhMucDonViTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static DanhMucDonVi getDanhMucDonViSample1() {
        return new DanhMucDonVi()
            .idDonVi(1L)
            .tenDonVi("tenDonVi1")
            .diaChi("diaChi1")
            .nguoiDaiDien("nguoiDaiDien1")
            .soDienThoai("soDienThoai1")
            .idDonViQl(1L)
            .trangThai(1L)
            .soNha("soNha1")
            .maSoThue("maSoThue1")
            .hoaDonDt(1)
            .maDonViIgate("maDonViIgate1")
            .maCoQuanIgate("maCoQuanIgate1")
            .kySo(1L)
            .qrScan(1L)
            .verifyIdCard(1L)
            .isVerifyFace(1L)
            .isElastic(1L)
            .apikeyCccd("apikeyCccd1")
            .apikeyFace("apikeyFace1")
            .verifyCodeCccd("verifyCodeCccd1")
            .usernameElastic("usernameElastic1")
            .passwordElastic("passwordElastic1")
            .idNhiemVu("idNhiemVu1")
            .idLoaiDv("idLoaiDv1")
            .idCapQl("idCapQl1");
    }

    public static DanhMucDonVi getDanhMucDonViSample2() {
        return new DanhMucDonVi()
            .idDonVi(2L)
            .tenDonVi("tenDonVi2")
            .diaChi("diaChi2")
            .nguoiDaiDien("nguoiDaiDien2")
            .soDienThoai("soDienThoai2")
            .idDonViQl(2L)
            .trangThai(2L)
            .soNha("soNha2")
            .maSoThue("maSoThue2")
            .hoaDonDt(2)
            .maDonViIgate("maDonViIgate2")
            .maCoQuanIgate("maCoQuanIgate2")
            .kySo(2L)
            .qrScan(2L)
            .verifyIdCard(2L)
            .isVerifyFace(2L)
            .isElastic(2L)
            .apikeyCccd("apikeyCccd2")
            .apikeyFace("apikeyFace2")
            .verifyCodeCccd("verifyCodeCccd2")
            .usernameElastic("usernameElastic2")
            .passwordElastic("passwordElastic2")
            .idNhiemVu("idNhiemVu2")
            .idLoaiDv("idLoaiDv2")
            .idCapQl("idCapQl2");
    }

    public static DanhMucDonVi getDanhMucDonViRandomSampleGenerator() {
        return new DanhMucDonVi()
            .idDonVi(longCount.incrementAndGet())
            .tenDonVi(UUID.randomUUID().toString())
            .diaChi(UUID.randomUUID().toString())
            .nguoiDaiDien(UUID.randomUUID().toString())
            .soDienThoai(UUID.randomUUID().toString())
            .idDonViQl(longCount.incrementAndGet())
            .trangThai(longCount.incrementAndGet())
            .soNha(UUID.randomUUID().toString())
            .maSoThue(UUID.randomUUID().toString())
            .hoaDonDt(intCount.incrementAndGet())
            .maDonViIgate(UUID.randomUUID().toString())
            .maCoQuanIgate(UUID.randomUUID().toString())
            .kySo(longCount.incrementAndGet())
            .qrScan(longCount.incrementAndGet())
            .verifyIdCard(longCount.incrementAndGet())
            .isVerifyFace(longCount.incrementAndGet())
            .isElastic(longCount.incrementAndGet())
            .apikeyCccd(UUID.randomUUID().toString())
            .apikeyFace(UUID.randomUUID().toString())
            .verifyCodeCccd(UUID.randomUUID().toString())
            .usernameElastic(UUID.randomUUID().toString())
            .passwordElastic(UUID.randomUUID().toString())
            .idNhiemVu(UUID.randomUUID().toString())
            .idLoaiDv(UUID.randomUUID().toString())
            .idCapQl(UUID.randomUUID().toString());
    }
}
