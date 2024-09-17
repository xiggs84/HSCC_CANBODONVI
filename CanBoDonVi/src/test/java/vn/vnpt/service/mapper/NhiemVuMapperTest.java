package vn.vnpt.service.mapper;

import static vn.vnpt.domain.NhiemVuAsserts.*;
import static vn.vnpt.domain.NhiemVuTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NhiemVuMapperTest {

    private NhiemVuMapper nhiemVuMapper;

    @BeforeEach
    void setUp() {
        nhiemVuMapper = new NhiemVuMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getNhiemVuSample1();
        var actual = nhiemVuMapper.toEntity(nhiemVuMapper.toDto(expected));
        assertNhiemVuAllPropertiesEquals(expected, actual);
    }
}
