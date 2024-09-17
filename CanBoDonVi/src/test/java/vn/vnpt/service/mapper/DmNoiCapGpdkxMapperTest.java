package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DmNoiCapGpdkxAsserts.*;
import static vn.vnpt.domain.DmNoiCapGpdkxTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DmNoiCapGpdkxMapperTest {

    private DmNoiCapGpdkxMapper dmNoiCapGpdkxMapper;

    @BeforeEach
    void setUp() {
        dmNoiCapGpdkxMapper = new DmNoiCapGpdkxMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDmNoiCapGpdkxSample1();
        var actual = dmNoiCapGpdkxMapper.toEntity(dmNoiCapGpdkxMapper.toDto(expected));
        assertDmNoiCapGpdkxAllPropertiesEquals(expected, actual);
    }
}
