package vn.vnpt.service.mapper;

import static vn.vnpt.domain.LoaiDonViAsserts.*;
import static vn.vnpt.domain.LoaiDonViTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoaiDonViMapperTest {

    private LoaiDonViMapper loaiDonViMapper;

    @BeforeEach
    void setUp() {
        loaiDonViMapper = new LoaiDonViMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getLoaiDonViSample1();
        var actual = loaiDonViMapper.toEntity(loaiDonViMapper.toDto(expected));
        assertLoaiDonViAllPropertiesEquals(expected, actual);
    }
}
