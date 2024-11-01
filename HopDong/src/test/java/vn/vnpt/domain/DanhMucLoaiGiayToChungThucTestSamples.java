package vn.vnpt.domain;

import java.util.UUID;

public class DanhMucLoaiGiayToChungThucTestSamples {

    public static DanhMucLoaiGiayToChungThuc getDanhMucLoaiGiayToChungThucSample1() {
        return new DanhMucLoaiGiayToChungThuc().id("id1").dienGiai("dienGiai1").idLoaiSo("idLoaiSo1");
    }

    public static DanhMucLoaiGiayToChungThuc getDanhMucLoaiGiayToChungThucSample2() {
        return new DanhMucLoaiGiayToChungThuc().id("id2").dienGiai("dienGiai2").idLoaiSo("idLoaiSo2");
    }

    public static DanhMucLoaiGiayToChungThuc getDanhMucLoaiGiayToChungThucRandomSampleGenerator() {
        return new DanhMucLoaiGiayToChungThuc()
            .id(UUID.randomUUID().toString())
            .dienGiai(UUID.randomUUID().toString())
            .idLoaiSo(UUID.randomUUID().toString());
    }
}
