package vn.vnpt.service.mapper;

import static vn.vnpt.domain.DanhMucLoaiGiayToChungThucAsserts.*;
import static vn.vnpt.domain.DanhMucLoaiGiayToChungThucTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DanhMucLoaiGiayToChungThucMapperTest {

    private DanhMucLoaiGiayToChungThucMapper danhMucLoaiGiayToChungThucMapper;

    @BeforeEach
    void setUp() {
        danhMucLoaiGiayToChungThucMapper = new DanhMucLoaiGiayToChungThucMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDanhMucLoaiGiayToChungThucSample1();
        var actual = danhMucLoaiGiayToChungThucMapper.toEntity(danhMucLoaiGiayToChungThucMapper.toDto(expected));
        assertDanhMucLoaiGiayToChungThucAllPropertiesEquals(expected, actual);
    }
}
