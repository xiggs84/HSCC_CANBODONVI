package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DanhSachChungThucTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DanhSachChungThuc getDanhSachChungThucSample1() {
        return new DanhSachChungThuc()
            .id("id1")
            .idDonVi(1L)
            .nguoiChungThuc(1L)
            .nguoiThaoTac(1L)
            .trangThai(1L)
            .quyenSo(1L)
            .srcChungThuc("srcChungThuc1")
            .chuKyNgoaiTruSo(1L)
            .ngayText("ngayText1")
            .strSearch("strSearch1")
            .soTienThu(1L)
            .ldPheDuyet(1L);
    }

    public static DanhSachChungThuc getDanhSachChungThucSample2() {
        return new DanhSachChungThuc()
            .id("id2")
            .idDonVi(2L)
            .nguoiChungThuc(2L)
            .nguoiThaoTac(2L)
            .trangThai(2L)
            .quyenSo(2L)
            .srcChungThuc("srcChungThuc2")
            .chuKyNgoaiTruSo(2L)
            .ngayText("ngayText2")
            .strSearch("strSearch2")
            .soTienThu(2L)
            .ldPheDuyet(2L);
    }

    public static DanhSachChungThuc getDanhSachChungThucRandomSampleGenerator() {
        return new DanhSachChungThuc()
            .id(UUID.randomUUID().toString())
            .idDonVi(longCount.incrementAndGet())
            .nguoiChungThuc(longCount.incrementAndGet())
            .nguoiThaoTac(longCount.incrementAndGet())
            .trangThai(longCount.incrementAndGet())
            .quyenSo(longCount.incrementAndGet())
            .srcChungThuc(UUID.randomUUID().toString())
            .chuKyNgoaiTruSo(longCount.incrementAndGet())
            .ngayText(UUID.randomUUID().toString())
            .strSearch(UUID.randomUUID().toString())
            .soTienThu(longCount.incrementAndGet())
            .ldPheDuyet(longCount.incrementAndGet());
    }
}
