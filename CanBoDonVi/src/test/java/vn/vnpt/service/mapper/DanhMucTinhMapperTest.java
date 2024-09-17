package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DanhMucTinhAsserts.*;
import static vn.vnpt.domain.DanhMucTinhTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DanhMucTinhMapperTest {

    private DanhMucTinhMapper danhMucTinhMapper;

    @BeforeEach
    void setUp() {
        danhMucTinhMapper = new DanhMucTinhMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDanhMucTinhSample1();
        var actual = danhMucTinhMapper.toEntity(danhMucTinhMapper.toDto(expected));
        assertDanhMucTinhAllPropertiesEquals(expected, actual);
    }
}
