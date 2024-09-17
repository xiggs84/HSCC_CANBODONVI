package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DanhMucHuyenAsserts.*;
import static vn.vnpt.domain.DanhMucHuyenTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DanhMucHuyenMapperTest {

    private DanhMucHuyenMapper danhMucHuyenMapper;

    @BeforeEach
    void setUp() {
        danhMucHuyenMapper = new DanhMucHuyenMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDanhMucHuyenSample1();
        var actual = danhMucHuyenMapper.toEntity(danhMucHuyenMapper.toDto(expected));
        assertDanhMucHuyenAllPropertiesEquals(expected, actual);
    }
}
