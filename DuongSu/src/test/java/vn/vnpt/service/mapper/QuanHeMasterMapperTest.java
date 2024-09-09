package vn.vnpt.service.mapper;

import static vn.vnpt.domain.QuanHeMasterAsserts.*;
import static vn.vnpt.domain.QuanHeMasterTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuanHeMasterMapperTest {

    private QuanHeMasterMapper quanHeMasterMapper;

    @BeforeEach
    void setUp() {
        quanHeMasterMapper = new QuanHeMasterMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getQuanHeMasterSample1();
        var actual = quanHeMasterMapper.toEntity(quanHeMasterMapper.toDto(expected));
        assertQuanHeMasterAllPropertiesEquals(expected, actual);
    }
}
