package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DuongSuAsserts.*;
import static vn.vnpt.domain.DuongSuTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DuongSuMapperTest {

    private DuongSuMapper duongSuMapper;

    @BeforeEach
    void setUp() {
        duongSuMapper = new DuongSuMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDuongSuSample1();
        var actual = duongSuMapper.toEntity(duongSuMapper.toDto(expected));
        assertDuongSuAllPropertiesEquals(expected, actual);
    }
}
