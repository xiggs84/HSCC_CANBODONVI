package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhMucLoaiTaiSan;
import vn.vnpt.domain.TaiSanDatNha;
import vn.vnpt.domain.TinhTrangTaiSan;
import vn.vnpt.service.dto.DanhMucLoaiTaiSanDTO;
import vn.vnpt.service.dto.TaiSanDatNhaDTO;
import vn.vnpt.service.dto.TinhTrangTaiSanDTO;

/**
 * Mapper for the entity {@link TaiSanDatNha} and its DTO {@link TaiSanDatNhaDTO}.
 */
@Mapper(componentModel = "spring")
public interface TaiSanDatNhaMapper extends EntityMapper<TaiSanDatNhaDTO, TaiSanDatNha> {
    @Mapping(target = "danhMucLoaiTaiSan", source = "danhMucLoaiTaiSan", qualifiedByName = "danhMucLoaiTaiSanIdLoaiTs")
    @Mapping(target = "tinhTrangTaiSan", source = "tinhTrangTaiSan", qualifiedByName = "tinhTrangTaiSanIdTinhTrang")
    TaiSanDatNhaDTO toDto(TaiSanDatNha s);

    @Named("danhMucLoaiTaiSanIdLoaiTs")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idLoaiTs", source = "idLoaiTs")
    DanhMucLoaiTaiSanDTO toDtoDanhMucLoaiTaiSanIdLoaiTs(DanhMucLoaiTaiSan danhMucLoaiTaiSan);

    @Named("tinhTrangTaiSanIdTinhTrang")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idTinhTrang", source = "idTinhTrang")
    TinhTrangTaiSanDTO toDtoTinhTrangTaiSanIdTinhTrang(TinhTrangTaiSan tinhTrangTaiSan);
}
