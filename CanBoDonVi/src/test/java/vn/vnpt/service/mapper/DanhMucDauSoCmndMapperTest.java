package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DanhMucDauSoCmndAsserts.*;
import static vn.vnpt.domain.DanhMucDauSoCmndTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DanhMucDauSoCmndMapperTest {

    private DanhMucDauSoCmndMapper danhMucDauSoCmndMapper;

    @BeforeEach
    void setUp() {
        danhMucDauSoCmndMapper = new DanhMucDauSoCmndMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDanhMucDauSoCmndSample1();
        var actual = danhMucDauSoCmndMapper.toEntity(danhMucDauSoCmndMapper.toDto(expected));
        assertDanhMucDauSoCmndAllPropertiesEquals(expected, actual);
    }
}
