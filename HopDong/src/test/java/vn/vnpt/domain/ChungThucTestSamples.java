package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ChungThucTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ChungThuc getChungThucSample1() {
        return new ChungThuc()
            .id("id1")
            .idDonVi(1L)
            .nguoiYeuCau("nguoiYeuCau1")
            .nguoiChungThuc(1L)
            .nguoiThaoTac(1L)
            .soTienThu(1L)
            .soBanSao(1L)
            .vanBan("vanBan1")
            .trangThai(1L)
            .quyenSo(1L)
            .duongSu("duongSu1")
            .taiSan("taiSan1")
            .strSearch("strSearch1")
            .srcChungThuc("srcChungThuc1")
            .thongTinChungThuc("thongTinChungThuc1")
            .chuKyNgoaiTruSo(1L)
            .idCtGoc(1L)
            .ngayText("ngayText1")
            .chucDanhCanBo("chucDanhCanBo1")
            .ldPheDuyet(1L)
            .chucDanhLd("chucDanhLd1");
    }

    public static ChungThuc getChungThucSample2() {
        return new ChungThuc()
            .id("id2")
            .idDonVi(2L)
            .nguoiYeuCau("nguoiYeuCau2")
            .nguoiChungThuc(2L)
            .nguoiThaoTac(2L)
            .soTienThu(2L)
            .soBanSao(2L)
            .vanBan("vanBan2")
            .trangThai(2L)
            .quyenSo(2L)
            .duongSu("duongSu2")
            .taiSan("taiSan2")
            .strSearch("strSearch2")
            .srcChungThuc("srcChungThuc2")
            .thongTinChungThuc("thongTinChungThuc2")
            .chuKyNgoaiTruSo(2L)
            .idCtGoc(2L)
            .ngayText("ngayText2")
            .chucDanhCanBo("chucDanhCanBo2")
            .ldPheDuyet(2L)
            .chucDanhLd("chucDanhLd2");
    }

    public static ChungThuc getChungThucRandomSampleGenerator() {
        return new ChungThuc()
            .id(UUID.randomUUID().toString())
            .idDonVi(longCount.incrementAndGet())
            .nguoiYeuCau(UUID.randomUUID().toString())
            .nguoiChungThuc(longCount.incrementAndGet())
            .nguoiThaoTac(longCount.incrementAndGet())
            .soTienThu(longCount.incrementAndGet())
            .soBanSao(longCount.incrementAndGet())
            .vanBan(UUID.randomUUID().toString())
            .trangThai(longCount.incrementAndGet())
            .quyenSo(longCount.incrementAndGet())
            .duongSu(UUID.randomUUID().toString())
            .taiSan(UUID.randomUUID().toString())
            .strSearch(UUID.randomUUID().toString())
            .srcChungThuc(UUID.randomUUID().toString())
            .thongTinChungThuc(UUID.randomUUID().toString())
            .chuKyNgoaiTruSo(longCount.incrementAndGet())
            .idCtGoc(longCount.incrementAndGet())
            .ngayText(UUID.randomUUID().toString())
            .chucDanhCanBo(UUID.randomUUID().toString())
            .ldPheDuyet(longCount.incrementAndGet())
            .chucDanhLd(UUID.randomUUID().toString());
    }
}
