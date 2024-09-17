package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DanhMucDonViAsserts.*;
import static vn.vnpt.domain.DanhMucDonViTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DanhMucDonViMapperTest {

    private DanhMucDonViMapper danhMucDonViMapper;

    @BeforeEach
    void setUp() {
        danhMucDonViMapper = new DanhMucDonViMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDanhMucDonViSample1();
        var actual = danhMucDonViMapper.toEntity(danhMucDonViMapper.toDto(expected));
        assertDanhMucDonViAllPropertiesEquals(expected, actual);
    }
}
