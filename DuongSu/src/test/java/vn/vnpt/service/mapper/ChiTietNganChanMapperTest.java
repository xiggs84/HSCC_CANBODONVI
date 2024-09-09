package vn.vnpt.service.mapper;

import static vn.vnpt.domain.ChiTietNganChanAsserts.*;
import static vn.vnpt.domain.ChiTietNganChanTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChiTietNganChanMapperTest {

    private ChiTietNganChanMapper chiTietNganChanMapper;

    @BeforeEach
    void setUp() {
        chiTietNganChanMapper = new ChiTietNganChanMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getChiTietNganChanSample1();
        var actual = chiTietNganChanMapper.toEntity(chiTietNganChanMapper.toDto(expected));
        assertChiTietNganChanAllPropertiesEquals(expected, actual);
    }
}
