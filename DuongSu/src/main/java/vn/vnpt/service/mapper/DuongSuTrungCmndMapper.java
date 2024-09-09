package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DuongSu;
import vn.vnpt.domain.DuongSuTrungCmnd;
import vn.vnpt.service.dto.DuongSuDTO;
import vn.vnpt.service.dto.DuongSuTrungCmndDTO;

/**
 * Mapper for the entity {@link DuongSuTrungCmnd} and its DTO {@link DuongSuTrungCmndDTO}.
 */
@Mapper(componentModel = "spring")
public interface DuongSuTrungCmndMapper extends EntityMapper<DuongSuTrungCmndDTO, DuongSuTrungCmnd> {
    @Mapping(target = "idDuongSu", source = "idDuongSu", qualifiedByName = "duongSuIdDuongSu")
    DuongSuTrungCmndDTO toDto(DuongSuTrungCmnd s);

    @Named("duongSuIdDuongSu")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idDuongSu", source = "idDuongSu")
    DuongSuDTO toDtoDuongSuIdDuongSu(DuongSu duongSu);
}
