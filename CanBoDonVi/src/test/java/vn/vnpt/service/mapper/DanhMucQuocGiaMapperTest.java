package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DanhMucQuocGiaAsserts.*;
import static vn.vnpt.domain.DanhMucQuocGiaTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DanhMucQuocGiaMapperTest {

    private DanhMucQuocGiaMapper danhMucQuocGiaMapper;

    @BeforeEach
    void setUp() {
        danhMucQuocGiaMapper = new DanhMucQuocGiaMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDanhMucQuocGiaSample1();
        var actual = danhMucQuocGiaMapper.toEntity(danhMucQuocGiaMapper.toDto(expected));
        assertDanhMucQuocGiaAllPropertiesEquals(expected, actual);
    }
}
