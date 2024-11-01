package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhMucLoaiTaiSan;
import vn.vnpt.domain.TaiSan;
import vn.vnpt.domain.TaiSanDgc;
import vn.vnpt.domain.TinhTrangTaiSan;
import vn.vnpt.service.dto.DanhMucLoaiTaiSanDTO;
import vn.vnpt.service.dto.TaiSanDTO;
import vn.vnpt.service.dto.TaiSanDgcDTO;
import vn.vnpt.service.dto.TinhTrangTaiSanDTO;

/**
 * Mapper for the entity {@link TaiSanDgc} and its DTO {@link TaiSanDgcDTO}.
 */
@Mapper(componentModel = "spring")
public interface TaiSanDgcMapper extends EntityMapper<TaiSanDgcDTO, TaiSanDgc> {
    @Mapping(target = "taiSan", source = "taiSan", qualifiedByName = "taiSanIdTaiSan")
    @Mapping(target = "danhMucLoaiTaiSan", source = "danhMucLoaiTaiSan", qualifiedByName = "danhMucLoaiTaiSanIdLoaiTs")
    @Mapping(target = "tinhTrangTaiSan", source = "tinhTrangTaiSan", qualifiedByName = "tinhTrangTaiSanIdTinhTrang")
    TaiSanDgcDTO toDto(TaiSanDgc s);

    @Named("taiSanIdTaiSan")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idTaiSan", source = "idTaiSan")
    TaiSanDTO toDtoTaiSanIdTaiSan(TaiSan taiSan);

    @Named("danhMucLoaiTaiSanIdLoaiTs")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idLoaiTs", source = "idLoaiTs")
    DanhMucLoaiTaiSanDTO toDtoDanhMucLoaiTaiSanIdLoaiTs(DanhMucLoaiTaiSan danhMucLoaiTaiSan);

    @Named("tinhTrangTaiSanIdTinhTrang")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idTinhTrang", source = "idTinhTrang")
    TinhTrangTaiSanDTO toDtoTinhTrangTaiSanIdTinhTrang(TinhTrangTaiSan tinhTrangTaiSan);
}
