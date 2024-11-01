package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DuongSu;
import vn.vnpt.domain.LoaiDuongSu;
import vn.vnpt.domain.LoaiGiayTo;
import vn.vnpt.domain.ThongTinCapNhatDuongSu;
import vn.vnpt.service.dto.DuongSuDTO;
import vn.vnpt.service.dto.LoaiDuongSuDTO;
import vn.vnpt.service.dto.LoaiGiayToDTO;
import vn.vnpt.service.dto.ThongTinCapNhatDuongSuDTO;

/**
 * Mapper for the entity {@link ThongTinCapNhatDuongSu} and its DTO {@link ThongTinCapNhatDuongSuDTO}.
 */
@Mapper(componentModel = "spring")
public interface ThongTinCapNhatDuongSuMapper extends EntityMapper<ThongTinCapNhatDuongSuDTO, ThongTinCapNhatDuongSu> {
    @Mapping(target = "loaiDuongSu", source = "loaiDuongSu", qualifiedByName = "loaiDuongSuIdLoaiDuongSu")
    @Mapping(target = "loaiGiayTo", source = "loaiGiayTo", qualifiedByName = "loaiGiayToIdLoaiGiayTo")
    @Mapping(target = "duongSu", source = "duongSu", qualifiedByName = "duongSuIdDuongSu")
    ThongTinCapNhatDuongSuDTO toDto(ThongTinCapNhatDuongSu s);

    @Named("loaiDuongSuIdLoaiDuongSu")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idLoaiDuongSu", source = "idLoaiDuongSu")
    LoaiDuongSuDTO toDtoLoaiDuongSuIdLoaiDuongSu(LoaiDuongSu loaiDuongSu);

    @Named("loaiGiayToIdLoaiGiayTo")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idLoaiGiayTo", source = "idLoaiGiayTo")
    LoaiGiayToDTO toDtoLoaiGiayToIdLoaiGiayTo(LoaiGiayTo loaiGiayTo);

    @Named("duongSuIdDuongSu")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idDuongSu", source = "idDuongSu")
    DuongSuDTO toDtoDuongSuIdDuongSu(DuongSu duongSu);
}
