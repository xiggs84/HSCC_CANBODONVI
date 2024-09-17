package vn.vnpt.service.mapper;

import static vn.vnpt.domain.MenuQuyenAsserts.*;
import static vn.vnpt.domain.MenuQuyenTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenuQuyenMapperTest {

    private MenuQuyenMapper menuQuyenMapper;

    @BeforeEach
    void setUp() {
        menuQuyenMapper = new MenuQuyenMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getMenuQuyenSample1();
        var actual = menuQuyenMapper.toEntity(menuQuyenMapper.toDto(expected));
        assertMenuQuyenAllPropertiesEquals(expected, actual);
    }
}
