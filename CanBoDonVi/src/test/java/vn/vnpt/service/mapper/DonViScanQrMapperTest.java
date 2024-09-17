package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DonViScanQrAsserts.*;
import static vn.vnpt.domain.DonViScanQrTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DonViScanQrMapperTest {

    private DonViScanQrMapper donViScanQrMapper;

    @BeforeEach
    void setUp() {
        donViScanQrMapper = new DonViScanQrMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDonViScanQrSample1();
        var actual = donViScanQrMapper.toEntity(donViScanQrMapper.toDto(expected));
        assertDonViScanQrAllPropertiesEquals(expected, actual);
    }
}
