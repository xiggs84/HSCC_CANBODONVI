package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class LoaiChungThucTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static LoaiChungThuc getLoaiChungThucSample1() {
        return new LoaiChungThuc()
            .id("id1")
            .dienGiai("dienGiai1")
            .khungGia(1L)
            .hasBenB(1L)
            .hasTaiSan(1L)
            .trangThai(1L)
            .fileChungThuc("fileChungThuc1")
            .nguoiThaoTac(1L)
            .idDonVi(1L)
            .idLoaiSo("idLoaiSo1");
    }

    public static LoaiChungThuc getLoaiChungThucSample2() {
        return new LoaiChungThuc()
            .id("id2")
            .dienGiai("dienGiai2")
            .khungGia(2L)
            .hasBenB(2L)
            .hasTaiSan(2L)
            .trangThai(2L)
            .fileChungThuc("fileChungThuc2")
            .nguoiThaoTac(2L)
            .idDonVi(2L)
            .idLoaiSo("idLoaiSo2");
    }

    public static LoaiChungThuc getLoaiChungThucRandomSampleGenerator() {
        return new LoaiChungThuc()
            .id(UUID.randomUUID().toString())
            .dienGiai(UUID.randomUUID().toString())
            .khungGia(longCount.incrementAndGet())
            .hasBenB(longCount.incrementAndGet())
            .hasTaiSan(longCount.incrementAndGet())
            .trangThai(longCount.incrementAndGet())
            .fileChungThuc(UUID.randomUUID().toString())
            .nguoiThaoTac(longCount.incrementAndGet())
            .idDonVi(longCount.incrementAndGet())
            .idLoaiSo(UUID.randomUUID().toString());
    }
}
