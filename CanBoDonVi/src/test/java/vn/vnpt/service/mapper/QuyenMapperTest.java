package vn.vnpt.service.mapper;

import static vn.vnpt.domain.QuyenAsserts.*;
import static vn.vnpt.domain.QuyenTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuyenMapperTest {

    private QuyenMapper quyenMapper;

    @BeforeEach
    void setUp() {
        quyenMapper = new QuyenMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getQuyenSample1();
        var actual = quyenMapper.toEntity(quyenMapper.toDto(expected));
        assertQuyenAllPropertiesEquals(expected, actual);
    }
}
