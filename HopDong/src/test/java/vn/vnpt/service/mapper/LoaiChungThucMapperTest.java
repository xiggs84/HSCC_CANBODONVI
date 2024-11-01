package vn.vnpt.service.mapper;

import static vn.vnpt.domain.LoaiChungThucAsserts.*;
import static vn.vnpt.domain.LoaiChungThucTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoaiChungThucMapperTest {

    private LoaiChungThucMapper loaiChungThucMapper;

    @BeforeEach
    void setUp() {
        loaiChungThucMapper = new LoaiChungThucMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getLoaiChungThucSample1();
        var actual = loaiChungThucMapper.toEntity(loaiChungThucMapper.toDto(expected));
        assertLoaiChungThucAllPropertiesEquals(expected, actual);
    }
}
