package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DanhMucTuVietTatAsserts.*;
import static vn.vnpt.domain.DanhMucTuVietTatTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DanhMucTuVietTatMapperTest {

    private DanhMucTuVietTatMapper danhMucTuVietTatMapper;

    @BeforeEach
    void setUp() {
        danhMucTuVietTatMapper = new DanhMucTuVietTatMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDanhMucTuVietTatSample1();
        var actual = danhMucTuVietTatMapper.toEntity(danhMucTuVietTatMapper.toDto(expected));
        assertDanhMucTuVietTatAllPropertiesEquals(expected, actual);
    }
}
