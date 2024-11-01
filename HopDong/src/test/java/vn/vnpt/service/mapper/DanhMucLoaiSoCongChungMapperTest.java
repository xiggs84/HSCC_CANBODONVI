package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DanhMucLoaiSoCongChungAsserts.*;
import static vn.vnpt.domain.DanhMucLoaiSoCongChungTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DanhMucLoaiSoCongChungMapperTest {

    private DanhMucLoaiSoCongChungMapper danhMucLoaiSoCongChungMapper;

    @BeforeEach
    void setUp() {
        danhMucLoaiSoCongChungMapper = new DanhMucLoaiSoCongChungMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDanhMucLoaiSoCongChungSample1();
        var actual = danhMucLoaiSoCongChungMapper.toEntity(danhMucLoaiSoCongChungMapper.toDto(expected));
        assertDanhMucLoaiSoCongChungAllPropertiesEquals(expected, actual);
    }
}
