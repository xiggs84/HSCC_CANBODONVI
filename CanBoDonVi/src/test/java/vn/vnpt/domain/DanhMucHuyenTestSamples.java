package vn.vnpt.domain;

import java.util.UUID;

public class DanhMucHuyenTestSamples {

    public static DanhMucHuyen getDanhMucHuyenSample1() {
        return new DanhMucHuyen().maHuyen("maHuyen1").tenHuyen("tenHuyen1").maTinh("maTinh1");
    }

    public static DanhMucHuyen getDanhMucHuyenSample2() {
        return new DanhMucHuyen().maHuyen("maHuyen2").tenHuyen("tenHuyen2").maTinh("maTinh2");
    }

    public static DanhMucHuyen getDanhMucHuyenRandomSampleGenerator() {
        return new DanhMucHuyen()
            .maHuyen(UUID.randomUUID().toString())
            .tenHuyen(UUID.randomUUID().toString())
            .maTinh(UUID.randomUUID().toString());
    }
}
