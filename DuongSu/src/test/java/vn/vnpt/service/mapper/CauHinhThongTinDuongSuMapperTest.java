package vn.vnpt.service.mapper;

import static vn.vnpt.domain.CauHinhThongTinDuongSuAsserts.*;
import static vn.vnpt.domain.CauHinhThongTinDuongSuTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CauHinhThongTinDuongSuMapperTest {

    private CauHinhThongTinDuongSuMapper cauHinhThongTinDuongSuMapper;

    @BeforeEach
    void setUp() {
        cauHinhThongTinDuongSuMapper = new CauHinhThongTinDuongSuMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getCauHinhThongTinDuongSuSample1();
        var actual = cauHinhThongTinDuongSuMapper.toEntity(cauHinhThongTinDuongSuMapper.toDto(expected));
        assertCauHinhThongTinDuongSuAllPropertiesEquals(expected, actual);
    }
}
