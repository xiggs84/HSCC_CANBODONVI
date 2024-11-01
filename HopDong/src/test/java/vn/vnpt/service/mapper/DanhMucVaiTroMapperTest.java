package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DanhMucVaiTroAsserts.*;
import static vn.vnpt.domain.DanhMucVaiTroTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DanhMucVaiTroMapperTest {

    private DanhMucVaiTroMapper danhMucVaiTroMapper;

    @BeforeEach
    void setUp() {
        danhMucVaiTroMapper = new DanhMucVaiTroMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDanhMucVaiTroSample1();
        var actual = danhMucVaiTroMapper.toEntity(danhMucVaiTroMapper.toDto(expected));
        assertDanhMucVaiTroAllPropertiesEquals(expected, actual);
    }
}
