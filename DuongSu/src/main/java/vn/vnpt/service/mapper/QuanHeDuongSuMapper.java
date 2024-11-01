package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DuongSu;
import vn.vnpt.domain.QuanHeDuongSu;
import vn.vnpt.service.dto.DuongSuDTO;
import vn.vnpt.service.dto.QuanHeDuongSuDTO;

/**
 * Mapper for the entity {@link QuanHeDuongSu} and its DTO {@link QuanHeDuongSuDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuanHeDuongSuMapper extends EntityMapper<QuanHeDuongSuDTO, QuanHeDuongSu> {
    @Mapping(target = "duongSu", source = "duongSu", qualifiedByName = "duongSuIdDuongSu")
    QuanHeDuongSuDTO toDto(QuanHeDuongSu s);

    @Named("duongSuIdDuongSu")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idDuongSu", source = "idDuongSu")
    DuongSuDTO toDtoDuongSuIdDuongSu(DuongSu duongSu);
}
