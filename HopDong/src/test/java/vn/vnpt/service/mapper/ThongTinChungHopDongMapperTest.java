package vn.vnpt.service.mapper;

import static vn.vnpt.domain.ThongTinChungHopDongAsserts.*;
import static vn.vnpt.domain.ThongTinChungHopDongTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ThongTinChungHopDongMapperTest {

    private ThongTinChungHopDongMapper thongTinChungHopDongMapper;

    @BeforeEach
    void setUp() {
        thongTinChungHopDongMapper = new ThongTinChungHopDongMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getThongTinChungHopDongSample1();
        var actual = thongTinChungHopDongMapper.toEntity(thongTinChungHopDongMapper.toDto(expected));
        assertThongTinChungHopDongAllPropertiesEquals(expected, actual);
    }
}
