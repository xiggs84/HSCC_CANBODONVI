package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DanhMucNhomHopDongAsserts.*;
import static vn.vnpt.domain.DanhMucNhomHopDongTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DanhMucNhomHopDongMapperTest {

    private DanhMucNhomHopDongMapper danhMucNhomHopDongMapper;

    @BeforeEach
    void setUp() {
        danhMucNhomHopDongMapper = new DanhMucNhomHopDongMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDanhMucNhomHopDongSample1();
        var actual = danhMucNhomHopDongMapper.toEntity(danhMucNhomHopDongMapper.toDto(expected));
        assertDanhMucNhomHopDongAllPropertiesEquals(expected, actual);
    }
}
