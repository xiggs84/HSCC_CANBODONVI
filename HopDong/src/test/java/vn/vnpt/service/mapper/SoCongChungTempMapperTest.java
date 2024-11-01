package vn.vnpt.service.mapper;

import static vn.vnpt.domain.SoCongChungTempAsserts.*;
import static vn.vnpt.domain.SoCongChungTempTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SoCongChungTempMapperTest {

    private SoCongChungTempMapper soCongChungTempMapper;

    @BeforeEach
    void setUp() {
        soCongChungTempMapper = new SoCongChungTempMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getSoCongChungTempSample1();
        var actual = soCongChungTempMapper.toEntity(soCongChungTempMapper.toDto(expected));
        assertSoCongChungTempAllPropertiesEquals(expected, actual);
    }
}
