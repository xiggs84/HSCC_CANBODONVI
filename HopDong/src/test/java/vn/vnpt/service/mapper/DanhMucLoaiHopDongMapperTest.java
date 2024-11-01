package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DanhMucLoaiHopDongAsserts.*;
import static vn.vnpt.domain.DanhMucLoaiHopDongTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DanhMucLoaiHopDongMapperTest {

    private DanhMucLoaiHopDongMapper danhMucLoaiHopDongMapper;

    @BeforeEach
    void setUp() {
        danhMucLoaiHopDongMapper = new DanhMucLoaiHopDongMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDanhMucLoaiHopDongSample1();
        var actual = danhMucLoaiHopDongMapper.toEntity(danhMucLoaiHopDongMapper.toDto(expected));
        assertDanhMucLoaiHopDongAllPropertiesEquals(expected, actual);
    }
}
