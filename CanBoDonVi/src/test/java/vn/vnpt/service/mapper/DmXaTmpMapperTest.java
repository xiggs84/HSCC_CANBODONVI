package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DmXaTmpAsserts.*;
import static vn.vnpt.domain.DmXaTmpTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DmXaTmpMapperTest {

    private DmXaTmpMapper dmXaTmpMapper;

    @BeforeEach
    void setUp() {
        dmXaTmpMapper = new DmXaTmpMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDmXaTmpSample1();
        var actual = dmXaTmpMapper.toEntity(dmXaTmpMapper.toDto(expected));
        assertDmXaTmpAllPropertiesEquals(expected, actual);
    }
}
