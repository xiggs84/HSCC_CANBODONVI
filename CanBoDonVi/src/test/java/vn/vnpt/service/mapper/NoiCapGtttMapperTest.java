package vn.vnpt.service.mapper;

import static vn.vnpt.domain.NoiCapGtttAsserts.*;
import static vn.vnpt.domain.NoiCapGtttTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NoiCapGtttMapperTest {

    private NoiCapGtttMapper noiCapGtttMapper;

    @BeforeEach
    void setUp() {
        noiCapGtttMapper = new NoiCapGtttMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getNoiCapGtttSample1();
        var actual = noiCapGtttMapper.toEntity(noiCapGtttMapper.toDto(expected));
        assertNoiCapGtttAllPropertiesEquals(expected, actual);
    }
}
