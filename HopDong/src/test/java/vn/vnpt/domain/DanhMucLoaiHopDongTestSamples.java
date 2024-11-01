package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DanhMucLoaiHopDongTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DanhMucLoaiHopDong getDanhMucLoaiHopDongSample1() {
        return new DanhMucLoaiHopDong()
            .id("id1")
            .dienGiai("dienGiai1")
            .idVaiTro1("idVaiTro11")
            .idVaiTro2("idVaiTro21")
            .fileHopDong("fileHopDong1")
            .dieuKhoan("dieuKhoan1")
            .idDonVi(1L)
            .trangThai(1L)
            .nguoiThaoTac(1L)
            .idNhomHopDong("idNhomHopDong1")
            .fileLoiChung("fileLoiChung1")
            .chuyenTaiSan(1L)
            .loaiSuaDoi(1L)
            .loaiHuyBo(1L)
            .trangThaiDuyet(1L)
            .idPhanLoaiHopDong("idPhanLoaiHopDong1")
            .srcCv("srcCv1")
            .srcTb("srcTb1")
            .srcTtpc("srcTtpc1")
            .dgTen("dgTen1")
            .nhomTen("nhomTen1")
            .idVaiTro3("idVaiTro31");
    }

    public static DanhMucLoaiHopDong getDanhMucLoaiHopDongSample2() {
        return new DanhMucLoaiHopDong()
            .id("id2")
            .dienGiai("dienGiai2")
            .idVaiTro1("idVaiTro12")
            .idVaiTro2("idVaiTro22")
            .fileHopDong("fileHopDong2")
            .dieuKhoan("dieuKhoan2")
            .idDonVi(2L)
            .trangThai(2L)
            .nguoiThaoTac(2L)
            .idNhomHopDong("idNhomHopDong2")
            .fileLoiChung("fileLoiChung2")
            .chuyenTaiSan(2L)
            .loaiSuaDoi(2L)
            .loaiHuyBo(2L)
            .trangThaiDuyet(2L)
            .idPhanLoaiHopDong("idPhanLoaiHopDong2")
            .srcCv("srcCv2")
            .srcTb("srcTb2")
            .srcTtpc("srcTtpc2")
            .dgTen("dgTen2")
            .nhomTen("nhomTen2")
            .idVaiTro3("idVaiTro32");
    }

    public static DanhMucLoaiHopDong getDanhMucLoaiHopDongRandomSampleGenerator() {
        return new DanhMucLoaiHopDong()
            .id(UUID.randomUUID().toString())
            .dienGiai(UUID.randomUUID().toString())
            .idVaiTro1(UUID.randomUUID().toString())
            .idVaiTro2(UUID.randomUUID().toString())
            .fileHopDong(UUID.randomUUID().toString())
            .dieuKhoan(UUID.randomUUID().toString())
            .idDonVi(longCount.incrementAndGet())
            .trangThai(longCount.incrementAndGet())
            .nguoiThaoTac(longCount.incrementAndGet())
            .idNhomHopDong(UUID.randomUUID().toString())
            .fileLoiChung(UUID.randomUUID().toString())
            .chuyenTaiSan(longCount.incrementAndGet())
            .loaiSuaDoi(longCount.incrementAndGet())
            .loaiHuyBo(longCount.incrementAndGet())
            .trangThaiDuyet(longCount.incrementAndGet())
            .idPhanLoaiHopDong(UUID.randomUUID().toString())
            .srcCv(UUID.randomUUID().toString())
            .srcTb(UUID.randomUUID().toString())
            .srcTtpc(UUID.randomUUID().toString())
            .dgTen(UUID.randomUUID().toString())
            .nhomTen(UUID.randomUUID().toString())
            .idVaiTro3(UUID.randomUUID().toString());
    }
}
