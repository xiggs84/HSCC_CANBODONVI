package vn.vnpt.domain;

import java.util.UUID;

public class DanhMucTinhTestSamples {

    public static DanhMucTinh getDanhMucTinhSample1() {
        return new DanhMucTinh().maTinh("maTinh1").tenTinh("tenTinh1");
    }

    public static DanhMucTinh getDanhMucTinhSample2() {
        return new DanhMucTinh().maTinh("maTinh2").tenTinh("tenTinh2");
    }

    public static DanhMucTinh getDanhMucTinhRandomSampleGenerator() {
        return new DanhMucTinh().maTinh(UUID.randomUUID().toString()).tenTinh(UUID.randomUUID().toString());
    }
}
