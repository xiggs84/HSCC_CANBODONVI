package vn.vnpt.service.mapper;

import static vn.vnpt.domain.QuanHeNhanThanAsserts.*;
import static vn.vnpt.domain.QuanHeNhanThanTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuanHeNhanThanMapperTest {

    private QuanHeNhanThanMapper quanHeNhanThanMapper;

    @BeforeEach
    void setUp() {
        quanHeNhanThanMapper = new QuanHeNhanThanMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getQuanHeNhanThanSample1();
        var actual = quanHeNhanThanMapper.toEntity(quanHeNhanThanMapper.toDto(expected));
        assertQuanHeNhanThanAllPropertiesEquals(expected, actual);
    }
}
