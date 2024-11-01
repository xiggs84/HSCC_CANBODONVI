package vn.vnpt.service.mapper;

import static vn.vnpt.domain.LoaiHopDongCongChungAsserts.*;
import static vn.vnpt.domain.LoaiHopDongCongChungTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoaiHopDongCongChungMapperTest {

    private LoaiHopDongCongChungMapper loaiHopDongCongChungMapper;

    @BeforeEach
    void setUp() {
        loaiHopDongCongChungMapper = new LoaiHopDongCongChungMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getLoaiHopDongCongChungSample1();
        var actual = loaiHopDongCongChungMapper.toEntity(loaiHopDongCongChungMapper.toDto(expected));
        assertLoaiHopDongCongChungAllPropertiesEquals(expected, actual);
    }
}
