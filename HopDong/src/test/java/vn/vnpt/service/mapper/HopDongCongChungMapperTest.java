package vn.vnpt.service.mapper;

import static vn.vnpt.domain.HopDongCongChungAsserts.*;
import static vn.vnpt.domain.HopDongCongChungTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HopDongCongChungMapperTest {

    private HopDongCongChungMapper hopDongCongChungMapper;

    @BeforeEach
    void setUp() {
        hopDongCongChungMapper = new HopDongCongChungMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getHopDongCongChungSample1();
        var actual = hopDongCongChungMapper.toEntity(hopDongCongChungMapper.toDto(expected));
        assertHopDongCongChungAllPropertiesEquals(expected, actual);
    }
}
