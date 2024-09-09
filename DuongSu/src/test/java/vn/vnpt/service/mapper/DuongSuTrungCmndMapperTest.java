package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DuongSuTrungCmndAsserts.*;
import static vn.vnpt.domain.DuongSuTrungCmndTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DuongSuTrungCmndMapperTest {

    private DuongSuTrungCmndMapper duongSuTrungCmndMapper;

    @BeforeEach
    void setUp() {
        duongSuTrungCmndMapper = new DuongSuTrungCmndMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDuongSuTrungCmndSample1();
        var actual = duongSuTrungCmndMapper.toEntity(duongSuTrungCmndMapper.toDto(expected));
        assertDuongSuTrungCmndAllPropertiesEquals(expected, actual);
    }
}
