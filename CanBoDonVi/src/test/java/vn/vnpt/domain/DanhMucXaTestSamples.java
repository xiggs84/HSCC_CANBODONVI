package vn.vnpt.domain;

import java.util.UUID;

public class DanhMucXaTestSamples {

    public static DanhMucXa getDanhMucXaSample1() {
        return new DanhMucXa().maXa("maXa1").tenXa("tenXa1").maHuyen("maHuyen1");
    }

    public static DanhMucXa getDanhMucXaSample2() {
        return new DanhMucXa().maXa("maXa2").tenXa("tenXa2").maHuyen("maHuyen2");
    }

    public static DanhMucXa getDanhMucXaRandomSampleGenerator() {
        return new DanhMucXa().maXa(UUID.randomUUID().toString()).tenXa(UUID.randomUUID().toString()).maHuyen(UUID.randomUUID().toString());
    }
}
