package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DuongSu;
import vn.vnpt.domain.LoaiDuongSu;
import vn.vnpt.domain.LoaiGiayTo;
import vn.vnpt.service.dto.DuongSuDTO;
import vn.vnpt.service.dto.LoaiDuongSuDTO;
import vn.vnpt.service.dto.LoaiGiayToDTO;

/**
 * Mapper for the entity {@link DuongSu} and its DTO {@link DuongSuDTO}.
 */
@Mapper(componentModel = "spring")
public interface DuongSuMapper extends EntityMapper<DuongSuDTO, DuongSu> {
    @Mapping(target = "loaiDuongSu", source = "loaiDuongSu", qualifiedByName = "loaiDuongSuIdLoaiDuongSu")
    @Mapping(target = "loaiGiayTo", source = "loaiGiayTo", qualifiedByName = "loaiGiayToIdLoaiGiayTo")
    DuongSuDTO toDto(DuongSu s);

    @Named("loaiDuongSuIdLoaiDuongSu")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idLoaiDuongSu", source = "idLoaiDuongSu")
    LoaiDuongSuDTO toDtoLoaiDuongSuIdLoaiDuongSu(LoaiDuongSu loaiDuongSu);

    @Named("loaiGiayToIdLoaiGiayTo")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idLoaiGiayTo", source = "idLoaiGiayTo")
    LoaiGiayToDTO toDtoLoaiGiayToIdLoaiGiayTo(LoaiGiayTo loaiGiayTo);
}
