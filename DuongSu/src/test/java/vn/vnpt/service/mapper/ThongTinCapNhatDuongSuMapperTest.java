package vn.vnpt.service.mapper;

import static vn.vnpt.domain.ThongTinCapNhatDuongSuAsserts.*;
import static vn.vnpt.domain.ThongTinCapNhatDuongSuTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ThongTinCapNhatDuongSuMapperTest {

    private ThongTinCapNhatDuongSuMapper thongTinCapNhatDuongSuMapper;

    @BeforeEach
    void setUp() {
        thongTinCapNhatDuongSuMapper = new ThongTinCapNhatDuongSuMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getThongTinCapNhatDuongSuSample1();
        var actual = thongTinCapNhatDuongSuMapper.toEntity(thongTinCapNhatDuongSuMapper.toDto(expected));
        assertThongTinCapNhatDuongSuAllPropertiesEquals(expected, actual);
    }
}
