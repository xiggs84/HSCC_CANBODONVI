package vn.vnpt.domain;

import java.util.UUID;

public class LoaiDonViTestSamples {

    public static LoaiDonVi getLoaiDonViSample1() {
        return new LoaiDonVi().idLoaiDv("idLoaiDv1").tenLoaiDv("tenLoaiDv1");
    }

    public static LoaiDonVi getLoaiDonViSample2() {
        return new LoaiDonVi().idLoaiDv("idLoaiDv2").tenLoaiDv("tenLoaiDv2");
    }

    public static LoaiDonVi getLoaiDonViRandomSampleGenerator() {
        return new LoaiDonVi().idLoaiDv(UUID.randomUUID().toString()).tenLoaiDv(UUID.randomUUID().toString());
    }
}
