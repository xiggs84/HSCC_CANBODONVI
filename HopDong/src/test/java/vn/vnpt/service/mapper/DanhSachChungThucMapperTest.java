package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DanhSachChungThucAsserts.*;
import static vn.vnpt.domain.DanhSachChungThucTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DanhSachChungThucMapperTest {

    private DanhSachChungThucMapper danhSachChungThucMapper;

    @BeforeEach
    void setUp() {
        danhSachChungThucMapper = new DanhSachChungThucMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDanhSachChungThucSample1();
        var actual = danhSachChungThucMapper.toEntity(danhSachChungThucMapper.toDto(expected));
        assertDanhSachChungThucAllPropertiesEquals(expected, actual);
    }
}
