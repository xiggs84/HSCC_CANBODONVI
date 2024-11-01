package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.ChiTietNganChanTaiSan;
import vn.vnpt.domain.TaiSan;
import vn.vnpt.service.dto.ChiTietNganChanTaiSanDTO;
import vn.vnpt.service.dto.TaiSanDTO;

/**
 * Mapper for the entity {@link ChiTietNganChanTaiSan} and its DTO {@link ChiTietNganChanTaiSanDTO}.
 */
@Mapper(componentModel = "spring")
public interface ChiTietNganChanTaiSanMapper extends EntityMapper<ChiTietNganChanTaiSanDTO, ChiTietNganChanTaiSan> {
    @Mapping(target = "taiSan", source = "taiSan", qualifiedByName = "taiSanIdTaiSan")
    ChiTietNganChanTaiSanDTO toDto(ChiTietNganChanTaiSan s);

    @Named("taiSanIdTaiSan")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idTaiSan", source = "idTaiSan")
    TaiSanDTO toDtoTaiSanIdTaiSan(TaiSan taiSan);
}
