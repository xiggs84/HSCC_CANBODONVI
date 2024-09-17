package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DmHuyenTmpAsserts.*;
import static vn.vnpt.domain.DmHuyenTmpTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DmHuyenTmpMapperTest {

    private DmHuyenTmpMapper dmHuyenTmpMapper;

    @BeforeEach
    void setUp() {
        dmHuyenTmpMapper = new DmHuyenTmpMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDmHuyenTmpSample1();
        var actual = dmHuyenTmpMapper.toEntity(dmHuyenTmpMapper.toDto(expected));
        assertDmHuyenTmpAllPropertiesEquals(expected, actual);
    }
}
