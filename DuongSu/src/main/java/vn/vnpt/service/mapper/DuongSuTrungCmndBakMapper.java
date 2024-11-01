package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DuongSu;
import vn.vnpt.domain.DuongSuTrungCmndBak;
import vn.vnpt.service.dto.DuongSuDTO;
import vn.vnpt.service.dto.DuongSuTrungCmndBakDTO;

/**
 * Mapper for the entity {@link DuongSuTrungCmndBak} and its DTO {@link DuongSuTrungCmndBakDTO}.
 */
@Mapper(componentModel = "spring")
public interface DuongSuTrungCmndBakMapper extends EntityMapper<DuongSuTrungCmndBakDTO, DuongSuTrungCmndBak> {
    @Mapping(target = "duongSu", source = "duongSu", qualifiedByName = "duongSuIdDuongSu")
    DuongSuTrungCmndBakDTO toDto(DuongSuTrungCmndBak s);

    @Named("duongSuIdDuongSu")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idDuongSu", source = "idDuongSu")
    DuongSuDTO toDtoDuongSuIdDuongSu(DuongSu duongSu);
}
