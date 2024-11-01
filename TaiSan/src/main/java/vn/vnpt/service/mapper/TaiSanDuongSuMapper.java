package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.TaiSan;
import vn.vnpt.domain.TaiSanDuongSu;
import vn.vnpt.service.dto.TaiSanDTO;
import vn.vnpt.service.dto.TaiSanDuongSuDTO;

/**
 * Mapper for the entity {@link TaiSanDuongSu} and its DTO {@link TaiSanDuongSuDTO}.
 */
@Mapper(componentModel = "spring")
public interface TaiSanDuongSuMapper extends EntityMapper<TaiSanDuongSuDTO, TaiSanDuongSu> {
    @Mapping(target = "taiSan", source = "taiSan", qualifiedByName = "taiSanIdTaiSan")
    TaiSanDuongSuDTO toDto(TaiSanDuongSu s);

    @Named("taiSanIdTaiSan")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idTaiSan", source = "idTaiSan")
    TaiSanDTO toDtoTaiSanIdTaiSan(TaiSan taiSan);
}
