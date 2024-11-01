package vn.vnpt.service.mapper;

import static vn.vnpt.domain.SoChungThucAsserts.*;
import static vn.vnpt.domain.SoChungThucTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SoChungThucMapperTest {

    private SoChungThucMapper soChungThucMapper;

    @BeforeEach
    void setUp() {
        soChungThucMapper = new SoChungThucMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getSoChungThucSample1();
        var actual = soChungThucMapper.toEntity(soChungThucMapper.toDto(expected));
        assertSoChungThucAllPropertiesEquals(expected, actual);
    }
}
