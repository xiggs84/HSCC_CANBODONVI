package vn.vnpt.domain;

import java.util.UUID;

public class PhanLoaiHopDongTestSamples {

    public static PhanLoaiHopDong getPhanLoaiHopDongSample1() {
        return new PhanLoaiHopDong().id("id1").dienGiai("dienGiai1").idNhomHopDong("idNhomHopDong1");
    }

    public static PhanLoaiHopDong getPhanLoaiHopDongSample2() {
        return new PhanLoaiHopDong().id("id2").dienGiai("dienGiai2").idNhomHopDong("idNhomHopDong2");
    }

    public static PhanLoaiHopDong getPhanLoaiHopDongRandomSampleGenerator() {
        return new PhanLoaiHopDong()
            .id(UUID.randomUUID().toString())
            .dienGiai(UUID.randomUUID().toString())
            .idNhomHopDong(UUID.randomUUID().toString());
    }
}
