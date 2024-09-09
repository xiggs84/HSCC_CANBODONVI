package vn.vnpt.service.mapper;

import static vn.vnpt.domain.QuanHeDuongSuAsserts.*;
import static vn.vnpt.domain.QuanHeDuongSuTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuanHeDuongSuMapperTest {

    private QuanHeDuongSuMapper quanHeDuongSuMapper;

    @BeforeEach
    void setUp() {
        quanHeDuongSuMapper = new QuanHeDuongSuMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getQuanHeDuongSuSample1();
        var actual = quanHeDuongSuMapper.toEntity(quanHeDuongSuMapper.toDto(expected));
        assertQuanHeDuongSuAllPropertiesEquals(expected, actual);
    }
}
