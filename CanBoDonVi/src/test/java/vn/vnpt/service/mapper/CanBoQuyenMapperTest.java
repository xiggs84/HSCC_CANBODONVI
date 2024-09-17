package vn.vnpt.service.mapper;

import static vn.vnpt.domain.CanBoQuyenAsserts.*;
import static vn.vnpt.domain.CanBoQuyenTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CanBoQuyenMapperTest {

    private CanBoQuyenMapper canBoQuyenMapper;

    @BeforeEach
    void setUp() {
        canBoQuyenMapper = new CanBoQuyenMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getCanBoQuyenSample1();
        var actual = canBoQuyenMapper.toEntity(canBoQuyenMapper.toDto(expected));
        assertCanBoQuyenAllPropertiesEquals(expected, actual);
    }
}
