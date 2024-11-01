package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DanhSachHopDongAsserts.*;
import static vn.vnpt.domain.DanhSachHopDongTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DanhSachHopDongMapperTest {

    private DanhSachHopDongMapper danhSachHopDongMapper;

    @BeforeEach
    void setUp() {
        danhSachHopDongMapper = new DanhSachHopDongMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDanhSachHopDongSample1();
        var actual = danhSachHopDongMapper.toEntity(danhSachHopDongMapper.toDto(expected));
        assertDanhSachHopDongAllPropertiesEquals(expected, actual);
    }
}
