package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DanhSachDuongSuAsserts.*;
import static vn.vnpt.domain.DanhSachDuongSuTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DanhSachDuongSuMapperTest {

    private DanhSachDuongSuMapper danhSachDuongSuMapper;

    @BeforeEach
    void setUp() {
        danhSachDuongSuMapper = new DanhSachDuongSuMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDanhSachDuongSuSample1();
        var actual = danhSachDuongSuMapper.toEntity(danhSachDuongSuMapper.toDto(expected));
        assertDanhSachDuongSuAllPropertiesEquals(expected, actual);
    }
}
