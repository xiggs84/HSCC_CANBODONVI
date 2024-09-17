package vn.vnpt.service.mapper;

import static vn.vnpt.domain.CapQuanLyAsserts.*;
import static vn.vnpt.domain.CapQuanLyTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CapQuanLyMapperTest {

    private CapQuanLyMapper capQuanLyMapper;

    @BeforeEach
    void setUp() {
        capQuanLyMapper = new CapQuanLyMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getCapQuanLySample1();
        var actual = capQuanLyMapper.toEntity(capQuanLyMapper.toDto(expected));
        assertCapQuanLyAllPropertiesEquals(expected, actual);
    }
}
