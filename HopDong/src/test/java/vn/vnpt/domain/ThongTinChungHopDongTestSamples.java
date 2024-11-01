package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ThongTinChungHopDongTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ThongTinChungHopDong getThongTinChungHopDongSample1() {
        return new ThongTinChungHopDong()
            .id("id1")
            .nguoiLapHd(1L)
            .thongTinVanBan("thongTinVanBan1")
            .trangThai(1L)
            .idLoaiHd("idLoaiHd1")
            .dieuKhoanHd("dieuKhoanHd1")
            .idDonVi(1L)
            .nguoiThaoTac(1L)
            .idHdGoc("idHdGoc1")
            .maHopDong("maHopDong1")
            .srcHopDong("srcHopDong1")
            .idSoCongChung("idSoCongChung1")
            .soCongChung(1L)
            .congChungVien(1L)
            .nguoiRutTrich(1L)
            .soTienRutTrich(1L)
            .hdThuCong(1L)
            .trangThaiRutTrich(1L)
            .chuKyNgoaiTruSo(1L)
            .strSearch("strSearch1")
            .idMaster(1L)
            .idHdSdHb(1L)
            .srcDmMaster("srcDmMaster1")
            .repRefUnique(1L)
            .ngayText("ngayText1")
            .thongTinChung("thongTinChung1")
            .thongTinChungClob("thongTinChungClob1");
    }

    public static ThongTinChungHopDong getThongTinChungHopDongSample2() {
        return new ThongTinChungHopDong()
            .id("id2")
            .nguoiLapHd(2L)
            .thongTinVanBan("thongTinVanBan2")
            .trangThai(2L)
            .idLoaiHd("idLoaiHd2")
            .dieuKhoanHd("dieuKhoanHd2")
            .idDonVi(2L)
            .nguoiThaoTac(2L)
            .idHdGoc("idHdGoc2")
            .maHopDong("maHopDong2")
            .srcHopDong("srcHopDong2")
            .idSoCongChung("idSoCongChung2")
            .soCongChung(2L)
            .congChungVien(2L)
            .nguoiRutTrich(2L)
            .soTienRutTrich(2L)
            .hdThuCong(2L)
            .trangThaiRutTrich(2L)
            .chuKyNgoaiTruSo(2L)
            .strSearch("strSearch2")
            .idMaster(2L)
            .idHdSdHb(2L)
            .srcDmMaster("srcDmMaster2")
            .repRefUnique(2L)
            .ngayText("ngayText2")
            .thongTinChung("thongTinChung2")
            .thongTinChungClob("thongTinChungClob2");
    }

    public static ThongTinChungHopDong getThongTinChungHopDongRandomSampleGenerator() {
        return new ThongTinChungHopDong()
            .id(UUID.randomUUID().toString())
            .nguoiLapHd(longCount.incrementAndGet())
            .thongTinVanBan(UUID.randomUUID().toString())
            .trangThai(longCount.incrementAndGet())
            .idLoaiHd(UUID.randomUUID().toString())
            .dieuKhoanHd(UUID.randomUUID().toString())
            .idDonVi(longCount.incrementAndGet())
            .nguoiThaoTac(longCount.incrementAndGet())
            .idHdGoc(UUID.randomUUID().toString())
            .maHopDong(UUID.randomUUID().toString())
            .srcHopDong(UUID.randomUUID().toString())
            .idSoCongChung(UUID.randomUUID().toString())
            .soCongChung(longCount.incrementAndGet())
            .congChungVien(longCount.incrementAndGet())
            .nguoiRutTrich(longCount.incrementAndGet())
            .soTienRutTrich(longCount.incrementAndGet())
            .hdThuCong(longCount.incrementAndGet())
            .trangThaiRutTrich(longCount.incrementAndGet())
            .chuKyNgoaiTruSo(longCount.incrementAndGet())
            .strSearch(UUID.randomUUID().toString())
            .idMaster(longCount.incrementAndGet())
            .idHdSdHb(longCount.incrementAndGet())
            .srcDmMaster(UUID.randomUUID().toString())
            .repRefUnique(longCount.incrementAndGet())
            .ngayText(UUID.randomUUID().toString())
            .thongTinChung(UUID.randomUUID().toString())
            .thongTinChungClob(UUID.randomUUID().toString());
    }
}
