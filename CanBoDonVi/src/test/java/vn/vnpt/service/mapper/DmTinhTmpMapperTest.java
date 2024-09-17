package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DmTinhTmpAsserts.*;
import static vn.vnpt.domain.DmTinhTmpTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DmTinhTmpMapperTest {

    private DmTinhTmpMapper dmTinhTmpMapper;

    @BeforeEach
    void setUp() {
        dmTinhTmpMapper = new DmTinhTmpMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDmTinhTmpSample1();
        var actual = dmTinhTmpMapper.toEntity(dmTinhTmpMapper.toDto(expected));
        assertDmTinhTmpAllPropertiesEquals(expected, actual);
    }
}
