package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhMucLoaiGiayToChungThuc;
import vn.vnpt.domain.DanhSachChungThuc;
import vn.vnpt.service.dto.DanhMucLoaiGiayToChungThucDTO;
import vn.vnpt.service.dto.DanhSachChungThucDTO;

/**
 * Mapper for the entity {@link DanhSachChungThuc} and its DTO {@link DanhSachChungThucDTO}.
 */
@Mapper(componentModel = "spring")
public interface DanhSachChungThucMapper extends EntityMapper<DanhSachChungThucDTO, DanhSachChungThuc> {
    @Mapping(target = "danhMucLoaiGiayToChungThuc", source = "danhMucLoaiGiayToChungThuc", qualifiedByName = "danhMucLoaiGiayToChungThucId")
    DanhSachChungThucDTO toDto(DanhSachChungThuc s);

    @Named("danhMucLoaiGiayToChungThucId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DanhMucLoaiGiayToChungThucDTO toDtoDanhMucLoaiGiayToChungThucId(DanhMucLoaiGiayToChungThuc danhMucLoaiGiayToChungThuc);
}
