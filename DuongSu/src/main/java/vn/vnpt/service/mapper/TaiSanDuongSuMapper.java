package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DuongSu;
import vn.vnpt.domain.TaiSanDuongSu;
import vn.vnpt.service.dto.DuongSuDTO;
import vn.vnpt.service.dto.TaiSanDuongSuDTO;

/**
 * Mapper for the entity {@link TaiSanDuongSu} and its DTO {@link TaiSanDuongSuDTO}.
 */
@Mapper(componentModel = "spring")
public interface TaiSanDuongSuMapper extends EntityMapper<TaiSanDuongSuDTO, TaiSanDuongSu> {
    @Mapping(target = "duongSu", source = "duongSu", qualifiedByName = "duongSuIdDuongSu")
    TaiSanDuongSuDTO toDto(TaiSanDuongSu s);

    @Named("duongSuIdDuongSu")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idDuongSu", source = "idDuongSu")
    DuongSuDTO toDtoDuongSuIdDuongSu(DuongSu duongSu);
}
