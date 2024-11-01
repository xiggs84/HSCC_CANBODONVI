package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DanhMucLoaiVanBanAsserts.*;
import static vn.vnpt.domain.DanhMucLoaiVanBanTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DanhMucLoaiVanBanMapperTest {

    private DanhMucLoaiVanBanMapper danhMucLoaiVanBanMapper;

    @BeforeEach
    void setUp() {
        danhMucLoaiVanBanMapper = new DanhMucLoaiVanBanMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDanhMucLoaiVanBanSample1();
        var actual = danhMucLoaiVanBanMapper.toEntity(danhMucLoaiVanBanMapper.toDto(expected));
        assertDanhMucLoaiVanBanAllPropertiesEquals(expected, actual);
    }
}
