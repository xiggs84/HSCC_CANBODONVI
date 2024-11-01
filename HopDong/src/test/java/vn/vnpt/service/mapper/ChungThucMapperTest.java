package vn.vnpt.service.mapper;

import static vn.vnpt.domain.ChungThucAsserts.*;
import static vn.vnpt.domain.ChungThucTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChungThucMapperTest {

    private ChungThucMapper chungThucMapper;

    @BeforeEach
    void setUp() {
        chungThucMapper = new ChungThucMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getChungThucSample1();
        var actual = chungThucMapper.toEntity(chungThucMapper.toDto(expected));
        assertChungThucAllPropertiesEquals(expected, actual);
    }
}
