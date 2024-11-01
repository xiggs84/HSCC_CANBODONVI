package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.ChungThuc;
import vn.vnpt.domain.DanhMucLoaiGiayToChungThuc;
import vn.vnpt.service.dto.ChungThucDTO;
import vn.vnpt.service.dto.DanhMucLoaiGiayToChungThucDTO;

/**
 * Mapper for the entity {@link ChungThuc} and its DTO {@link ChungThucDTO}.
 */
@Mapper(componentModel = "spring")
public interface ChungThucMapper extends EntityMapper<ChungThucDTO, ChungThuc> {
    @Mapping(target = "danhMucLoaiGiayToChungThuc", source = "danhMucLoaiGiayToChungThuc", qualifiedByName = "danhMucLoaiGiayToChungThucId")
    ChungThucDTO toDto(ChungThuc s);

    @Named("danhMucLoaiGiayToChungThucId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DanhMucLoaiGiayToChungThucDTO toDtoDanhMucLoaiGiayToChungThucId(DanhMucLoaiGiayToChungThuc danhMucLoaiGiayToChungThuc);
}
