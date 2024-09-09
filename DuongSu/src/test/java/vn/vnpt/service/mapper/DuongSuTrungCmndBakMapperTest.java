package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DuongSuTrungCmndBakAsserts.*;
import static vn.vnpt.domain.DuongSuTrungCmndBakTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DuongSuTrungCmndBakMapperTest {

    private DuongSuTrungCmndBakMapper duongSuTrungCmndBakMapper;

    @BeforeEach
    void setUp() {
        duongSuTrungCmndBakMapper = new DuongSuTrungCmndBakMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDuongSuTrungCmndBakSample1();
        var actual = duongSuTrungCmndBakMapper.toEntity(duongSuTrungCmndBakMapper.toDto(expected));
        assertDuongSuTrungCmndBakAllPropertiesEquals(expected, actual);
    }
}
