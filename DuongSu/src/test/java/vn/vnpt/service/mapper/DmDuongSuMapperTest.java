package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DmDuongSuAsserts.*;
import static vn.vnpt.domain.DmDuongSuTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DmDuongSuMapperTest {

    private DmDuongSuMapper dmDuongSuMapper;

    @BeforeEach
    void setUp() {
        dmDuongSuMapper = new DmDuongSuMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDmDuongSuSample1();
        var actual = dmDuongSuMapper.toEntity(dmDuongSuMapper.toDto(expected));
        assertDmDuongSuAllPropertiesEquals(expected, actual);
    }
}
