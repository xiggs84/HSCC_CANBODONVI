package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhMucLoaiTaiSan;
import vn.vnpt.domain.DanhSachTaiSan;
import vn.vnpt.service.dto.DanhMucLoaiTaiSanDTO;
import vn.vnpt.service.dto.DanhSachTaiSanDTO;

/**
 * Mapper for the entity {@link DanhSachTaiSan} and its DTO {@link DanhSachTaiSanDTO}.
 */
@Mapper(componentModel = "spring")
public interface DanhSachTaiSanMapper extends EntityMapper<DanhSachTaiSanDTO, DanhSachTaiSan> {
    @Mapping(target = "danhMucLoaiTaiSan", source = "danhMucLoaiTaiSan", qualifiedByName = "danhMucLoaiTaiSanIdLoaiTs")
    DanhSachTaiSanDTO toDto(DanhSachTaiSan s);

    @Named("danhMucLoaiTaiSanIdLoaiTs")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idLoaiTs", source = "idLoaiTs")
    DanhMucLoaiTaiSanDTO toDtoDanhMucLoaiTaiSanIdLoaiTs(DanhMucLoaiTaiSan danhMucLoaiTaiSan);
}
