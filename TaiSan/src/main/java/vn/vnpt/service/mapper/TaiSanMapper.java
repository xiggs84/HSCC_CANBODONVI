package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhMucLoaiTaiSan;
import vn.vnpt.domain.TaiSan;
import vn.vnpt.domain.TinhTrangTaiSan;
import vn.vnpt.service.dto.DanhMucLoaiTaiSanDTO;
import vn.vnpt.service.dto.TaiSanDTO;
import vn.vnpt.service.dto.TinhTrangTaiSanDTO;

/**
 * Mapper for the entity {@link TaiSan} and its DTO {@link TaiSanDTO}.
 */
@Mapper(componentModel = "spring")
public interface TaiSanMapper extends EntityMapper<TaiSanDTO, TaiSan> {
    @Mapping(target = "danhMucLoaiTaiSan", source = "danhMucLoaiTaiSan", qualifiedByName = "danhMucLoaiTaiSanIdLoaiTs")
    @Mapping(target = "tinhTrangTaiSan", source = "tinhTrangTaiSan", qualifiedByName = "tinhTrangTaiSanIdTinhTrang")
    TaiSanDTO toDto(TaiSan s);

    @Named("danhMucLoaiTaiSanIdLoaiTs")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idLoaiTs", source = "idLoaiTs")
    DanhMucLoaiTaiSanDTO toDtoDanhMucLoaiTaiSanIdLoaiTs(DanhMucLoaiTaiSan danhMucLoaiTaiSan);

    @Named("tinhTrangTaiSanIdTinhTrang")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idTinhTrang", source = "idTinhTrang")
    TinhTrangTaiSanDTO toDtoTinhTrangTaiSanIdTinhTrang(TinhTrangTaiSan tinhTrangTaiSan);
}
