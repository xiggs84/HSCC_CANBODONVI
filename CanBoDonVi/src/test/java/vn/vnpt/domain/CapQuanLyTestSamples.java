package vn.vnpt.domain;

import java.util.UUID;

public class CapQuanLyTestSamples {

    public static CapQuanLy getCapQuanLySample1() {
        return new CapQuanLy().idCapQl("idCapQl1").tenCapQl("tenCapQl1");
    }

    public static CapQuanLy getCapQuanLySample2() {
        return new CapQuanLy().idCapQl("idCapQl2").tenCapQl("tenCapQl2");
    }

    public static CapQuanLy getCapQuanLyRandomSampleGenerator() {
        return new CapQuanLy().idCapQl(UUID.randomUUID().toString()).tenCapQl(UUID.randomUUID().toString());
    }
}
