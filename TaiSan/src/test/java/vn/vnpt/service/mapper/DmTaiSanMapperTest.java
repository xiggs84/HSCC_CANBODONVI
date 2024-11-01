package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DmTaiSanAsserts.*;
import static vn.vnpt.domain.DmTaiSanTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DmTaiSanMapperTest {

    private DmTaiSanMapper dmTaiSanMapper;

    @BeforeEach
    void setUp() {
        dmTaiSanMapper = new DmTaiSanMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDmTaiSanSample1();
        var actual = dmTaiSanMapper.toEntity(dmTaiSanMapper.toDto(expected));
        assertDmTaiSanAllPropertiesEquals(expected, actual);
    }
}
