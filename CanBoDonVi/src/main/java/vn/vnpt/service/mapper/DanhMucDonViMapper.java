package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.CapQuanLy;
import vn.vnpt.domain.DanhMucDonVi;
import vn.vnpt.domain.LoaiDonVi;
import vn.vnpt.domain.NhiemVu;
import vn.vnpt.service.dto.CapQuanLyDTO;
import vn.vnpt.service.dto.DanhMucDonViDTO;
import vn.vnpt.service.dto.LoaiDonViDTO;
import vn.vnpt.service.dto.NhiemVuDTO;

/**
 * Mapper for the entity {@link DanhMucDonVi} and its DTO {@link DanhMucDonViDTO}.
 */
@Mapper(componentModel = "spring")
public interface DanhMucDonViMapper extends EntityMapper<DanhMucDonViDTO, DanhMucDonVi> {
    @Mapping(target = "capQuanLy", source = "capQuanLy", qualifiedByName = "capQuanLyIdCapQl")
    @Mapping(target = "loaiDonVi", source = "loaiDonVi", qualifiedByName = "loaiDonViIdLoaiDv")
    @Mapping(target = "nhiemVu", source = "nhiemVu", qualifiedByName = "nhiemVuIdNhiemVu")
    DanhMucDonViDTO toDto(DanhMucDonVi s);

    @Named("capQuanLyIdCapQl")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idCapQl", source = "idCapQl")
    CapQuanLyDTO toDtoCapQuanLyIdCapQl(CapQuanLy capQuanLy);

    @Named("loaiDonViIdLoaiDv")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idLoaiDv", source = "idLoaiDv")
    LoaiDonViDTO toDtoLoaiDonViIdLoaiDv(LoaiDonVi loaiDonVi);

    @Named("nhiemVuIdNhiemVu")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idNhiemVu", source = "idNhiemVu")
    NhiemVuDTO toDtoNhiemVuIdNhiemVu(NhiemVu nhiemVu);
}
