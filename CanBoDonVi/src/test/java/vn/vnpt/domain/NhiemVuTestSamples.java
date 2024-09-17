package vn.vnpt.domain;

import java.util.UUID;

public class NhiemVuTestSamples {

    public static NhiemVu getNhiemVuSample1() {
        return new NhiemVu().idNhiemVu("idNhiemVu1").tenNhiemVu("tenNhiemVu1");
    }

    public static NhiemVu getNhiemVuSample2() {
        return new NhiemVu().idNhiemVu("idNhiemVu2").tenNhiemVu("tenNhiemVu2");
    }

    public static NhiemVu getNhiemVuRandomSampleGenerator() {
        return new NhiemVu().idNhiemVu(UUID.randomUUID().toString()).tenNhiemVu(UUID.randomUUID().toString());
    }
}
