package vn.vnpt.service.mapper;

import static vn.vnpt.domain.PhanLoaiHopDongAsserts.*;
import static vn.vnpt.domain.PhanLoaiHopDongTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PhanLoaiHopDongMapperTest {

    private PhanLoaiHopDongMapper phanLoaiHopDongMapper;

    @BeforeEach
    void setUp() {
        phanLoaiHopDongMapper = new PhanLoaiHopDongMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getPhanLoaiHopDongSample1();
        var actual = phanLoaiHopDongMapper.toEntity(phanLoaiHopDongMapper.toDto(expected));
        assertPhanLoaiHopDongAllPropertiesEquals(expected, actual);
    }
}
