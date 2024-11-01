package vn.vnpt.domain;

import java.util.UUID;

public class DanhMucVaiTroTestSamples {

    public static DanhMucVaiTro getDanhMucVaiTroSample1() {
        return new DanhMucVaiTro().id("id1").dienGiai("dienGiai1").idLoaiHopDong("idLoaiHopDong1").idLoaiVaiTro("idLoaiVaiTro1");
    }

    public static DanhMucVaiTro getDanhMucVaiTroSample2() {
        return new DanhMucVaiTro().id("id2").dienGiai("dienGiai2").idLoaiHopDong("idLoaiHopDong2").idLoaiVaiTro("idLoaiVaiTro2");
    }

    public static DanhMucVaiTro getDanhMucVaiTroRandomSampleGenerator() {
        return new DanhMucVaiTro()
            .id(UUID.randomUUID().toString())
            .dienGiai(UUID.randomUUID().toString())
            .idLoaiHopDong(UUID.randomUUID().toString())
            .idLoaiVaiTro(UUID.randomUUID().toString());
    }
}
