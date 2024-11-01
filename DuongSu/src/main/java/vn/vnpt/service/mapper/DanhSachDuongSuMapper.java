package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhSachDuongSu;
import vn.vnpt.domain.DuongSu;
import vn.vnpt.service.dto.DanhSachDuongSuDTO;
import vn.vnpt.service.dto.DuongSuDTO;

/**
 * Mapper for the entity {@link DanhSachDuongSu} and its DTO {@link DanhSachDuongSuDTO}.
 */
@Mapper(componentModel = "spring")
public interface DanhSachDuongSuMapper extends EntityMapper<DanhSachDuongSuDTO, DanhSachDuongSu> {
    @Mapping(target = "duongSu", source = "duongSu", qualifiedByName = "duongSuIdDuongSu")
    DanhSachDuongSuDTO toDto(DanhSachDuongSu s);

    @Named("duongSuIdDuongSu")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idDuongSu", source = "idDuongSu")
    DuongSuDTO toDtoDuongSuIdDuongSu(DuongSu duongSu);
}
