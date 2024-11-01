package vn.vnpt.service.mapper;

import static vn.vnpt.domain.SoCongChungAsserts.*;
import static vn.vnpt.domain.SoCongChungTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SoCongChungMapperTest {

    private SoCongChungMapper soCongChungMapper;

    @BeforeEach
    void setUp() {
        soCongChungMapper = new SoCongChungMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getSoCongChungSample1();
        var actual = soCongChungMapper.toEntity(soCongChungMapper.toDto(expected));
        assertSoCongChungAllPropertiesEquals(expected, actual);
    }
}
