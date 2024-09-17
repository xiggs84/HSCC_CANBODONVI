package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DanhMucXaAsserts.*;
import static vn.vnpt.domain.DanhMucXaTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DanhMucXaMapperTest {

    private DanhMucXaMapper danhMucXaMapper;

    @BeforeEach
    void setUp() {
        danhMucXaMapper = new DanhMucXaMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDanhMucXaSample1();
        var actual = danhMucXaMapper.toEntity(danhMucXaMapper.toDto(expected));
        assertDanhMucXaAllPropertiesEquals(expected, actual);
    }
}
