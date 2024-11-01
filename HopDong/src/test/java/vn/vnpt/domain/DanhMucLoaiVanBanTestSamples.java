package vn.vnpt.domain;

import java.util.UUID;

public class DanhMucLoaiVanBanTestSamples {

    public static DanhMucLoaiVanBan getDanhMucLoaiVanBanSample1() {
        return new DanhMucLoaiVanBan().id("id1").dienGiai("dienGiai1").idLoaiHopDong("idLoaiHopDong1");
    }

    public static DanhMucLoaiVanBan getDanhMucLoaiVanBanSample2() {
        return new DanhMucLoaiVanBan().id("id2").dienGiai("dienGiai2").idLoaiHopDong("idLoaiHopDong2");
    }

    public static DanhMucLoaiVanBan getDanhMucLoaiVanBanRandomSampleGenerator() {
        return new DanhMucLoaiVanBan()
            .id(UUID.randomUUID().toString())
            .dienGiai(UUID.randomUUID().toString())
            .idLoaiHopDong(UUID.randomUUID().toString());
    }
}
