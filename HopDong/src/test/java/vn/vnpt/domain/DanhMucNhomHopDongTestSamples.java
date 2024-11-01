package vn.vnpt.domain;

import java.util.UUID;

public class DanhMucNhomHopDongTestSamples {

    public static DanhMucNhomHopDong getDanhMucNhomHopDongSample1() {
        return new DanhMucNhomHopDong().id("id1").dienGiai("dienGiai1");
    }

    public static DanhMucNhomHopDong getDanhMucNhomHopDongSample2() {
        return new DanhMucNhomHopDong().id("id2").dienGiai("dienGiai2");
    }

    public static DanhMucNhomHopDong getDanhMucNhomHopDongRandomSampleGenerator() {
        return new DanhMucNhomHopDong().id(UUID.randomUUID().toString()).dienGiai(UUID.randomUUID().toString());
    }
}
