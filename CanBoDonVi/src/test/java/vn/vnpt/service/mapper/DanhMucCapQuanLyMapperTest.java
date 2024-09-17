package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DanhMucCapQuanLyAsserts.*;
import static vn.vnpt.domain.DanhMucCapQuanLyTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DanhMucCapQuanLyMapperTest {

    private DanhMucCapQuanLyMapper danhMucCapQuanLyMapper;

    @BeforeEach
    void setUp() {
        danhMucCapQuanLyMapper = new DanhMucCapQuanLyMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDanhMucCapQuanLySample1();
        var actual = danhMucCapQuanLyMapper.toEntity(danhMucCapQuanLyMapper.toDto(expected));
        assertDanhMucCapQuanLyAllPropertiesEquals(expected, actual);
    }
}
