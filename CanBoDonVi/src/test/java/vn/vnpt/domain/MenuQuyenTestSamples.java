package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class MenuQuyenTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static MenuQuyen getMenuQuyenSample1() {
        return new MenuQuyen().id(1L).listMenu("listMenu1");
    }

    public static MenuQuyen getMenuQuyenSample2() {
        return new MenuQuyen().id(2L).listMenu("listMenu2");
    }

    public static MenuQuyen getMenuQuyenRandomSampleGenerator() {
        return new MenuQuyen().id(longCount.incrementAndGet()).listMenu(UUID.randomUUID().toString());
    }
}
