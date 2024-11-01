package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ChiTietNganChanTaiSanTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ChiTietNganChanTaiSan getChiTietNganChanTaiSanSample1() {
        return new ChiTietNganChanTaiSan()
            .idNganChan(1L)
            .soHsCv("soHsCv1")
            .soCc("soCc1")
            .soVaoSo("soVaoSo1")
            .moTa("moTa1")
            .trangThai(1L)
            .nguoiThaoTac(1L)
            .loaiNganChan(1L);
    }

    public static ChiTietNganChanTaiSan getChiTietNganChanTaiSanSample2() {
        return new ChiTietNganChanTaiSan()
            .idNganChan(2L)
            .soHsCv("soHsCv2")
            .soCc("soCc2")
            .soVaoSo("soVaoSo2")
            .moTa("moTa2")
            .trangThai(2L)
            .nguoiThaoTac(2L)
            .loaiNganChan(2L);
    }

    public static ChiTietNganChanTaiSan getChiTietNganChanTaiSanRandomSampleGenerator() {
        return new ChiTietNganChanTaiSan()
            .idNganChan(longCount.incrementAndGet())
            .soHsCv(UUID.randomUUID().toString())
            .soCc(UUID.randomUUID().toString())
            .soVaoSo(UUID.randomUUID().toString())
            .moTa(UUID.randomUUID().toString())
            .trangThai(longCount.incrementAndGet())
            .nguoiThaoTac(longCount.incrementAndGet())
            .loaiNganChan(longCount.incrementAndGet());
    }
}
