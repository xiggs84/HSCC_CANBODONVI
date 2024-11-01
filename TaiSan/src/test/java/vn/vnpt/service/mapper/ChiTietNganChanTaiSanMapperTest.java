package vn.vnpt.service.mapper;

import static vn.vnpt.domain.ChiTietNganChanTaiSanAsserts.*;
import static vn.vnpt.domain.ChiTietNganChanTaiSanTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChiTietNganChanTaiSanMapperTest {

    private ChiTietNganChanTaiSanMapper chiTietNganChanTaiSanMapper;

    @BeforeEach
    void setUp() {
        chiTietNganChanTaiSanMapper = new ChiTietNganChanTaiSanMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getChiTietNganChanTaiSanSample1();
        var actual = chiTietNganChanTaiSanMapper.toEntity(chiTietNganChanTaiSanMapper.toDto(expected));
        assertChiTietNganChanTaiSanAllPropertiesEquals(expected, actual);
    }
}
